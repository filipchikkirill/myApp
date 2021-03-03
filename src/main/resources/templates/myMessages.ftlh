<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">

<#if isCurrentUser == true>
    <#include "parts/msgEditor.ftlh" />
</#if>
<#include "parts/msgList.ftlh" />

</section>
</@c.page>