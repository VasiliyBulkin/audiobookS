<#import "parts/common.ftlh" as c>
<@c.page>


<#if messageOK??>
    <div class="alert alert-success" role="alert">
        ${messageOK!''}
    </div>
</#if>

<#if messageNotOk??>
    <div class="alert alert-danger" role="alert">
        ${messageNotOk!''}
    </div>
</#if>

<#if messageDenied??>
    <div class="alert alert-danger" role="alert">
        ${messageDenied!''}
    </div>
</#if>
<#if messageSuccess??>
<div class="alert alert-success" role="alert">
    ${messageSuccess!''}
</div>
</#if>


<form  action="/author/add" method="get">
    <div class="form-group row">
        <label for="authorname" class="col-sm-2 col-form-label"> Add new author </label>

        <div class="col-sm-10">
            <input type="text" name="authorname" id="authorname" class="form-control"  placeholder="Input author name"   <#if messageNotOk??>value="${authorname!''}" </#if>>
        </div>

    </div>
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Add name</button>
        </div>
    </div>
</form>

<form method="get" action="/author">
    <div class="form-group row">
        <label for="filterAuthorName" class="col-sm-2 col-form-label">Search author</label>
        <div class="col-sm-10">
            <input type="text" name="filterAuthorName" class="form-control" id="filterAuthorName" placeholder="Input a pattern"  value="${filterAuthorName!''}"/>
        </div>
    </div>
    <fieldset class="form-group">
        <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Radios</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input type="radio" name="orderByAuthorName" class="form-check-input" id="orderByAuthorName" value="checked"  ${orderByAuthorName!''}/>
                    <label for="orderByAuthorName">
                        Order
                    </label>
                </div>
                <div class="form-check">
                    <input type="radio" name="orderByAuthorName" class="form-check-input" id="reOrderByAuthorName" value="unchecked" ${reOrderByAuthorName!''}/>
                    <label for="reOrderByAuthorName">
                        Reorder
                    </label>
                </div>
            </div>
        </div>
    </fieldset>
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </div>
</form>

<div class="form-row">
    <table>
        <thead>
            <tr>
                <th>Author</th>
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
                <#else>
                    <tr>
                        <td>
                            <div class="alert alert-warning" role="alert">
                                No author found for this request
                            </div>
                        </td>
                        <td></td>
                    </tr>
                </#list>
        </tbody>
    </table>
</div>
</@c.page>