<template>
  <aside class="sidebar">
    <RouterLink class="brand" to="/errors" aria-label="Error Note 홈">
      <span class="brand-mark"><FrogFaceIcon :size="27" :stroke-width="2.3" /></span>
      <span>Error Note</span>
    </RouterLink>

    <nav class="sidebar-nav" aria-label="주 메뉴">
      <RouterLink
        v-for="item in navigation"
        :key="item.to"
        :to="item.to"
        class="nav-item"
      >
        <component :is="item.icon" :size="20" />
        <span>{{ item.label }}</span>
      </RouterLink>
    </nav>

    <div class="sidebar-tip">
      <span class="tip-icon"><Lightbulb :size="18" /></span>
      <p>오늘의 실수는<br />내일의 해결 노트가 돼요.</p>
    </div>
  </aside>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { FolderOpen, Lightbulb, NotebookTabs } from '@lucide/vue'
import FrogFaceIcon from '@/components/icons/FrogFaceIcon.vue'

const navigation = [
  { label: '에러 노트', to: '/errors', icon: NotebookTabs },
  { label: '카테고리', to: '/categories', icon: FolderOpen },
]
</script>
