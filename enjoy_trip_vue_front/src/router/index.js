import { createRouter, createWebHistory } from 'vue-router'
import {getCookie} from '@/assets/js/cookie.js'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('@/views/Home.vue')
    },
    {
      path: '/map',
      name: 'map',
      beforeEnter: [checkLogin],
      component: () => import('@/views/Map.vue')
    },
    {
      path: '/login',
      name : 'login',
      component: () => import('@/components/user/Login.vue')
    },
    {
      path: '/board',
      name: 'board',
      component: () => import('@/views/Board.vue'),
      redirect: { name: "board-list" },
      beforeEnter: [checkLogin],
      children: [
        {
          path: 'list',
          name: 'board-list',
          component: () => import('@/views/BoardList.vue'),
        },
        {
          path: 'write',
          name: 'board-write',
          component: () => import('@/views/BoardWrite.vue'),
        },
        {
          path: 'modify',
          name: 'board-modify',
          component: () => import('@/views/BoardModify.vue'),
        },
        {
          path: 'detail',
          name: 'board-detail',
          component: () => import('@/views/BoardDetail.vue'),
        }
      ]
    },
    {
      path: '/todo',
      name: 'todo',
      beforeEnter: [checkLogin],
      component: () => import('@/views/Todo.vue')
    }
  ]
})
function checkLogin() {
  if(getCookie('sessionId')==null){
    alert('로그인을 해주세요');
    return {path: '/'}
  }
}

export default router
