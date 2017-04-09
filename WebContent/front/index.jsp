<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>新闻</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="description" content="新闻天天看">
<link href="//cdn.bootcss.com/tether/1.3.6/css/tether.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="front/index.css">
</head>

<body>
	<div class="container">
		<div class="leftArea left">
			<ul>
				<li>娱乐</li>
				<li>军事</li>
			</ul>
		</div>
		<div class="centerArea left">
			<div class="news_content">aaaa</div>
			<div class="news_bottom">bbb</div>
		</div>
	</div>
</body>
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</html>