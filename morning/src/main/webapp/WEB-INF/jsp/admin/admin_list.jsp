<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../common/common_tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>彩票管理系统-管理员</title>

</head>

<%--<body>--%>
<body style="margin: 0 auto;">

<table id="list_data" class="easyui-datagrid" toolbar="#tb">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',hidden:true"></th>
        <th data-options="field:'serialNumber',width:120,align:'center',halign:'center'">编号</th>
        <th data-options="field:'username',width:120,align:'center',halign:'center'">用户名</th>
        <th data-options="field:'roleName',width:100,align:'center',halign:'center'">角色</th>
        <th data-options="field:'realName',width:120,align:'center',halign:'center'">姓名</th>
        <th data-options="field:'tel',width:150,align:'center',halign:'center'">电话</th>
        <th data-options="field:'email',width:150,align:'center',halign:'center'">邮箱</th>
        <th data-options="field:'statusName',width:100,align:'center',halign:'center'">是否启用</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
           onclick="adminAdd()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="adminUpdate()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
           onclick="deletes()">删除</a>
    </div>
    <div>
        用户名:
        <input class="easyui-textbox" style="width:100px" id="queryUsername" name="queryUsername">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs()">查询</a>
    </div>
</div>


<div id="w-admin" class="easyui-window" title="管理员新增"
     data-options="modal:true,resizable:false,collapsible:false,minimizable:false,maximizable:false,closed:true,iconCls:'icon-edit'"
     style="width:500px;height:280px;padding:10px;">
    <form id="f-admin" method="post" class="easyui-form" data-options="novalidate:true">
        <input type="hidden" id="id" name="id"/>

        <div style="padding-top: 10px">

            户名: &nbsp;&nbsp;<input class="easyui-textbox"
                                   id="username"
                                   name="username"
                                   data-options="required:true"
                                   style="width: 170px; height: 32px">
            &nbsp;&nbsp;密码: &nbsp;&nbsp;<input class="easyui-passwordbox"
                                               id="password"
                                               name="password"
                                               data-options="required:true"
                                               style="width: 170px; height: 32px">
        </div>

        <div style="padding-top: 10px">
            编号: &nbsp;&nbsp;<input class="easyui-numberbox"
                                   id="serialNumber"
                                   name="serialNumber"
                                   data-options="required:true"
                                   style="width: 170px; height: 32px">
            &nbsp;&nbsp;角色: &nbsp;&nbsp;<input class="easyui-combobox"
                                               id="roleId"
                                               name="roleId"
                                               url="admin/roles"
                                               method="get"
                                               valueField="value"
                                               textField="label"
                                               data-options="editable:false,required:true"
                                               style="width: 170px; height: 32px">
        </div>

        <div style="padding-top: 10px">
            姓名: &nbsp;&nbsp;<input class="easyui-textbox"
                                   id="realName"
                                   name="realName"
                                   data-options="required:true"
                                   style="width: 170px; height: 32px">
            &nbsp;&nbsp;电话: &nbsp;&nbsp;<input class="easyui-textbox"
                                               id="tel"
                                               name="tel"
                                               data-options="required:true"
                                               style="width: 170px; height: 32px">
        </div>

        <div style="padding-top: 10px">
            邮箱: &nbsp;&nbsp;<input class="easyui-textbox"
                                   id="email"
                                   name="email"
                                   data-options="required:true"
                                   style="width: 170px; height: 32px">
            &nbsp;&nbsp;状态: &nbsp;&nbsp;<select class="easyui-combobox"
                                                id="status"
                                                name="status"
                                                data-options="editable:false,required:true"
                                                style="width: 170px; height: 32px">
            <option value="ENABLED">正常</option>
            <option value="DISABLE">禁用</option>
        </select>
        </div>
        <div style="padding-top:15px;text-align:center;">
            <a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok" onclick="saveOrUpdate()">确认</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-cancel" id="cancel" name="cancel"
               onclick="clearForm()">取消</a>
        </div>
    </form>
</div>

</body>

