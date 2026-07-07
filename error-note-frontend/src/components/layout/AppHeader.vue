<template>
  <header class="topbar">
    <RouterLink class="mobile-brand" to="/errors">
      <span class="brand-mark brand-mark--small"><FrogFaceIcon :size="23" :stroke-width="2.3" /></span>
      <span>Error Note</span>
    </RouterLink>

    <nav class="mobile-nav" aria-label="모바일 주 메뉴">
      <RouterLink to="/errors" aria-label="에러 노트">
        <NotebookTabs :size="20" />
      </RouterLink>
      <RouterLink to="/categories" aria-label="카테고리">
        <FolderOpen :size="20" />
      </RouterLink>
    </nav>

    <div class="topbar-greeting">
      <span class="status-dot"></span>
      <span>차근차근 해결 중</span>
    </div>
  </header>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { FolderOpen, NotebookTabs } from '@lucide/vue'
import FrogFaceIcon from '@/components/icons/FrogFaceIcon.vue'
</script>
