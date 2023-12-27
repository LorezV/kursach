import { createRouter, createWebHistory } from 'vue-router'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      name: 'index',
      path: '/',
      component: async () => await import('./views/Index.vue'),
    },
  ],
})
