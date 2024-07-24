<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아작스 파일 첨부 비동기식</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
$(document).ready(function() {
    $("#uploadBtn").on("click", function(e) {
        e.preventDefault(); // 폼 기본 제출 막기
        var formData = new FormData();
        var inputFile = $("input[name='uploadFile']");
        var files = inputFile[0].files;

        for (var i = 0; i < files.length; i++) {
            formData.append("uploadFile", files[i]);
        }

        $.ajax({
            url: '/uploadAjaxAction',
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            success: function(result) {
                alert("Uploaded ok");
            },
            error: function(xhr, status, error) {
                console.error("Upload failed:", status, error);
                alert("Upload failed.");
            }
        });
    });
});
</script>
</head>
<body>
<h1>Upload with Ajax</h1>
<input type='file' name='uploadFile' multiple>
<hr />
<button id='uploadBtn' type="button">Upload</button> 
</body>
</html>
