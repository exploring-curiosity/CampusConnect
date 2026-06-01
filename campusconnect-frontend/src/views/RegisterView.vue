<script setup>
import InputField from '@/components/InputField.vue';
import PrimaryButton from '@/components/PrimaryButton.vue';
import Department from '@/enums/Department';
import Role from '@/enums/Role';
import AuthLayout from '@/layouts/AuthLayout.vue';
import User from '@/models/User';
import router from '@/router';
import { reactive, ref } from 'vue';

const user = reactive({...User})
const confirmPassword = ref('')

async function register() {
    
    if(user.password !== confirmPassword.value) {
        alert("Passwords Do not match")
        return
    } 

    try {
        const res = await fetch("/api/auth/register", {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify(user)
        })

        const data = await res.text()
        if (!res.ok) {
            throw new Error(data)
        }
        console.log("Registered: ", data)
        router.push("/login")
    } catch (err) {
        alert(err)
    }
}

</script>
<template>
    <AuthLayout>
        <h2 class="mb-3">Register</h2>
        <form @submit.prevent="register" class="space-y-3 mb-3">
            <InputField v-model="user.email" placeholder="Email"/>
            <InputField v-model="user.username" placeholder="Username"/>
            <InputField v-model="user.batch" placeholder="Batch"/>
            <select v-model="user.department" placeholder="DEPARTMENT" class="w-full p-2 border rounded-md">
                <option disabled value="Select Department">Select Department</option>
                <option v-for="dept in Department" :key="dept">{{ dept }}</option>
            </select>
            <select v-model="user.role" placeholder="ROLE" class="w-full p-2 border rounded-md">
                <option disabled value="Select Role">Select Role</option>
                <option v-for="role in Role" :key="role">{{ role }}</option>
            </select>
            <InputField v-model="user.password" type="password" placeholder="Password"/>
            <InputField v-model="confirmPassword" type="password" placeholder="Confirm Password"/>
            <PrimaryButton type="submit" color="red" label="Register"/>
        </form>
        <p>
            Have an Account?
            <router-link to="/login">Login</router-link>
        </p>
    </AuthLayout>    
</template>
