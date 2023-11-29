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
		String id=request.getParameter("id");  
		String sh = cb.getString("select sh from xm where id='"+id+"'"); 
		String yj = cb.getString("select yj from xm where id='"+id+"'");
%>
<body>
<div class="right_cont">
<div class="title_right"><strong>科研项目管理</strong></div>  
<div style="width:900px;margin:auto;">
<form action="<%=basePath %>ComServlet" name="form1">
<table class="table table-bordered"> 
     <tr><input type="hidden" name="method" value="<%=method%>" /><input type="hidden" name="id" value="<%=id%>" />
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">审批项目：</td>
     <td><select name="sh">
    <option value="<%=sh%>"><%=sh%></option> 
    <option value="通过">通过</option> 
    <option value="未通过">未通过</option>  
    </select></td> 
     </tr>  
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">审批意见：</td>
     <td><textarea name="yj" cols="100" class="span4" rows="5" required><%=yj%></textarea></td> 
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