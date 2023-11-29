<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>images/css/css.css" />
<script type="text/javascript" src="<%=basePath %>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/sdmenu.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/laydate/laydate.js"></script>
</head>
 
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
	
	String username=(String)session.getAttribute("user"); 
	if(username==null){
		response.sendRedirect(basePath+"index.jsp");
	}
	else{
		 String method="upxs2";
		 String realname="";String sex="";String age="";String address="";String tel=""; 
		 
			List alist=cb.get1Com("select * from admin where username='"+username+"'",10); 
			realname=alist.get(3).toString();
			sex=alist.get(4).toString();
			age=alist.get(5).toString();
			address=alist.get(6).toString();
			tel=alist.get(7).toString();  
%>
<body>
<div class="right_cont">
<div class="title_right"><strong>科研人员管理</strong></div>  
<div style="width:900px;margin:auto;">
<form action="<%=basePath %>AdminServlet?method=<%=method%>" name="form1">
<table class="table table-bordered">
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户姓名：</td>
     <td><input type="text" name="realname" class="span4" value="<%=realname %>" required/></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">职务信息：</td>
     <td><input type="text" name="sex" class="span4" value="<%=sex %>" required/></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">职称信息：</td>
     <td><input type="text" name="age" class="span4" value="<%=age %>" required/></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">联系电话：</td>
     <td><input type="text" name="tel" class="span4" value="<%=tel %>" pattern="[0-9]{11}" title="11位手机号码" required /></td> 
     </tr>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">单位信息：</td>
     <td><input type="text" name="address" class="span4" value="<%=address %>" readonly/></td> 
     </tr>  
     <tr>
     	<td class="text-center" colspan="2"><input type="submit" value="确定" class="btn btn-info  " style="width:80px;" /></td>
     </tr>
     </table> 
</form>
   </div>  
 </div>  
</body>
<%} %>