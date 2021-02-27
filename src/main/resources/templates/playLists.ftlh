<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
<div>
    <h1>Spring audios</h1>
    <h2>PlayLists</h2>
</div>

<div class="my-3" align="center">
    <form action="/playLists" method="post">
        <input type="text" name="name" placeholder="Название" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input class="button" type="submit" value="добавить playlist" />
    </form>
</div>
<div>
    <#list playLists>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Дата добавления</th>
                <th scope="col">Кто добавил</th>
                <th scope="col">Название</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
        <#items as playList>
            <tr>
                <th scope="row">${playList.dateAdd?string("dd-M-yy hh:mm")}</th>
                <td>${playList.user.name}</td>
                <td>${playList.name}</td>
                <td><a class="button" href="/playList?id=${playList.id}">открыть</a></td>
            </tr>
        </#items>
        </tbody>
    </table>
    <#else>
        Playlist'ов пока нет
    </#list>
</div>
</section>
</@c.page>