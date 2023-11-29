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
		
		
		if(method.equals("adddw")){  // ��λ   ***********����ע��  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			int flag = cBean.comUp("insert into dw(mc,bz)  values('"+mc+"','"+bz+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("updw")){ //�޸� ��λ 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			int flag = cBean.comUp("update dw set mc='"+mc+"',bz='"+bz+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("deldw")){//ɾ�� ��λ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from dw where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/dw/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("delrz")){//ɾ����־
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from rz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xt/rz.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xt/rz.jsp").forward(request, response);
			}
		} 
		else if(method.equals("bfdb")){//����
			try {       
	            Runtime rt = Runtime.getRuntime();   
	            // ���� mysql �� cmd 
	            Process child = rt.exec("C:/Program Files/MySQL/MySQL Server 5.5/bin/mysqldump -uroot -p123  keyan");// ���õ�������Ϊutf8�����������utf8       
	                  
	            // �ѽ���ִ���еĿ���̨�����Ϣд��.sql�ļ����������˱����ļ���ע��������Կ���̨��Ϣ���ж�������ᵼ�½��̶����޷�����       
	            InputStream in = child.getInputStream();// ����̨�������Ϣ��Ϊ������       
	                              
	            InputStreamReader xx = new InputStreamReader(in, "utf8");// �������������Ϊutf8�����������utf8����������ж�������� 

	            String inStr;       
	            StringBuffer sb = new StringBuffer("");       
	            String outStr;       
	            // ��Ͽ���̨�����Ϣ�ַ���       
	            BufferedReader br = new BufferedReader(xx);       
	            while ((inStr = br.readLine()) != null) {       
	                sb.append(inStr + "\r\n");       
	            }       
	            outStr = sb.toString();       
	                  
	            // Ҫ�����������õ�sqlĿ���ļ���       
	            FileOutputStream fout = new FileOutputStream("d:/db.sql");       
	            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");       
	            writer.write(outStr);       
	            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���       
	            writer.flush();       
	      
	            // �����ǹر����������       
	            in.close ();       
	             
	            xx.close();       
	            br.close();       
	            writer.close();       
	            fout.close();       
	      
	            request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xt/bf.jsp").forward(request, response);  
	      
	        } catch (Exception e) {       
	            e.printStackTrace();  
	            request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xt/bf.jsp").forward(request, response);
	        }       
		}
		
		else if(method.equals("addtz")){  // ֪ͨ   ***********����ע��  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("insert into tz(mc,bz,sj,yh,dw)  values('"+mc+"','"+bz+"','"+date+"','"+username+"','"+dw+"')");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("uptz")){ //�޸� ֪ͨ 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("update tz set mc='"+mc+"',bz='"+bz+"',sj='"+date2+"',yh='"+username+"',dw='"+dw+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("deltz")){//ɾ�� ֪ͨ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from tz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/tz/index.jsp").forward(request, response);
			}
		}  
		
		else if(method.equals("delwj")){//ɾ���ļ�
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from wj where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/wj/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/wj/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("addfk")){  // ����   ***********����ע��  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("insert into fk(mc,bz,sj,yh,dw)  values('"+mc+"','"+bz+"','"+date+"','"+username+"','"+dw+"')");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upfk")){ //�޸� ���� 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("update fk set mc='"+mc+"',bz='"+bz+"',sj='"+date2+"',yh='"+username+"',dw='"+dw+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delfk")){//ɾ�� ����
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from fk where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/fk/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("delfk2")){//ɾ�� ����
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from fk where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/fk/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/fk/index2.jsp").forward(request, response);
			}
		}   
		
		else if(method.equals("delxm")){//ɾ����Ŀ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("spxm")){ //������Ŀ 
			String id=request.getParameter("id");
			String sh = request.getParameter("sh");  
			String yj = request.getParameter("yj"); 
			int flag = cBean.comUp("update xm set sh='"+sh+"' ,yj='"+yj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delxm2")){//ɾ����Ŀ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/index2.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delxm3")){//ɾ����Ŀ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/index3.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/index3.jsp").forward(request, response);
			}
		} 
		else if(method.equals("zjxm")){ //�м���Ŀ 
			String id=request.getParameter("id");
			String zj = request.getParameter("zj");   
			int flag = cBean.comUp("update xm set zj='"+zj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("zhjxm")){ //�м���Ŀ 
			String id=request.getParameter("id");
			String zhj = request.getParameter("zhj");   
			int flag = cBean.comUp("update xm set zhj='"+zhj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/zhj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/zhj.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delxm4")){//ɾ����Ŀ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/zj.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delxm5")){//ɾ����Ŀ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from xm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/index5.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/xm/index5.jsp").forward(request, response);
			}
		} 
////////////-----------------------------------�ɹ�
		else if(method.equals("delcg")){//ɾ���ɹ�
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/cg/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/cg/index.jsp").forward(request, response);
			}
		} 
		else if(method.equals("spcg")){ //�����ɹ� 
			String id=request.getParameter("id");
			String sh = request.getParameter("sh");  
			String yj = request.getParameter("yj"); 
			int flag = cBean.comUp("update cg set sh='"+sh+"' ,yj='"+yj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delcg2")){//ɾ���ɹ�
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/cg/index2.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delcg3")){//ɾ���ɹ�
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/cg/index3.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/cg/index3.jsp").forward(request, response);
			}
		} 
		else if(method.equals("delcg5")){//ɾ���ɹ�
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from cg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/cg/index5.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/cg/index5.jsp").forward(request, response);
			}
		} 
		
		
		
		
		else if(method.equals("addhd")){  // ѧ���   ***********����ע��  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("insert into hd(mc,bz,sj,yh,dw)  values('"+mc+"','"+bz+"','"+date+"','"+username+"','"+dw+"')");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("uphd")){ //�޸� ѧ��� 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String username=(String)session.getAttribute("user");
			String dw=cBean.getString("select address from admin where username='"+username+"'");
			int flag = cBean.comUp("update hd set mc='"+mc+"',bz='"+bz+"',sj='"+date2+"',yh='"+username+"',dw='"+dw+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delhd")){//ɾ�� ѧ���
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from hd where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/hd/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("sphd")){ //�����ɹ� 
			String id=request.getParameter("id");
			String sh = request.getParameter("sh");  
			String yj = request.getParameter("yj"); 
			int flag = cBean.comUp("update hd set sh='"+sh+"' ,yj='"+yj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delhd2")){//ɾ�� ѧ���
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from hd where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/hd/index2.jsp").forward(request, response);
			}
		}  
		else if(method.equals("delhd5")){//ɾ�� ѧ���
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from hd where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/hd/index5.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/hd/index5.jsp").forward(request, response);
			}
		} 
		
		else if(method.equals("pjxm")){  
			String id=request.getParameter("id");
			String pf = request.getParameter("pf");   
			int flag = cBean.comUp("update xm set pf='"+pf+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/xm/pj.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
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
