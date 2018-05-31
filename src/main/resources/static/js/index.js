layui.use(['element'], function(){
    var element = layui.element;//监听侧边菜单点击
    var mainLayout = $('#main-layout');
    var hideBtn = $('#hideBtn');
    element.on('nav(sider-menu)', function(elem) {
        var url = elem.attr('lay-href');
        var id = elem.attr('lay-id');
        var text = elem.text();
        /*console.log(elem[0]);*/
        console.log("id: " + id + "|url : " + url + "|text: " + text);
        //查看左边菜单栏是否已经收起
        var isShrink = $(".layadmin-side-shrink").length;
        if (isShrink > 0) {
            //已经收起,就打开菜单栏
            mainLayout.removeClass('layadmin-side-shrink');
        }
        if (typeof(url) == "undefined") {
            console.log("typeof here");
            return;
        }
        //查看所点击的导航页面是否已经激活
        var isActive = $('.layui-tab-brief .layui-tab-title').find("li[lay-id=" + id + "]");
        if(isActive.length > 0) {
            //切换到选项卡
            element.tabChange('tab', id);
        } else {
            //打开相关页面,添加选项卡
            element.tabAdd('tab', {
                title: text,
                content: '<iframe src="' + url + '" name="iframe' + id + '" class="iframe" frameborder="0" data-id="' + id + '" scrolling="auto" width="100%"  height="100%"></iframe>',
                id: id
            });
            element.tabChange('tab', id);
        }
        mainLayout.removeClass('layadmin-side-shrink');
    });
    //菜单隐藏显示
    hideBtn.on('click', function() {
        console.log("菜单隐藏");
        if(!mainLayout.hasClass('layadmin-side-shrink')) {
            mainLayout.addClass('layadmin-side-shrink');
        } else {
            //菜单显示
            mainLayout.removeClass('layadmin-side-shrink');
        }
    });
});