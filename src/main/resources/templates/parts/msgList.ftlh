<#include "security.ftlh" />

<div class="card-columns">
<#list messages as message>
    <div class="card my-3">
        <div class="card-header" align="center">
            ${message.title}
        </div>
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top">
        </#if>
        <div class="card-body">
            <i>${message.text}</i>
        </div>
        <div class="card-footer text-muted">
            <p>
                <strong>${message.author.name}</strong>
                <#if message.author.id == s_curUserId>
                <a class="btn btn-primary" href="/myMessages/${message.author.id}?message=${message.id}">
                    Edit
                </a>
                </#if>
            </p>
        </div>
    </div>
<#else>
No message
</#list>
</div>

