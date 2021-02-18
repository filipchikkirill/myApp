<#import "parts/common.ftlh" as c>

<@c.page>
<section class="container">
	<div class="mx-auto border my-5 py-3 px-3 form">
		<form action="/login" method="post">
			<div class="form-group">
				<label for="email">Email или имя</label>
				<input class="form-control" id="email" type="text" name="username">
			</div>
			<div class="form-group">
				<label for="password">Пароль</label>
				<input class="form-control" id="password" type="password" name="password">
			</div>
			<input type="hidden" name="_csrf" value="${_csrf.token}" />
			<input class="button" type="submit" value="Вход"/>
		</form>
	</div>
</section>
</@c.page>