<#import "parts/common.ftlh" as c>
<@c.page>

<#if authorId??>
      <p><a href="/author/${authorId!''}">Add another book</a></p>
</#if>
<div>
    <table>
        <tr>
            <th>Book name</th>
            <th>Book owner</th>
            <th>Book author</th>

            <th>Book cover</th>
            <th>Action</th>
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
            <td>
                    <form method="post" action="deleteBook" enctype="multipart/form-data">
                        <input type="hidden" value="${book.id}" name="bookId">
                        <input type="hidden" value="${book.authorName}" name="authorName">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit">Delete book</button>
                    </form>
            </td>
        </tr>
    </table>
</div>
<#else>
No books
</#list>
</@c.page>