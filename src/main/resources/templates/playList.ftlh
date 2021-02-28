<#import "parts/common.ftlh" as c />
<#import "parts/audioTable.ftlh" as at />

<@c.page>
<section class="container">
    <div class="my-3" align="center">
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

    <@at.table value=playList.audio />
</section>
</@c.page>