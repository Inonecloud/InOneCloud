<template>
    <!--Cover and Registration-->
    <section id="banner">
        <div id="cover">
            <header>
                <h2 id="hello">Welcome to InOneCloud</h2>
            </header>
            <form id="auth" method="post" action="/login/find_user">
                <fieldset id="inputs">
                    <input class="username" name="username" type="text" v-model="username" placeholder="Username"
                           autofocus required>
                    <input class="password" name="password" type="password" placeholder="Password" required>
                </fieldset>
                <fieldset id="actions">
                    <input class="submit" type="submit" name="logIN" value="Log in">
                </fieldset>
            </form>
        </div>
    </section>
</template>

<script>
    export default {
        name: "Auth",
        data: function() {
            return {
            username: ''
        }
    },
    methods:{
        output()
        {
            console.log("hello!")
        }
    }
    }
</script>

<style scoped>

</style>