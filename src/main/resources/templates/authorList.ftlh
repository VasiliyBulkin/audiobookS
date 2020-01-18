<#import "parts/common.ftlh" as c>

<@c.page>
<div>
    <fieldset>
        <legend><strong>Add the new author</strong></legend>
        <form  action="/author/add" method="get">
            <input type="text" name="authorname" id="authorname" placeholder="Input the author name">
            <button type="submit">Add name</button>
            <label for="authorname"> ${message!''}</label>
        </form>

    </fieldset>
</div>

<table>
    <caption>List of Author</caption>

    <thead>
        <tr>
            <th>
                <form method="get" action="/author">
                    <p>
                        <label for="filterAuthorName">Search author</label>
                        <input type="text" name="filterAuthorName" id="filterAuthorName" placeholder="Input the author name"  value="${filterAuthorName!''}"/>
                        <label for="orderByAuthorName">Order</label>
                        <input type="radio" name="orderByAuthorName" id="orderByAuthorName" value="checked"  ${orderByAuthorName!''}/>
                        <label for="reOrderByAuthorName">Reorder</label>
                        <input type="radio" name="orderByAuthorName" id="reOrderByAuthorName" value="unchecked" ${reOrderByAuthorName!''}/>
                        <button type="submit">Search</button>
                    </p>
                </form>
            </th>
            <th>Select an action</th>
        </tr>
    </thead>
    <tbody>
            <#list authors as author>
            <tr>
                <td>${author.authorname}</td>
                <td>
                    <p><a href="/book/${author.authorname}">View books</a></p>
                    <p><a href="/author/${author.id}">Add book</a></p>
                    <p><a href="/author/delete/${author.id}">Delete author</a></p>
                </td>
            </tr>
            </#list>
    </tbody>
</table>
</@c.page>