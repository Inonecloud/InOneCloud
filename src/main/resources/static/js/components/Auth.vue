<template>
    <!--Cover and Registration-->
    <section id="banner">
        <div id="cover">
            <header>
                <h2 id="hello">Welcome to InOneCloud</h2>
            </header>
            <!--            <form id="auth" method="post" action="">-->
            <form id="auth">
                <fieldset id="inputs">
                    <input class="username" name="username" type="text" placeholder="Username"
                           v-model="username" autofocus required>
                    <input class="password" name="password" type="password" placeholder="Password" v-model="password"
                           required>
                </fieldset>
                <fieldset id="actions">
                    <input class="button" type="button" name="logIN" value="Log in" @click="signIn">
                </fieldset>
            </form>
            <!--            </form>-->
        </div>
    </section>
</template>

<script>
// import axios from 'axios';

    export default {
        name: "Auth",
        data: function () {
            return {
                username: '',
                password: ''
            }
        },
        methods: {
            signIn() {
                if (this.username !== '' && this.password !== '')
                    axios.post('http://localhost:8080/api/auth', {
                        username: this.username,
                        password: this.password
                    }).then(function (response) {
                        console.log(response)
                    })

            }
        }
    }
</script>

<style scoped>

</style>