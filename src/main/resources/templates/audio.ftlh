<#import "parts/common.ftlh" as c/>
<#import "parts/audioTable.ftlh" as at/>

<@c.page>
<section class="container">
<div class="my-3" align="center">
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
<@at.table value=audios />
</section>
</@c.page>