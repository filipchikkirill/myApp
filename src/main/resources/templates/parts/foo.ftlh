<#function summ arr>
    <#local sum=0>
    <#list arr as item>
        <#local sum += item>
    </#list>
    <#return sum>
</#function>


<#function fs v>
    <#if v < 1024 >
        <#return v?string["0.## b"]>
    </#if>
    <#if v < (1024*1024)>
        <#return (v / 1024)?string["0.## Kb"]>
    </#if>
    <#return (v / (1024*1024))?string["0.## Mb"]>
</#function>
