<!DOCTYPE html>
<html>
<head>
    <title>留言栏</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="sourceId" class="layui-input searchVal" placeholder="接收人" />
                </div>
                <div class="layui-input-inline">
                    <select name="isRead"  id="state">
                        <option value="" >读取状态</option>
                        <option value="0">未读</option>
                        <option value="1" >已读</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" id="btnSearch" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="messageList" class="layui-table"  lay-filter="message">
    </table>

    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加留言
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除留言
            </a>
        </div>
    </script>


    <!--操作 行工具栏-->
    <script id="messageListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/message/message.js"></script>
</body>
</html>