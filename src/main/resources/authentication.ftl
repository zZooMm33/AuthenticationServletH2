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

<#else>
    <h2>Hello ${name}! Your mail ${mail}</h2>
    <h2>Info ${info}</h2>
    <a href="/logout">Logout</a>
</#if>

</body>
</html>