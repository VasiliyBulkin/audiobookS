<#import "parts/common.ftlh" as c>

<@c.page>

<p><span><a href="/main">Main page</a></span></p>
<p><span><a href="/user">User list</a> </span></p>

<div>
    <fieldset>
        <legend><strong>Add the new author</strong></legend>
        <form  action="/author/add" method="get">
            <label for="authorname">Input the author name</label>
            <input type="text" name="authorname" id="authorname" value="${authorname!''}" >
            <button type="submit">Add name</button>
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
                        <label for="filterAuthorName">Name</label>
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
                </td>
            </tr>
            </#list>
    </tbody>
</table>
</@c.page>