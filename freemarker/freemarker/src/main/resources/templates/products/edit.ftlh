<#import "../_layout.ftlh" as base>

<@base.layout title="Product - Edit">
    <form action="/products/edit/${product.id}" method="post">
        <#include "_productForm.ftlh" />
        <button class="btn waves-effect waves-light" type="submit">Modify</button>
    </form>
</@base.layout>