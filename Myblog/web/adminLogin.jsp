<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]>
<html xmlns="http://www.w3.org/1999/xhtml" class="ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 8) ]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>管理员登录</title>
    <link rel="stylesheet" href="href='${pageContext.request.contextPath}/resource/assets/plugin/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.png">
    <link rel='stylesheet' id='dashicons-css'  href='${pageContext.request.contextPath}/resource/assets/plugin/login/dashicons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='buttons-css'  href='${pageContext.request.contextPath}/resource/assets/plugin/login/buttons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='forms-css'  href='${pageContext.request.contextPath}/resource/assets/plugin/login/forms.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='l10n-css'  href='${pageContext.request.contextPath}/resource/assets/plugin/login/l10n.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='login-css'  href='${pageContext.request.contextPath}/resource/assets/plugin/login/login.min.css' type='text/css' media='all' />
    <style type="text/css">
        body{
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            width:100%;
            height:100%;
        }
        .login h1 a {
            background-size: 220px 50px;
            width: 220px;
            height: 50px;
            padding: 0;
            margin: 0 auto 1em;
        }
        .login form {
            background: #fff;
            background: rgba(255, 255, 255, 0.6);
            border-radius: 2px;
            border: 1px solid #fff;
        }
        .login label {
            color: #000;
            font-weight: bold;
        }
        #nav a {
            color: #000 !important;
        }
        #backtoblog{
            color: #000 !important;
            margin-left: 160px;
        }
    </style><meta name='robots' content='noindex,follow' />
    <meta name="viewport" content="width=device-width" />
    <style>
        body {
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }
    </style>
</head>
<body class="login login-action-login wp-core-ui locale-zh-cn">
<div style="margin-left: 780px">
<div id="login" style="position: fixed">
    <h1><a href="" title="欢迎您光临本站！" tabindex="-1"></a></h1>
    <form name="loginForm" id="loginForm" method="post">
        <p>
            <label for="user_login">手机号<br />
                <input type="text" name="username" id="user_login" class="input" size="20" required/></label>
        </p>
        <p>
            <label for="user_pass">密码<br />
                <input type="password" name="password" id="user_pass" class="input" size="20" required/>
            </label>
        </p>
        <p class="forgetmenot"><label for="rememberme"><input name="rememberme" type="checkbox" id="rememberme" value="1" checked /> 记住密码</label></p>
        <p class="submit">
            <input type="button" name="wp-submit" id="submit-btn" class="button button-primary button-large" value="登录" />
        </p>
    </form>

    <p id="backtoblog"><a href="${pageContext.request.contextPath}/home/index.jsp">&larr; 返回到风吟博客</a></p>

</div>
</div>

<div class="clear"></div>

<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.min.js"></script>
<script type="text/javascript">
    $("#submit-btn").click(function () {
        var user = $("#user_login").val();
        var password = $("#user_pass").val();
        if (user === "") {
            alert("用户名不可为空!");
        } else if (password === "") {
            alert("密码不可为空!");
        } else{
            $.ajax({
                type: "GET",
                url: "loginAdmin?command=adminLogin&username="+user+"&password="+password,
                data: null,
                success: function(msg){
                    if (msg==="NO")
                        alert("用户不存在");
                    else if (msg==="ERROR")
                        alert("密码错误");
                    else if (msg==="SAME")
                        alert("该用户已登录");
                    else if (msg==="RIGHT")
                        window.location.href = "${pageContext.request.contextPath}/admin/index.jsp";
                    // alert(msg==="NO"?"用户不存在":"用户存在");
                }
            })
        }
    })
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

