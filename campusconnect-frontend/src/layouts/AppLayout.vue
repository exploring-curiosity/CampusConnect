<script setup>
import { useRouter } from 'vue-router';

const router = useRouter()
function logout() {
    localStorage.removeItem("token")
    router.push("/login")
}
</script>

<template>
    <div>
        <nav>
            <h1> Campus Connect </h1>
            <div>
                <router-link to="/">Home</router-link>
                <router-link to="/events">Events</router-link>
                <router-link to="/dashboard">Dashboard</router-link>
                <button @click="logout">Logout</button>
            </div>
        </nav>
        
        <slot />
    </div>
</template>