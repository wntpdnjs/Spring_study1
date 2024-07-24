<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동기식(화면전환 있다) 파일첨부 자료실 실습</title>
</head>
<body>
<h1>동기식(화면전환 있다) 파일첨부 자료실 실습</h1>
<form method="post" action="uploadFormAction" enctype="multipart/form-data">
<%--
파일 첨부하는 기능을 만들 때 주의사항
1. 파일 첨부기능을 만들려면 반드시 method="post" 방식으로 해야 한다.
2. form 태그 내에 enctype="multipart/form-data"를 지정해야 한다.
--%>
파일 첨부 : <input type="file" name="uploadFile" multiple/>
<%-- multiple 기능을 이용하면 한 개 또는 다중 파일 동시 업로드 가능 --%>
 
<input type="submit" value="파일업로드" />
</form>
</body>
</html>
