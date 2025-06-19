<#macro layout title="Document" js="">
    <!doctype html>
    <html lang="en" theme="light">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/@materializecss/materialize@2.2.2/dist/css/materialize.min.css">
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/@materializecss/materialize@2.2.2/dist/js/materialize.min.js"></script>

    </head>
    <body>
    <#include "_menu.ftlh" />
    <#if message??>
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
    </#if>
    <div class="container">
        <#nested />
    </div>
    ${js}
    </body>
    </html>
</#macro>
