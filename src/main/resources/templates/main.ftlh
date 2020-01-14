<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />

    <#if showLink??>
        <p><span><a href="/author">Storage management</a></span></p>
    </#if>
    <p><span><a href="/search">Advanced Search</a></span></p>
</div>
<!--<div>
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
</div>-->

<fieldset>
    <legend><strong>Book Search</strong></legend>

        <form method="get" action="/main">
            <p>
                <label for="filter">Book by pattern</label>
                <input type="text" name="filter" id="filter" placeholder="Input the search pattern"  value="${filter!''}"/>

            </p>

            <p>
                <label for="filterAuthorName">Book by author name</label>
                <input type="text" name="filterAuthorName" id="filterAuthorName" placeholder="Input the author name"  value="${filterAuthorName!''}"/>
                <button type="submit">Search</button>
            </p>
        </form>

</fieldset>

        <div>
            <table>
                <caption>List of books</caption>
                <tr>
                    <th>Book name</th>
                    <th>Book owner</th>
                    <th>Book author</th>
                    <th>Book cover</th>
                </tr>
            </table>
<#list books as book>
            <table >
                <tr>
                    <td>${book.name!""}</td>
                    <td>${book.ownerName!""}</td>
                    <td>${book.authorName!""}</td>
                    <td>
                        <#if book.filename??> <!-- проверка на наличее поля filename, ??приведение к булеву типу -->
                        <img  alt="There could be an image" style="width:200px;height:200px;" src="/img/${book.filename} ">
                    </#if>
                    </td>
                </tr>
            </table>
        </div>
<#else>
No books
 </#list>

</@c.page>