<#import "parts/common.ftlh" as c >
<#include "parts/foo.ftlh" >

<@c.page>
<section class="container">
<div>
    <h1>Директории и файлы</h1>
</div>
<form action="/dirs_and_files" method="post">
    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup"> Новая директория: </span>
        <input type="text" class="form-control"
               aria-describedby="inputGroup"
               name="path"
        />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-outline-secondary" type="submit" id="btnAddInList">Добавить в список</button>
    </div>
</form>

<div>
    <h3>Список директорий и файлов</h3>
</div>

<#list baseDirs>
<div align="center">
    <table class="table table-striped table-bordered">
        <caption>List of items</caption>
        <thead>
            <tr>
                <th scope="col">Дата</th>
                <th scope="col">Базовая директория</th>
                <th scope="col">Директорий</th>
                <th scope="col">Файлов</th>
                <th scope="col">Суммарный размер файлов</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <#items as baseDir>
        <tr>
                <td>${baseDir.dateAdd?string("dd.MM.yy hh:mm")}</td>
                <td>${baseDir.path}</td>
                <td>${baseDir.dirs?size}</td>
                <td>${baseDir.files?size}</td>
                <td>${fs(summ(baseDir.files?map(file->file.size)))}</td>
                <td><a class="btn btn-dark" href="/dirs_and_files">открыть</a></td>
            </tr>
        </#items>
        </tbody>
    </table>
</div>
<#else>
Список пуст
</#list>

</section>
</@c.page>