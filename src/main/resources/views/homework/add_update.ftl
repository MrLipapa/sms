<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--隐藏域中存放id属性值,用于在 add.update.js 页面中区分修改(有id值)还是添加-->
    <input type="hidden" name="id" id="hidId" value="${(homework.id)!}">

    <#--获取当前指派人id 此时这个数据 并不需要前后台交互 所以不需要设置name属性-->
    <input type="hidden" id="courseId" value="${(homework.courseId)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程</label>
        <div class="layui-input-block">
            <select name="courseId" id="courseName">
                <option value="">${(courseName)!}</option>
            </select>
        </div>
    </div>
    <#--获取当前指派人id 此时这个数据 并不需要前后台交互 所以不需要设置name属性-->
    <input type="hidden" id="classId" value="${(homework.courseId)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <select name="classId" id="className">
                <option value="">${(className)!}</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">作业名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="homeworkName"
                   id="homeworkName" value="${(homework.homeworkName)!}" placeholder='请输入作业名称'>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">作业描述</label>
        <div class="layui-input-block">
            <textarea placeholder="作业描述" name="context" class="layui-textarea">
                ${(homework.context)!}
            </textarea>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-lg" id='sure' lay-submit="" lay-filter="addOrUpdateSaleChance">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript"
        src="${ctx}/js/homework/add.update.js">
</script>
</body>
</html>
