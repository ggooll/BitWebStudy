<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
</head>
<body>

	<h2>파일 업로드 테스트</h2>
	<!-- 파일 전송을 위해 form의 enctype을 multipart/form-data 형태로 변경해야함 -->
	<form action="uploadInfo.jsp" method="post" name="fileUploadForm" enctype="multipart/form-data">
		ID: <input type="text" name="id"><br>
		파일: <input type="file" name="uploadFile"><br>
		<input type="submit" value="전 송">
	</form>

</body>
</html>
