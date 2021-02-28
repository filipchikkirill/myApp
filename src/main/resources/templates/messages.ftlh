<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
<div class="my-3" align="center">
    <h3>Здравствуй, ${user.name}</h3>
</div>
<div class="my-3" align="center">
    <form action="/messages" method="post" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="Название">
        <input type="text" name="text" placeholder="Сообщение">
        <div class="custom-file">
            <input type="file" name="file" id="customFile">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input class="button" type="submit" value="добавить сообщение">
    </form>
</div>
<div class="my-3" align="center">
    <form action="/messages" method="get">
        <input type="text" name="filter" placeholder="Фильтр" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input class="button" type="submit" value="OK"/>
    </form>
</div>
<#list messages>
<table class="table">
    <thead>
        <tr>
            <th scope="col">title</th>
            <th scope="col">text</th>
            <th scope="col">author</th>
            <th scope="col">img</th>
        </tr>
    </thead>
    <tbody>
        <#items as message>
        <tr>
            <th scope="row">${message.title}</th>
            <td>${message.text}</td>
            <td>${message.author.name}</td>
            <td>
                <#if message.filename??>
                <img src="/img/${message.filename}" class="img-thumbnail"/>
            </#if>
            </td>
        </tr>
        </#items>
    </tbody>
</table>
<#else>
Сообщений пока нет
</#list>

</section>
</@c.page>