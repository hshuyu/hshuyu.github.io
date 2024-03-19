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
    <!--界面标、标题-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.png">
    <title>管理员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/back.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/plugin/font-awesome/css/font-awesome.min.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <a href="javascript:;" style="color:#009688;">管理员</a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/home/index.jsp" target="_blank">回到前台</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/img/${caretaker.caretakerAvatar}" class="layui-nav-img">
                    ${caretaker.caretakerName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/loginAdminAction?command=profile&caretakerCode=${caretaker.caretakerCode}">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/loginAdminAction?command=exitLogin&caretakerCode=${caretaker.caretakerCode}">注销</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">文章</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/adminArticle?command=index">全部文章</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/adminArticle?command=toInsert">写文章</a></dd>
<%--                        <dd><a href="${pageContext.request.contextPath}/category/index.jsp">全部分类</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/tag/index.jsp">全部标签</a></dd>--%>
                    </dl>
                </li>
<%--                <li class="layui-nav-item">
                    <a href="javascript:;">页面</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/page/index.jsp">全部页面</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/page/insert.jsp">添加页面</a></dd>
                    </dl>
                </li>--%>
<%--                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        链接
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/link/index.jsp">全部链接</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/link/insert.jsp">添加链接</a></dd>
                    </dl>
                </li>--%>
<%--                <li class="layui-nav-item">
                    <a href="javascript:;">公告</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/notice/index.jsp">全部公告</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/notice/insert.jsp">添加公告</a></dd>
                    </dl>
                </li>--%>
<%--                <li class="layui-nav-item">
                    <a href="/admin/comment">
                        评论
                    </a>
                </li>--%>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/adminUser?command=index">用户列表</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/admin/user/insert.jsp">添加用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">菜单</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/menu/index.jsp">菜单</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/options/index.jsp">主要选项</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">标签</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/menu/index.jsp">菜单</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/options/index.jsp">主要选项</a></dd>
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

            <%--            <c:if test="${sessionScope.user != null && sessionScope.user.userRole == 'user'}">--%>
<%--            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
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
            </ul>--%>
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
        © <a href="http://blog.liuyanzhao.com">言曌博客</a> 2022&emsp;&emsp;&emsp;十分冷淡存知己，一曲微茫度此生，欢迎加入开发者交流群 88888888，群主免费回答大家所有问题，详情参看 <a href="https://www.baidu.com" target="_blank">问题咨询</a>
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
