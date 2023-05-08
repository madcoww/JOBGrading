<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="controller.RepDTO" %>
<%@page import="controller.RepDAO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>202044036_final</title>
<style>
	.fmt.form-control{
		display: inline-block;
    	width: 200px;
    	height: 40px;
    	vertical-align: middle;
	}
</style>
</head>
<body>
	<%		
		String userMail = null;
		if(session.getAttribute("mail") != null){
			userMail = (String)session.getAttribute("mail");
			request.setAttribute("mail", userMail);
		}
		if(session.getAttribute("mail") == null){
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('로그인을 해주세요.');");
			writer.println("location.href = 'index.jsp';");
			writer.println("</script>");
			writer.close();
		}
	%>
	<jsp:include page="/header.jsp" flush="false"/>
	<div class="container">
		<section class="container">
			<form method="get" action="./repForm.jsp" class="form-inline mt-3">
				<input type="text" name="search" class="fmt form-control mx-1 mt-2" placeholder="내용을 입력하세요.">
				<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
				<button type="button" class="btn btn-primary mx-1 mt-2" data-bs-toggle="modal" data-bs-target="#registerRep">
  				등록하기
				</button>
			</form>
		</section>
		<div class="modal" id="registerRep" tabindex="-1">
 			<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
       					<h5 class="modal-title">Grading</h5>
        				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      				</div>
      				<div class="modal-body">
       					<form action="repWriteAction" method="post">  
       						<div class="row">
       							<div class="form-group col-sm-6">
       								<label> 회사명</label>
       								<input type="text" name="reqCname" class="form-control mt-1" maxlength="20">
       							</div>
       							<div class="form-group col-sm-6">
       								<label> 근무기간 예) 2년, 1년4개월</label>
       								<input type="text" name="repPer" class="form-control mt-1" maxlength="10">
       							</div>
       						</div>
       						<div>
       							<div class="form-group col-sm-12">
       								<label> 평가</label>
       								<select name="repGrad" class="form-control mt-1">
       									<option value="1.0">1.0</option>
       									<option value="1.5">1.5</option>
       									<option value="2.0">2.0</option>
       									<option value="2.5">2.5</option>
       									<option value="3.0" selected>3.0</option>
       									<option value="3.5">3.5</option>
       									<option value="4.0">4.0</option>
       									<option value="4.5">4.5</option>
       									<option value="5.0">5.0</option>
       								</select>
       							</div>
       						</div>
       						<div class="form-group col-sm-12">
       							<label> 내용</label>
       							<textarea name="repContent" class="form-control mt-1" maxlength="2048" rows="5"></textarea>
       						</div>
       						<div class="modal-footer">
        						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        						<button type="submit" class="btn btn-primary">Register</button>
      						</div>	
       					</form>
      				</div>
    			</div>
  			</div>
		</div>
	<div class="row">
	<%
		ArrayList<RepDTO> repList = new ArrayList<RepDTO>();
		repList = new RepDAO().getList();
		if(repList != null){
			for(int i = 0; i < repList.size(); i++){
				RepDTO rep = repList.get(i);
	%>
	<div class="col-md-6">
		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<div class="col-8 text-left"><%=rep.getrCname() %>&nbsp;<small>(<%=rep.getrPer() %>)</small></div>
					<div class="col-4 text-right"><%=rep.getmName() %></div>
				</div>
			</div>
			<div class="card-body">
				<h5 class="card-title">Grad  <span style="color: red;"><%=rep.getrScore() %></span></h5>
				<p class="card-text"><%=rep.getrContent() %></p>
			</div>
		</div>
	</div>
	<%
			}
		}
	%>
	</div>
	</div>
</body>
</html>