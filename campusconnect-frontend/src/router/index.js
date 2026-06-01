import DashboardView from '@/views/DashboardView.vue'
import EventDetailView from '@/views/EventDetailView.vue'
import EventsView from '@/views/EventsView.vue'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', name: 'home', component: HomeView},
    {path: '/login', name: 'login', component: LoginView},
    {path: '/register', name: 'register', component: RegisterView},
    {path: '/events', name: 'events', component: EventsView},
    {path: '/event/:id', name: 'event-detail', component: EventDetailView},
    {path: '/dashboard', name: 'dashboard', component: DashboardView}
  ],
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register']
  const authRequired = !publicPages.includes(to.path)
  const token = localStorage.getItem("token")
  if (authRequired && !token) {
    return next("/login")
  }

  next()
})

export default router
