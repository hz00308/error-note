<template>
  <svg
    :width="size"
    :height="size"
    viewBox="0 0 24 24"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    aria-hidden="true"
  >
    <path
      d="M5.8 9.3C6.9 6.9 9 5.6 12 5.6s5.1 1.3 6.2 3.7c1.4.8 2.3 2.3 2.3 4.1 0 3.3-3.1 5.9-8.5 5.9s-8.5-2.6-8.5-5.9c0-1.8.9-3.3 2.3-4.1Z"
      :stroke-width="strokeWidth"
      stroke="currentColor"
      stroke-linejoin="round"
    />
    <path
      d="M7.5 8.2a2.7 2.7 0 1 1 4.1-2.3M16.5 8.2a2.7 2.7 0 1 0-4.1-2.3"
      :stroke-width="strokeWidth"
      stroke="currentColor"
      stroke-linecap="round"
      stroke-linejoin="round"
    />
    <path
      d="M9.2 12.7h.1M14.7 12.7h.1"
      :stroke-width="strokeWidth + 1.4"
      stroke="currentColor"
      stroke-linecap="round"
    />
    <path
      d="M9.2 15.3c.8.7 1.7 1 2.8 1s2-.3 2.8-1"
      :stroke-width="strokeWidth"
      stroke="currentColor"
      stroke-linecap="round"
    />
  </svg>
</template>

<script setup>
defineProps({
  size: {
    type: [Number, String],
    default: 24,
  },
  strokeWidth: {
    type: Number,
    default: 2,
  },
})
</script>
