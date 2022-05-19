<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<tr>
										<td>PW :</td>
										<td>
											<input type="password" name="customerPw1" id="customerPw1"> 
											
											<span id="customerPwHelper1" class="helper"></span>
										</td>
									</tr>
									<tr>
										<td>PW Check :</td>
										<td>
											<input type="password" name="customerPw2" id="customerPw2"> 
											
											<span id="customerPwHelper2" class="helper"></span>
										</td>
									</tr>
</body>

<script>
if($('#customerPw1').val() == '' || $('#customerPw1').val().length < 4) {
	$('#customerPwHelper1').text('pw는 4자 이상 입력해주세요');
	$('#customerPw1').focus();
	
} else if($('#customerPw1').val() != $('#customerPw2').val()) {
	$('#customerPwHelper1').text('');
	$('#customerPwHelper2').text('pw가 일치하지 않습니다.');
	$('#customerPw2').focus();
}
</script>
</html>