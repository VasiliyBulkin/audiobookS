<#import "parts/common.ftlh" as c>

<@c.page>

User editor
    <form action="/user" method="post">
        <div class="form-group row">
            <div class="col-sm-10">
                <input type="text" class="form-control"  name="username" value="${user.username}">
            </div>
        </div>
                    <div class="form-group row">
                        <#list roles as role>
                        <div class="col-sm-10">
                            <div class="form-check">

                                <label  class="form-check-label"  class="col-sm-2 col-form-label">
                                    <input type="checkbox" class="form-check-input" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                                    ${role}
                                </label>
                            </div>
                        </div>
                        </#list>
                    </div>

        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</@c.page>