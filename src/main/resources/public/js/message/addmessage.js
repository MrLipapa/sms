layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    /**
     * 监听表单的提交
     *     on监听 submit事件
     */
    form.on('submit(addOrUpdateMessage)',function (data){
        console.log(data.field);
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });
        var url= ctx + "/message/save";

        //判断当前页面中是否有id值，如果有则是修改
        if($("#hidId").val()){
            url= ctx + "/message/update";
        }
        console.log(data.field);
        //发送请求
        $.post(url,data.field,function (data){
            if(data.code == 200){
                //关闭弹出框
                layer.close(index);
                //关闭iframe层
                layer.closeAll("iframe");
                //刷新父页面，将添加的新数据展示
                parent.location.reload();
            }else{
                layer.msg(data.msg,{icon:5})
            }
        });

        return false;//阻止表单提交
    })


});

