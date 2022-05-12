<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="vo.Customer"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>InsertMemberForm</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body class = "container">
<%
	//오류 메세지 받기
	String msg ="";
	if(request.getParameter("msg")!=null){
		if(request.getParameter("msg").equals("notMatch")){
			msg ="비밀번호와 확인비밀번호가 일치하지 않습니다.";
		}
	}
%>
	<h1>회원가입</h1>
	<!-- 오류메세지 출력 -->
	<h2><%=msg %></h2>
	<form method="post" action="<%=request.getContextPath()%>/insertCustomerController" class="was-validated">
		<div class="form-group">
			<table class="table table-bordered">
				<tr>
					<td>아이디 입력</td>
					<td>
						<input type = "text" name ="customerId" id="customerId">
						<span id="customerIdHelper" class="helper"></span>
					</td>
				</tr>
				<tr>
					<td>
						비밀번호 입력
					</td>
					<td>
						<input type = "password" name ="customerPw1" id="customerPw1" value="">
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input type = "password" name ="customerPw2" id="customerPw2" value="">
						<span id="customerPwHelper" class="helper"></span>
					</td>
				</tr>
				<tr>
					<td>name</td>
					<td>
						<input type ="text" name="name"  id="name">
						<span id="nameHelper" class="helper"></span>
					</td>
				</tr>
				<tr>
					<td>gender</td>
					<td>
 						<input type="radio" value="male" name="gender" class="gender">남
                   		<input type="radio" value="female" name="gender" class="gender">여
                    	<span id="genderHelper" class="helper"></span>
					</td>
				</tr>
				<tr>
                <td>생일</td>
                <td>
                	<input type="date"  name="birth" id="birth">
                	<span id="birthHelper" class="helper"></span>
                </td>
            </tr>
            <tr>
                <td>나이</td>
                <td>
                	<!-- 생일입력시 자동입력, 직접 입력불가(readonly) -->
                	<input type="text" id="age" name="age" readonly="readonly">
                </td>
            </tr>
              <tr>
                <td>이메일(필수)</td>
                <td>
                    <input type="text" id="emailId" name="emailId">
                    @
                    <select id="emailUrl" name="emailUrl">
						<!-- 필수 -->
                    	<option value="">::선택::</option>
                        <option value="naver.com">naver.com</option>
                        <option value="daum.net">daum.net</option>
                        <option value="gmail.com">gmail.com</option>
                    </select>
                    <span id="emailHelper" class="helper"></span>
                </td>
            </tr>
            <tr>
            	<td>전화번호</td>
            	<td>
            		 <input type="text" id="phone" name="phone">
            		 <span id="phoneHelper" class="helper"></span>
            	</td>
            </tr>
			<tr>
				<td colspan="2">
					<button type ="submit" class="btn btn-primary">회원가입</button>
				</td>
			</tr>
		</table>
	</div>
</form>
		<a  href="<%=request.getContextPath()%>/loginController" type ="button" class="btn btn-outline-secondary">취소</a>
</body>

<script>
	$('#customerId').focus();
	
	$('#customerId').blur(function(){
		if($('#customerId').val().length < 4) {
			$('#customerIdHelper').text('id는 4자이상');
			$('#customerId').focus();
		} else {
			$('#customerIdHelper').text('');
		}
	});
	
	$('#customerPw2').blur(function(){
		if($('#customerPw1').val().length < 4) {
			$('#customerPwHelper').text('pw는 4자이상');
			$('#customerPw1').focus();
		} else if($('#customerPw1').val() != $('#customerPw2').val()) {
			$('#customerPwHelper').text('pw가 일치하지 않습니다');
			$('#customerPw1').focus();
		} else {
			$('#customerPwHelper').text('');
		}
	});
	
	$('#name').blur(function(){
		if($('#name').val() == '') {
			$('#nameHelper').text('name을 입력하세요');
			$('#name').focus();
		} else {
			$('#nameHelper').text('');
		}
	});
	
	$('#birth').blur(function() {
		if($('#birth').val() == '') {
			$('#birthHelper').text('birth을 입력하세요');
			$('#birth').focus();
		} else {
			$('#birthHelper').text('');
			// age 계산
			let today = new Date();
			let y = today.getFullYear();
			let age = y - Number($('#birth').val().substr(0, 4));
			$('#age').val(age);
		}
	});
	
	$('#signup').click(function(){
		if($('#customerId').val() == '') {
			$('#customerIdHelper').text('id는 4자이상');
			
			$('#customerId').focus();
		} else if($('#customerPw').val() == '') {
			$('#customerIdHelper').text('');
			
			$('#customerPwHelper').text('pw는 4자이상');
			$('#customerPw').focus();
		} else if($('.gender:checked').length == 0) {
			$('#customerPwHelper').text('');
			
			$('#genderHelper').text('gender를 선택하세요');
			$('.gender').focus(); // ?
		} else if($('.interest:checked').length < 2) {
			$('#genderHelper').text('');
		
			
			$('#birthHelper').text('birth를 입력하세요');
			$('#birth').focus();
		} else if($('#emailId').val()=='' || $('#emailUrl').val()=='') {
			$('#birthHelper').text('');
			
			$('#emailHelper').text('email를 입력하세요');
			$('#emailId').focus();
		} else if($('#phone').val() == '') {
			$('phoneHelper').text('phone을 입력하세요')
			$('#phone').focus();
		} else {
			$('#signupForm').submit();
		} 
	});
</script>
</html>