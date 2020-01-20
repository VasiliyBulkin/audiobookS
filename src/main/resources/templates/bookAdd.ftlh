<#import "parts/common.ftlh" as c>

<@c.page>
<#list authors as author>
<div class="alert alert-info" role="alert">
    Form fo  ${author.authorname!""}
</div>
<form action="/author/add" method="post" enctype="multipart/form-data">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label"> Input name of book </label>
        <div class="col-sm-10">
            <input type="text" name="name" id="name" class="form-control"  placeholder="more then zero simbols" <#if messageNotOk??>value="${authorname!''}" </#if>>
        </div>
    </div>

    <div class="custom-file">
        <input type="file" class="custom-file-input" id="filecover" name="file">
        <label class="custom-file-label" for="filecover">Choose file</label>
    </div>

    <div class="form-group row">
        <div class="col-sm-2">Checkbox</div>
        <div class="col-sm-10">
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Detective fiction" class="form-check-input" value="Detective fiction"/>
                <label for="Detective fiction" class="form-check-label">
                    Detective fiction
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Crime" class="form-check-input" value="Crime"/>
                <label for="Crime" class="form-check-label">
                    Crime
                </label>
            </div>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Add book</button>
        </div>
    </div>
    <input type="hidden" value="${author.id}" name="authorId">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>

</#list>

</@c.page>








