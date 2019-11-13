import Vue from 'vue'
import VueResource from 'vue-resource'
import router from 'router/router'
import App from 'pages/App.vue'
import axios from 'axios'

// Vue.use(VueResource)

window.axios = axios


new Vue({
    el: '#app',
    router,
    render: a=>a(App)
});
