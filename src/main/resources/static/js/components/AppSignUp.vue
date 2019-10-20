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
                        <input class="username" name="username" type="text" placeholder="Username" autofocus required>
                        <input class="email" name="email" type="email" placeholder="Email" required>
                        <input class="password" name="password" type="password" placeholder="Password" required
                               pattern="[0-9a-zA-Z]{6,32}" title="Password should have 6 sybols with digits">
                        <input class="password" name="CNFpassword" type="password" placeholder="Repeat password"
                               required>
                        <select name="lang">
                            <option></option>
                            <option value="en">English</option>
                            <option value="de">Deutsch</option>
                            <option value="ru">Русский</option>
                        </select>
                    </fieldset>
                    <fieldset id="actions">
                        <input type="checkbox" required><label>I agree with license</label>
                        <input class="submit" type="submit" name="Registration" value="Create account">
                    </fieldset>
                </form>
            </article>
        </article>
    </div>
    <!--End Main-->
</template>

<script>
    export default {
        name: "AppSignUp"
    }
</script>

<style scoped>

</style>