<template>
  <section class="not-found">
    <div class="not-found-art">
      <span>4</span>
      <div><Bug :size="54" /></div>
      <span>4</span>
    </div>
    <span class="eyebrow">A TINY DETOUR</span>
    <h1>길을 살짝 잃었어요</h1>
    <p>찾으시는 페이지가 이동했거나 존재하지 않아요.</p>
    <RouterLink class="button button--primary" to="/errors">
      <Home :size="18" />
      에러 노트로 돌아가기
    </RouterLink>
  </section>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { Bug, Home } from '@lucide/vue'
</script>
