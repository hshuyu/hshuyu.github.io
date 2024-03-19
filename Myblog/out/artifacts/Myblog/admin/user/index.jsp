<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 用户列表
    </rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/

        .layui-table {
            margin-top: 0;
        }

        .layui-btn {
            margin: 2px 0!important;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="${pageContext.request.contextPath}/loginAdminAction?command=index&caretakerCode=${caretaker.caretakerCode}">首页</a>
              <a><cite>用户列表</cite></a>

        </span>
        <div>
            <label><input type="text" style="width: 180px;height: 24px" id="selectUser"></label>
            <a href="javascript:Select();" class="layui-btn layui-btn-danger layui-btn-mini">搜索</a>
        </div>
    </blockquote>


<table class="layui-table" lay-even lay-skin="nob" >
    <colgroup>
        <col width="250">
        <col width="250">
        <col width="250">
        <col width="250">
        <col width="250">
        <col width="150">
        <col width="180">
    </colgroup>
    <thead>
    <tr>
        <th>用户名</th>
        <th>手机号</th>
        <th>电子邮件</th>
        <th>生日</th>
        <th>注册时间</th>
        <th>状态</th>
        <th>操作</th>
        <th>ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users.data}" var="u">
        <tr>
            <td>
                 <img src="${pageContext.request.contextPath}/img/${u.userAvatar}" width="48" height="48">
                 <strong>${u.userName}</strong>
            </td>
            <td>
                ${u.userTel}
            </td>
            <td >
                ${u.userEmail}
            </td>
            <td>
                ${u.userBirth}
            </td>
            <td>
                    ${u.userRegisterTime}
            </td>
            <td>
                <c:choose>
                    <c:when test="${u.userLoginStatus==0}">
                        <span style="color:#FF5722;">禁用</span>
                    </c:when>
                    <c:when test="${u.userLoginStatus==1}">
                        <span style="color:greenyellow;">在线</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color:lightgray;">不在线</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/adminUser?command=toEdit&userCode=${u.userCode}"
                   class="layui-btn layui-btn-mini">编辑</a>
                <a href="javascript:Delete(${u.userCode});" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
            </td>
            <td>
                ${u.userCode}
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>

</rapid:override>
<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.min.js"></script>
<SCRIPT type="text/javascript">
    function Delete(epid) {
        if (window.confirm("确认删除该用户吗？")) {
            location.href = "${pageContext.request.contextPath}/adminUser?command=delete&userCode=" + epid;
        }
    }
    function Select() {
        var selectUser = $("#selectUser").val();
        if (selectUser.length>0){
            window.location.href = "${pageContext.request.contextPath}/adminUser?command=select&userName="+selectUser;
        }else {
            window.location.href = "${pageContext.request.contextPath}/adminUser?command=index";
        }
    }
    <%--$("#selectBtn").onclick(function () {--%>
    <%--    alert("111");--%>
    <%--    var selectUser = $("#selectUser").val();--%>
    <%--    if (selectUser != null){--%>
    <%--        window.location.href = "${pageContext.request.contextPath}/adminUser?command=select&userName="+selectUser;--%>
    <%--    }--%>
    <%--})--%>
</SCRIPT>

<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../../admin/index.jsp"%>
