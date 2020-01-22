<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div class="alert alert-primary" role="alert" class="mb-1">Add new user</div>

${message!""}
<@l.login "/registration" true/>
</@c.page>