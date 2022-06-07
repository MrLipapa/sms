<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" id="hidId" value="${(message.id)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">接收人id</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="targetId" id="targetId"  value="${(message.targetId)!}" placeholder="请输入接收人id">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">留言内容</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="message"
                   id="message" value="${(message.message)!}" placeholder="请输入留言内容">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级id</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="classId">
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