<#import "parts/common.ftlh" as c>

<@c.page>

<div>
    <fieldset>
        <legend><strong>Add the new author</strong></legend>
        <form  action="/author" method="post">

            <label for="authorname">Input the author name</label>
            <p><input type="text" name="authorname" id="authorname" value="no name" ></p>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add name</button>
        </form>
    </fieldset>
</div>

<table>
    <caption>List of Author</caption>

    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Books name</th>
        </tr>
    </thead>
    <tbody>
            <#list authors as author>
            <tr>
                <td>${author.id}</td>
                <td>${author.authorname}</td>
                <td><a href="/author/${author.id}">Add book</a></td>
            </tr>
            </#list>
    </tbody>
</table>
</@c.page>