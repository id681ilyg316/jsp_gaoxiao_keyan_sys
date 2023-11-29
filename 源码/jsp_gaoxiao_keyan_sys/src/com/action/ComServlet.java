package com.action;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String date2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		
		
		if(method.equals("adddw")){  // 单位   ***********代码注释  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			int flag = cBean.comUp("insert into dw(mc,bz)  values('"+mc+"','"+bz+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("updw")){ //修改 单位 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			int flag = cBean.comUp("update dw set mc='"+mc+"',bz='"+bz+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("deldw")){//删除 单位
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from dw where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("delrz")){//删除日志
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from rz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xt/rz.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xt/rz.jsp").forward(request, response);
			}
		} 
		else if(method.equals("bfdb")){//备份
			try {       
	            Runtime rt = Runtime.getRuntime();   
	            // 调用 mysql 的 cmd 
	            Process child = rt.exec("C:/Program Files/MySQL/MySQL Server 5.5/bin/mysqldump -uroot -p123  keyan");// 设置导出编码为utf8。这里必须是utf8       
	                  
	            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行       
	            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流       
	                              
	            InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱 

	            String inStr;       
	            StringBuffer sb = new StringBuffer("");       
	            String outStr;       
	            // 组合控制台输出信息字符串       
	            BufferedReader br = new BufferedReader(xx);       
	            while ((inStr = br.readLine()) != null) {       
	                sb.append(inStr + "\r\n");       
	            }       
	            outStr = sb.toString();       
	                  
	            // 要用来做导入用的sql目标文件：       
	            FileOutputStream fout = new FileOutputStream("d:/db.sql");       
	            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");       
	            writer.write(outStr);       
	            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免       
	            writer.flush();       
	      
	            // 别忘记关闭输入输出流       
	            in.close ();       
	             
	            xx.close();       
	            br.close();       
	            writer.close();       
	            fout.close();       
	      
	            request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xt/bf.jsp").forward(request, response);  
	      
	        } catch (Exception e) {       
	            e.printStackTrace();  
	            request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xt/bf.jsp").forward(request, response);
	        }       
		}
		
		else if(method.equals("addtz")){  // 通知   ***********代码注释  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("insert into tz(mc,bz,sj,yh,dw)  values('"+mc+"','"+bz+"','"+date+"','"+username+"','"+dw+"')");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("uptz")){ //修改 通知 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("update tz set mc='"+mc+"',bz='"+bz+"',sj='"+date2+"',yh='"+username+"',dw='"+dw+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("deltz")){//删除 通知
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from tz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
		}  
		
		else if(method.equals("delwj")){//删除文件
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from wj where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/wj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/wj/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("addfk")){  // 反馈   ***********代码注释  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("insert into fk(mc,bz,sj,yh,dw)  values('"+mc+"','"+bz+"','"+date+"','"+username+"','"+dw+"')");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upfk")){ //修改 反馈 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("update fk set mc='"+mc+"',bz='"+bz+"',sj='"+date2+"',yh='"+username+"',dw='"+dw+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delfk")){//删除 反馈
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from fk where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("delfk2")){//删除 反馈
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from fk where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/fk/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/fk/index2.jsp").forward(request, response);
			}
		}   
		
		else if(method.equals("delxm")){//删除项目
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("spxm")){ //审批项目 
			String id=request.getParameter("id");
			String sh = request.getParameter("sh");  
			String yj = request.getParameter("yj"); 
			int flag = cBean.comUp("update xm set sh='"+sh+"' ,yj='"+yj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delxm2")){//删除项目
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delxm3")){//删除项目
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/index3.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/index3.jsp").forward(request, response);
			}
		} 
		else if(method.equals("zjxm")){ //中检项目 
			String id=request.getParameter("id");
			String zj = request.getParameter("zj");   
			int flag = cBean.comUp("update xm set zj='"+zj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("zhjxm")){ //中检项目 
			String id=request.getParameter("id");
			String zhj = request.getParameter("zhj");   
			int flag = cBean.comUp("update xm set zhj='"+zhj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/zhj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/zhj.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delxm4")){//删除项目
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delxm5")){//删除项目
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/index5.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/index5.jsp").forward(request, response);
			}
		} 
////////////-----------------------------------成果
		else if(method.equals("delcg")){//删除成果
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/cg/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/cg/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("spcg")){ //审批成果 
			String id=request.getParameter("id");
			String sh = request.getParameter("sh");  
			String yj = request.getParameter("yj"); 
			int flag = cBean.comUp("update cg set sh='"+sh+"' ,yj='"+yj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delcg2")){//删除成果
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delcg3")){//删除成果
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/cg/index3.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/cg/index3.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delcg5")){//删除成果
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/cg/index5.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/cg/index5.jsp").forward(request, response);
			}
		} 
		
		
		
		
		else if(method.equals("addhd")){  // 学术活动   ***********代码注释  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("insert into hd(mc,bz,sj,yh,dw)  values('"+mc+"','"+bz+"','"+date+"','"+username+"','"+dw+"')");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("uphd")){ //修改 学术活动 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("update hd set mc='"+mc+"',bz='"+bz+"',sj='"+date2+"',yh='"+username+"',dw='"+dw+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delhd")){//删除 学术活动
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from hd where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("sphd")){ //审批成果 
			String id=request.getParameter("id");
			String sh = request.getParameter("sh");  
			String yj = request.getParameter("yj"); 
			int flag = cBean.comUp("update hd set sh='"+sh+"' ,yj='"+yj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delhd2")){//删除 学术活动
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from hd where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}
		}  
		else if(method.equals("delhd5")){//删除 学术活动
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from hd where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hd/index5.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hd/index5.jsp").forward(request, response);
			}
		} 
		
		else if(method.equals("pjxm")){  
			String id=request.getParameter("id");
			String pf = request.getParameter("pf");   
			int flag = cBean.comUp("update xm set pf='"+pf+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/xm/pj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/xm/pj.jsp").forward(request, response);
			}  
		} 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
