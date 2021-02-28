<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
<div class="my-3" align="center">
    <h3>Здравствуй, ${user.name}</h3>
<a class="btn btn-primary" data-toggle="collapse" href="#collapse" role="button" aria-expanded="false" aria-controls="collapse">
    new Message
</a>
</div>
<div class="collapse my-3" align="center" id="collapse" >
    <form action="/messages" method="post" enctype="multipart/form-data">
        <div class="input-group my-3">
            <span class="input-group-text" id="title">Заголовок</span>
            <input type="text" name="title" class="form-control"  aria-describedby="title" />
        </div>
        <div class="input-group my-3">
            <span class="input-group-text" id="text">Текст</span>
            <input type="text" name="text" class="form-control"  aria-describedby="text" />
        </div>
        <div class="input-group my-3">
            <label class="input-group-text" for="inputFile">Upload</label>
            <input type="file" class="form-control" id="inputFile" name="file"/>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input class="btn btn-primary" type="submit" value="добавить сообщение">
    </form>
</div>
<div class="my-3" align="center">
    <form action="/messages" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="text" name="filter" placeholder="Фильтр" />

        <input class="button" type="submit" value="OK"/>
    </form>
</div>
<#include "parts/msgList.ftlh" />
</section>
</@c.page>