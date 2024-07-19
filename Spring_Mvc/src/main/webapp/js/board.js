/**
 * board.js
 */

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