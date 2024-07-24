function test_check() {
    if ($.trim($('#test_title').val()) == '') {
        alert('제목을 입력하세용');
        $('#test_title').val('').focus();
        return false;
    }
    if ($.trim($('#test_cont').val()) == '') {
        alert('내용을 입력하세용');
        $('#test_cont').val('').focus();
        return false;
    }
    return true;
}
