<template>
  <section class="view view--narrow">
    <RouterLink class="back-link" :to="backTo">
      <ArrowLeft :size="18" />
      {{ isEdit ? '상세로 돌아가기' : '목록으로 돌아가기' }}
    </RouterLink>

    <header class="page-heading page-heading--compact">
      <div>
        <span class="eyebrow">{{ isEdit ? 'POLISH YOUR NOTE' : 'CAPTURE THE MOMENT' }}</span>
        <h1>{{ isEdit ? '에러 기록 다듬기' : '새 에러 기록하기' }}</h1>
        <p>
          {{
            isEdit
              ? '더 정확한 해결 기록이 되도록 내용을 정리해 보세요.'
              : '문제가 생긴 맥락을 자세히 적을수록 미래의 내가 고마워해요.'
          }}
        </p>
      </div>
    </header>

    <div v-if="initialLoading" class="form-card form-card--loading">
      <LoaderCircle class="spin" :size="28" />
      기록을 준비하고 있어요...
    </div>

    <form v-else class="form-card" @submit.prevent="submitForm">
      <div class="form-intro">
        <span><PencilLine :size="21" /></span>
        <div>
          <strong>에러 정보</strong>
          <p>핵심 내용을 알아보기 쉽게 남겨 주세요.</p>
        </div>
      </div>

      <div class="field">
        <label for="category">카테고리 <em>*</em></label>
        <div class="select-field select-field--full">
          <FolderOpen :size="18" />
          <select id="category" v-model="form.categoryId">
            <option value="" disabled>카테고리를 선택하세요</option>
            <option
              v-for="category in categories"
              :key="category.categoryId"
              :value="String(category.categoryId)"
            >
              {{ category.categoryName }}
            </option>
          </select>
          <ChevronDown :size="17" />
        </div>
        <span v-if="errors.categoryId" class="field-error">{{ errors.categoryId }}</span>
      </div>

      <div class="field">
        <label for="title">제목 <em>*</em></label>
        <input
          id="title"
          v-model.trim="form.title"
          class="input"
          type="text"
          maxlength="100"
          placeholder="예: 로그인 요청에서 401 응답 발생"
        />
        <div class="field-bottom">
          <span class="field-error">{{ errors.title }}</span>
          <span>{{ form.title.length }}/100</span>
        </div>
      </div>

      <div class="field">
        <label for="contents">에러 내용과 해결 과정 <em>*</em></label>
        <textarea
          id="contents"
          v-model.trim="form.contents"
          class="textarea"
          rows="12"
          placeholder="발생 상황, 원인, 시도한 방법과 최종 해결 과정을 자유롭게 기록하세요."
        ></textarea>
        <span v-if="errors.contents" class="field-error">{{ errors.contents }}</span>
      </div>

      <div class="form-actions">
        <RouterLink class="button button--ghost" :to="backTo">취소</RouterLink>
        <button class="button button--primary" type="submit" :disabled="submitting">
          <LoaderCircle v-if="submitting" class="spin" :size="18" />
          <Save v-else :size="18" />
          {{ submitting ? '저장 중...' : isEdit ? '수정 완료' : '기록 저장' }}
        </button>
      </div>
    </form>
  </section>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import { RouterLink, useRoute, useRouter } from 'vue-router'
import {
  ArrowLeft,
  ChevronDown,
  FolderOpen,
  LoaderCircle,
  PencilLine,
  Save,
} from '@lucide/vue'
import { getCategories } from '@/api/category'
import { createError, getError, updateError } from '@/api/error'
import { useToast } from '@/composables/useToast'

const route = useRoute()
const router = useRouter()
const { showToast } = useToast()

const isEdit = computed(() => route.name === 'error-edit')
const errorId = computed(() => Number(route.params.id))
const backTo = computed(() => (isEdit.value ? `/errors/${errorId.value}` : '/errors'))

const categories = ref([])
const initialLoading = ref(true)
const submitting = ref(false)
const form = reactive({
  categoryId: '',
  title: '',
  contents: '',
})
const errors = reactive({
  categoryId: '',
  title: '',
  contents: '',
})

function validate() {
  errors.categoryId = form.categoryId ? '' : '카테고리를 선택해 주세요.'
  errors.title = form.title ? '' : '제목을 입력해 주세요.'
  errors.contents = form.contents ? '' : '에러 내용과 해결 과정을 입력해 주세요.'
  return !errors.categoryId && !errors.title && !errors.contents
}

async function initialize() {
  initialLoading.value = true
  try {
    const requests = [getCategories()]
    if (isEdit.value) requests.push(getError(errorId.value))

    const [categoryData, errorData] = await Promise.all(requests)
    categories.value = categoryData

    if (isEdit.value && errorData) {
      form.categoryId = String(errorData.categoryId)
      form.title = errorData.title
      form.contents = errorData.contents
    }
  } catch (error) {
    showToast(error.userMessage, 'error')
    router.replace('/errors')
  } finally {
    initialLoading.value = false
  }
}

async function submitForm() {
  if (!validate()) return

  submitting.value = true
  const payload = {
    categoryId: Number(form.categoryId),
    title: form.title,
    contents: form.contents,
  }

  try {
    const saved = isEdit.value
      ? await updateError(errorId.value, payload)
      : await createError(payload)

    showToast(isEdit.value ? '에러 기록을 수정했어요.' : '새 에러를 기록했어요.')
    router.push(`/errors/${saved.errorId}`)
  } catch (error) {
    showToast(error.userMessage, 'error')
  } finally {
    submitting.value = false
  }
}

onMounted(initialize)
</script>