<script>
    //判断是否进行修改,以便检索是否存在的提示
    var info;

    $(function () {
        //检索用户名是否重复
        $("#username").textbox({
            onChange: function (n, o) {
                $.ajax({
                    url: 'admin/repeat-name',
                    type: 'get',
                    async: true,
                    dataType: 'json',
                    data: {'username': n},
                    success: function (data) {
                        if (data.ret != 'ok' && info == 'add') {
                            $.messager.alert("操作提示", '该帐号已存在!');
                            $("#username").textbox('setValue', o);
                        }
                    }
                });
            }
        });
        //初始化分页
        $('#list_data').datagrid({
            striped: true,
            border: false,
            fit: true,
            url: 'admin/pages',
            method: 'get',
            singleSelect: false,//是否单选
            checkOnSelect: true,
            selectOnCheck: true,
            pagination: true,//分页控件
            pageList: [10, 20, 30]
        });
    })

    //查询
    function searchs() {
        //获取list的查询集合
        var queryParams = $('#list_data').datagrid('options').queryParams;
        //将查询条件赋予列表
        queryParams.username = $("#queryUsername").textbox('getValue');
        $('#list_data').datagrid('options').queryParams = queryParams;
        $("#list_data").datagrid('reload');
    }
    //删除用户
    function deletes() {
        var rows = $('#list_data').datagrid('getSelections');
        if (rows.length > 0) {
            $.messager.confirm('警告', '确定删除?', function (r) {
                if (r) {
                    var ids = [];
                    for (var i = 0; i < rows.length; i++) {
                        ids.push({"id": rows[i].id});
                    }
                    $.ajax({
                        url: 'admin/delete',
                        type: 'post',
                        async: true,
                        dataType: 'json',
                        data: {'delIdArray': JSON.stringify(ids)},
                        success: function (data) {
                            if (data.ret == 'ok') {
                                $.messager.alert("操作提示", '操作成功！');
                            } else {
                                $.messager.alert("操作提示", data.msg);
                            }
                            $('#list_data').datagrid('reload');
                        }
                    });
                }
            });
        } else {
            $.messager.alert("操作提示", "请选择！");
        }
    }
    //打开新增用户
    function adminAdd() {
        $('#w-admin').window({title: "新增管理员"});
        $('#f-admin').form('clear');
        $('#w-admin').window('open');
        $("#cancel").show();
        $("#username").textbox('enable');
        $('#f-admin').form('disableValidation');
        info = 'add';
    }
    //打开修改用户
    function adminUpdate() {
        $('#w-admin').window({title: "修改管理员"});
        $('#f-admin').form('clear');

        var rows = $('#list_data').datagrid('getSelections');
        if (rows.length > 0 && rows.length == 1) {
            $.ajax({
                url: 'admin/info',
                type: 'get',
                async: true,
                data: {'id': rows[0].id},
                success: function (data) {
                    var jsonResult = JSON.parse(data);
                    if (jsonResult.ret == 'ok') {
                        $("#w-admin").window('open');
                        $('#f-admin').form('disableValidation');
                        $("#cancel").hide();
                        $("#id").val(jsonResult.content.id);
                        $("#username").textbox('disable');
                        $("#username").textbox('setValue', jsonResult.content.username);
                        $("#serialNumber").textbox('setValue', jsonResult.content.serialNumber);
                        $("#tel").textbox('setValue', jsonResult.content.tel);
                        $("#password").textbox('setValue', '●●●●●●');
                        $("#email").textbox('setValue', jsonResult.content.email);
                        $("#realName").textbox('setValue', jsonResult.content.realName);
                        $('#status').combobox('setValue', jsonResult.content.status);
                        $('#roleId').combobox('setValue', jsonResult.content.roleId);
                        info = 'update';
                    } else {
                        $.messager.alert("操作提示", jsonResult.msg);
                        $('#w-admin').window('close');
                    }
                }
            });

        } else if (rows.length > 1) {
            $.messager.alert("操作提示", "请选择一条数据进行修改！");
        }
        else {
            $.messager.alert("操作提示", "请选择！");
        }
    }
    //保存用户信息
    function saveOrUpdate() {
        $('#f-admin').form('submit', {
            url: 'admin/save',
            type: 'post',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var jsonResult = JSON.parse(data);
                if (jsonResult.ret == 'ok') {
                    $.messager.alert("操作提示", "操作成功!");
                    $('#w-admin').window('close');
                    $('#list_data').datagrid('reload');
                } else {
                    $.messager.alert("操作提示", jsonResult.msg);
                }
            }
        });
    }
    //清空新增用户信息
    function clearForm() {
        $('#f-admin').form('clear');
    }

</script>
</html>
