<#include "security.ftlh" />
<#import "macro.ftlh" as m />
<nav class="navbar navbar-expand-lg navbar-light bg-light ">
    <a class="navbar-brand" href="/">My App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if s_user??>
            <li class="nav-item">
                <a class="nav-link" href="/messages">Messages</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/myMessages/${s_curUserId}">My Messages</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/playLists">Play Lists</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/audio">Audios</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/notes">Notes</a>
            </li>
            </#if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    другие проекты
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/dirs_and_files">dirs_and_files</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="navbar-text mr-3">${s_name}</div>
        <#if s_name != "unknown">
        <@m.logout />
        </#if>
    </div>
</nav>