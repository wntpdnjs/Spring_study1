<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- JSTL c코어 태그립 추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 mvc게시판 목록 </title>
</head>
<body>
<table border="1">
 <tr>
  <th colspan="5">스프링 MVC 게시판 목록</th>
 </tr>
 <tr>
  <td colspan="5" align="right">총게시물수: <b>${totalCount}</b> 개</td>
 </tr>
 <tr>
  <th>번호</th> <th>제목</th> <th>글쓴이</th> <th>조회수</th> <th>등록날짜</th>
 </tr>
 
 <c:if test="${!empty blist}"> <%-- c:if는 jstl 조건문이다. --%>
  <c:forEach var="b" items="${blist}"> <%-- c:forEach는 JSTL 반복문이다. --%>
   <tr>
    <th>${b.bno}</th>
    <th>
    <a href="/board/board_cont?bno=${b.bno}&page=${page}&state=cont">${b.title}</a>
    <%--board_cont?bno=번호&page=쪽번호&state=cont get방식으로 3개의 파라미터값이 전달된다. --%>
    </th>
    
    <th>${b.writer}</th>
    <th>${b.viewcnt}</th>
    <th>${b.regdate}</th>
   </tr>
  </c:forEach>
 </c:if>
 
 <c:if test="${empty blist}">
  <tr>
   <th colspan="5">게시판 목록이 없습니다!</th>
  </tr>
 </c:if>
 
 <%--페이징 쪽번호 출력부분 --%>
  <tr>
   <th colspan="5">
   <%-- begin --%>
   <c:if test="${page <= 1}">
   [이전]&nbsp;
  </c:if>
  <c:if test="${page > 1}">
   <a href="/board/board_list?page=${page-1}">[이전]</a>&nbsp;
  </c:if>
  
  <%--쪽번호 출력 --%>
  <c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
    <c:if test="${a == page}"><%--현재 쪽번호가 선택된 경우 --%>
     <${a}>
    </c:if>
    <c:if test="${a != page}"><%--현재 쪽번호가 선택 안된 경우 --%>
    <a href="/board/board_list?page=${a}">[${a}]</a>&nbsp;
    </c:if>
  </c:forEach>
  
  <c:if test="${page >= maxpage}">
   [다음]
  </c:if>
  <c:if test="${page < maxpage}">
  <a href="/board/board_list?page=${page+1}">[다음]</a>
  </c:if>
   <%-- end --%>
   </th>
  </tr>
  
 <tr>
  <td colspan="5" align="right">
   <input type="button" value="글쓰기" onclick="location='/board/board_write?page=${page}';" />
   <%--board_write?page=쪽번호를 get방식으로 전달한다. --%>
  </td>
 </tr>
</table>
</body>
