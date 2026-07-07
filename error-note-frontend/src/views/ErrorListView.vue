<template>
  <section class="view">
    <header class="page-heading">
      <div>
        <span class="eyebrow">MY TROUBLESHOOTING LOG</span>
        <h1>에러 노트</h1>
        <p>막혔던 순간을 기록하고, 다음 해결을 더 빠르게 시작해요.</p>
      </div>
      <RouterLink class="button button--primary" to="/errors/new">
        <Plus :size="19" />
        에러 등록
      </RouterLink>
    </header>

    <div class="summary-strip">
      <div class="summary-copy">
        <span class="summary-icon"><Sparkles :size="21" /></span>
        <div>
          <strong>{{ errors.length }}개의 해결 흔적</strong>
          <span>작은 기록이 든든한 개발 자산이 돼요.</span>
        </div>
      </div>
      <div class="summary-bubbles" aria-hidden="true">
        <span></span><span></span><span></span>
      </div>
    </div>

    <div class="filter-card">
      <label class="search-field">
        <Search :size="20" />
        <input v-model.trim="searchQuery" type="search" placeholder="제목이나 내용으로 검색해 보세요" />
      </label>
      <label class="select-field">
        <FolderOpen :size="18" />
        <select v-model="selectedCategory" aria-label="카테고리 필터">
          <option value="">모든 카테고리</option>
          <option
            v-for="category in categories"
            :key="category.categoryId"
            :value="String(category.categoryId)"
          >
            {{ category.categoryName }}
          </option>
        </select>
        <ChevronDown :size="17" />
      </label>
    </div>

    <div v-if="loading" class="error-list" aria-label="목록을 불러오는 중">
      <div v-for="index in 4" :key="index" class="skeleton-card">
        <span></span><div><i></i><i></i><i></i></div>
      </div>
    </div>

    <div v-else-if="loadError" class="state-card">
      <CircleAlert :size="30" />
      <h2>목록을 불러오지 못했어요</h2>
      <p>{{ loadError }}</p>
      <button class="button button--outline" type="button" @click="loadAll">다시 시도</button>
    </div>

    <template v-else-if="pagedErrors.length">
      <div class="list-meta">
        <span>총 <strong>{{ filteredErrors.length }}</strong>개</span>
        <span>{{ currentPage }} / {{ totalPages }} 페이지</span>
      </div>
      <div class="error-list">
        <ErrorCard
          v-for="errorItem in pagedErrors"
          :key="errorItem.errorId"
          :error-item="errorItem"
        />
      </div>
      <PaginationBar v-model="currentPage" :total-pages="totalPages" />
    </template>

    <EmptyState
      v-else
      :title="searchQuery ? '검색 결과가 없어요' : '아직 등록된 에러가 없어요'"
      :description="
        searchQuery
          ? '검색어를 바꾸거나 다른 카테고리를 선택해 보세요.'
          : '첫 번째 에러를 기록하고 해결의 흔적을 남겨 보세요.'
      "
    >
      <RouterLink v-if="!searchQuery" class="button button--primary" to="/errors/new">
        <Plus :size="18" /> 첫 에러 등록하기
      </RouterLink>
    </EmptyState>
  </section>
</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue'
import { RouterLink } from 'vue-router'
import {
  ChevronDown,
  CircleAlert,
  FolderOpen,
  Plus,
  Search,
  Sparkles,
} from '@lucide/vue'
import { getCategories } from '@/api/category'
import { getErrors } from '@/api/error'
import EmptyState from '@/components/common/EmptyState.vue'
import ErrorCard from '@/components/errors/ErrorCard.vue'
import PaginationBar from '@/components/common/PaginationBar.vue'

const PAGE_SIZE = 6

const errors = ref([])
const categories = ref([])
const searchQuery = ref('')
const selectedCategory = ref('')
const currentPage = ref(1)
const loading = ref(true)
const loadError = ref('')

const filteredErrors = computed(() => {
  const query = searchQuery.value.toLocaleLowerCase()
  if (!query) return errors.value

  return errors.value.filter((item) =>
    [item.title, item.contents, item.categoryName]
      .filter(Boolean)
      .some((value) => value.toLocaleLowerCase().includes(query)),
  )
})

const totalPages = computed(() =>
  Math.max(1, Math.ceil(filteredErrors.value.length / PAGE_SIZE)),
)

const pagedErrors = computed(() => {
  const start = (currentPage.value - 1) * PAGE_SIZE
  return filteredErrors.value.slice(start, start + PAGE_SIZE)
})

async function loadErrors() {
  loading.value = true
  loadError.value = ''
  try {
    const data = await getErrors(
      selectedCategory.value ? Number(selectedCategory.value) : undefined,
    )
    errors.value = [...data].sort(
      (a, b) =>
        new Date(b.updatedAt || b.createdAt) - new Date(a.updatedAt || a.createdAt),
    )
  } catch (error) {
    loadError.value = error.userMessage
  } finally {
    loading.value = false
  }
}

async function loadAll() {
  loading.value = true
  loadError.value = ''
  try {
    const [categoryData, errorData] = await Promise.all([
      getCategories(),
      getErrors(selectedCategory.value ? Number(selectedCategory.value) : undefined),
    ])
    categories.value = categoryData
    errors.value = [...errorData].sort(
      (a, b) =>
        new Date(b.updatedAt || b.createdAt) - new Date(a.updatedAt || a.createdAt),
    )
  } catch (error) {
    loadError.value = error.userMessage
  } finally {
    loading.value = false
  }
}

watch(searchQuery, () => {
  currentPage.value = 1
})

watch(selectedCategory, () => {
  currentPage.value = 1
  loadErrors()
})

onMounted(loadAll)
</script>
