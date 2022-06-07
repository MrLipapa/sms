<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" id="hidId" value="${(saleChance.id)!}">
    <input type="hidden" id="uname" value="${(saleChance.userClassId)!}"><#--获取当前班级-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学生姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="userName" id="userName"  value="${(saleChance.userName)!}" placeholder="请输入学生姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="userPwd" value="${(saleChance.userPwd)!}"
                   placeholder="请输入密码">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="userSex" value="${(saleChance.userSex)!}"
                   placeholder="请输入性别">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">所在班级</label>
        <div class="layui-input-inline">
            <select name="userClassId"  id="userClassId" >
                <#if (saleChance.userClassId)??>
                    <option value=${(saleChance.userClassId)!} selected="selected">${(claName)!}</option>
                </#if>
                <option value="">请选择</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">电话号码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="userPhone"
                   lay-verify="required"  value="${(saleChance.userPhone)!}" placeholder="请输入电话号码">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">邮件</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="userEmail"
                   name="userEmail" value="${(saleChance.userEmail)!}" id="phone" placeholder="请输入邮件">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">兴趣爱好</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="userHobby" value="${(saleChance.userHobby)!}"
                   placeholder="请输入兴趣爱好">
        </div>
    </div>





    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateSaleChance">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal"id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/saleChance/add.update.js"></script>
</body>
</html>