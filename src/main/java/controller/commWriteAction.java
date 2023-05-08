package controller;

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

import util.connectDB;

@WebServlet("/commWriteAction")
public class commWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public commWriteAction() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글 작성 컨트롤
		request.setCharacterEncoding("UTF-8");
		
		String cId = getcId();
		
		String qId = request.getParameter("que_id");
		
		
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
		String cText = null;
		String cDate = getDate();
		
		
		if(request.getParameter("commText") != null) {
			cText = request.getParameter("commText");
		}
		
		
		if(cText == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('빈 항목이 있습니다. ');");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.close();
		}
		
		CommDAO commDAO = new CommDAO();
		
		int chk = commDAO.write(new CommDTO(cId, qId, mName, cText, cDate));
		
		if(chk == 1) {	
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('등록이 완료되었습니다. ');");
			writer.println("history.go(-1);");
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
	// comm_id 값 구하기
	public String getcId() {
		String commId = null;
		
		String sql1 = "select count(*)+1 from comm";
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql1);
			
			if(rs.next()) {
				commId = rs.getString(1).toString();
			}
			rs.close();
			stat.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return commId;
	}
	//현재 날짜 가져오기
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

}
