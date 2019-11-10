<template>
    <!-- Main-->
    <div>
        <article id="main" class="container">
            <header class="aboutcloud">
                <h2>Create your InOneCloud account</h2>
                <hr>
            </header>
            <article id="registration">
                <p>You can create your personal account here.</p>
                <form id="regist" method="post" action="?">
                    <fieldset id="inputs">
                        <input class="username" name="username" type="text" placeholder="Username" v-model="username"
                               autofocus required>
                        <input class="email" name="email" type="email" placeholder="Email" v-model="email" required>
                        <input class="password" name="password" type="password" placeholder="Password"
                               v-model="password" required
                               pattern="[0-9a-zA-Z]{6,32}" title="Password should have 6 sybols with digits">
                        <input class="password" name="CNFpassword" type="password" placeholder="Repeat password"
                               v-model="repeatedPassword"
                               required>
                        <select name="lang" v-model="language">
                            <option disabled value="">choose language</option>
                            <option value="en">English</option>
                            <option value="de">Deutsch</option>
                            <option value="ru">Русский</option>
                        </select>
                    </fieldset>
                    <fieldset id="actions">
                        <input type="checkbox" v-model="agreement" required><label>I agree with license</label>
                        <input class="button" type="button" name="Registration" value="Create account" @click="signUp">
                    </fieldset>
                </form>
            </article>
        </article>
    </div>
    <!--End Main-->
</template>

<script>

    import axios from 'axios';

    export default {
        name: "AppSignUp",
        data: function () {
            return {
                username: '',
                email: '',
                password: '',
                repeatedPassword: '',
                language: '',
                agreement: ''

            }
        },
        methods: {
            signUp() {
                if (this.password === this.repeatedPassword && this.agreement) {
                    axios.post('http://localhost:8080/api/user/signUp', {
                        username: this.username,
                        email: this.email,
                        password: this.password,
                        activation: "true"

                    })
                        .then(function (response) {
                            console.log(response)

                        })
                } else {
                    console.log("Do something")
                }
            }
        }
    }
</script>

<style scoped>

</style>