<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#--设置课程ID的隐藏域-->
            <input type="hidden" name="id" value="${(course.id)!}">
           <#-- &lt;#&ndash;设置营销人员的ID&ndash;&gt;
            <input type="hidden" name="courseName" value="${(course.courseName)!}">-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">教师id</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="tearcherId"  lay-verify="required"  value="${(course.teacherId)!}" placeholder="请输入授课教室id">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">课程名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"  lay-verify="required" name="courseName" id="courseName" value="${(course.courseName)!}" placeholder="请输入课程名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">授课教室id</label>
                <div class="layui-input-block">
<#--                    <input type="text" class="layui-input" name="classId"  lay-verify="required"  value="${(course.classId)!}" placeholder="请输入授课教室id">-->
                    <div class="layui-input-inline">
                                         <select name="classId"  id="classId" >
                                             <#--<#if module.grade==0>selected="selected"</#if>-->
                                             <#if (course.classId)??>
                                                 <option value=${course.classId} selected="selected">${course.classId}</option>
                                             </#if>
                                             <option value="">请选择</option>
                                             <option value="1">1</option>
                                             <option value="2">2</option>
                                         </select>
                      				</div>
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">开课时间</label>
                <div class="layui-input-block">
                    <input type="date" class="layui-input" name="beginTime"  lay-verify="date"  value="${((course.beginTime)?string("yyyy-MM-dd"))!}" placeholder="请输入开课时间">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">结课时间</label>
                <div class="layui-input-block">
                    <input type="date" class="layui-input" name="endTime"  lay-verify="date"  value="${((course.endTime)?string("yyyy-MM-dd"))!}" placeholder="请输入结课时间">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">上课时间</label>
                <div class="layui-input-block">
                    <input type="date" class="layui-input" name="time"  lay-verify="date"  value="${((course.time)?string("yyyy-MM-dd"))!}" placeholder="请输入上课时间">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateUser">
                       确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消
                    </button>
                </div>
            </div>
        </form>
    <script type="text/javascript" src="${ctx}/js/course/add.update.js"></script>
    </body>
</html>