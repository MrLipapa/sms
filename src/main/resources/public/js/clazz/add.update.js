layui.use(['form','jquery','jquery_cookie','upload', 'element', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($),
        $ = layui.jquery
            ,upload = layui.upload
            ,element = layui.element
            ,layer = layui.layer;

    /**
     * 监听表单的提交
     *      on 监听
     *      submit 事件
     */
    form.on("submit(addOrUpdateClazz)", function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}

        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });

        //设置请求发送路径<默认是添加数据>
        var url=ctx+'/clazz/save';
        //根据 当前页面的请求域中是否有id值,来判断是 修改(有id则是修改) 还是 添加
        if ($('#hidId').val()){
            url=ctx+'/clazz/update';
            console.log($('#hidId').val());
        }

        //发送请求
        $.post(url,data.field,function (data) {
            if(data.code==200){
                //关闭提交加载层弹出框
                layer.close(index);
                //关闭iframe
                layer.closeAll("iframe");
                //刷新父页面,将添加的新数据加载出来
                parent.location.reload();
            }else {
                layer.msg(data.msg,{icon:5});
            }
        })

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        // 先得到当前iframe层的索引
        var index = parent.layer.getFrameIndex(window.name);
        // 再执行关闭
        parent.layer.close(index);
    });
});
