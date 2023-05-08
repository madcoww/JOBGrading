package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import util.connectDB;


@WebServlet("/queWriteAction")
public class queWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public queWriteAction() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	//게시물 작성 컨트롤 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String qId = getqId();
		
		HttpSession session = request.getSession();
		String mMail = (String) session.getAttribute("mail");
		String mName = getName(mMail);
		
		String realPath = request.getServletContext().getRealPath("/") +"upload";
		int max = 10*1024*1024;
		
		System.out.println(realPath);
		
		File uploadDir = new File(realPath);
		
		if(! uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		
		MultipartRequest mr = new MultipartRequest(request, realPath, max, "UTF-8", new DefaultFileRenamePolicy());
		
		String qTitle = mr.getParameter("queTitle");
		String qContent = mr.getParameter("queContent");
		String qImage = mr.getFilesystemName("queFile");
		
		
		String qDate = getDate();

		
		if(qTitle == null || qContent == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('빈 항목이 있습니다. ');");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.close();
		}
		
		QueDAO queDAO = new QueDAO();
		
		int chk = queDAO.write(new QueDTO(qId, mName, qTitle, qContent, qImage, qDate));
		
		
		if(chk == 1) {	
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('등록이 완료되었습니다. ');");
			writer.println("location.href = 'queForm.jsp';");
			writer.println("</script>");
			writer.close();
		}else{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('DB 오류');");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.close();
		}
		
		
	}
	public String getqId() {
		String repId = null;
		
		String sql1 = "select count(*)+1 from que";
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql1);
			
			if(rs.next()) {
				repId = rs.getString(1).toString();
			}
			rs.close();
			stat.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return repId;
	}
	
	public String getDate() {
		String tdayDate = null;
		
		String sql2 = "select date_format(now(), '%Y-%m-%d')";
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql2);
			
			if(rs.next()) {
				tdayDate = rs.getString(1).toString();
			}
			rs.close();
			stat.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tdayDate;
	}
	public String getName(String mMail) {
		
		String mName = null;
		String sql2 = "select member_name from member where member_mail='" + mMail + "'";
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql2);
			
			if(rs.next()) {
				mName = rs.getString(1).toString();
			}
			rs.close();
			stat.close();
			conn.close();
			return mName;
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return mName;
	}
}
