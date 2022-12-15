var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-comment-save').on('click', function () {
             _this.commentSave();
        });

        document.querySelectorAll('#btn-comment-update').forEach(function (item) {
             item.addEventListener('click',function () {
                 const form=this.closest('form');
                 _this.commentUpdate(form);
             })

        })
    },
    commentUpdate : function (form) {
         const data = {
             id: form.querySelector('#id').value,
             postId: form.querySelector('#postId').value,
             comment: form.querySelector('#comment-content').value,
         }
         if (!data.comment || data.comment.trim() === "") {
             alert("공백 또는 입력하지 않은 부분이 있습니다.");
             return false;
         }
         const con_check = confirm("수정하시겠습니까?");
         if (con_check === true) {
             $.ajax({
                 type: 'PUT',
                 url: '/posts/detail/' + data.postId + '/comments/' + data.id,
                 dataType: 'JSON',
                 contentType: 'application/json; charset=utf-8',
                 data: JSON.stringify(data)
             }).done(function () {
                 window.location.reload();
             }).fail(function (error) {
                 alert(JSON.stringify(error));
             });
         }
    },
    commentDelete : function (postId, commentId) {
         const con_check = confirm("삭제하시겠습니까?");
         if(con_check===true){
             $.ajax({
                 type: 'DELETE',
                 url: '/posts/detail/' + postId + '/comments/' + commentId,
                 dataType: 'JSON',
             }).done(function () {
                 alert('댓글이 삭제되었습니다.');
                 window.location.reload();
             }).fail(function (error) {
                 alert(JSON.stringify(error));
             });
         }
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    commentSave : function () {
         const data = {
             postId : $('#postId').val(),
             comment : $('#comment').val()
         }
         if (!data.comment || data.comment.trim() === "") {
             alert("공백 또는 입력하지 않은 부분이 있습니다.");
             return false;
         } else {
             $.ajax({
                 type: 'POST',
                 url: '/posts/detail/' + data.postId + '/comments',
                 dataType: 'JSON',
                 contentType: 'application/json; charset=utf-8',
                 data: JSON.stringify(data)
             }).done(function () {
                 alert('댓글이 등록되었습니다.');
                 window.location.reload();
             }).fail(function (error) {
                 alert(JSON.stringify(error));
             });
         }
    }

};

main.init();