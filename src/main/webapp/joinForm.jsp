<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
<title>202044036_final</title>
<style>
	#loginButton{
                text-decoration: underline;
                color: #0064FF;
            }  
</style>
<script type="text/javascript">
	function mailCheck()
</script>            
</head>
<body class="text-center">
	<section class="py-4">
        <div class="container">
	        <div class="row d-flex align-items-center justify-content-center">
	        	<div style="max-width:420px;">
                	<form action="joinAction" name="joinInfo" class="bg-white border py-4 px-5" method="post"> <!-- joinServlet -->
            	    	<div class="form-floating mb-3">
                        	<input class="form-control" name="memberEmail" id="memberEmail" type="text" required /><label>이메일</label>
                        </div>
                        <div class="mb-2">
                        	<button class="btn btn-primary fw-bold w-100 bg-gradient" type="submit" onclick="mailCheck()">이메일 중복 확인</button>
                        </div>
                        <div class="form-floating mb-3">
                        	<input class="form-control" name="memberPass" id="memberPass" type="password" required/><label>비밀번호</label>
                        </div>
                        <div class="form-floating mb-3">
                       		<input class="form-control" name="memberName" id="memberName" type="text" required/><label>닉네임</label>
                        </div>
                        <fieldset class="row mb-3">
    						<div class="col-sm-10">
      							<div class="form-check">
									<input class="form-check-input" type="radio" name="divRadios" id="divRadios" value="취준생" checked>
       								<label class="form-check-label" for="gridRadios1">취준생</label>
      							</div>
     						 	<div class="form-check">
        							<input class="form-check-input" type="radio" name="divRadios" id="divRadios" value="직장인">
      								<label class="form-check-label" for="gridRadios2">직장인</label>
  								</div> 
    						</div>
  						</fieldset>
                        <div class="mb-2">
                        	<button class="btn btn-primary fw-bold w-100 bg-gradient" type="submit">회원 가입</button>
                        </div>
                 </form>
                 <div class="bg-white py-4 px-5 text-center border mt-4">
      	         	 <p class="m-0">
      	         		계정이 있으신가요 ? <a id="loginButton" onclick="location.href='loginForm.jsp'">로그인하러 가기</a>
                	</p>
                </div>
			</div>
      </div>
 </div>
 </section>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" 
 integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous">
</script>
</body>
</html>