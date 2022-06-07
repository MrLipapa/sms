layui.use(['form','jquery','jquery_cookie'], function () {
    //2.layUI模块化的引入
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    /**
     * 3.
     * 监听表单的提交
     *     on监听
     *     submit事件里面的值在index.ftl表单中ay-filter="login"
     *     function (data)表回调的函数
     */
    form.on("submit(login)",function (data){
        // console.log(data.elem)//被执行事件的元素DOM对象，一般为button对象
        // console.log(data.form)//被执行提交的元素form对象，一般都存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}

        // TODO 4.数据校验
        //5.发送请求
        $.ajax({
            type:"post",
            //表示controller内的请求方式
            url: ctx + "/user/login",
            //ctx表示  http://localhost:8080/
            //date内的数据必须和后台controller的数据一模一样（userName，userPwd是controller）（username，password是index.ftl内的name）
            data:{
                userName:data.field.username,
                userPwd:data.field.password
            },
            dataType:'json',
            success:function (data){
                //表回调函数
                if(data.code == 200){
                    //判断
                    //存储cookie   result表示Usermodel
                    $.cookie("userIdStr",data.result.userId);
                    $.cookie("userName",data.result.userName);
                    $.cookie("trueName",data.result.trueName);

                    //记住密码
                    if($("#rememberMe").prop("checked")){
                        $.cookie("userIdStr", data.result.userId, { expires: 7 });
                        $.cookie("userName", data.result.userName, { expires: 7 });
                        $.cookie("trueName", data.result.trueName, { expires: 7 });
                    }
                    //跳转到首页
                    window.location.href = ctx + "/main";
                    //main表示IndexController里的
                }else{
                    layer.msg(data.msg,{icon:5});
                }
            }
        });


    });


});

/*
4.
封装的function
function istrue(str){
    if(str == null || str.trim() == ""){
        return true;
    }
    return false;
}*/
