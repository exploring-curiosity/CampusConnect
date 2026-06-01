import LoginView from '@/views/LoginView.vue'
import {mount} from '@vue/test-utils'
import { describe, it, expect } from 'vitest'


describe('LoginView.vue', () => {
    it('render login form', () => {
        const wrapper = mount(LoginView)
        expect(wrapper.find('h2').text()).toBe('Login')
    })

    it('update username with input', async () => {
        const wrapper = mount(LoginView)
        const input = wrapper.find('input[placeholder="Username"]')
        await input.setValue('Sudharshan')
        expect(wrapper.find('input[placeholder="Username"]').element.value).toBe('Sudharshan')
    })
})