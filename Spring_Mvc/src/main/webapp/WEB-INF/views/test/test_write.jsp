<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>태스트 글쓰기 </title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <%-- jsp주석문기호, jQuery CDN방식 --%>
 <script type="text/javascript" src=".js/jquery-3.7.1.min.js">
 </script>
 <script src=".js/test.js"> </script>
</head>

<body>
  <h2>제목과 내용 입력 폼</h2>
  <form method="post" action="test_ok" onsubmit="return test_check();" >
>
   
   <label for="test_title">글제목</label>
   
   <input name="test_title" id="title" size="36" placeholder="글제목 입력" />
   <br/>
   
   <label for="test_cont">글내용</label>
   <textarea name="test_cont" id="test_cont" rows="10" cols="34" placeholder="글내용 입력"></textarea>
   <hr/>
   
   <button >저장</button> <!-- 버튼에서 타입속성 생략하면 기본값 서브밋 -->
 <button type="reset" onclick="$('#test_title').focus();"> 취소 </button>
  </form>
</body>
</html>

