package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.connectDB;


@WebServlet("/repWriteAction")
public class repWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public repWriteAction() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	//평점 등록 컨트롤
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String rId = getrId();
		
		HttpSession session = request.getSession();
		String mMail = (String) session.getAttribute("mail");
		
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
		}catch(Exception e) {
			e.printStackTrace();
		}		
		String rCname = null;
		String rPer = null;
		String rScore = null;
		String rContent = null;
		
		if(request.getParameter("reqCname") != null) {
			rCname = request.getParameter("reqCname");
		}
		if(request.getParameter("repPer") != null) {
			rPer = request.getParameter("repPer");
		}
		if(request.getParameter("repGrad") != null) {
			rScore = request.getParameter("repGrad");
		}
		if(request.getParameter("repContent") != null) {
			rContent = request.getParameter("repContent");
		}
		if(rCname == null || rPer == null || rScore == null || rContent ==null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('빈 항목이 있습니다. ');");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.close();
		}
		
		RepDAO repDAO = new RepDAO();
		
		int chk = repDAO.write(new RepDTO(rId, mName, rCname, rPer, rScore, rContent));
		
		if(chk == 1) {	
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('등록이 완료되었습니다. ');");
			writer.println("location.href = 'repForm.jsp';");
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
	public String getrId() {
		String repId = null;
		
		String sql1 = "select count(*)+1 from rep";
		
		
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

}
