<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="shortcut icon" href="/img/logo.png">
    <title>
        ${options.optionSiteTitle}后台
            <rapid:block name="title"></rapid:block>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/plugin/font-awesome/css/font-awesome.min.css">
    <rapid:block name="header-style"></rapid:block>
    <rapid:block name="header-script"></rapid:block>
    <style>
        body {
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="/admin" style="color:#009688;">
        ${options.optionSiteTitle}后台
        </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/" target="_blank">前台</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">新建</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/article/insert.jsp">文章</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/page/insert.jsp">页面</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/category/index.jsp">分类</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/notice/insert.jsp">公告</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/link/insert.jsp">链接</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${sessionScope.user.userAvatar}" class="layui-nav-img">
                    ${sessionScope.user.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/user/profile.jsp">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="/admin/logout">退了</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->

<%--            <c:if test="${sessionScope.user != null && sessionScope.user.userRole == 'admin'}">--%>
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">文章</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/article/index.jsp">全部文章</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/article/insert.jsp">写文章</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/category/index.jsp">全部分类</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/tag/index.jsp">全部标签</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">页面</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/page/index.jsp">全部页面</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/page/insert.jsp">添加页面</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        链接
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/link/index.jsp">全部链接</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/link/insert.jsp">添加链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">公告</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/notice/index.jsp">全部公告</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/notice/insert.jsp">添加公告</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="/admin/comment">
                        评论
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/user/index.jsp">全部用户</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/user/insert.jsp">添加用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/menu/index.jsp">菜单</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/options/index.jsp">主要选项</a></dd>
                    </dl>
                </li>
            </ul>
<%--            </c:if>--%>

<%--            <c:if test="${sessionScope.user != null && sessionScope.user.userRole == 'user'}">--%>
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">文章</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/article/index.jsp">我的文章</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/article/insert.jsp">写文章</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">评论</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/comment/index.jsp">我的评论</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/comment/reply.jsp">评论我的</a></dd>
                        </dl>
                    </li>
                </ul>
<%--            </c:if>--%>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">

            </rapid:block>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © <a href="http://blog.liuyanzhao.com">言曌博客</a> 2017  欢迎加入开发者交流群 590480292，博主免费回答大家日常问题。同时博主代做毕设，解决开发问题，详情参看 <a href="https://liuyanzhao.com/shop.html" target="_blank">有偿服务</a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/plugin/layui/layui.all.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/back.js"></script>
<rapid:block name="footer-script">

</rapid:block>
<script>
    //给文本编辑器的iframe引入代码高亮的css
    $("iframe").contents().find("head").append("<link rel=\"stylesheet\" href=\"${pageContext.request.contextPath}/resource/assets/css/highlight.css\">\n");

</script>

</body>
</html>
<script>
    window.onload=function(){
        //定义body的margin由默认值8px->0px
        document.body.style.margin="0";
        document.body.style.background="#eeeeee";
        //创建canvas画布
        document.body.appendChild(document.createElement('canvas'));
        var canvas = document.querySelector('canvas'),
            ctx = canvas.getContext('2d') //ctx返回一个在canvas上画图的api/dom
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        canvas.style.position='static';
        ctx.lineWidth = .3;
        ctx.strokeStyle = (new Color(150)).style;
        //定义鼠标覆盖范围
        var mousePosition = {
            x: 30 * canvas.width / 100,
            y: 30 * canvas.height / 100
        };
        var dots = {
            nb: 1000,//Dot的总数
            distance: 50,
            d_radius: 100,
            array: []
        };
        //创建颜色类，Color类返回字符串型rgba（*,*,*,.8）
        function mixComponents(comp1, weight1, comp2, weight2) {
            return (comp1 * weight1 + comp2 * weight2) / (weight1 + weight2);
        }
        function averageColorStyles(dot1, dot2) {
            var color1 = dot1.color,
                color2 = dot2.color;

            var r = mixComponents(color1.r, dot1.radius, color2.r, dot2.radius),
                g = mixComponents(color1.g, dot1.radius, color2.g, dot2.radius),
                b = mixComponents(color1.b, dot1.radius, color2.b, dot2.radius);
            return createColorStyle(Math.floor(r), Math.floor(g), Math.floor(b));
        }
        function colorValue(min) {
            return Math.floor(Math.random() * 255 + min);
        }
        function createColorStyle(r,g,b) {
            return 'rgba(' + r + ',' + g + ',' + b + ', 0.8)';
        }
        function Color(min) {
            min = min || 0;
            this.r = colorValue(min);
            this.g = colorValue(min);
            this.b = colorValue(min);
            this.style = createColorStyle(this.r, this.g, this.b);
        }
        //创建Dot类以及一系列方法
        function Dot(){
            this.x = Math.random() * canvas.width;
            this.y = Math.random() * canvas.height;

            this.vx = -.5 + Math.random();
            this.vy = -.5 + Math.random();

            this.radius = Math.random() * 2;

            this.color = new Color();
        }

        Dot.prototype = {
            draw: function(){
                ctx.beginPath();
                ctx.fillStyle = this.color.style;
                ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
                ctx.fill();
            }
        };
        function moveDots() {//Dot对象的移动
            for(i = 0; i < dots.nb; i++){

                var dot = dots.array[i];

                if(dot.y < 0 || dot.y > canvas.height){
                    dot.vx = dot.vx;
                    dot.vy = - dot.vy;
                }
                else if(dot.x < 0 || dot.x > canvas.width){
                    dot.vx = - dot.vx;
                    dot.vy = dot.vy;
                }
                dot.x += dot.vx;
                dot.y += dot.vy;
            }
        }
        function connectDots(){//DOt对象的连接
            for(i = 0; i < dots.nb; i++){
                for(j = i; j < dots.nb; j++){
                    i_dot = dots.array[i];
                    j_dot = dots.array[j];

                    if((i_dot.x - j_dot.x) < dots.distance && (i_dot.y - j_dot.y) < dots.distance && (i_dot.x - j_dot.x) > - dots.distance && (i_dot.y - j_dot.y) > - dots.distance){
                        if((i_dot.x - mousePosition.x) < dots.d_radius && (i_dot.y - mousePosition.y) < dots.d_radius && (i_dot.x - mousePosition.x) > - dots.d_radius && (i_dot.y - mousePosition.y) > - dots.d_radius){
                            ctx.beginPath();
                            ctx.strokeStyle = averageColorStyles(i_dot, j_dot);
                            ctx.moveTo(i_dot.x, i_dot.y);
                            ctx.lineTo(j_dot.x, j_dot.y);
                            ctx.stroke();//绘制定义的路线
                            ctx.closePath();//创建从当前点回到起始点的路径
                        }
                    }
                }
            }
        }
        function createDots(){//创建nb个Dot对象
            for(i = 0; i < dots.nb; i++){
                dots.array.push(new Dot());
            }
        }
        function drawDots() {//引用Dot原型链，使用draw方法，在canvas上画出Dot对象
            for(i = 0; i < dots.nb; i++){
                var dot = dots.array[i];
                dot.draw();
            }
        }
        function animateDots() {
            ctx.clearRect(0, 0, canvas.width, canvas.height);//清除画布，否则线条会连在一起
            moveDots();
            connectDots();
            drawDots();
            requestAnimationFrame(animateDots);
        }
        createDots();//使用创建Dot类函数
        requestAnimationFrame(animateDots);//使用canvas独有的60Hz刷新屏幕画布的方法

        document.querySelector('canvas').addEventListener('mousemove',function(e){
            mousePosition.x = e.pageX;
            mousePosition.y = e.pageY;
        })

        document.querySelector('canvas').addEventListener('mouseleave',function(e){//鼠标离开时，连接自动返回到画布中心
            mousePosition.x = canvas.width / 2;
            mousePosition.y = canvas.height / 2;
        })
    }
</script>