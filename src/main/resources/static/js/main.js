import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'

Vue.use(VueResource)

new Vue({
    el: '#app',
    render: a=>a(App)
});



// var app = new Vue({
//     el: '#cover',
//     components: {
//
//     },
//     data: {
//         message: 'Привет, Username!'
//     }
// });
//
// Vue.component('top', {
//     template: 'header id="logo">\n' +
//         '    <h1>InOneCloud</h1>\n' +
//         '    <nav>\n' +
//         '        <ul class="topmenu">\n' +
//         '            <li><a href="/">Home</a></li>\n' +
//         '            <li><a href="/about">About</a></li>\n' +
//         '            <li><a href="/registration">Sign Up</a></li>\n' +
//         '        </ul>\n' +
//         '    </nav>' +
//         '</header>'
// });
//
// Vue.component('foot',{
//     template: '<footer id="footer">\n' +
//         '    <ul class="copyright">\n' +
//         '        <li>Andrew Yelmanov</li>\n' +
//         '        <li>&copy; 2015</li>\n' +
//         '    </ul>\n' +
//         '    <nav class="buttonmenu">\n' +
//         '        <ul class="repeatemenu">\n' +
//         '            <li><a href="/">Home</a></li>\n' +
//         '            <li><a href="/about">About</a></li>\n' +
//         '            <li><a href="/signup">Sign Up</a></li>\n' +
//         '        </ul>\n' +
//         '        <ul class="storages">\n' +
//         '            <li><a href="https://drive.google.com">Google Drive</a></li>\n' +
//         '            <li><a href="https://disk.yandex.ru">Yandex Disk</a></li>\n' +
//         '        </ul>\n' +
//         '    </nav>\n' +
//         '    <div class="tu-ilmenau"></div>\n' +
//         '</footer>'
// });
