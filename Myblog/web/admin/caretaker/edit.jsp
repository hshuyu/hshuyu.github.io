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
        .layui-form-label {
            width: 120px;
        }
        .layui-word-aux {
            color: #FF5722 !important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="${pageContext.request.contextPath}/loginAdminAction?command=index&caretakerCode=${caretaker.caretakerCode}">首页</a>
              <a><cite>编辑个人信息</cite></a>
        </span>
    </blockquote>
    <br><br>

    <form class="layui-form" action="${pageContext.request.contextPath}/loginAdminAction?command=upFile&caretakerCode=${caretaker.caretakerCode}" enctype="multipart/form-data" id="userForm" method="post">
        <input type="hidden" name="caretakerCode" id="caretakerCode" value="${caretaker.caretakerCode}">
        <div class="layui-form-item">
            <label class="layui-form-label">头像</label>  <%--enctype="multipart/form-data"--%>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list" style="">
                        <img src="${pageContext.request.contextPath}/img/${caretaker.caretakerAvatar}" id="caretakerAvatar" width="100"
                             height="100" >
                        <p id="demoText"></p>
                    </div>
                    <input type="file" class="layui-btn" style="width: 190px" name="caretakerAvatar" id="file"/>

<%--                    <button type="button" class="layui-btn" id="test1">上传图片</button>--%>
<%--                    <input type="hidden" name="caretakerAvatar" id="caretakerAvatar" value="${caretaker.caretakerAvatar}">--%>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="text" name="caretakerName" value="${caretaker.caretakerName}" required
                       placeholder="" autocomplete="off" min="2" max="10"
                       class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="text" value="${caretaker.caretakerTel}" name="caretakerTel" id="caretakerTel" required
                       lay-verify="userName"
                       autocomplete="off" class="layui-input" onblur="checkUserName()">
            </div>
            <div class="layui-form-mid layui-word-aux" id="userNameTips"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="password" name="caretakerPwd" value="${caretaker.caretakerPwd}" id="caretakerPwd" required
                       autocomplete="off" class="layui-input" min="3" max="20">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="submit" value="保存"/>
                <input type="reset"/>
<%--                <button class="layui-btn" lay-submit lay-filter="demo1" id="submit-btn">保存</button>--%>
<%--                <button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
    </form>


</rapid:override>
<rapid:override name="footer-script">
    <script>
        $("#file").change(function () {
            $("#caretakerAvatar").attr("src",URL.createObjectURL($(this)[0].files[0]));
        });

        <%--$("#submit-btn").click(function () {--%>
        <%--    location.href = "${pageContext.request.contextPath}/loginAdminAction?command=upFile";--%>
        <%--})--%>
    </script>
<%--    <script>--%>
<%--        //上传图片--%>
<%--        layui.use('upload', function () {--%>
<%--            var $ = layui.jquery,--%>
<%--                upload = layui.upload;--%>
<%--            var uploadInst = upload.render({--%>
<%--                elem: '#test1',--%>
<%--                url: '/img',--%>
<%--                before: function (obj) {--%>
<%--                    obj.preview(function (index, file, result) {--%>
<%--                        $('#demo1').attr('src', result);--%>
<%--                    });--%>
<%--                },--%>
<%--                done: function (res) {--%>
<%--                    $("#caretakerAvatar").attr("value", res.data.src);--%>
<%--                    if (res.code > 0) {--%>
<%--                        return layer.msg('上传失败');--%>
<%--                    }--%>
<%--                },--%>
<%--                error: function () {--%>
<%--                    var demoText = $('#demoText');--%>
<%--                    demoText.html('' +--%>
<%--                        '<span style="color: #FF5722;">上传失败</span>' +--%>
<%--                        ' <a class="layui-btn layui-btn-mini demo-reload">重试</a>');--%>
<%--                    demoText.find('.demo-reload').on('click', function () {--%>
<%--                        uploadInst.upload();--%>
<%--                    });--%>
<%--                }--%>
<%--            });--%>

<%--        });--%>
<%--    </script>--%>
</rapid:override>

<%@ include file="../../admin/index.jsp" %>
