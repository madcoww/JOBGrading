package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/joinAction")
public class joinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public joinAction() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 가입 컨트롤
		request.setCharacterEncoding("UTF-8");
		
		String iMail = null;
		String iPw = null;
		String iDiv = null;
		String iName = null;
		
		if(request.getParameter("memberEmail") != null) {
			iMail = request.getParameter("memberEmail");
		}
		if(request.getParameter("memberPass") != null) {
			iPw = request.getParameter("memberPass");
		}
		if(request.getParameter("memberEmail") != null) {
			iDiv = request.getParameter("divRadios");
		}
		if(request.getParameter("memberEmail") != null) {
			iName = request.getParameter("memberName");
		}
		if(iMail == null || iPw == null || iDiv == null || iName == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('빈 항목이 있습니다. ');");
			writer.println("history.go(-1);");
			writer.println("</script>");
			writer.close();
			
		}

		MemberDAO memberDAO = new MemberDAO();
		int chk1 = memberDAO.join(iMail, iPw, iDiv, iName);
		
		
		if(chk1 == 1) {	
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('회원가입이 완료되었습니다. 로그인해주세요.');");
			writer.println("location.href = 'index.jsp';");
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
}


