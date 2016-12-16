<%@ page import="com.cjgod.candy.morning.common.auth.UserInfoCommon" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/common_tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>彩票管理系统-主页</title>

</head>

<body class="easyui-layout">
<div region="north" class="north" style="height: 85px">

    <div style="float: left; height: 100%; width: 49%;">
        <img alt="" src="<c:url value='/images/logo.png'/>">

    </div>
    <div style="float: left; height: 100%; width: 50%; text-align: right;">
        <p>欢迎您！${username}
        </p>
        <p>
            <a href="javascript:void(0)" class="easyui-linkbutton"
               onclick="$('#w').window('open')">修改密码</a> <a href="javascript:void(0)"
                                                            class="easyui-linkbutton" onclick="logout()">退出系统</a>
        </p>

    </div>
</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页">
            <div style="width: 600px; margin: 0 auto; padding-top: 50px">
                <table class="easyui-datagrid" style="width: 600px; height: 250px;">
                    <thead>
                    <tr>
                        <th data-options="field:'itemid',width:200">登录时间</th>
                        <th data-options="field:'productid',width:190">登录IP</th>
                        <th data-options="field:'listprice',width:205">IP地址</th>
                    </tr>
                    </thead>
                </table>
                <br/> <br/> <br/>
                <div class="easyui-panel" style="width: 600px; height: 150px;">
                    <br/> <br/>
                    <h3 style="color: red; padding-left: 10px; padding-right: 10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎进入彩票销售服务管理软件管理后台，您是本站管理员，在这里您可以修改本站您拥有权限的所有重要数据，请谨慎操作！</h3>
                </div>
            </div>


        </div>
    </div>
</div>
<div region="west" class="west"  id="current-time" title="&nbsp;"
     style="width: 180px; padding: 10px;font-size: 5px">
    <ul id="tree"></ul>
</div>

<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
    <div name="close">关闭</div>
    <div name="Other">关闭其他</div>
    <div name="All">关闭所有</div>
</div>

<div id="w" class="easyui-window" title="密码修改"
     data-options="modal:true,resizable:false,collapsible:false,minimizable:false,maximizable:false,closed:true,iconCls:'icon-edit'"
     style="width:350px;height:200px;padding:10px;">
    <form id="update" method="post" class="easyui-form"
          action="<c:url value='/update-password'/>" data-options="novalidate:true">
        <p>原密码: &nbsp;&nbsp;<input id="oldPassword" name="oldPassword" class="easyui-passwordbox"
                                   data-options="prompt:'Enter old password...',required:true"
                                   style="width: 80%; height: 32px"></p>
        <p>新密码: &nbsp;&nbsp;<input id="newPassword" name="newPassword" class="easyui-passwordbox"
                                   data-options="prompt:'Enter new password...',required:true"
                                   style="width: 80%; height: 32px"></p>
        <div style="padding:5px;text-align:center;">
            <a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok" onclick="updatePassword()">确认</a>
        </div>
    </form>
</div>

</body>


<script type="text/javascript">
    setInterval(function() {
        var now = (new Date()).toLocaleTimeString();
        $('#current-time').panel({ title: now});
    }, 1000);

    //实例化树形菜单
    $("#tree").tree({
        url: 'tree',
        lines: true,
        onClick: function (node) {
            if (node.attributes && node.attributes.url != null && node.attributes.url != "") {
                Open(node.text, node.attributes.url);
            }
        },
        onSelect:function(node){
            $(this).tree('expand',node.target);
        }
    });
    //在右边center区域打开菜单，新增tab
    function Open(text, url) {
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
        } else {
            $('#tabs')
                    .tabs(
                            'add',
                            {
                                title: text,
                                closable: true,
                                tools: [{
                                    iconCls: 'icon-mini-refresh',
                                    handler: function() {
                                        $('#tabs').tabs("select", $(this).parent().parent().first().first().text());
                                        var tab = $('#tabs').tabs('getSelected');
                                        $('#tabs').tabs('update', {
                                            tab: tab,
                                            options: {
                                                title: text,
                                                content: "<iframe width='100%' height='100%' frameborder='0'  src='"
                                                + url
                                                + "' style='width:100%;height:100%;'></iframe>"
                                            }
                                        });
                                    }
                                }],
                                content: "<iframe width='100%' height='100%' frameborder='0'  src='"
                                + url
                                + "' style='width:100%;height:100%;'></iframe>"
                            });
        }
    }

    //绑定tabs的右键菜单
    $("#tabs").tabs({
        onContextMenu: function (e, title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            }).data("tabTitle", title);
        }
    });

    //实例化menu的onClick事件
    $("#tabsMenu").menu({
        onClick: function (item) {
            CloseTab(this, item.name);
        }
    });

    //几个关闭事件的实现
    function CloseTab(menu, type) {
        var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#tabs");

        if (type === "close") {
            tabs.tabs("close", curTabTitle);
            return;
        }

        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];

        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "Other") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "All") {
                closeTabsTitle.push(opt.title);
            }
        });

        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
    }
    function logout() {
        $.messager.confirm('操作提示', '确定注销?', function (r) {
            if (r) {
                location.replace('logout');
            }
        });
    }

    function updatePassword() {
        $('#update').form('submit', {
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var jsonResult = JSON.parse(data);
                if (jsonResult.ret == 'ok') {
                    $.messager.alert("操作提示", '操作成功！');
                    $('#w').window('close');
                } else {
                    $.messager.alert("操作提示", jsonResult.msg);
                }
            }
        });
    }
</script>
</html>
