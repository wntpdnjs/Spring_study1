<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아작스 댓글 연습</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- JSP 주석문 기호, jQuery CDN 방식 --%>
<style type="text/css">
  #modDiv {
    width: 300px;
    height: 100px;
    background-color: gray;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -50px;
    margin-left: -150px;
    padding: 10px;
    z-index: 1000;
  } /* 댓글 수정폼 UI */
</style>
</head>
<body>
 <%-- 댓글 수정 화면 --%>
 <div id="modDiv" style="display:none;">
  <%-- display:none; CSS 속성으로 댓글 수정 화면을 안 나오게 함. --%>
  <div class="modal-title"></div> <%-- 댓글 번호 --%>
  <div>
    <textarea rows="3" cols="30" id="replytext"></textarea>
  </div>
  <div>
    <button type="button" id="replyModBtn">댓글 수정</button>
    <button type="button" id="replyDelBtn">댓글 삭제</button>
    <button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
  </div>
 </div>

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
  <button id="replyAddBtn">댓글 등록</button>
 </div>

 <br/><hr/><br/>

 <%-- 댓글 목록 --%>
 <ul id="replies"></ul>
 <script type="text/javascript">
   var bno = 11; // 게시판 번호, bno는 JavaScript 변수명

   getAllList(); // 댓글 목록 함수
   function getAllList() {
     $.getJSON("/replies/all/" + bno, function(data) {
       var str = "";
       $(data).each(function() { // each() jQuery 함수는 댓글 목록을 반복한다.
         str += "<li data-rno='" + this.rno + "' class='replyLi'>"
             + this.rno + " : <span class='com' style='color:blue;font-weight:bold;'>"
             + this.replytext + "</span>"
             + " <button>댓글 수정</button></li><br/>";
       });
       $('#replies').html(str); // jQuery html() 함수로 문자와 태그를 함께 변경 적용
     });
   } // getAllList()

   // 댓글 추가
   $("#replyAddBtn").on("click", function() {
     var replyer = $("#newReplyWriter").val();
     var replytext = $("#newReplyText").val();
     $.ajax({
       type: 'post',
       url: '/replies/addReply',
       headers: {
         "Content-Type": "application/json",
         "X-HTTP-Method-Override": "POST"
       },
       dataType: 'text',
       data: JSON.stringify({
         bno: bno,
         replyer: replyer,
         replytext: replytext
       }),
       success: function(result) {
         if (result == 'SUCCESS') {
           alert("등록 되었습니다.");
           getAllList(); // 댓글 목록 함수
         }
       }
     });
   });

   // 댓글 수정 화면
   $("#replies").on("click", ".replyLi button", function() {
     var reply = $(this).parent();
     var rno = reply.attr("data-rno"); // 댓글번호
     var replytext = reply.children('.com').text(); // 댓글 내용 텍스트 가져오기
     $(".modal-title").html(rno); // 댓글 번호를 표시
     $("#replytext").val(replytext); // 댓글 내용을 textarea 입력박스에 표시
     $("#modDiv").show("slow"); // 댓글 수정화면을 천천히 보이기
   });

   // 댓글 수정 화면 닫기
   function modDivClose() {
     $("#modDiv").hide('slow');
   }
   
   // 댓글 수정 완료
   $("#replyModBtn").on("click", function() {
     var rno = $(".modal-title").html();
     var replytext = $("#replytext").val();
     
     $.ajax({
       type: 'put',
       url: '/replies/editReply/' + rno,
       headers: {
         "Content-Type": "application/json",
         "X-HTTP-Method-Override": "PUT"
       },
       data: JSON.stringify({ replytext: replytext }),
       dataType: 'text',
       success: function(result) {
         if (result == 'SUCCESS') {
           alert("수정 되었습니다.");
           $("#modDiv").hide("slow");
           getAllList();
         }
       }
     });
   });
   
   // 댓글 삭제
   $("#replyDelBtn").on("click", function() {
     var rno = $(".modal-title").html();
     $.ajax({
       type: 'delete',
       url: '/replies/delReply/' + rno, // 삭제 매핑 주소
       headers: {
         "Content-Type": "application/json",
         "X-HTTP-Method-Override": "DELETE"
       },
       dataType: 'text',
       success: function(result) {
         if (result == 'SUCCESS') {
           alert("삭제 되었습니다.");
           $("#modDiv").hide("slow");
           getAllList();
         }
       }
     });
   });
 </script>
</body>
</html>
