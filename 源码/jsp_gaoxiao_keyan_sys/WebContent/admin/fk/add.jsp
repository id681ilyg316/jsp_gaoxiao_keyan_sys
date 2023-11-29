<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>  
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
	
	String admin=(String)session.getAttribute("user"); 
	if(admin==null){
		response.sendRedirect(path+"index.jsp");
	}
	else{ 
		String method=request.getParameter("method");  
		String id="";String mc="";String pic="";String bz=""; 
		if(method.equals("upfk")){
			id=request.getParameter("id");
			List jlist = cb.get1Com("select * from fk where id='"+id+"'",3);
			mc=jlist.get(1).toString(); 
			bz=jlist.get(2).toString();  
		}	  
%>
<body>
<div class="right_cont">
<div class="title_right"><strong>反馈信息管理</strong></div>  
<div style="width:900px;margin:auto;">
<form action="<%=basePath %>ComServlet" name="form1" >
<table class="table table-bordered"> 
     <tr><input type="hidden" name="method" value="<%=method%>" /><input type="hidden" name="id" value="<%=id%>" />
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">反馈标题：</td>
     <td><input type="text" name="mc" class="span4" value="<%=mc %>" required/></td> 
     </tr>  
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">反馈内容：</td>
     <td><textarea name="bz" cols="100" class="span4" rows="5" required><%=bz%></textarea></td> 
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