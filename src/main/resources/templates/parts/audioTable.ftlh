<#macro table value>
<#list value>
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
</#macro>