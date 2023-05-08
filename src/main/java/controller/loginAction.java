package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/loginAction")
public class loginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public loginAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 컨트롤
		String mail = request.getParameter("memberEmail");
		String pw = request.getParameter("memberPass");
		
		MemberDAO memberDAO = new MemberDAO();
		int chk = memberDAO.login(mail, pw);
		
		
		if(chk == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("mail", mail);
			response.sendRedirect("repForm.jsp");
		}else if(chk == 0 || chk == -1) {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('아이디 또는 패스워드가 일치하지 않습니다.');");
			out.println("history.go(-1)");
			out.println("</script>");	
		}else {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('DBconnetion error';");
			out.println("history.go(-1)");
			out.println("</script>");
		}
	}
}
