<script setup>
import AppLayout from '@/layouts/AppLayout.vue';
import { onMounted, ref } from 'vue';

const events = ref([])
const error = ref(null)

async function loadEvents() {
    try {
        const res = await fetch("/api/events/all", {
            method: "GET"
        })
        if (!res.ok) {
            throw new Error("Failed to load events")
        }
        const data = await res.json()
        events.value = data.map(e => ({...e}))
    } catch (err) {
        error.value = err.message
    }
} 

onMounted(() => {
    loadEvents()
})

</script>
<template>
    <AppLayout>
        <h2 class="text-2xl font-bold mb-4">Events</h2>
        <div v-if="error" class="text-red-600 font-medium">
            Error: {{ error }}
        </div>
        <div v-else-if="events.length === 0" class="text-gray-500">
            No Events Available
        </div>
        <div class="grid gap-4">
            <div v-for="event in events" :key="event.id"
                class="p-4 border rounded-lg shadow-sm bg-white">
                <h3 class="text-lg font-semibold">{{ event.title }}</h3>
                <p class="text-gray-600">{{ event.description }}</p>
                <p class="text-sm text-gray-500">{{ event.date }} -- {{ event.location }}</p>
                <span class="text-xs px-2 py-1 rounded bg-blue-100 text-blue-800 mt-2 inline-block"> 
                    {{ event.isPublic? "Public": "Private" }}</span>
            </div>
        </div>
    </AppLayout>
</template>
