<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
    <p><span><a href="/user">User list</a> </span></p>
    <p><span><a href="/author">Author list</a> </span></p>
</div>
<div>
    <fieldset>
        <legend><strong>Add new book</strong></legend>
        <form method="post" action="main" enctype="multipart/form-data">
            <label for="nameofbook">Input the name of book</label>
            <p><input type="text" name="name" id="nameofbook" value="no name"/></p>

            <label for="filecover">Chose the cover</label>
            <p><input type="file"  id="filecover" name="file"></p>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add book name</button>
        </form>
    </fieldset>
</div>

<fieldset>
    <legend><strong>Book Search</strong></legend>
        <form method="get" action="/main">
            <label for="bookid">Book by id</label>
             <p><input type="text" name="id" id="bookid" placeholder="Input the integer number" value="${id!""}"/></p>
            <button type="submit">Search</button>
        </form>

        <form method="get" action="/main">
            <label for="filter">Book by pattern</label>
            <p><input type="text" name="filter" id="filter" placeholder="Input the search pattern"  value="${filter!""}"/></p>
            <button type="submit">Search</button>
        </form>
</fieldset>

        <div>
            <table>
                <caption>List of books</caption>
                <tr>
                    <th>Book id</th>
                    <th>Book name</th>
                    <th>Book owner</th>
                    <th>Book author</th>
                    <th>Book cover</th>
                </tr>
            </table>
<#list books as book>
            <table >
                <tr>
                    <td>${book.id!""}</td>
                    <td>${book.name!""}</td>
                    <td>${book.ownerName!""}</td>
                    <td>${book.authorName!""}</td>
                    <td>
                        <#if book.filename??> <!-- проверка на наличее поля filename, ??приведение к булеву типу -->
                        <img src="/img/${book.filename}">
                    </#if>
                    </td>
                </tr>
            </table>
        </div>
<#else>
No books
 </#list>

</@c.page>