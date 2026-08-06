<script setup>
import InputField from '@/components/InputField.vue';
import PrimaryButton from '@/components/PrimaryButton.vue';
import AuthLayout from '@/layouts/AuthLayout.vue';
import { ref } from 'vue'
import { useRouter } from 'vue-router';

const username = ref('');
const password = ref('');
const router = useRouter();
async function login() {  
    try {
        const res = await fetch("/api/auth/login", {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify({
                username: username.value,
                password: password.value
            })
        })

        const data = await res.json()
        if(!res.ok) {
            throw new Error(data.error)
        }
        router.push("/dashboard")
    } catch (err) {
        alert(err)
    }
}

</script>

<template>
    <AuthLayout>
        <h2 class="mb-3">Login</h2>
        <form @submit.prevent="login" class="spacy-y-3 mb-3">
            <InputField v-model="username" placeholder="Username"/>
            <InputField v-model="password" type="password" placeholder="Password"/>
            <PrimaryButton label="Login" type="submit"/>
        </form>
        <p>
            Dont have an Account?
            <router-link class="text-blue-600 hover:underline" to="/register">Register</router-link>
        </p>
    </AuthLayout>
</template>
