<template>
  <section class="view view--narrow">
    <RouterLink class="back-link" to="/errors">
      <ArrowLeft :size="18" />
      목록으로 돌아가기
    </RouterLink>

    <div v-if="loading" class="detail-card detail-loading">
      <LoaderCircle class="spin" :size="30" />
      기록을 펼치고 있어요...
    </div>

    <div v-else-if="!errorItem" class="state-card">
      <CircleAlert :size="30" />
      <h2>에러 기록을 찾을 수 없어요</h2>
      <RouterLink class="button button--outline" to="/errors">목록으로 이동</RouterLink>
    </div>

    <template v-else>
      <article class="detail-card">
        <header class="detail-header">
          <div class="detail-title">
            <span class="badge badge--green">{{ errorItem.categoryName }}</span>
            <h1>{{ errorItem.title }}</h1>
            <div class="detail-date">
              <CalendarDays :size="15" />
              <span>작성 {{ formatDate(errorItem.createdAt) }}</span>
              <template v-if="errorItem.updatedAt">
                <span class="dot"></span>
                <span>수정 {{ formatDate(errorItem.updatedAt) }}</span>
              </template>
            </div>
          </div>
          <div class="detail-actions">
            <RouterLink class="icon-button" :to="`/errors/${errorItem.errorId}/edit`" aria-label="수정">
              <Pencil :size="19" />
            </RouterLink>
            <button class="icon-button icon-button--danger" type="button" aria-label="삭제" @click="deleteModalOpen = true">
              <Trash2 :size="19" />
            </button>
          </div>
        </header>
        <div class="detail-content">{{ errorItem.contents }}</div>
      </article>

      <section class="memo-card">
        <header class="section-heading">
          <div>
            <span class="section-icon"><MessageSquareText :size="20" /></span>
            <div>
              <h2>메모</h2>
              <p>추가로 발견한 팁이나 후속 내용을 남겨 보세요.</p>
            </div>
          </div>
          <span class="count-badge">{{ memos.length }}</span>
        </header>

        <form class="memo-form" @submit.prevent="submitMemo">
          <textarea
            v-model.trim="memoContents"
            rows="3"
            placeholder="짧은 메모를 남겨 보세요."
            aria-label="메모 내용"
          ></textarea>
          <button class="button button--secondary" type="submit" :disabled="memoSubmitting || !memoContents">
            <Send :size="17" />
            {{ memoSubmitting ? '등록 중...' : '메모 등록' }}
          </button>
        </form>

        <div v-if="memos.length" class="memo-list">
          <article v-for="memo in memos" :key="memo.memoId" class="memo-item">
            <div class="memo-pin"><StickyNote :size="18" /></div>
            <div>
              <p>{{ memo.contents }}</p>
              <span>{{ formatDate(memo.createdAt) }}</span>
            </div>
            <button type="button" aria-label="메모 삭제" @click="removeMemo(memo.memoId)">
              <X :size="17" />
            </button>
          </article>
        </div>
        <div v-else class="memo-empty">아직 메모가 없어요. 첫 팁을 남겨 보세요!</div>
      </section>
    </template>

    <BaseModal
      :open="deleteModalOpen"
      title="이 에러 기록을 삭제할까요?"
      description="삭제한 기록은 다시 되돌릴 수 없어요."
      confirm-text="삭제"
      danger
      :loading="deleting"
      @close="deleteModalOpen = false"
      @confirm="removeError"
    />
  </section>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { RouterLink, useRoute, useRouter } from 'vue-router'
import {
  ArrowLeft,
  CalendarDays,
  CircleAlert,
  LoaderCircle,
  MessageSquareText,
  Pencil,
  Send,
  StickyNote,
  Trash2,
  X,
} from '@lucide/vue'
import { deleteError, getError } from '@/api/error'
import { createMemo, deleteMemo, getMemos } from '@/api/memo'
import BaseModal from '@/components/common/BaseModal.vue'
import { useToast } from '@/composables/useToast'
import { formatDate } from '@/utils/date'

const route = useRoute()
const router = useRouter()
const { showToast } = useToast()
const errorId = Number(route.params.id)

const errorItem = ref(null)
const memos = ref([])
const loading = ref(true)
const memoContents = ref('')
const memoSubmitting = ref(false)
const deleteModalOpen = ref(false)
const deleting = ref(false)

async function loadDetail() {
  loading.value = true
  try {
    const [errorData, memoData] = await Promise.all([getError(errorId), getMemos(errorId)])
    errorItem.value = errorData
    memos.value = [...memoData].sort(
      (a, b) => new Date(b.createdAt) - new Date(a.createdAt),
    )
  } catch (error) {
    showToast(error.userMessage, 'error')
  } finally {
    loading.value = false
  }
}

async function submitMemo() {
  if (!memoContents.value) return
  memoSubmitting.value = true
  try {
    await createMemo({ errorId, contents: memoContents.value })
    memoContents.value = ''
    memos.value = await getMemos(errorId)
    memos.value.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt))
    showToast('메모를 추가했어요.')
  } catch (error) {
    showToast(error.userMessage, 'error')
  } finally {
    memoSubmitting.value = false
  }
}

async function removeMemo(memoId) {
  try {
    await deleteMemo(memoId)
    memos.value = memos.value.filter((memo) => memo.memoId !== memoId)
    showToast('메모를 삭제했어요.')
  } catch (error) {
    showToast(error.userMessage, 'error')
  }
}

async function removeError() {
  deleting.value = true
  try {
    await deleteError(errorId)
    showToast('에러 기록을 삭제했어요.')
    router.push('/errors')
  } catch (error) {
    showToast(error.userMessage, 'error')
  } finally {
    deleting.value = false
    deleteModalOpen.value = false
  }
}

onMounted(loadDetail)
</script>
