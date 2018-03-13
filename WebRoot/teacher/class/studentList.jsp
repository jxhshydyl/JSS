<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>学生管理</title>
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
					<li><a class="am-btn am-btn-default am-radius am-btn-xs" href="../index/homepage.html" target="rightFrame">首页</a></li>
				</ul>
			</div>
		
			<div class="admin-biaogelist"
				style="positioin:absolute;top:30px;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-users">学生管理
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="Class-queryAllClass.action">班级列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
					<ul>
					<form action="Student-queryStudent.action" method="post">
						<li><input type="text" name="condition"
							class="am-form-field am-input-sm am-input-xm" placeholder="学号或姓名" /></li>
						<li><button type="submit"
								class="am-btn am-radius am-btn-xs am-btn-success"
								style="margin-top: -1px;">搜索</button></li>
					</form>
					</ul>
				</div>
				<form class="am-form am-g">
					<table width="100%"
						class="am-table am-table-bordered am-table-radius am-table-striped">
						<thead>
							<tr class="am-success">
								<th class="table-check"><input type="checkbox" /></th>
								<th class="table-id">序号</th>
								<th class="table-title">学号</th>
								<th class="table-type">姓名</th>
								<th class="table-author am-hide-sm-only">性别</th>
								<th class="table-author am-hide-sm-only">入学日期</th>
								<th class="table-date am-hide-sm-only">所在院系</th>
								<th class="table-date am-hide-sm-only">所学专业</th>
								<th class="table-date am-hide-sm-only">所在班级</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#session['student']" id="student" status="no">
								<tr>
									<td><input type="checkbox" /></td>
									<td><s:property value="(#session['page'].pageNow-1)*#session['page'].pageSize+#no.count"/></td>
									<td><s:property value="#student.Sno"/></td>
									<td><s:property value="#student.Sname"/></td>
									<td class="am-hide-sm-only"><s:property value="#student.Ssex"/></td>
									<td class="am-hide-sm-only"><s:property value="#student.Syear"/></td>
									<td class="am-hide-sm-only"><s:property value="#student.Sacademy"/></td>
									<td class="am-hide-sm-only"><s:property value="#student.Smajor"/></td>
									<td class="am-hide-sm-only"><s:property value="#student.Sclass"/></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="am-btn-group am-btn-group-xs">
						<a type="button" class="am-btn am-btn-default" href="ExportExcel-exportExcel.action">
							<span class="am-icon-archive"></span> 导出学生信息
						</a>

					</div>
					<ul class="am-pagination am-fr">
						<li><a href="Student-queryStudent.action"><<</a></li>
						<s:if test="#session['page'].hasPre">
							<li class="am-disabled"><a href="#"><</a></li>
						</s:if>
						<s:else>
							<li><a href="Student-queryStudent.action?pageNow=<s:property value="#session['page'].pageNow-1"/>"><</a></li>
						</s:else>
						<s:if test="#session['page'].hasNext">
							<li><a
								href="Student-queryStudent.action?pageNow=<s:property value="#session['page'].pageNow+1"/>">></a></li>
						</s:if>
						<s:else>
							<li class="am-disabled"><a href="#">></a></li>
						</s:else>
						<li><a href="Student-queryStudent.action?pageNow=<s:property value="#session['page'].totalPage"/>">>></a></li>
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