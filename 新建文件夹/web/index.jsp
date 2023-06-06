<!DOCTYPE html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>汉唐剧院管理系统-首页</title>
    <!-- Styles -->
<%--    <link rel="stylesheet" href="./ttms/css/my.css" />--%>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="./ttms/css/bootstrap.css" />
    <link rel="stylesheet" href="./ttms/css/font-awesome.min.css" />
    <link rel="stylesheet" href="./ttms/css/slick.css" />
    <link rel="stylesheet" href="./ttms/css/slicknav.css" />
    <link rel="stylesheet" href="./ttms/css/animate.css" />
    <link rel="stylesheet" href="./ttms/css/theme.css" />

    <script type="text/javascript">
        function search(){
            // //alert("我在运行search");
            var url = "../PlayServlet";
            if (window.XMLHttpRequest)
                req = new XMLHttpRequest();
            else if (window.ActiveXObject)
                req = new ActiveXObject("Microsoft.XMLHTTP");
            if (req) {
                //采用POST方式，异步传输
                req.open("post", url, true);
                //POST方式，必须加入如下头信息设定
                req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                req.onreadystatechange = playComplete;
                ////alert("name:"+document.getElementById("playname").value);
                var w = document.getElementById("playname").value;
                //  //alert(w);
                req.send("type=search&name="+encodeURIComponent(w));
            }
        }
        function search1(){
            // //alert("我在运行search");
            var url = "../PlayServlet";
            if (window.XMLHttpRequest)
                req = new XMLHttpRequest();
            else if (window.ActiveXObject)
                req = new ActiveXObject("Microsoft.XMLHTTP");
            if (req) {
                //采用POST方式，异步传输
                req.open("post", url, true);
                //POST方式，必须加入如下头信息设定
                req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                req.onreadystatechange = PlayComplete;
                ////alert("name:"+document.getElementById("playname").value);
                var w = document.getElementById("playname").value;
                //  //alert(w);
                req.send("type=search&name="+encodeURIComponent(w));
            }
        }
        function PlayComplete() {
            // //alert("wozaizhe");
            if (req.readyState == 4 && req.status == 200) {
                var info = document.getElementById("info");//info的id
            }
            ////alert(req.responseText);

            var json =  JSON.parse(req.responseText);//转换为json对象

            for(i = 0; i<json.length; i++){


                var tmp = json[i].PlayId + ",\'" + json[i].DictTypeId + "\',\'" + json[i].PlayIntroduction+ "\',\'" + json[i].PlayName+ "\'," + json[i].PlayLength+ ",\'" + json[i].PlayImage+ "\',\'" + json[i].PlayTicketPrice+ "\',\'" + json[i].DictLangId +"\'";
                //alert(tmp);
                info.innerHTML = '<div class="col-lg-3 col-sm-6 layout-item-wrap">'+
                    '<article class="property layout-item clearfix">'+
                    '<figure class="feature-image">'+
                    '<a class="clearfix zoom" href="single-property.html"><img data-action="zoom" src="../images/' + json[i].PlayImage  +'.jpg" alt="Property Image" /></a>'+
                    '</figure>'+
                    '<div class="property-contents clearfix">'+
                    '<header class="property-header clearfix">'+
                    '<div class="pull-left">'+
                    '<h6 class="entry-title">'+ json[i].PlayName +'</h6>'+
                    '<span class="property-location">'+
                    '<i class="fa fa-map-marker">'+ json[i].DictTypeId +'</i>'+
                    '</span>'+
                    '</div>'+
                    '<button type="button" class="btn btn-primary btn-price pull-right btn-3d" data-hover="详情" onclick="modify('+ tmp +')"/><strong>详情</strong></button>'+
                    '</header>'+
                    '</div>'+
                    '</article>'+
                    '</div>';

            }
            //var x = encodeURIComponent(w);
            //alert("woshi X:"+x);
        }
        function playComplete() {
            // //alert("wozaizhe");
            if (req.readyState == 4 && req.status == 200) {
                var info = document.getElementById("info");//info的id
            }
            ////alert(req.responseText);

            var json =  JSON.parse(req.responseText);//转换为json对象

            for(i = 0; i<json.length; i++){


                var tmp = json[i].PlayId + ",\'" + json[i].DictTypeId + "\',\'" + json[i].PlayIntroduction+ "\',\'" + json[i].PlayName+ "\'," + json[i].PlayLength+ ",\'" + json[i].PlayImage+ "\',\'" + json[i].PlayTicketPrice+ "\',\'" + json[i].DictLangId +"\'";
                //alert(tmp);
                info.innerHTML += '<div class="col-lg-3 col-sm-6 layout-item-wrap">'+
                    '<article class="property layout-item clearfix">'+
                    '<figure class="feature-image">'+
                    '<a class="clearfix zoom" href="single-property.html"><img data-action="zoom" src="../images/' + json[i].PlayImage  +'.jpg" alt="Property Image" /></a>'+
                    '</figure>'+
                    '<div class="property-contents clearfix">'+
                    '<header class="property-header clearfix">'+
                    '<div class="pull-left">'+
                    '<h6 class="entry-title">'+ json[i].PlayName +'</h6>'+
                    '<span class="property-location">'+
                    '<i class="fa fa-map-marker">'+ json[i].DictTypeId +'</i>'+
                    '</span>'+
                    '</div>'+
                    '<button type="button" class="btn btn-primary btn-price pull-right btn-3d" data-hover="详情" onclick="modify('+ tmp +')"/><strong>详情</strong></button>'+
                    '</header>'+
                    '</div>'+
                    '</article>'+
                    '</div>';

            }
            //var x = encodeURIComponent(w);
            //alert("woshi X:"+x);
        }
        //window.onload =
        function modify(a,b,c,d,e,f,g,h){
            //window.location="schedule.html?playid="+a+"&dicttypeid="+encodeURIComponent(b)+"&playintroduction="+encodeURIComponent(c)+
            //"&playname="+encodeURIComponent(d)+"&playlength="+e+"&playimage"+encodeURIComponent(f)+"&dictlangid="+encodeURIComponent(g);
            //window.location.href="schedule.html";
            window.open("schedule.html?playid="+a+"&dicttypeid="+encodeURIComponent(b)+"&playintroduction="+encodeURIComponent(c)+
                "&playname="+encodeURIComponent(d)+"&playlength="+e+"&playimage="+encodeURIComponent(f)+"&playticketprice="+encodeURIComponent(g)+"&dictlangid"+encodeURIComponent(h))
        }

        function init(){
            //alert("我在运行init");
            search();
        }
        window.onload  = init;
        function user(){
            window.location ="umodify.html?&username="+encodeURIComponent(username)
                +"&pwd="+encodeURIComponent(pwd)
                +"&status="+encodeURIComponent(status)
                +"&userquestion="+encodeURIComponent(userquestion)
                +"&useranswer="+encodeURIComponent(useranswer)
                +"&usertel="+encodeURIComponent(usertel);
        }
    </script>
