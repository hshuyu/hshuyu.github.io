<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 编辑用户
</rapid:override>
<rapid:override name="header-style">
    <style>
        .layui-form-item .layui-input-inline {
            width: 300px;
        }

        .layui-word-aux {
            color: #FF5722 !important;
        }
        .layui-form-label {
            width: 120px;
        }
        input {
            border: 0!important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="${pageContext.request.contextPath}/loginAdminAction?command=index&caretakerCode=${caretaker.caretakerCode}">首页</a>
              <a><cite>个人信息</cite></a>
        </span>
    </blockquote>
    <br><br>
    <form class="layui-form" id="userForm"
          method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list" style="">
                        <img class="layui-upload-img" src="${pageContext.request.contextPath}/img/${caretaker.caretakerAvatar}" id="caretakerAvatar" width="100"
                             height="100">
                        <p id="demoText"></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称 </label>
            <div class="layui-input-inline">
                <input type="text" value="${caretaker.caretakerName}"  id="caretakerName" required
                       autocomplete="off" class="layui-input" disabled>
            </div>
            <div class="layui-form-mid layui-word-aux" id="userNameTips"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号 </label>
            <div class="layui-input-inline">
                <input type="text" value="${caretaker.caretakerTel}" id="caretakerTel" required
                       autocomplete="off" class="layui-input"  disabled>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码 </label>
            <div class="layui-input-inline">
                <input type="password"  value="${caretaker.caretakerPwd}" id="caretakerPwd" required
                       placeholder="" autocomplete="off"
                       class="layui-input" disabled>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <a class="layui-btn layui-btn-primary" style="margin: 20px 140px" href="${pageContext.request.contextPath}/loginAdminAction?command=toEdit&caretakerCode=${caretaker.caretakerCode}">编辑</a>
    </form>

</rapid:override>
<rapid:override name="footer-script">

    <script>

    </script>
</rapid:override>

<%@ include file="../../admin/index.jsp" %>
