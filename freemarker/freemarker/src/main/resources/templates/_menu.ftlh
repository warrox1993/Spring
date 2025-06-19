<#import "_security.ftlh" as security>

<nav class="nav navbar">
    <div class="nav-wrapper">
        <ul class="left">
            <@security.isAnonymous>
                <li><a href="/login">Login</a></li>
            </@security.isAnonymous>
            <@security.isAuthenticated>
                <li><a href="/products">Products</a></li>
                <li><a href="/logout">Logout</a></li>
            </@security.isAuthenticated>
        </ul>
    </div>
</nav>