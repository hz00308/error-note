<template>
  <div class="empty-state">
    <div class="empty-illustration">
      <SearchX :size="38" stroke-width="1.8" />
    </div>
    <h2>{{ title }}</h2>
    <p>{{ description }}</p>
    <slot />
  </div>
</template>

<script setup>
import { SearchX } from '@lucide/vue'

defineProps({
  title: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
})
</script>
