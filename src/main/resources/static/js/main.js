import Vue from 'vue'
import VueResource from 'vue-resource'
import router from 'router/router'
import App from 'pages/App.vue'

Vue.use(VueResource)

new Vue({
    el: '#app',
    router,
    render: a=>a(App)
});
