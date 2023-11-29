<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8" %> 
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean"/> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow">
<meta http-equiv="Pragma" content="no-cache">    
<meta http-equiv="Cache-Control" content="no-cache">    
<meta http-equiv="Expires" content="0">   
<link rel="stylesheet" href="<%=basePath%>images/style.css?v=0310" media="all" id="base">

<script language="javascript" src="<%=basePath%>images/jquery-1.7.1.min.js"></script> 
<script language="javascript" src="<%=basePath%>images/jquery.main.js"></script>
<script language="javascript" src="<%=basePath%>images/slides.min.jquery.js"></script>
<script language="javascript" src="<%=basePath%>images/jquery.KinSlideshow.js?v=0123"></script> 
<script>
window.onerror=function(){return true;} 
</script> 
<meta http-equiv="Pragma" content="no-cache">    
<meta http-equiv="Cache-Control" content="no-cache">    
<meta http-equiv="Expires" content="0">   
<style type="text/css">
.nav table th  .nav2List a,
.nav table th  .nav2List a:active, 
.nav table th  .nav2List a:visited { font-size:14px;padding:0 11px;  }
.header-link { right:20px; }
.header-link .setlang, 
.header-link .setlang:active,
.header-link .setlang:visited { width:50px; }
</style>
<script>
$(function(){
  var adli= $('.ad-thumbs-list');
if (adli.length==1){
  var adw=$('.ad-thumbs-list img').length*86;
   //document.title+=adw;
$('.ad-thumbs-list').width(adw);
}  
//$('.ad-thumbs-list').width(8000);
});
</script>
</head>
<body>
	
<div class="body"> 
<div class="headerWrap">
  <div class="header">
      <div class="header-link"><a class="sethome" href="#"><span>欢迎光临</span></a></div>
  </div>
  
</div>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>