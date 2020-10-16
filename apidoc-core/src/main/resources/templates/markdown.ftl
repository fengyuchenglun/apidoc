[TOC]
# 欢迎使用ApiDoc

#  文档说明
${description!''}

# 接口 ${version}
<#-- ----------  BEGIN 循环遍历book  ---------->
<#if books??>
    <#list books as bookKey,bookValue>
    <#-- ----------  BEGIN currentBook  ---------->
        <#if bookValue.id == currentBook>
    <#-- ----------  BEGIN 循环遍历chapter  ---------->
        <#assign chapterIndex=0>
        <#list bookValue.chapters as chapter>
 <#if !chapter.isIgnore()>

<#assign chapterIndex++>
<#--                章节名称-->
## ${chapterIndex}.  ${chapter.name}
<#--                章节描述-->
                <#if chapter.description??>

${chapter.description}
                </#if>
            <#-- ----------  BEGIN 循环遍历section  ---------->
                <#assign sectionIndex=0>
                <#list chapter.sections as section>
                        <#if !section.isIgnore()>
<#assign sectionIndex++/>
<#--                        接口名称-->
### ${chapterIndex}.${sectionIndex} ${section.name}
<#--                        接口描述-->
                        <#if section.description??>

${section.description}
                        </#if>

<#--                        请求-->
**请求**

<#--                        请求示例-->
```HTTP
HTTP/1.1
<#if section.inHeaders??>
    <#list section.inHeaders as inHeaderKey,inHeaderValue>
${inHeaderValue!''}
    </#list>
</#if>

Url:
${section.method} ${section.uri!''}

<#if (section.getQueryParameterString()?length gt 1)>
Query param:
?${section.getQueryParameterString()}
</#if>
                            <#if section.hasRequestBody()>

Request body:
${section.getRequestBodyParameterString()}
                        </#if>
```
<#--                        请求参数table列表-->
                   <#if section.requestRows?? && (section.requestRows?size>0)>

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
                       <#list section.requestRows as rowKey,rowValue>
| ${rowValue.getMarkdownKey()!''}  | ${rowValue.getLabelType()!''} | **${rowValue.parameterType!''}** |${rowValue.required?string('true','false')} | ${rowValue.condition!''} | ${rowValue.def!''} | ${rowValue.getHtmlRemark()!''} |
                       </#list>
                    </#if>
<#--                        响应-->
                        <#if section.hasResponseBody()>
**响应**
```
${section.getResponseString()}
```
                        </#if>
<#--                        响应参数table列表-->
                        <#if section.responseRows?? && (section.responseRows?size>0)>

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
                        <#list section.responseRows as rowKey,rowValue>
| ${rowValue.getMarkdownKey()!''}  | ${rowValue.getLabelType()!''} | ${rowValue.def!''} | ${rowValue.mock!''} | ${rowValue.getHtmlRemark()!''} |
                        </#list>
                        </#if>
                        </#if>
                </#list>
            <#------------  END 循环遍历section  ---------->
             </#if>
        </#list>
        <#-- ----------  END currentBook  ---------->
        </#if>
    <#------------  END 循环遍历chapter  ---------->
    </#list>
<#------------  END 循环遍历book  ---------->
</#if>




# 附录
<#if appendices??>
    <#list appendices as appendfix>
## ${appendfix.name}
        <#if appendfix.cells?? && (appendfix.cells?size>0)>
| 编码    | 值 | 说明  |
| :------- | :----- |:-----
            <#list appendfix.cells as cell>
                 <#if  (cell.values?size=1)>
| ${cell.values[0]} | ${cell.values[0]}  | ${cell.values[0]} |
                <#elseif (cell.values?size=2)>
| ${cell.values[0]} | ${cell.values[0]}  | ${cell.values[1]} |
                <#else>
| ${cell.values[0]} | ${cell.values[1]} | ${cell.values[2]} |
                </#if>
            </#list>
        </#if>
        </#list >
</#if>
