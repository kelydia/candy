<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../common/common_tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>彩票管理系统-提现明细</title>

</head>

<%--<body>--%>
<body style="margin: 0 auto;">

<div id="tb" style="padding:5px;height:auto">
    <div>
        用户名:
        <input class="easyui-textbox" style="width:100px" id="queryUserName" name="queryUserName">
        彩店编码:
        <input class="easyui-textbox" style="width:100px" id="querySiteId" name="querySiteId">
        提现状态：
        <input id='queryDrawStatus' name="queryDrawStatus" class="easyui-combobox"
               url="capital/status"
               method="get"
               valueField="value"
               textField="label"
               value="ALL"
               data-options="editable:false">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs()">查询</a>
    </div>
</div>

<table id="dg" class="easyui-datagrid" toolbar="#tb"  style="width:800px;height:300px">
    <thead>
    <tr>
        <th data-options="field:'userName',width:120,align:'center',halign:'center'">用户名</th>
        <th data-options="field:'siteId',width:120,align:'center',halign:'center'">所属彩店</th>
        <th data-options="field:'amount',width:140,align:'center',halign:'center'">提现金额</th>
        <th data-options="field:'account',width:200,align:'center',halign:'center'">汇款账户</th>
        <th data-options="field:'drawStatus',width:100,align:'center',halign:'center'">状态</th>
        <th data-options="field:'applyForDate',width:145,align:'center',halign:'center'">申请时间</th>
        <th data-options="field:'acceptDate',width:145,align:'center',halign:'center'">受理时间</th>
    </tr>
    </thead>
</table>

</body>

<script>
    var count = 0;
    $(function () {
        if(count == 0){
            var queryParams = $('#dg').datagrid('options').queryParams;
            queryParams.drawStatus = $("#queryDrawStatus").textbox('getValue');
            $('#dg').datagrid('options').queryParams = queryParams;
        }
        //初始化分页
        loadData();
        count++;

    })

    function loadData(){
        $('#dg').datagrid({
            striped: true,
            border: false,
            fit:true,
            url: 'capital/withdraw_pages',
            method: 'get',
            singleSelect: true,
            fitcolumns:true,
            pagination: true,//分页控件
            pageList: [10, 20, 30]

        });
    }
    //查询
    function searchs() {
        //获取list的查询集合
        var queryParams = $('#dg').datagrid('options').queryParams;
        //将查询条件赋予列表
        queryParams.userName = $("#queryUserName").textbox('getValue');
        queryParams.siteId = $("#querySiteId").textbox('getValue');
        queryParams.drawStatus = $("#queryDrawStatus").textbox('getValue');
        $('#dg').datagrid('options').queryParams = queryParams;
        loadData();
    }
</script>

</html>