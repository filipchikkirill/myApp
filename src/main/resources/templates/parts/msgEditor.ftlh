<div class="my-3" align="center">
    <h3>Здравствуй, ${user.name}</h3>
    <a class="btn btn-primary" data-toggle="collapse"
       href="#collapse" role="button" aria-expanded="false"
       aria-controls="collapse">
        new Message
    </a>
</div>
<div class="collapse my-3 <#if message??>show</#if>"
     align="center" id="collapse" >
    <form method="post" enctype="multipart/form-data">
        <div class="input-group my-3">
            <span class="input-group-text" id="title">Заголовок</span>
            <input type="text" name="title"
                   class="form-control"  aria-describedby="title"
                   placeholder="Введите заголовок"
                   value="<#if message??>${message.title}</#if>"
            />
        </div>
        <div class="input-group my-3">
            <span class="input-group-text" id="text">Текст</span>
            <input type="text" name="text"
                   class="form-control"  aria-describedby="text"
                   placeholder="Введите текст"
                   value="<#if message??>${message.text}</#if>"
            />
        </div>
        <div class="input-group my-3">
            <label class="input-group-text"
                   for="inputFile">Upload</label>
            <input type="file" class="form-control"
                   id="inputFile" name="file"/>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />

        <input class="btn btn-primary" type="submit" value="сохранить сообщение">
    </form>
</div>
