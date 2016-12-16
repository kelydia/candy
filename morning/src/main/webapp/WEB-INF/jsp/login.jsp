<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/common_tag.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>彩票管理系统-登录</title>

</head>
<body style="text-align:center;background-image: URL(<c:url value='/images/index.jpg'/>)" onload=cen()>
<div id="windiv" name="windiv" style="margin:0 auto; width:400px;">
    <div class="easyui-panel" title="后台管理系统" style="width: 400px; padding: 30px 60px">
        <form id="login" method="post" class="easyui-form"
              action="<c:url value='/login'/>" data-options="novalidate:true">
            <div style="margin-bottom: 20px">
                <div>用 户 名:</div>
                <input id="username" name="username" class="easyui-textbox"
                       data-options="prompt:'Enter a username...',required:true"
                       style="width: 100%; height: 32px">
            </div>
            <div style="margin-bottom: 20px">
                <div>密 码:</div>
                <input id="password" name="password" class="easyui-passwordbox"
                       data-options="prompt:'Enter a password...',required:true"
                       style="width: 100%; height: 32px">
            </div>
            <div>
                <a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok"
                   onclick="submitForm()">登录</a> <a href="javascript:void(0)" icon="icon-cancel"
                                                    class="easyui-linkbutton" onclick="clearForm()">取消</a>
            </div>
        </form>
    </div>
</div>
</body>
<script>


    //登录窗口居中
    function cen() {
        //判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
        if (window.top != null && window.top.document.URL != document.URL) {
            window.top.location = document.URL;
        }

        var divname = document.all("windiv");
        //居中高度等于页面内容高度减去DIV的高度 除以2
        var topvalue = (document.body.clientHeight - divname.clientHeight) / 2;
        divname.style.marginTop = topvalue;
    }
    //页面大小发生变化时触发
    window.onresize = cen;

    //回车事件
    $(function () {
        $('#username').textbox('textbox').keydown(function (e) {
            if (e.keyCode == 13) {
                $('#password').textbox('textbox').focus();
            }
        });
        $('#password').textbox('textbox').keydown(function (e) {
            if (e.keyCode == 13) {
                submitForm();
            }
        });
    })
    //登录按钮
    function submitForm() {
        $('#login').form('submit', {
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var jsonResult = JSON.parse(data);
                if (jsonResult.ret == 'ok') {
                    location.replace('home');
                } else {
                    $.messager.alert("操作提示", jsonResult.msg);
                }
            }
        });
    }
    //取消按钮
    function clearForm() {
        $('#login').form('clear');
    }
</script>
</html>
