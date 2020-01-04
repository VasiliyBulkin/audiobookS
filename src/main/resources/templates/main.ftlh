<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
</div>
<div>
        <form method="post" action="main">
            <input type="text" name="name" placeholder="Input name of book"/>
            <button type="submit">Add book name</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
</div>

<div>List of authors</div>

        <form method="post" action="filter">
            <input type="text" name="filter"/>
            <button type="submit">Search</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
<#list books as book>

        <div>
        <b>${book.id}</b>
        <span>${book.name}</span>
            <strong>${book.ownerName!""}</strong>
        </div>
<#else>
No books
 </#list>

</@c.page>