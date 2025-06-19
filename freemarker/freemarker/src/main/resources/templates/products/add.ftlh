<#import "../_layout.ftlh" as base>

<@base.layout title="Product - add">
    <form action="/products/add" method="post">
        <div class="input-field">
            <input id="name" type="text" name="name"/>
            <label for="name">Name</label>
        </div>
        <div class="input-field">
            <input id="description" type="text" name="description"/>
            <label for="description">Description</label>
        </div>
        <div class="input-field">
            <input id="price" type="number" step="0.01" name="price"/>
            <label for="price">Price</label>
        </div>
        <button class="btn waves-effect waves-light" type="submit">Ajouter</button>
    </form>
</@base.layout>