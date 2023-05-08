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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import util.connectDB;

@WebServlet("/queUpdateAction")
public class queUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public queUpdateAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	//질문 게시물 수정 컨트롤
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String realPath = request.getServletContext().getRealPath("/") +"upload";
		int max = 10*1024*1024;

		//지정한 위치에 폴더가 없다면 폴더 생성
		File uploadDir = new File(realPath);
		
		if(! uploadDir.exists()) {
			uploadDir.mkdir();
		}
		//업로드 파일 경로
		System.out.println(realPath);
		
		MultipartRequest mr = new MultipartRequest(request, realPath, max, "UTF-8", new DefaultFileRenamePolicy());
		
		String qId = mr.getParameter("queId");
		String qTitle = mr.getParameter("queTitle");
		String qContent = mr.getParameter("queContent");
		String qImage = mr.getFilesystemName("queFile");
		
		
		
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
		
		int chk = queDAO.update(qTitle, qContent, qImage, qId);
		
		if(chk == 1) {	
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('수정되었습니다. ');");
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
		
		//doGet(request, response);

	}
}
