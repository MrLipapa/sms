layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        form=layui.form,
        table = layui.table;


    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#courseList',
        url : ctx + '/course/list',
        cellMinWidth : 95,
        page:true,
        height : "full-125",
        limits : [10,20,30,40,50],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "courseListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'courseName', title: '课程名', minWidth:50, align:"center"},
            {field: 'teacherId', title: '教师id', minWidth:100, align:'center'},
            {field: 'classId', title: '授课班级id', minWidth:100, align:'center'},
            {field: 'beginTime', title: '开课时间', align:'center'},
            {field: 'endTime', title: '结课时间', align:'center',minWidth:150},
            {field: 'time', title: '上课时间', align:'center',minWidth:150},
            {field: 'isVaild', title: '是否有效', align:'center',minWidth:150},
            {field: 'creatDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'uodateDate', title: '修改时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#courseListBar',fixed:"right",align:"center"}

        ]]
    });

    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        table.reload('courseListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                id: $("input[name='id']").val(), // 课程id
                courseName: $("input[name='courseName']").val(), // 课程名称
                classID: $("input[name='classID']").val() // 老师id
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        }); // 只重载数据
    });

  /*  /!**
     * 头部工具栏 监听事件
     *!/
    table.on('toolbar(course)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                saveCourse();
                break;

        };
    });*/
    /**
     * 监听表格的头部工具栏
     */
//监听事件
    table.on('toolbar(course)', function(obj){
        console.log(obj);
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data);
        switch(obj.event){
            case 'add':
                saveCourse();//打开添加留言的窗口页面
                break;
            case 'del':
                deleteBatch(checkStatus.data);
                break;
        };
    });

//批量删除
    function deleteBatch(data){
        //判断是否选中数据
        if(data.length == 0){
            layer.msg("请至少选中一条数据");
            return;
        }
        //向用户确认删除行为
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            //关闭弹出框
            layer.close(index);

            //拼接后台需要的id数组  ids=1&ids=2
            var str = "ids=";
            for(var i = 0; i < data.length; i++){
                //判断是否是倒数第二个
                if(i < data.length - 1){
                    str += data[i].id + "&ids=";
                }else{
                    str += data[i].id;
                }
            }
            console.log(str);

            $.ajax({
                type:"post",
                url: ctx+"/course/delete",
                data:str,
                dataType:"json",
                success:function(data){
                    if(data.code == 200){
                        //刷新数据表格
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg,{icon:5})
                    }
                }
            });


        })

    }
    /**
     * 打开添加/修改课程的对话框
     */
    function saveCourse(courseId) {
        var title = "<h2>课程添加</h2>";
        var url = ctx + "/course/saveCourse";
        // 判断营销机会是否为空
        if (courseId) {
            // 更新操作
            title = "<h2>课程更新</h2>";
            //请求地址传递营销机会的ID
            url = url + "?courseId=" + courseId;
        }
        layui.layer.open({
            title:title,
            type:2, //iframe
            content: url,
            area:["500px","620px"],
            maxmin:true
        });
    }


    /*/!**
     * 监听submit事件
     * 实现课程的添加与更新
     *!/
    form.on("submit(saveCourse)", function (data) {
// 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });
        // 请求的地址
        var url = ctx + "/course/saveCourse";
        // 发送ajax请求
        $.post(url, data.field, function (result) {
            // 操作成功
            if (result.code == 200) {
                // 提示成功
                layer.msg("操作成功！");
                // 关闭加载层
                layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新父页面，重新渲染表格数据
                parent.location.reload();
            } else {
                layer.msg(result.msg);
            }
        });
        return false; // 阻止表单提交
    });*/

    /**
     * 行监听事件
     */
    table.on("tool(course)", function(data){
        var courseId=data.data.id;
        var layEvent = data.event;
        console.log(data);
        // 监听编辑事件
        if(layEvent == "edit") {
            saveCourse(courseId);
        }else if(layEvent== "del"){
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"营销机会数据管理"},
                function (index) {
                    // 关闭窗口
                    layer.close(index);
                    // 发送ajax请求，删除记录
                    $.ajax({
                        type:"post",
                        url: ctx + "/course/delete",
                        data:{
                            ids:data.data.id
                        },
                        dataType:"json",
                        success:function (result) {
                            if (result.code == 200) {
                                // 加载表格
                                tableIns.reload();
                            } else {
                                layer.msg(result.msg, {icon: 5});
                            }
                        }
                    });
                });

        }
    });



});