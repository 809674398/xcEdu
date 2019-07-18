<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
    Hello ${name}!<br/>
<#--遍历List-->
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>金额</td>
        <td>生日</td>
    </tr>
    <#if stus ??>
    <#list stus as stu>
    <tr>
        <td>${stu_index+1}</td>
        <td>${stu.name}</td>
        <td>${stu.age}</td>
        <td>${stu.mondy}</td>
        <td>${stu.birthday?date}</td>
    </tr>
    </#list>
    </#if>
</table>
<br/>
    <#--取出map的数据-->
    <span>${(stuMap['stu1'].name)!''}</span>
    <span>${(stuMap['stu1'].age)!''}</span>
<br/>
    ${int?c}<br/>
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#assign data=text?eval />
    开户行：${data.bank}  账号：${data.account}
</body>
</html>