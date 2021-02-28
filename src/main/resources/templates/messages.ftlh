<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
<#include "parts/msgEditor.ftlh" />

<div class="my-3" align="center">
    <form action="/messages" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="text" name="filter" placeholder="Фильтр пльзователей" />

        <input class="button" type="submit" value="OK"/>
    </form>
</div>

<#include "parts/msgList.ftlh" />

</section>
</@c.page>