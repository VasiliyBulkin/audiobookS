<#import "parts/common.ftlh" as c>

<@c.page>
<p><span><a href="/main">Main page</a></span></p>
<p><span><a href="/author">Storage management</a></span></p>
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
            <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>
                    <#list user.roles as role>${role}<#sep>, </#list>
                </td>
                <td><a href="/user/${user.id}">edit</a></td>
            </tr>
            </#list>
    </tbody>
</table>
</@c.page>