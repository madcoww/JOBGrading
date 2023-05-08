package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/queDeleteAtcion")
public class queDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public queDeleteAction() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//질문 게시글 삭제 컨트롤
		String qId = request.getParameter("que_id");
		
		QueDAO queDAO = new QueDAO();
		
		int chk = queDAO.delQue(qId);
		
		
		if(chk == 1) {	
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('삭제되었습니다. ');");
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
