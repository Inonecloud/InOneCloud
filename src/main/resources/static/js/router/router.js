import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from 'pages/Main.vue'
import SignUp from 'pages/SignUp.vue'
import About from 'pages/About.vue'


Vue.use(VueRouter);

const routes = [
    { path: '/', component: Main },
    { path: '/signup', component: SignUp },
    { path: '/about', component: About }
];

export default new VueRouter({
    mode: 'history',
    routes
})