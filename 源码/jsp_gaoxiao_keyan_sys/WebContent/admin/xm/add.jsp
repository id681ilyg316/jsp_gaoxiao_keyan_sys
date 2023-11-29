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
		String id="";String bh=String.valueOf(System.currentTimeMillis());String mc="";String fzr="";String lb="";String xk="";String jf=""; String ksj="";String esj="";String pic=""; 
		if(method.equals("upxm")){
			id=request.getParameter("id");
			List jlist = cb.get1Com("select * from xm where id='"+id+"'",10);
			bh=jlist.get(1).toString(); 
			mc=jlist.get(2).toString(); 
			fzr=jlist.get(3).toString();  
			lb=jlist.get(4).toString();  
			xk=jlist.get(5).toString();  
			jf=jlist.get(6).toString();  
			ksj=jlist.get(7).toString();  
			esj=jlist.get(8).toString();  
			pic=jlist.get(9).toString();  
		}	  
%>
<body>
<div class="right_cont">
<div class="title_right"><strong>科研项目管理</strong></div>  
<div style="width:900px;margin:auto;">
<form action="<%=basePath %>UpServlet" name="form1" enctype="multipart/form-data">
<table class="table table-bordered"> 
     <tr><input type="hidden" name="method" value="<%=method%>" /><input type="hidden" name="id" value="<%=id%>" />
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">项目编号：</td>
     <td><input type="text" name="bh" class="span4" value="<%=bh %>" readonly/></td> 
     </tr>
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">项目名称：</td>
     <td><input type="text" name="mc" class="span4" value="<%=mc %>" required/></td> 
     </tr> 
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">项目负责人：</td>
     <td><input type="text" name="fzr" class="span4" value="<%=fzr %>" required/></td> 
     </tr>  
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">申请类别：</td>
     <td><select name="lb">
    <%if(method.equals("upxm")){ %><option value="<%=lb%>"><%=lb%></option> <%} %>  
    <option value="国家级">国家级</option> 
    <option value="省级">省级</option> 
    <option value="市级">市级</option>  
    </select></td> 
     </tr>
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">学科分类：</td>
     <td><input type="text" name="xk" class="span4" value="<%=xk %>" required/></td>  
     </tr>
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">预算经费：</td>
     <td><input type="text" name="jf" class="span2" value="<%=jf %>" onKeyUp="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  required/> 万元</td> 
     </tr> 
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">开始时间：</td>
     <td><input type="date" name="ksj" class="span2" value="<%=ksj %>" required/></td> 
     </tr> 
     <tr> 
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">结束时间：</td>
     <td><input type="date" name="esj" class="span2" value="<%=esj %>" required/></td> 
     </tr> 
     <%if(method.equals("upxm")){ %>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">上传申请书：</td>
     <td><input type=file name="pic" class="span4" />  </td> 
     </tr> 
     <%}else{ %>
     <tr>
     <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">上传申请书：</td>
     <td><input type=file name="pic" class="span4" required/>  </td> 
     </tr> 
     <%} %>  
     <tr>
     	<td class="text-center" colspan="2"><input type="submit" value="确定" class="btn btn-info  " style="width:80px;" /></td>
     </tr> 
     </table> 
</form>
   </div>  
 </div>  
</body>
<%} %> 