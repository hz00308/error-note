<template>
  <div class="toast-stack" aria-live="polite">
    <TransitionGroup name="toast">
      <button
        v-for="toast in toasts"
        :key="toast.id"
        class="toast"
        :class="`toast--${toast.type}`"
        type="button"
        @click="removeToast(toast.id)"
      >
        <CircleCheck v-if="toast.type === 'success'" :size="19" />
        <CircleAlert v-else :size="19" />
        <span>{{ toast.message }}</span>
        <X :size="16" />
      </button>
    </TransitionGroup>
  </div>
</template>

<script setup>
import { CircleAlert, CircleCheck, X } from '@lucide/vue'
import { useToast } from '@/composables/useToast'

const { toasts, removeToast } = useToast()
</script>
