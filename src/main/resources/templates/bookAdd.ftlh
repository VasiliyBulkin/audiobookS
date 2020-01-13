<#import "parts/common.ftlh" as c>

<@c.page>
<p><span><a href="/main">Main page</a></span></p>
<p><span><a href="/author">Storage management</a></span></p>
<table>
    <caption>Add a book to this author</caption>
    <thead>
    <tr>
        <th>Author id</th>
        <th> Author name</th>
        <th> Book name</th>
    </tr>
    </thead>
    <tbody>
    <#list authors as author>
    <tr>
        <td>${author.id!""}</td>
        <td>${author.authorname!""}</td>
        <td>
            <!--    <form action="/bookAdd" method="get">
                    <input type="text" name="name" value="some book">
                    <input type="hidden" value="" name="authorid">
                </form>-->

            <fieldset>
                <legend><strong>Add new book</strong></legend>
                <form method="post" action="main" enctype="multipart/form-data">
                    <label for="nameofbook">Input the name of book</label>
                    <p><input type="text" name="name" id="nameofbook" value="no name"/></p>

                    <label for="filecover">Chose the cover</label>
                    <p><input type="file"  id="filecover" name="file"></p>

                    <input type="hidden" value="${author.id}" name="authorId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit">Add book name</button>
                </form>
            </fieldset>


        </td>
    </tr>
    </#list>
    </tbody>
</table>





</@c.page>