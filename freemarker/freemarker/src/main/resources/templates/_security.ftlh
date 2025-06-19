<#macro isAnonymous>
    <#assign anonymous = true>
    <#if SPRING_SECURITY_CONTEXT??>
        <#assign anonymous = false>
    </#if>
    <#if anonymous>
        <#nested>
    </#if>
</#macro>

<#macro isAuthenticated>
    <#assign authenticated = false>
    <#if SPRING_SECURITY_CONTEXT??>
        <#assign authentication = SPRING_SECURITY_CONTEXT.authentication
        isUserAuthenticated = authentication.isAuthenticated()>
        <#if isUserAuthenticated>
            <#assign authenticated = true>
        </#if>
    </#if>
    <#if authenticated>
        <#nested>
    </#if>
</#macro>

<#macro hasRole role>
    <#assign authorized = false>
    <#if SPRING_SECURITY_CONTEXT?? && role??>
        <#list SPRING_SECURITY_CONTEXT.authentication.authorities as authority>
            <#if authority == role>
                <#assign authorized = true>
            </#if>
        </#list>
    </#if>
    <#if authorized>
        <#nested>
    </#if>
</#macro>

<#macro ifAnyGranted roles...>
    <#assign authorized = false>
    <#if SPRING_SECURITY_CONTEXT?? && roles??>
        <#list SPRING_SECURITY_CONTEXT.authentication.authorities as authority>
            <#list roles as role>
                <#if authority == role>
                    <#assign authorized = true>
                </#if>
            </#list>
        </#list>
    </#if>
    <#if authorized>
        <#nested>
    </#if>
</#macro>


<#macro ifNotGranted roles...>
    <#assign authorized = false>
    <#if SPRING_SECURITY_CONTEXT?? && roles??>
        <#assign authorized = true>
        <#list SPRING_SECURITY_CONTEXT.authentication.authorities as authority>
            <#list roles as role>
                <#if authority == role>
                    <#assign authorized = false>
                </#if>
            </#list>
        </#list>
    </#if>
    <#if authorized>
        <#nested>
    </#if>
</#macro>