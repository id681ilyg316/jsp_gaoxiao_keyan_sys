<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>images/css/css.css" />
<script type="text/javascript" src="<%=basePath %>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/sdmenu.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/laydate/laydate.js"></script>
</HEAD>
<%
	String username=(String)session.getAttribute("user");  String sf=(String)session.getAttribute("sf");  
	if(username==null){
		response.sendRedirect(path+"index.jsp");
	}
	else{ 
%>
<body>
<div class="left">
     
<script type="text/javascript">
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
};
</script>

<div id="my_menu" class="sdmenu">

	<div class="collapsed">
		<span>密码信息管理</span>
		<a href="<%=basePath %>admin/system/editpwd.jsp" target="MainFrame">密码信息管理</a> 
	</div>
	<%if(sf.equals("系统管理员")){ %>  
	<div class="collapsed">
		<span>科研管理人员</span>
		<a href="<%=basePath %>admin/jys/index.jsp" target="MainFrame">科研管理人员</a> 
		<a href="<%=basePath %>admin/jys/add.jsp?method=addjys" target="MainFrame">增加管理人员</a> 
		 <a href="<%=basePath %>admin/jys/s.jsp" target="MainFrame">管理人员查询</a>  
	</div> 
	<div class="collapsed">
		<span>单位信息管理</span>
		<a href="<%=basePath %>admin/dw/index.jsp" target="MainFrame">单位信息管理</a> 
		<a href="<%=basePath %>admin/dw/add.jsp?method=adddw" target="MainFrame">增加单位信息</a>  
		<a href="<%=basePath %>admin/dw/s.jsp" target="MainFrame">单位信息查询</a> 
	</div> 
	<div class="collapsed">
		<span>科研秘书管理</span>
		<a href="<%=basePath %>admin/system/index.jsp" target="MainFrame">科研秘书管理</a> 
		<a href="<%=basePath %>admin/system/add.jsp?method=addm" target="MainFrame">增加科研秘书</a> 
		 <a href="<%=basePath %>admin/system/s.jsp" target="MainFrame">科研秘书查询</a>  
	</div>
	<div class="collapsed">
		<span>科研人员管理</span>
		<a href="<%=basePath %>admin/xs/index.jsp" target="MainFrame">科研人员管理</a> 
		<a href="<%=basePath %>admin/xs/add.jsp?method=addxs" target="MainFrame">增加科研人员</a>  
		<a href="<%=basePath %>admin/xs/s.jsp" target="MainFrame">科研人员查询</a> 
	</div> 
	<div class="collapsed">
		<span>系统数据管理</span>
		<!-- <a href="<%=basePath %>admin/xt/bf.jsp" target="MainFrame">数据备份管理</a>  -->
		<a href="<%=basePath %>admin/xt/rz.jsp?method=addxs" target="MainFrame">系统日志管理</a>  
		<a href="<%=basePath %>admin/xt/dc.jsp" target="MainFrame">数据导出管理</a> 
	</div> 
	
	
	<%}else if(sf.equals("科研管理人员")){ %>
	<div class="collapsed">
		<span>个人信息管理</span>
		<a href="<%=basePath %>admin/jys/index2.jsp" target="MainFrame">个人信息管理</a>  
	</div>
	<div class="collapsed">
		<span>科研项目管理</span>
		<a href="<%=basePath %>admin/xm/index5.jsp" target="MainFrame">科研项目管理</a>   
		 <a href="<%=basePath %>admin/xm/s5.jsp" target="MainFrame">科研项目查询</a>  
	</div>
	<div class="collapsed">
		<span>科研成果管理</span>
		<a href="<%=basePath %>admin/cg/index5.jsp" target="MainFrame">科研成果管理</a>   
		 <a href="<%=basePath %>admin/cg/s5.jsp" target="MainFrame">科研成果查询</a>  
	</div>  
	<div class="collapsed">
		<span>学术活动管理</span>
		<a href="<%=basePath %>admin/hd/index5.jsp" target="MainFrame">学术活动管理</a>   
		 <a href="<%=basePath %>admin/hd/s5.jsp" target="MainFrame">学术活动查询</a>  
	</div> 
	<div class="collapsed">
		<span>项目评价管理</span>
		<a href="<%=basePath %>admin/xm/pj.jsp" target="MainFrame">项目评价管理</a>   
		 <a href="<%=basePath %>admin/xm/pjs.jsp" target="MainFrame">项目评价查询</a>  
	</div>
	<div class="collapsed">
		<span>项目年度统计</span>
		<a href="<%=basePath %>admin/xm/tj.jsp" target="MainFrame">项目年度统计</a>     
	</div>
	<%}else if(sf.equals("科研秘书")){ %> 
	<div class="collapsed">
		<span>个人信息管理</span>
		<a href="<%=basePath %>admin/system/index2.jsp" target="MainFrame">个人信息管理</a>  
	</div>
	
	<div class="collapsed">
		<span>科研项目管理</span>
		<a href="<%=basePath %>admin/xm/index2.jsp" target="MainFrame">科研项目管理</a>   
		 <a href="<%=basePath %>admin/xm/s2.jsp" target="MainFrame">科研项目查询</a>  
	</div>
	<div class="collapsed">
		<span>科研成果管理</span>
		<a href="<%=basePath %>admin/cg/index2.jsp" target="MainFrame">科研成果管理</a>   
		 <a href="<%=basePath %>admin/cg/s2.jsp" target="MainFrame">科研成果查询</a>  
	</div>  
	<div class="collapsed">
		<span>通知信息管理</span>
		<a href="<%=basePath %>admin/tz/index.jsp" target="MainFrame">通知信息管理</a> 
		<a href="<%=basePath %>admin/tz/add.jsp?method=addtz" target="MainFrame">增加通知信息</a> 
		 <a href="<%=basePath %>admin/tz/s.jsp" target="MainFrame">通知信息查询</a>  
	</div>
	<div class="collapsed">
		<span>文件信息管理</span>
		<a href="<%=basePath %>admin/wj/index.jsp" target="MainFrame">文件信息管理</a> 
		<a href="<%=basePath %>admin/wj/add.jsp?method=addwj" target="MainFrame">增加文件信息</a> 
		 <a href="<%=basePath %>admin/wj/s.jsp" target="MainFrame">文件信息查询</a>  
	</div>  
	<div class="collapsed">
		<span>反馈信息管理</span>
		<a href="<%=basePath %>admin/fk/index2.jsp" target="MainFrame">反馈信息管理</a>  
		 <a href="<%=basePath %>admin/fk/s2.jsp" target="MainFrame">反馈信息查询</a>  
	</div>
	<div class="collapsed">
		<span>学术活动管理</span>
		<a href="<%=basePath %>admin/hd/index2.jsp" target="MainFrame">学术活动管理</a>   
		 <a href="<%=basePath %>admin/hd/s2.jsp" target="MainFrame">学术活动查询</a>  
	</div>
	<div class="collapsed">
		<span>项目中检管理</span>
		<a href="<%=basePath %>admin/xm/zj.jsp" target="MainFrame">项目中检管理</a>   
		 <a href="<%=basePath %>admin/xm/zjs.jsp" target="MainFrame">项目中检查询</a>  
	</div>
	<div class="collapsed">
		<span>项目终结管理</span>
		<a href="<%=basePath %>admin/xm/zhj.jsp" target="MainFrame">项目终结管理</a>   
		 <a href="<%=basePath %>admin/xm/zhjs.jsp" target="MainFrame">项目终结查询</a>  
	</div> 
	
	<%}else if(sf.equals("科研人员")){ %> 
	<div class="collapsed">
		<span>个人信息管理</span>
		<a href="<%=basePath %>admin/xs/index2.jsp" target="MainFrame">个人信息管理</a>  
	</div>
	<div class="collapsed">
		<span>科研项目管理</span>
		<a href="<%=basePath %>admin/xm/index.jsp" target="MainFrame">科研项目管理</a>  
		<a href="<%=basePath %>admin/xm/add.jsp?method=addxm" target="MainFrame">在线申报项目</a> 
		 <a href="<%=basePath %>admin/xm/s.jsp" target="MainFrame">科研项目查询</a>  
	</div>
	<div class="collapsed">
		<span>科研成果管理</span>
		<a href="<%=basePath %>admin/cg/index.jsp" target="MainFrame">科研成果管理</a>  
		<a href="<%=basePath %>admin/cg/add.jsp?method=addcg" target="MainFrame">在线申报成果</a> 
		 <a href="<%=basePath %>admin/cg/s.jsp" target="MainFrame">科研成果查询</a>  
	</div>
	<div class="collapsed">
		<span>学术活动管理</span>
		<a href="<%=basePath %>admin/hd/index.jsp" target="MainFrame">学术活动管理</a>  
		<a href="<%=basePath %>admin/hd/add.jsp?method=addhd" target="MainFrame">申请学术活动</a> 
		 <a href="<%=basePath %>admin/hd/s.jsp" target="MainFrame">学术活动查询</a>  
	</div>
	<div class="collapsed">
		<span>通知信息查看</span>
		<a href="<%=basePath %>admin/tz/index2.jsp" target="MainFrame">通知信息查看</a>  
		 <a href="<%=basePath %>admin/tz/s2.jsp" target="MainFrame">通知信息查询</a>  
	</div> 
	<div class="collapsed">
		<span>文件信息查看</span>
		<a href="<%=basePath %>admin/wj/index2.jsp" target="MainFrame">文件信息查看</a>  
		 <a href="<%=basePath %>admin/wj/s2.jsp" target="MainFrame">文件信息查询</a>  
	</div> 
	<div class="collapsed">
		<span>反馈信息管理</span>
		<a href="<%=basePath %>admin/fk/index.jsp" target="MainFrame">反馈信息管理</a>  
		<a href="<%=basePath %>admin/fk/add.jsp?method=addfk" target="MainFrame">提交反馈信息</a> 
		 <a href="<%=basePath %>admin/fk/s.jsp" target="MainFrame">反馈信息查询</a>  
	</div>
	<%} %> 
 
 	<div class="collapsed">
		<span>注销退出系统</span>
		<a onclick="if (confirm('确定要退出吗？')) return true; else return false;" href="<%=basePath %>AdminServlet?method=adminexit" target="_top" >注销退出系统</a>
	</div> 
</div>
     </div>
     <div class="Switch"></div>
     <script type="text/javascript">
	$(document).ready(function(e) {
    $(".Switch").click(function(){
	$(".left").toggle();
	 
		});
});
</script> 
</body>
<%} %>

</html>
