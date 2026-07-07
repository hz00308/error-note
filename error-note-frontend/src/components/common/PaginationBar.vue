<template>
  <nav v-if="totalPages > 1" class="pagination" aria-label="페이지 이동">
    <button
      type="button"
      aria-label="이전 페이지"
      :disabled="modelValue === 1"
      @click="$emit('update:modelValue', modelValue - 1)"
    >
      <ChevronLeft :size="18" />
    </button>
    <button
      v-for="page in pages"
      :key="page"
      type="button"
      :class="{ active: page === modelValue }"
      :aria-current="page === modelValue ? 'page' : undefined"
      @click="$emit('update:modelValue', page)"
    >
      {{ page }}
    </button>
    <button
      type="button"
      aria-label="다음 페이지"
      :disabled="modelValue === totalPages"
      @click="$emit('update:modelValue', modelValue + 1)"
    >
      <ChevronRight :size="18" />
    </button>
  </nav>
</template>

<script setup>
import { computed } from 'vue'
import { ChevronLeft, ChevronRight } from '@lucide/vue'

const props = defineProps({
  modelValue: {
    type: Number,
    required: true,
  },
  totalPages: {
    type: Number,
    required: true,
  },
})

defineEmits(['update:modelValue'])

const pages = computed(() => {
  if (props.totalPages <= 5) {
    return Array.from({ length: props.totalPages }, (_, index) => index + 1)
  }

  const start = Math.min(Math.max(props.modelValue - 2, 1), props.totalPages - 4)
  return Array.from({ length: 5 }, (_, index) => start + index)
})
</script>
