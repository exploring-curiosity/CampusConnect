<script setup>
defineProps({
    label: String,
    color: {type: String, default: "blue"}
})
</script>

<template>
    <button class="w-full text-white p2 rounded-md"
    :class="color === 'blue' ? 'bg-blue-600 hover:bg-blue-700' : 'bg-green-600 hover:bg-green-700' ">
        {{ label }}
    </button>
</template>