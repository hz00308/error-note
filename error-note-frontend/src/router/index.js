import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/errors',
    },
    {
      path: '/errors',
      name: 'errors',
      component: () => import('@/views/ErrorListView.vue'),
      meta: { title: '에러 노트' },
    },
    {
      path: '/errors/new',
      name: 'error-create',
      component: () => import('@/views/ErrorFormView.vue'),
      meta: { title: '에러 등록' },
    },
    {
      path: '/errors/:id',
      name: 'error-detail',
      component: () => import('@/views/ErrorDetailView.vue'),
      meta: { title: '에러 상세' },
    },
    {
      path: '/errors/:id/edit',
      name: 'error-edit',
      component: () => import('@/views/ErrorFormView.vue'),
      meta: { title: '에러 수정' },
    },
    {
      path: '/categories',
      name: 'categories',
      component: () => import('@/views/CategoryView.vue'),
      meta: { title: '카테고리' },
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: () => import('@/views/NotFoundView.vue'),
      meta: { title: '페이지를 찾을 수 없어요' },
    },
  ],
  scrollBehavior() {
    return { top: 0, behavior: 'smooth' }
  },
})

router.afterEach((to) => {
  document.title = `${to.meta.title || 'Error Note'} · Error Note`
})

export default router
