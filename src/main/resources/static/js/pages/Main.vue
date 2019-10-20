<template>
    <div>
        <auth/>
        <app-main/>
    </div>
</template>

<script>
    import Auth from 'components/Auth.vue'
    import AppMain from 'components/AppMain.vue'

    export default {
        name: "Main",
        components: {AppMain, Auth}
    }
</script>

<style scoped>

</style>