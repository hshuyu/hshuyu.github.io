<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 文章列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input {
            display: inline-block;
            width: 33.333% !important;
        }

        .layui-input-block {
            margin: 0px 10px;
        }


    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
          <a href="${pageContext.request.contextPath}/loginAdminAction?command=index&caretakerCode=${caretaker.caretakerCode}">首页</a>
          <a><cite>文章列表</cite></a>
        </span>
        <div>
            <label><input type="text" maxlength="5" style="width: 180px;height: 24px" id="selectUser"></label>
            <a href="javascript:Select();" class="layui-btn layui-btn-danger layui-btn-mini">搜索</a>
        </div>
    </blockquote>

    <div class="layui-tab layui-tab-card">
        <form id="articleForm" method="post">
            <input type="hidden" name="currentUrl" id="currentUrl" value="">
            <table class="layui-table">
                <colgroup>
                    <col width="100">
                    <col width="300">
                    <col width="150">
                    <col width="100">
                    <col width="150">
                    <col width="100">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>用户</th>
                    <th>标题</th>
                    <th>所属分类</th>
                    <th>状态</th>
                    <th>发布时间</th>
                    <th>操作</th>
                    <th>id</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${articles.data}" var="article">
                    <c:if test="${articles == null}"><tr>none</tr></c:if>
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${article.articleUserCode == 0}">
                                    <span style="color:#FF5722;">管理员</span>
                                </c:when>
                                <c:otherwise>
                                    <span>${article.userName}</span>
                                </c:otherwise>
                            </c:choose>
                            <%--<a title="${a.user.userName}" href="/admin/user/edit/${a.articleUserId}" target="_blank">${a.user.userNickname}</a>--%>
                        </td>
                        <td>
                                ${article.articleTitle}
                        <td>
                                ${article.categoryName}
                        </td>
                        <td>

                                    <c:choose>
                                        <c:when test="${article.articleStatus == 1}">
                                                <span style="color:#5FB878;">已发布</span>
                                        </c:when>
                                        <c:otherwise>
                                                <span style="color:#FF5722;">未审核</span>
                                        </c:otherwise>
                                    </c:choose>
                        </td>
                        <td>
                                ${article.articleCredate}
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/adminArticle?command=toEdit&articleCode=${article.articleCode}"
                               class="layui-btn layui-btn-mini">审核</a>
                            <a href="javascript:Delete(${article.articleCode});" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                        </td>
                        <td>
                                ${article.articleCode}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div style="text-align: center">
            <a href="adminArticle?command=index&page=${articles.prePage}">上一页</a>
            <c:forEach begin="1" end="${articles.totalPage}" var="page">
                <a href="adminArticle?command=index&page=${page}">${page}</a>
            </c:forEach>
            <a href="adminArticle?command=index&page=${articles.nextPage}">下一页</a>
            </div>
        </form>
        <%@ include file="../../admin/paging.jsp" %>
    </div>

</rapid:override>
<SCRIPT type="text/javascript">
    function Delete(epid) {
        if (window.confirm("确认删除这篇文章吗？")) {
            location.href = "${pageContext.request.contextPath}/adminArticle?command=delete&articleCode=" + epid;
        }
    }

    function Select() {
        var selectUser = $("#selectUser").val();
        if (selectUser.length>0){
            window.location.href = "${pageContext.request.contextPath}/adminArticle?command=select&articleTitle="+selectUser;
        }else {
            window.location.href = "${pageContext.request.contextPath}/adminArticle?command=index";
        }
    }
</SCRIPT>

<rapid:override name="footer-script">
    <script></script>
</rapid:override>
<%@ include file="../../admin/index.jsp" %>
