<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
<div>
    <h1>Spring audios</h1>
    <h2>audio</h2>
</div>

<div class="my-3" align="center">
    <form action="/audio" method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Название" />
        <input type="text" name="author" placeholder="Автор" />
        <div class="custom-file">
            <input type="file" name="file" id="customFile" />
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input class="button" type="submit" value="добавить трэк" />
    </form>
</div>
<#list audios>
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