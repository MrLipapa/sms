layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 班级列表展示
     */
    var tableIns = table.render({  //返回表格渲染的唯一标识
        elem: '#clazzList', // 表格绑定的ID
        url : ctx + '/clazz/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "clazzListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'className', title: '班级名称',align:"center"},
            {field: 'isValid', title: '班级状态',align:"center"},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
            {title: '操作', templet:'#clazzListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


    //这里以搜索为例
    $("#btnSearch").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                className:$('[name="className"]').val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });


    //触发事件
    //监听头部工具栏
    table.on('toolbar(clazz)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(obj);
        switch(obj.event){
            case 'add':
                openAddOrUpdateDialog();//打开添加或修改页面
                break;
            case 'del':
                detelebatch(checkStatus.data);//打开批量删除用户页面
                break;
        };
    });

    //打开批量删除用户的页面
    function detelebatch(data) {
        if (data.length==0){
            layer.msg("请选择要删除的班级!");
            return;
        }
        //向用户确认删除行为
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            //关闭弹出框
            layer.close(index);
            //拼接后台需要的数组 ids=1&ids=2&...
            var str = 'ids='
            for (var i=0;i<data.length;i++){
                if(i < data.length - 1){
                    str += data[i].id + '&ids=';
                }else {
                    str += data[i].id;
                }
            }
            console.log(str);

            //向后台发送请求 --- Ajax
            $.ajax({
                type:'post',
                url:ctx+'/clazz/deleteBatch',
                data:str,
                dataType:'json',
                success:function (data) {
                    if(data.code == 200){
                        //刷新数据表格
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg,{icon:5})
                    }
                }
            });
        });
    }

    //触发事件
    //监听行工具栏
    table.on('tool(clazz)', function(obj){
        console.log(obj);
        switch(obj.event){
            case 'edit':
                openAddOrUpdateDialog(obj.data.id);//打开添加或修改页面
                break;
            case 'del':
                //询问是否要删除这条数据
                layer.confirm("确定要删除这条记录吗？",{icon: 3, title:"班级管理"},function (index) {
                    //关闭弹出框
                    layer.close(index);
                    //发送ajax请求 删除用户
                    $.ajax({
                        type:'post',
                        url:ctx+'/clazz/deleteBatch',
                        data:{
                            ids:obj.data.id
                        },dataType:'json',
                        success:function (data) {
                            if (data.code == 200) {
                                // 加载表格
                                tableIns.reload();
                            } else {
                                layer.msg(result.msg, {icon: 5});
                            }
                        }
                    });
                })
                break;
        };
    });

    /**
     * 打开添加营销机会的对话框
     */
    function openAddOrUpdateDialog(id){
        var title='<h2>班级管理 - 添加班级</h2>';
        var url=ctx+'/clazz/toAddUpdatePage';
        //通过id判断是修改还是添加
        if(id){
            title='<h2>班级管理 - 修改班级</h2>';
            url += '?id='+id;
        }

        layer.open({
            type:2,     //打开的页面位iframe框
            title:title,        //页面题目
            content:url,        //页面内容
            area:['500px','350px'],     //页面大小
            maxmin:true     //是否可以伸缩页面大小
        });
    }
});