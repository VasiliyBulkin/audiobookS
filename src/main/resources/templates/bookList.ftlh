<#import "parts/common.ftlh" as c>
<@c.page>

<#if author.id??>
      <p><a href="/author/${author.id!''}">Add another  book by ${author.authorname!''}</a></p>
</#if>
<div>
    <table class="table table-hover table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th> Author</th>
            <th> Genre</th>
            <th> Owner</th>
            <th> Cover</th>
        </tr>
        </thead>
        <tbody>
            <#list books as book>
                        <tr>
                            <td>${book.name!""}</td>
                            <td>${book.authorName!""}</td>
                            <th>
                                ${book.genresName!""}
                            </th>
                            <td>${book.ownerName!""}</td>
                            <td>
                                <#if book.filename??> <!-- проверка на наличее поля filename, ??приведение к булеву типу -->
                                <img class="img-fluid mx-auto d-block" style="width:150px;height:150px;" src="/img/${book.filename}">
                            </#if>
                            </td>
                        </tr>
                    <#else>
                    No books
                </#list>
        </tbody>
    </table>
</div>
</@c.page>