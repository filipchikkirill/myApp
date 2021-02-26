<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
<div>
    <h1>Spring Playlist</h1>
    <h2>name: ${playList.name}</h2>
    <h2>Author: ${playList.user.name}</h2>
    <h2>Date: ${playList.dateAdd?string("dd-M-yy hh:mm")}</h2>
</div>
<#if owner??>
<#list audios>
<div class="my-3" align="center">
    <form action="/playList" method="post">

        <select name="audio">
            <#items as audio>
            <option value="${audio.id}">${audio.name}</option>
            </#items>
        </select>
        <input type="hidden" name="listId" value="${playList.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input class="button" type="submit" value="добавить трэк" />

    </form>
</div>
</#list>
</#if>

<#list playList.audio>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Дата добавления</th>
        <th scope="col">Кто добавил</th>
        <th scope="col">Название трэка</th>
        <th scope="col">Автор</th>
        <th scope="col">Трэк</th>
    </tr>
    </thead>
    <tbody>
    <#items as audio>
    <tr>
        <th scope="row">${audio.dateAdd?string("dd-M-yy hh:mm")}</th>
        <td>${audio.user.name}</td>
        <td>${audio.name}</td>
        <td>${audio.author}</td>
        <td>
            <div class="col-sm-4 col-sm-offset-4 embed-responsive embed-responsive-4by3">
                <audio controls class="embed-responsive-item">
                    <source src="/audio/${audio.filename}" />
                </audio>
            </div>
        </td>
    </tr>
    </#items>
</tbody>
</table>
<#else>
Аудио файлов пока нет
</#list>

</section>
</@c.page>