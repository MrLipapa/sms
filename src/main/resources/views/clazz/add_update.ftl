<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--隐藏域中存放id属性值,用于在 add.update.js 页面中区分修改(有id值)还是添加-->
    <input type="hidden" name="id" id="hidId" value="${(clazz.id)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="className"
                   id="className" value="${(clazz.className)!}" placeholder='请输入班级名称'>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-lg" id='sure' lay-submit="" lay-filter="addOrUpdateClazz">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript"
        src="${ctx}/js/clazz/add.update.js">
</script>
</body>
</html>
