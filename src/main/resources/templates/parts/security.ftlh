<#assign
        known = Session.SPRING_SECURITY_CONTEXT??
        >

<#if known>
    <#assign
            s_user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
            s_name = s_user.getName()
            >
<#else>
    <#assign
            s_name = "unknown"
            >
</#if>