layui.use(['table', 'form', 'laydate', 'upload', 'layer'], function(){
    var table = layui.table;
    var form = layui.form;
    var laydate = layui.laydate;
    var upload = layui.upload;
    var layer = layui.layer;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });

    //动态表格实例
    var tab =  table.render({
        elem: '#table'
        ,height: 400
        ,url: 'permission/list' //数据接口
        ,page: true //开启分页
        ,cols: [
            [
                {type:'checkbox'}
                ,{field: 'id', width:60, title: 'ID', sort: true}
                ,{field: 'name', title: '名称'}
                ,{field: 'url', title: '地址', sort: true}
                ,{field: 'perms', title: '权限标识'}
                ,{field: 'icon', title: '图标'}
                ,{field: 'created', title: '创建时间', sort: true}
                ,{fixed: 'right', width:130, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]
        ]
    });

    //监听表格复选框选择
    table.on('checkbox(table)', function(obj){
        /*console.log(obj)*/
    });

    //监听工具条
    table.on('tool(table)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        console.log(data)
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(layEvent === 'del'){ //删除
            layer.confirm('确认删除该行?', function(index){
                console.log(obj)
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    url: "/permission/delete/" + data.id,
                    type: 'DELETE',
                    success: function(data) {
                        if (data.code === 0) {
                            layer.msg(data.msg, {icon: 1});
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                        //刷新数据表格
                        tab.reload();
                    }
                });
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something
            $.get("/permission/edit/" + data.id, function (json) {
                //填充数据到弹出层
                $("input[name='id']").val(json.id);   //ID
                $("input[name='name']").val(json.name);   //名称
                $("input[name='url']").val(json.url);   //URL
                $("input[name='perms']").val(json.perms);   //权限标识
                $("#icon").html("<i class=\"" + json.icon + "\"></i>");   //图标
                $('#iconClass').val(json.icon);
                //打开弹出层
                layer.open({
                    type: 1,
                    content: $('#add-window'), //这里content是一个普通的String
                    area: ['850px','545px'],
                    title :'修改文章',
                    offset: '-0px',
                    btn: ['提交', '返回'],
                    yes : function(index, layero){
                        //按钮【提交】的回调
                        //ajax提交表单
                        $.post("/permission/update", $("#form").serialize(), function (data) {
                            if (data.code === 0) {
                                layer.msg(data.msg, {icon: 1});
                                //关闭弹出层
                                layer.close(index);
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                            //刷新数据表格
                            tab.reload();
                        });
                    },
                    btn2 : function(index, layero){
                        //按钮【返回】的回调
                    }
                });
            });

            //清理表单项
            cleanForm();
            //同步更新缓存对应的值
            /*obj.update({
                username: '123'
                ,title: 'xxx'
            });*/
        }
    });

    //执行一个laydate实例
    laydate.render({
        elem: '#date'
        ,value: new Date()
        ,type: 'datetime'
        ,isInitValue: true
    });

    //执行上传实例
    upload.render({
        elem: '#cover' //绑定元素
        ,url: '/upload/' //上传接口
        ,done: function(res){
            //上传完毕回调
        }
        ,error: function(){
            //请求异常回调
        }
    });

    //点击添加打开弹出层
    $("#add").on('click',function () {
        layer.open({
            type: 1,
            content: $('#add-window'), //这里content是一个普通的String
            area: ['550px','545px'],
            title :'添加权限',
            offset: '-0px',
            maxmin: true,
            btn: ['提交', '返回'],
            yes : function(index, layero){
                //按钮【提交】的回调
                //ajax提交表单
                $.post("/permission/save", $("#form").serialize(), function (data) {
                    if (data.code === 0) {
                        layer.msg(data.msg, {icon: 1});
                        //关闭弹出层
                        layer.close(index);
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                    //清理表单项
                    cleanForm();
                    //刷新数据表格
                    tab.reload();
                });
            },
            btn2 : function(index, layero){
                //按钮【返回】的回调
            }
        });
    });

    //批量删除
    $("#delete-batch").on('click',function () {
        //获取选中的行
        var checkStatus = table.checkStatus('table')//test即为基础参数id对应的值
        /*var ids = "";*/
        var ids = [];
        if (checkStatus.data.length < 1) {
            //没有被选中的行
            layer.msg("没有被选中的行!", {icon: 7});
            return;
        }
        layer.confirm('确认删除选中的' + checkStatus.data.length + '行?', function(index){
            for(var i=0;i<checkStatus.data.length;i++){
                ids[i] = checkStatus.data[i].id;
                /*checkStatus.data[i].del();*///删除对应行（tr）的DOM结构，并更新缓存
            }
            $.post("/permission/batch", {"ids" : ids}, function (data) {
                if (data.code === 0) {
                    layer.msg(data.msg, {icon: 1});
                } else {
                    layer.msg(data.msg, {icon: 2});
                }

            });
            layer.close(index);//关闭窗口
            tab.reload();//表格重载
        })

    });

    //打开图标弹窗
    $("#icon").on('click',function () {
        layer.open({
            type: 2,
            title:'图标列表',
            content: '/icon',
            area: ['480px', '90%'],
            success: function(layero, index){
                //var body = layer.getChildFrame('.ico-list', index);
                //console.log(layero, index);
            }
        });
    });

});

//清理表单项
function cleanForm() {
    $('#form')[0].reset();  //重置表单内容
    $("#parentId").val(0);
    $("#level").attr("placeholder", "根目录");
}