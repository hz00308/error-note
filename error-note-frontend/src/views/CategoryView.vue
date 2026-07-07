<template>
  <section class="view">
    <header class="page-heading">
      <div>
        <span class="eyebrow">KEEP THINGS TIDY</span>
        <h1>카테고리</h1>
        <p>에러 기록을 찾기 쉽도록 나만의 분류함을 만들어 보세요.</p>
      </div>
    </header>

    <div class="category-layout">
      <aside class="category-create-card">
        <span class="category-hero-icon"><FolderPlus :size="28" /></span>
        <h2>새 카테고리</h2>
        <p>어떤 종류의 에러를 모아 둘까요?</p>
        <form @submit.prevent="addCategory">
          <label for="new-category">카테고리 이름</label>
          <input
            id="new-category"
            v-model.trim="newCategoryName"
            class="input"
            type="text"
            maxlength="30"
            placeholder="예: 데이터베이스"
          />
          <button
            class="button button--primary button--full"
            type="submit"
            :disabled="creating || !newCategoryName"
          >
            <Plus :size="18" />
            {{ creating ? '추가 중...' : '카테고리 추가' }}
          </button>
        </form>
      </aside>

      <div class="category-list-card">
        <header class="section-heading">
          <div>
            <span class="section-icon section-icon--purple"><Folders :size="20" /></span>
            <div>
              <h2>카테고리 목록</h2>
              <p>이름을 바꾸거나 사용하지 않는 분류를 정리할 수 있어요.</p>
            </div>
          </div>
          <span class="count-badge">{{ categories.length }}</span>
        </header>

        <div v-if="loading" class="category-loading">
          <LoaderCircle class="spin" :size="25" /> 카테고리를 불러오고 있어요...
        </div>

        <div v-else-if="categories.length" class="category-list">
          <article
            v-for="(category, index) in categories"
            :key="category.categoryId"
            class="category-item"
          >
            <span class="category-color" :class="`category-color--${(index % 4) + 1}`">
              <Folder :size="20" />
            </span>

            <form
              v-if="editingId === category.categoryId"
              class="category-edit"
              @submit.prevent="saveCategory(category.categoryId)"
            >
              <input
                v-model.trim="editingName"
                class="input"
                type="text"
                maxlength="30"
                aria-label="카테고리 이름 수정"
              />
              <button class="mini-button mini-button--save" type="submit" aria-label="저장">
                <Check :size="17" />
              </button>
              <button class="mini-button" type="button" aria-label="취소" @click="cancelEdit">
                <X :size="17" />
              </button>
            </form>

            <template v-else>
              <strong>{{ category.categoryName }}</strong>
              <div class="category-actions">
                <button type="button" aria-label="수정" @click="startEdit(category)">
                  <Pencil :size="17" />
                </button>
                <button
                  class="danger"
                  type="button"
                  aria-label="삭제"
                  @click="openDeleteModal(category)"
                >
                  <Trash2 :size="17" />
                </button>
              </div>
            </template>
          </article>
        </div>

        <EmptyState
          v-else
          title="아직 카테고리가 없어요"
          description="왼쪽 입력창에서 첫 분류를 만들어 보세요."
        />
      </div>
    </div>

    <BaseModal
      :open="Boolean(deleteTarget)"
      title="카테고리를 삭제할까요?"
      :description="`'${deleteTarget?.categoryName || ''}' 카테고리를 삭제합니다. 연결된 에러가 있다면 삭제할 수 없어요.`"
      confirm-text="삭제"
      danger
      :loading="deleting"
      @close="deleteTarget = null"
      @confirm="confirmDelete"
    />
  </section>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import {
  Check,
  Folder,
  FolderPlus,
  Folders,
  LoaderCircle,
  Pencil,
  Plus,
  Trash2,
  X,
} from '@lucide/vue'
import {
  createCategory,
  deleteCategory,
  getCategories,
  updateCategory,
} from '@/api/category'
import BaseModal from '@/components/common/BaseModal.vue'
import EmptyState from '@/components/common/EmptyState.vue'
import { useToast } from '@/composables/useToast'

const { showToast } = useToast()
const categories = ref([])
const loading = ref(true)
const creating = ref(false)
const deleting = ref(false)
const newCategoryName = ref('')
const editingId = ref(null)
const editingName = ref('')
const deleteTarget = ref(null)

async function loadCategories() {
  loading.value = true
  try {
    categories.value = await getCategories()
  } catch (error) {
    showToast(error.userMessage, 'error')
  } finally {
    loading.value = false
  }
}

async function addCategory() {
  if (!newCategoryName.value) return
  creating.value = true
  try {
    await createCategory({ categoryName: newCategoryName.value })
    newCategoryName.value = ''
    await loadCategories()
    showToast('새 카테고리를 추가했어요.')
  } catch (error) {
    showToast(error.userMessage, 'error')
  } finally {
    creating.value = false
  }
}

function startEdit(category) {
  editingId.value = category.categoryId
  editingName.value = category.categoryName
}

function cancelEdit() {
  editingId.value = null
  editingName.value = ''
}

async function saveCategory(categoryId) {
  if (!editingName.value) return
  try {
    const updated = await updateCategory(categoryId, {
      categoryName: editingName.value,
    })
    categories.value = categories.value.map((category) =>
      category.categoryId === categoryId ? updated : category,
    )
    cancelEdit()
    showToast('카테고리 이름을 바꿨어요.')
  } catch (error) {
    showToast(error.userMessage, 'error')
  }
}

function openDeleteModal(category) {
  deleteTarget.value = category
}

async function confirmDelete() {
  if (!deleteTarget.value) return
  deleting.value = true
  try {
    await deleteCategory(deleteTarget.value.categoryId)
    categories.value = categories.value.filter(
      (category) => category.categoryId !== deleteTarget.value.categoryId,
    )
    showToast('카테고리를 삭제했어요.')
    deleteTarget.value = null
  } catch (error) {
    showToast('연결된 에러가 있거나 삭제할 수 없는 카테고리예요.', 'error')
  } finally {
    deleting.value = false
  }
}

onMounted(loadCategories)
</script>
