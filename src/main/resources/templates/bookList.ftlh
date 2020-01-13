<#import "parts/common.ftlh" as c>

<@c.page>
<p><span><a href="/main">Main page</a></span></p>
<p><span><a href="/author">Storage management</a></span></p>

<#if authorId??>
      <p><a href="/author/${authorId!''}">Add another book</a></p>
</#if>


<div>
    <table>
        <caption>List of books in storage</caption>
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
                <img style="width:200px;height:200px;" src="/img/${book.filename}">
            </#if>
            </td>
        </tr>
    </table>
</div>
<#else>
No books
</#list>
</@c.page>