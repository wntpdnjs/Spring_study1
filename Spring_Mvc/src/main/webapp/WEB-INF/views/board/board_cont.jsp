<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 내용보기</title>
</head>
<body>
<table border="1">
<tr>
<th colspan="2">스프링 MVC 내용보기</th>
</tr>
<tr>
<th>제목</th> <td>${bc.title }</td>
</tr>
<tr>
<th>내용</th> <td>${bc.content }</td>
</tr>
<tr>
<th>조회수</th> <td>${bc.viewcnt }</td>
</tr>
<tr>
<th colspan="2">
  <input type="button" value="수정" onclick="location='/board/board_cont?bno=${bc.bno}&page=${page }&state=edit';"/>
  <input type="button" value="삭제" onclick="location='/board/board_del?bno=${bc.bno}&page=${page }';"/>
  <input type="button" value="목록" onclick="location='/board/board_list?bno=${bc.bno}&page=${page }';"/>
  </th>
  </tr>
</table>
</body>
</html>