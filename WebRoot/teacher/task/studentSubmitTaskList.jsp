<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"  %>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Amaze UI Admin index Examples</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="../assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="../assets/css/amazeui.min.css" />
<link rel="stylesheet" href="../assets/css/admin.css">
</head>
<body>
	<div class="am-cf admin-main">
		<div class=" admin-content">
			<div class="daohang" style="positioin:absolute;top:0px;">
				<ul>
					<li><a class="am-btn am-btn-default am-radius am-btn-xs" href="4.html" target="rightFrame">首页</a></li>
				</ul>
			</div>
			
			<div class="admin-biaogelist"
				style="positioin:absolute;top:30px;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-users">学生作业批阅
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a>学生作业列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
					<ul>
						<li><input type="text"
							class="am-form-field am-input-sm am-input-xm" placeholder="关键词搜索" /></li>
						<li><button type="button"
								class="am-btn am-radius am-btn-xs am-btn-success"
								style="margin-top: -1px;">搜索</button></li>
					</ul>
				</div>
				<form class="am-form am-g">
					<table width="100%"
						class="am-table am-table-bordered am-table-radius am-table-striped">
						<thead>
							<tr class="am-success">
								<th class="table-check"><input type="checkbox" /></th>
								<th class="table-type">学号</th>
								<th class="table-author am-hide-sm-only">姓名</th>
								<th class="table-author am-hide-sm-only">作业名称</th>
								<th class="table-date am-hide-sm-only">截止时间</th>
								<th  class="table-set">状态</th>
								<th  class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request['classTaslDetail']" id="classTaslDetail">
									<tr>
									<td><input type="checkbox" name="choose" onClick="check()" value="<s:property value="#arrangedTask.tid"/>,<s:property value="#arrangedTask.cno"/>" /></td>
									<td><a href="ClassTaskDetail-querySubmitTaskDetail.action?sid=<s:property value="#classTaslDetail.id"/>"  target="_blank"><s:property value="#classTaslDetail.sno"/></a></td>
									<td><s:property value="#classTaslDetail.sname"/></td>
									<td><s:property value="#classTaslDetail.task_name"/></td>
									<td><s:property value="#classTaslDetail.end_time"/></td>
									<td><s:property value="#classTaslDetail.Tstate"/></td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">									
												<button
												class="am-btn am-btn-default am-btn-xs am-text-success am-round">
												<span class="am-icon-search" title="查看批阅详情"></span>
											</button>
											<button
												class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												data-am-modal="{target: '#my-popups'}" type="button" title="批阅作业">
												<span class="am-icon-pencil-square-o"></span>
											</button>
											</div>
										</div>
									</td>
								</tr>
							</s:iterator>	
						</tbody>
					</table>
					<div class="am-btn-group am-btn-group-xs">
						<button type="button" class="am-btn am-btn-default">
							<span class="am-icon-plus"></span> 删除
						</button>
						<button type="button" class="am-btn am-btn-default">
							<span class="am-icon-archive"></span> 导出
						</button>
					</div>
					<ul class="am-pagination am-fr">
						<li class="am-disabled"><a href="#">«</a></li>
						<li class="am-active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">»</a></li>
					</ul>
					<hr />
				</form>
				<div class="foods">
					<ul>版权所有@2015
					</ul>
					<dl>
						<a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/app.js"></script>
	<script src="../assets/js/amazeui.min.js"></script>
	<!--<![endif]-->
</body>
</html>