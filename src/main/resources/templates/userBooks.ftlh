<#import "parts/common.ftlh" as c>
<@c.page>
<div class="form-row">
    <form method="get" action="/main" class="form-inline">
        <label class="sr-only" for="filter">Book</label>
        <input type="text" name="filter" id="filter" class="form-control mb-2 mr-sm-2" placeholder="Book name"  value="${filter!''}"/>

        <label class="sr-only" for="filterAuthorName">Author</label>
        <input type="text" name="filterAuthorName" id="filterAuthorName" class="form-control mb-2  mr-sm-2" placeholder="Author name"  value="${filterAuthorName!''}"/>

        <button type="submit" class="btn btn-primary ml-2 mb-2">Search</button>
    </form>

        <table >
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Owner</th>
                    <th>Cover</th>
                </tr>
            </thead>
            <tbody>
            <#list books as book>
                <tr>
                    <td>${book.name!""}</td>
                    <td>${book.authorName!""}</td>
                    <td>${book.ownerName!""}</td>
                    <td>
                        <#if book.filename??> <!-- проверка на наличее поля filename, ??приведение к булеву типу -->
                        <img  alt="There could be an image" style="width:200px;height:200px;" src="/img/${book.filename!''} ">
                    </#if>
                    </td>
                </tr>
            <#else>
                    <tr>
                        <td>
                            <div class="alert alert-warning" role="alert">
                                No books found for this request
                            </div>
                        </td>
                        <td></td>
                        <td></td>
                        <td>
                        </td>
                    </tr>
            </#list>
            </tbody>
        </table>
</div>
</@c.page>