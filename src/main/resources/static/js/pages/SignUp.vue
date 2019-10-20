<template>
    <div>

        <app-sign-up/>


    </div>
</template>

<script>
    import AppHeader from 'components/AppHeader.vue'
    import AppSignUp from 'components/AppSignUp.vue'
    import AppFooter from 'components/AppFooter.vue'

    export default {
        name: "SignUp",
        components: {
            AppSignUp,
            AppHeader,
            AppFooter
        },
    }
</script>

<style scoped>

</style>