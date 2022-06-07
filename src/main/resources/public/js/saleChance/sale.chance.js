layui.use(['table','layer'],function(){//用了一个模块
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 用户列表展示
     */
    var  tableIns = table.render({  //返回表格渲染的唯一标识render
        elem: '#saleChanceList', // 表格绑定的ID #指向ID
        url : ctx + '/sale_chance/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",//绑定的头部工具栏
        id : "saleChanceListTable",
        cols : [[//表头的基本信息，对应数据库信息，通过映射一一对应
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},//field表示数据库字段
            {field: 'userName', title: '用户名',align:"center"},
            {field: 'userPwd', title: '密码',  align:'center'},
            {field: 'className', title: '班级', align:'center'},
            {field: 'userSex', title: '性别', align:'center'},
            {field: 'userPhone', title: '电话',  align:'center'},
            {field: 'userEmail', title: '邮箱', align:'center'},
            {field: 'userHobby', title: '爱好', align:'center'},
            {field: 'isValid', title: '是否有效', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });
    //学生信息管理多条件查询条件  这里以搜索为例
    $("#btnSearch").click(function (){//点击按钮后触发
        tableIns.reload({
            where:{ //设定异步数据接口的额外参数，任意设
                userClassId: $("input[name='userClassId']").val(),//班级查找
                userSex:$('[name="userSex"]').val(),//性别
                userName:$('[name="userName"]').val()//姓名
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });
    /**
     * 监听表格的头部工具栏
     */
    //监听事件
    table.on('toolbar(saleChances)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data);
        switch(obj.event){
            case 'add':
                openAddOrUpdateDialog();//打开添加修改的窗口页面
                break;
            case 'del':
                // 点击删除按钮，将对应选中的记录删除
                deleteSaleChance(checkStatus.data);
                break;
        };
    });
    /**
     * 监听表格的行工具栏
     */
    //监听事件
    table.on('tool(saleChances)', function(obj){
        if(obj.event == "edit"){
            openAddOrUpdateDialog(obj.data.id);
        }else if(obj.event == "del"){
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"学生信息数据管理"}, function (index) {
                // 关闭窗口
                layer.close(index);
                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url: ctx + "/sale_chance/delete",
                    data:{
                        ids:obj.data.id
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


    //批量删除
    function deleteSaleChance(data){
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
            var ids = "ids=";
            for(var i = 0; i < data.length; i++){
                //判断是否是倒数第二个
                if(i < data.length - 1){
                    ids += data[i].id + "&ids=";
                }else{
                    ids += data[i].id;
                }
            }
            $.ajax({
                type:"post",
                url: ctx+"/sale_chance/delete",
                data:ids,
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
        })
    }




    /**
     * 打开添加修改的窗口页面
     */
    function openAddOrUpdateDialog(id){
        var title = "<h2>学生信息管理 - 信息添加</h2>";
        var url = ctx + "/sale_chance/toAddUpdatePage";

        //通过参数id判断目前是修改还是添加操作
        if(id){
            title = "<h2>学生信息管理 - 信息修改</h2>";
            url += "?id="+id;
        }

        //打开修改添加页面
        layer.open({
            type:2,   //ifame
            title:title,
            content: url,   //页面的内容
            area:['500px','620px'], //设置宽高
            maxmin:true //可以伸缩页面大小
        });
    }
});