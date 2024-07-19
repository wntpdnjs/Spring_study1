<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 글쓰기 </title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <%-- jsp주석문기호, jQuery CDN방식 --%>
 <script type="text/javascript">
  function check(){//function 키워드로 check()함수를 정의
     
     $writer = $.trim($('#writer').val());//$는 jQuery란 뜻이다. $.trim()함수는 양쪽공백을 제거한다. val()함수는 입력박스 입력값을
     //가져온다.
     if($writer.length == 0){ //length속성은 문자 길이를 반환한다.
        alert('글쓴이를 입력하세요!');
         $('#writer').val('');//val('')의미는 입력필드를 초기화
         $("#writer").focus();//입력필드로 포커스 이동
         return false;
     }
     
     if($.trim($('#title').val()) == ''){
        //==는 같다 연산
        alert('글제목을 입력하세요!');
        $('#title').val('').focus();
        return false;
     }
     
     if($.trim($('#content').val()).length == 0){
        alert('글내용을 입력하세요!');
        $('#content').val('').focus();
        return false;
     }
  }
 </script>
</head>
<body>
  <h2>스프링 MVC게시판 입력</h2>
  <form method="post" action="board_write_ok" onsubmit="return check();" >
   <label for="writer">글쓴이</label>
   <input type="text" name="writer" id="writer" size="14" placeholder="글쓴이 입력" />
   <br/>
   
   <label for="title">글제목</label>
   <input type="text" name="title" id="title" size="36" placeholder="글제목 입력" />
   <br/>
   
   <label for="content">글내용</label>
   <textarea name="content" id="content" rows="10" cols="34" placeholder="글내용 입력"></textarea>
   <hr/>
   
   <button type="submit">저장</button>
   <button type="reset" onclick="$('#writer').focus();">취소</button>
   <input type ="button" value="목록" onclick="location='/board/board_list?page=${page}';"/>
  </form>
</body>
</html>

