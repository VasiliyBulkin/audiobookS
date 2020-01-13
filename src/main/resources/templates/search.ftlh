<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
    <p><span><a href="/main">Main page</a></span></p>

</div>
            <table>
                <caption>List of books</caption>
                <tr>
                    <form method="get" action="/search">

                        <th>
                            <label for="filter">Book name</label>
                            <input type="text" name="filter" id="filter" placeholder="Input the search pattern"  value="${filter!''}"/>
                            <label for="orderByBookName">Order by book</label>
                            <input type="checkbox" name="orderByBookName" id="orderByBookName" value="checked" ${orderByBookName!''}/>
                            <button type="submit">Search</button>
                        </th>
                        <th>
                            <label for="filterAuthorName">Book author</label>
                            <input type="text" name="filterAuthorName" id="filterAuthorName" placeholder="Input the author name"  value="${filterAuthorName!''}"/>
                            <label for="orderByAuthorName">Order by author</label>
                            <input type="checkbox" name="orderByAuthorName" id="orderByAuthorName" value="checked" ${orderByAuthorName!''}/>
                            <button type="submit">Search</button>
                        </th>

                        <th>Book owner</th>

                        </form>

                        <th>Book cover</th>
                </tr>
            </table>
<#list books as book>
            <table >
                <tr>
                    <td>${book.name!""}</td>
                    <td>${book.authorName!""}</td>
                    <td>${book.ownerName!""}</td>

                    <td>
                        <#if book.filename??> <!-- проверка на наличее поля filename, ??приведение к булеву типу -->
                        <img  alt="There could be an image" style="width:200px;height:200px;" src="/img/${book.filename} ">
                    </#if>
                    </td>
                </tr>
            </table>

<#else>
No books
 </#list>

</@c.page>