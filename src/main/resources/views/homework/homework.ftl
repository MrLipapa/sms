<!DOCTYPE html>
<html>
<head>
    <title>作业管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="homeworkName" class="layui-input searchVal" placeholder="作业" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="courseName" class="layui-input searchVal" placeholder="课程" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="className" class="layui-input searchVal" placeholder="班级" />
                </div>
                <a class="layui-btn search_btn" id="btnSearch" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索 </a>
            </div>
        </form>
    </blockquote>
    <!-- 数据分页表格 -->
    <table id="homeworkList" class="layui-table" lay-filter="saleChances">
    </table>
    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
                <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                    <i class="layui-icon">&#xe608;</i>
                    添加
                </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>
    <!--操作 行工具栏-->
    <script id="homeworkListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" id="del" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/homework/homework.js">
</script>
</body>
</html>
