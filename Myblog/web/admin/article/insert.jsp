<%--保留此处 start--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%--保留此处 end--%>
<rapid:override name="title">
    - 新建文章
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="${pageContext.request.contextPath}/loginAdminAction?command=index&caretakerCode=${caretaker.caretakerCode}">首页</a>
              <a href="${pageContext.request.contextPath}/adminArticle?command=index&page=1">文章列表</a>
              <a><cite>添加文章</cite></a>
        </span>
    </blockquote>



    <form class="layui-form"  method="post" id="myForm" action="${pageContext.request.contextPath}/adminArticle?command=upFile" enctype="multipart/form-data">

        <div class="layui-form-item">
            <label class="layui-form-label">标题 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="articleTitle" min="1" max="5" lay-verify="title" id="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <textarea class="layui-textarea layui-hide" name="articleContext" lay-verify="content" id="content"></textarea>
            </div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">分类 <span style="color: #FF5722; ">*</span> </label>
            <div class="layui-input-inline">
                <select name="categoryCode" id="articleParentCategoryId" lay-filter="articleParentCategoryId" required>
                    <c:forEach items="${categorys}" var="c">
                            <option value="${c.categoryCode}">${c.categoryName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item" pane="">
            <label class="layui-form-label">标签 <span style="color: #FF5722; ">*</span> </label>
            <div class="layui-input-inline">
                <select name="tagCode"  lay-filter="" required>
                    <c:forEach items="${tags}" var="t">
                        <option value="${t.tagCode}">${t.tagName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">缩略图</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list" style="">
                        <img class="layui-upload-img"  id="demo1" width="100"
                             height="100">
                        <p id="demoText"></p>
                    </div>
                    <input type="file" class="layui-btn" style="width: 190px" name="articleThumbnail" id="file"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="articleStatus" value="1" title="已发布" checked>
                <input type="radio" name="articleStatus" value="0" title="未审核" >
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
<%--                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>--%>
<%--                <button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
    <input type="submit" value="立即提交"/>
    <input type="reset"/>
            </div>
        </div>

    </form>


</rapid:override>

<rapid:override name="footer-script">
    <script>
        $("#file").change(function () {
            $("#demo1").attr("src",URL.createObjectURL($(this)[0].files[0]));
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
<%--                url: '/admin/upload/img',--%>
<%--                before: function (obj) {--%>
<%--                    obj.preview(function (index, file, result) {--%>
<%--                        $('#demo1').attr('src', result);--%>
<%--                    });--%>
<%--                },--%>
<%--                done: function (res) {--%>
<%--                    $("#articleThumbnail").attr("value", res.data.src);--%>
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

    <script>
        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;


            //上传图片,必须放在 创建一个编辑器前面
            layedit.set({
                uploadImage: {
                    url: '/admin/upload/img' //接口url
                    ,type: 'post' //默认post
                }
            });

            //创建一个编辑器
            var editIndex = layedit.build('content',{
                    height:350,
                }
            );

            //自定义验证规则
            form.verify({
                title: function (value) {
                    if (value.length < 5) {
                        return '标题至少得5个字符啊';
                    }
                }
                , pass: [/(.+){6,12}$/, '密码必须6到12位']
                , content: function (value) {
                    layedit.sync(editIndex);
                }
            });

            layedit.build('content', {
                tool: [
                    'strong' //加粗
                    ,'italic' //斜体
                    ,'underline' //下划线
                    ,'del' //删除线
                    ,'|' //分割线
                    ,'left' //左对齐
                    ,'center' //居中对齐
                    ,'right' //右对齐
                    ,'link' //超链接
                    ,'unlink' //清除链接
                    ,'face' //表情
                    ,'image' //插入图片
                    ,'code'
                ]
            });

            layui.use('code', function(){ //加载code模块
                layui.code();
            });

            //二级联动
            form.on("select(articleParentCategoryId)",function () {
                var optionstring = "";
                var articleParentCategoryId = $("#articleParentCategoryId").val();
                <c:forEach items="${categoryList}" var="c">
                if(articleParentCategoryId==${c.categoryPid}) {
                    optionstring += "<option name='childCategory' value='${c.categoryId}'>${c.categoryName}</option>";
                }
                </c:forEach>
                $("#articleChildCategoryId").html("<option value=''selected>二级分类</option>"+optionstring);
                form.render('select'); //这个很重要
            })

        });
/*               window.onbeforeunload = function() {
                   return "确认离开当前页面吗？未保存的数据将会丢失";
               }*/



    </script>

</rapid:override>


<%--此句必须放在最后--%>
<%@ include file="../../admin/index.jsp"%>

