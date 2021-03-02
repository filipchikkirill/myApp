<#import "parts/common.ftlh" as c >
<#include "parts/foo.ftlh" >

<@c.page>
<section class="container">
<div align="center">
    <h4 class="text-left">${baseDir.path}</h4>
    <table class="table table-striped table-bordered">
        <caption>${baseDir.path} </caption>
        <thead>
            <tr>
                <th scope="col">Файл</th>
                <th scope="col">Размер</th>
            </tr>
        </thead>
        <tbody>
        <#list baseDir.dirs as dir>
            <tr>
                <th scope="col">${dir.name}</th>
                <th scope="col">&ltDIR&gt</th>
            </tr>
        </#list>
        <#list baseDir.files as file>
            <tr>
                <th scope="col">${file.name}</th>
                <th scope="col">${fs(file.size)}</th>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</section>
</@c.page>