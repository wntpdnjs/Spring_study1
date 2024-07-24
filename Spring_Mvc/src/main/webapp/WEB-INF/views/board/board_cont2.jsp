<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC게시판 내용보기 </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- jsp주석문기호, jQuery CDN방식 --%>
<style type="text/css">
  #modDiv{
    width:300px; height:100px;
    background-color:gray;
    position: absolute;
    top:50%;
    left:50%;
    margin-top: -50px;
    margin-left:-150px;
    padding:10px;
    z-index:1000; /* position 속성이 absolute나 fixed로 설정된 곳에서 사용한다. 이 속성은 요소가 겹쳐지는 순서를 제어할 수 있다.
    숫자값이 큰것이 앞에 나온다. */
  } /* 댓글 수정폼 ui */
  

</style>
</head>
<body>
<table border="1">
 <tr>
  <th colspan="2">스프링 MVC 게시판 내용보기</th>
 </tr>
 <tr>
  <th>제목</th> <td>${bc.title}</td>
 </tr>
 <tr>
  <th>내용</th> <td>${bc.content}</td>
 </tr>
 <tr>
  <th>조회수</th> <td>${bc.viewcnt}</td>
 </tr>
 <tr>
  <th colspan="2">
   <input type="button" value="수정" onclick="location='/board/board_cont?bno=${bc.bno}&page=${page}&state=edit';" />
   <input type="button" value="삭제" onclick="location='/board/board_del?bno=${bc.bno}&page=${page}';" />
   <input type="button" value="목록" onclick="location='/board/board_list?page=${page}';" />
  </th>
 </tr>
</table>

<br/><hr/><br/>

<%-- 댓글수정 화면 --%>
 <div id="modDiv" style="display:none;"> <%--display:none; css속성으로 댓글수정화면을 안나오게 함. --%>
  <div class="modal-title"></div> <%--댓글번호--%>
  <div>
   <textarea rows="3" cols="30" id="replytext"></textarea>
  </div> 
  <div>
   <button type="button" id="replyModBtn">댓글수정</button>
   <button type="button" id="replyDelBtn">댓글삭제</button>
   <button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
  </div>
 </div>
 
 
 [댓글 개수 : <span style="color:red;background:yellow; font-size:20px; border-radius:5px; padding:3px; box-shadow:3px 3px 3px gray;">${bc.replycnt }개</span>]
 <h2>아작스(Ajax) 댓글</h2>
 <div>
  <div>
   댓글 작성자 :<input type="text" name="replyer" id="newReplyWriter" />
  </div>
  <br/>
  <div>
   댓글 내용 : <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
  </div>
  <br/>
  <button id="replyAddBtn">댓글등록</button>
 </div>
 
 <br/><hr/><br/>
 
 <%--댓글 목록 --%>
 <ul id="replies"></ul>
 <script type="text/javascript">
   $bno = ${bc.bno};//게시판 번호, $bno는 jQuery변수명, ${bc.bno}는 jsp에서 사용하는 EL(표현언어)이다.
   
   getAllList();//댓글목록 함수
   function getAllList(){
      $.getJSON("/replies/all/"+$bno, function(data){
         $str="";
         $(data).each(function(){//each() jQuery함수는 댓글목록을 반복한다.
            $str += "<li data-rno='"+this.rno+"' class='replyLi'>"
            + this.rno +" : <span class='com' style='color:blue;font-weight:bold;'>"+this.replytext+"</span>"
            + " <button>댓글수정</button></li><br/>";
         });
         $('#replies').html($str);//jQuery html()함수로 문자와 태그를 함께 변경적용
      });      
   }//getAllList()
   
   //댓글 추가
   $('#replyAddBtn').on('click',function(){
      $replyer = $('#newReplyWriter').val();//댓글작성자
      $replytext = $('#newReplyText').val();//댓글내용
      
      $.ajax({
        type:'post',//메서드 전송방식
        url:"/replies/addReply",//아작스 서버매핑주소
        headers : {
           "Content-Type" :"application/json",
           "X-HTTP-Method-Override":"POST"
        },
        dataType:'text',
        data:JSON.stringify({
           bno:$bno, //키:값의 JSON데이터
           replyer:$replyer,
           replytext:$replytext
        }),
        success:function(data){
           if(data == 'SUCCESS'){
              alert('댓글이 등록되었습니다!');
              location.reload();//새로고침=>단축키는 F5
              getAllList();
           }
        }
      });
   });
   
   //댓글수정화면
   $('#replies').on('click','.replyLi button',function(){
      var reply = $(this).parent();//button태그의 부모요소 parent()는 li태그이다.
      var rno = reply.attr('data-rno');//data-rno속성값 댓글번호를 가져옴.
      var replytext = reply.children('.com').text();//댓글내용
      
      $('.modal-title').html(rno);//댓글번호를 표시
      $('#replytext').val(replytext);//댓글내용을 textarea입력박스에 표시
      $('#modDiv').show('slow');//댓글수정화면을 천천히 보이게 하기
   });
   
   //댓글수정 화면닫기
   function modDivClose(){
     $('#modDiv').hide('slow');//댓글수정화면을 천천히 닫기 
   }
   
   //댓글수정 완료
   $('#replyModBtn').on('click',function(){
     $rno = $('.modal-title').html();//댓글번호
     $replytext = $('#replytext').val();//수정할 댓글내용
     
     $.ajax({
       type:'put',
       url:'/replies/editReply/'+$rno,
       headers: {
          "Content-Type":"application/json",
          "X-HTTP-Method-Override":"PUT"
       },
       data:JSON.stringify({
          replytext:$replytext
       }),
       dataType:'text',
       success: function(result){
          if(result == 'SUCCESS'){
             alert('댓글이 수정되었습니다!');
             $('#modDiv').hide('slow');//댓글 수정화면 닫기
             getAllList();//댓글목록 함수호출
          }
       }
     });
   });
   
   //댓글삭제
   $('#replyDelBtn').on('click',function(){
     var rno = $('.modal-title').html();//댓글번호
     
     $.ajax({
       type:'delete',
       url:'/replies/delReply/'+rno,
       headers:{
          "Content-Type":"application/json",
          "X-HTTP-Method-Override":"DELETE"
       },
       dataType:'text',
       success: function(result){
          if(result == 'SUCCESS'){
             alert('댓글이 삭제되었습니다!');
             $('#modDiv').hide('slow');
             location.reload();
             getAllList();
          }
       }
     });
   });
 </script>
</body>
</html>