</head>

<body onload="init()">
<header id="site-header">
    <div class="container">
        <div class="row">
            <div class="col-md-2" style="margin:5px">
                <a href="index.html"><img src="./ttms/images/logo.png" width="127" height="48"/></a>
            </div>
            <div class="col-md-9">
                <nav id="site-nav" class="nav navbar-default">
                    <ul class="nav navbar-nav">
                        <li><a href="index.html">主页</a></li>
                        <li><a href="./ttms/customer/board.html">榜单</a></li>
                        <li><a href="./ttms/customer/contact.html">联系我们</a></li>
                        <li><a href="#" onclick="user()">个人中心</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <!--轮播图-->
</header>
<div class="main-slider-wrapper clearfix">
    <div id="main-slider">
        <div class="slide"><img src="./ttms/images/slider/1.jpg"/></div>
        <div class="slide"><img src="./ttms/images/slider/2.jpg"/></div>
        <div class="slide"><img src="./ttms/images/slider/3.jpg"/></div>
        <div class="slide"><img src="./ttms/images/slider/4.png"/></div>
    </div>
    <div id="slider-contents">
        <div class="container text-center">
            <div class="jumbotron">
                <h1>寻找您的理想剧目</h1>
                <div class="contents clearfix">
                    <p>汉唐剧院是观众喜爱的观影平台，为您提供了在线详情服务。<br />
                        同时还为您提供剧目预告片、票房查询、剧目排行榜、剧目资讯等信息。</p>
                </div>
            </div>
        </div>
    </div>
</div>

<!--搜索-->
<div id="advance-search" class="main-page clearfix">
    <div class="container">
        <button class="btn top-btn">寻找您的剧目</button>
        <form action="#" id="adv-search-form" class="clearfix">
            <input type="text" id="playname" name="playname" placeholder="请输入剧目名" aria-required="true" required="required">
            <input type="button" class="btn btn-primary" value="查询" onclick="search1()" >
        </form>
    </div>
</div>

<!--剧目列表-->
<section id="home-property-listing">
    <div class="container" id="inn">
        <div class="row" id="info">
        </div>
    </div>
</section>

<footer id="footer">
    <div class="site-footer">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-sm-6">
                    <section class="widget address-widget clearfix">
                        <h4 class="title">联系方式</h4>
                        <ul>
                            <li>
                                <i class="fa fa-map-marker"></i>
                                <a href="http://cs.xiyou.edu.cn/">西安市长安区西长安街618号东区计算机学院</a>
                            </li>
                            <li><i class="fa fa-phone"></i>029-88166712</li>
                            <li><i class="fa fa-envelope"></i> shuxf@xiyou.edu.cn</li>
                            <li>@ Copyright &copy; 2020. 西安邮电大学计算机学院.</li>
                        </ul>
                    </section>
                </div>
            </div>
        </div>
    </div>
</footer>
<a href="#top" id="scroll-top"><i class="fa fa-angle-up"></i></a>
<script src="./ttms/js/jquery.min.js"></script>
<script src="./ttms/js/bootstrap.min.js"></script>
<script src="./ttms/js/slick.min.js"></script>
<script src="./ttms/js/zoom.js"></script>
<script src="./ttms/js/theme.js"></script>
<script src="./ttms/js/jquery-ui.min.js"></script>
<script src="./ttms/js/jquery.form.min.js"></script>
<script src="./ttms/js/jquery.validate.min.js"></script>
</body>
</html>
