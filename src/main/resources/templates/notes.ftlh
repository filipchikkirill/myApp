<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
	<div class="my-3" align="center">
		<h3>Привет, ${user.name}</h3>
	</div>
	<div class="my-3" align="center">
		<form action="/addnote" method="post">
			<input type="text" name="title" placeholder="Название">
			<input type="text" name="note" placeholder="Заметка">
			<input type="hidden" name="_csrf" value="${_csrf.token}" />
			<input class="button" type="submit" value="+">
		</form>
	</div>

	<#list notes as note>
    <div class="mt-3">
        <div>
            <h2>${note.title}</h2>
        </div>
        <div>
            ${note.note}
        </div>
    </div>
	<#else>
    Заметок пока нет
    </#list>

</section>
</@c.page>