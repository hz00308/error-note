<template>
  <RouterLink class="error-card" :to="`/errors/${errorItem.errorId}`">
    <div class="error-card__icon">
      <Bug :size="22" />
    </div>
    <div class="error-card__body">
      <div class="error-card__top">
        <span class="badge badge--green">{{ errorItem.categoryName }}</span>
        <span class="error-card__date">
          <CalendarDays :size="14" />
          {{ formatDate(errorItem.updatedAt || errorItem.createdAt) }}
        </span>
      </div>
      <h2>{{ errorItem.title }}</h2>
      <p>{{ errorItem.contents }}</p>
    </div>
    <span class="error-card__arrow"><ChevronRight :size="20" /></span>
  </RouterLink>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { Bug, CalendarDays, ChevronRight } from '@lucide/vue'
import { formatDate } from '@/utils/date'

defineProps({
  errorItem: {
    type: Object,
    required: true,
  },
})
</script>
