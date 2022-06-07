<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" id="hidId" value="${(message.id)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">接收人</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="userName" id="userName"  value="${(message.targetId)!}" placeholder="请输入接收人姓名">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">留言内容</label>
        <div class="layui-input-block">
                    <textarea placeholder="请输入留言内容" name="message" class="layui-textarea">
                  	    ${(message.message)!}
                    </textarea>
        </div>
    </div>

    <#--获取当前指派人id 此时这个数据 并不需要前后台交互 所以不需要设置name属性-->
    <input type="hidden" id="classId" value="${(homework.courseId)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <select name="classId" id="className">
                <option value="">${(claName)!}</option>
            </select>
        </div>
    </div>

            <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateMessage">
                确认
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx!}/js/message/addmessage.js"></script>

</body>
</html>