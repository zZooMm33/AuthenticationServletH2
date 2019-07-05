<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>
    <#if name != "">
    Hi${name}!
    <#else>
    Hi nikto!
    </#if>
    </title>
    <style>

    </style>
</head>
<body>
<#if error != "">
<h1>${error}</h1>
<#elseif authButton != "">
        YARIK NAPISHI KNOPKI
    <a href="${webAddress}login?login=zZooMm&pass=Hasdasd1">Login</a>
<form action="${webAddress}login" method="post">
    <p><input name="login"> <input type="pass" name="pass"></p>
    <p><input type="submit" value="Login"></p>
</form>
<#else>
    <h2>Hello ${name}! Your mail ${mail}</h2>
    <h2>Info ${info}</h2>
<form action="${webAddress}logout" method="post">
    <p><input type="submit" value="Logout"></p>
</form>
</#if>

</body>
</html>