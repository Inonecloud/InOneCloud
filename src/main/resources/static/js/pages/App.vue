<template>
    <div>
        <app-header/>
        <app-footer/>

        <router-view></router-view>
    </div>
</template>

<script>
    import AppHeader from 'components/AppHeader.vue'
    import AppFooter from 'components/AppFooter.vue'

    export default {
        components: {
            AppHeader,
            AppFooter
        },
        data:function () {
            return {
                message: 'Andrew'
            }
        }
    }
</script>

<style scoped>

</style>