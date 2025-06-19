<#import "../_layout.ftlh" as base>
<#import "../_security.ftlh" as security>
<@base.layout title="Product - list">
    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>

    <@security.ifAnyGranted "ADMIN">
        <div>
            <a href="products/add" class="btn">Ajouter</a>
        </div>
    </@security.ifAnyGranted>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>

                <td>
                    <a class="btn" href="/products/${product.id}">Show</a>
                    <@security.ifAnyGranted "ADMIN">
                        <a class="btn" href="/products/${product.id}">Edit</a>
                        <a class="btn" href="/products/remove/${product.id}">Delete</a>
                    </@security.ifAnyGranted>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@base.layout>