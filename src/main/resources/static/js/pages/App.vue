<template>
    <div>
        <app-header/>


       <auth/>
        <app-main/>
        <app-footer/>
    </div>
</template>

<script>
    import AppHeader from 'components/AppHeader.vue'
    import Auth from 'components/Auth.vue'
    import AppMain from 'components/AppMain.vue'
    import AppFooter from 'components/AppFooter.vue'

    export default {
        components: {
            AppHeader,
            Auth,
            AppMain,
            AppFooter
        },
        data: {
            message: 'Andrew'
        }
    }
</script>

<style scoped>

</style>