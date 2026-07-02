<template>
  <Teleport to="body">
    <Transition name="modal">
      <div v-if="open" class="modal-backdrop" @click.self="$emit('close')">
        <section
          class="modal-card"
          role="dialog"
          aria-modal="true"
          :aria-labelledby="titleId"
        >
          <div class="modal-icon" :class="{ 'modal-icon--danger': danger }">
            <Trash2 v-if="danger" :size="24" />
            <CircleHelp v-else :size="24" />
          </div>
          <h2 :id="titleId">{{ title }}</h2>
          <p>{{ description }}</p>
          <div class="modal-actions">
            <button class="button button--ghost" type="button" @click="$emit('close')">
              취소
            </button>
            <button
              class="button"
              :class="danger ? 'button--danger' : 'button--primary'"
              type="button"
              :disabled="loading"
              @click="$emit('confirm')"
            >
              {{ loading ? '처리 중...' : confirmText }}
            </button>
          </div>
        </section>
      </div>
    </Transition>
  </Teleport>
</template>

<script setup>
import { CircleHelp, Trash2 } from '@lucide/vue'

defineProps({
  open: Boolean,
  title: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
  confirmText: {
    type: String,
    default: '확인',
  },
  danger: Boolean,
  loading: Boolean,
})

defineEmits(['close', 'confirm'])

const titleId = `modal-title-${Math.random().toString(36).slice(2)}`
</script>
