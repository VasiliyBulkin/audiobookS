<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
    <span><a href="/user">User list</a> </span>
</div>
<div>
        <form method="post" action="main" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Input name of book"/>
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add book name</button>
        </form>
</div>


<div>Book by id</div>
        <form method="get" action="/main">
            <input type="text" name="id" placeholder="Input the integer" value="${id!""}"/>
            <button type="submit">Search</button>
        </form>

<div>List of books</div>
        <form method="get" action="/main">
            <input type="text" name="filter" placeholder="Input the search pattern"  value="${filter!""}"/>
            <button type="submit">Search</button>
        </form>
<#list books as book>

        <div>
            <b>${book.id!""}</b>
            <span>${book.name!""}</span>
            <strong>${book.ownerName!""}</strong>
            <div>
                    <#if book.filename??> <!-- проверка на наличее поля filename, ??приведение к булеву типу -->
                        <img src="/img/${book.filename}">
                    </#if>
            </div>

        </div>
<#else>
No books
 </#list>

</@c.page>