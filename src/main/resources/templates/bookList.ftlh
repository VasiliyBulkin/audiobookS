<#import "parts/common.ftlh" as c>

<@c.page>

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