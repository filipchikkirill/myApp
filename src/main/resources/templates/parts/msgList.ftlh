
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
            <p><strong>${message.author.name}</strong></p>
        </div>
    </div>
<#else>
No message
</#list>
</div>