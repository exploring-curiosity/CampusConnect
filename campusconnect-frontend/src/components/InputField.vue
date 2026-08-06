<script setup>
defineProps({
    modelValue: String,
    type: {type: String, default: "text"},
    placeholder: String
})
defineEmits(["update:modelValue"])
</script>

<template>
    <input 
        :type="type"
        :placeholder="placeholder"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        class = "w-full p-2 border rounded-md"
    />
</template>