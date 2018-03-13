<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>班级列表</title>
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
			<div class="admin-biaogelist" style="positioin:absolute;top:30px;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-users">班级管理
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a>班级列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
				<form action="Class-queryClass.action" method="post">
					<ul>
						<li>
							<div class="am-btn-group am-btn-group-xs">
								<select id="Major" name="cc.Major"
									data-am-selected="{btnWidth: 100, btnSize: 'sm', btnStyle: 'default'}">
									<option value="major">专业</option>
									<s:iterator value="#session['totalmajor']" id="major">
										<option value="<s:property value="#major.mno"/>"><s:property value="#major.major"/></option>
									</s:iterator>
								</select>
							</div>
						</li>
						<li>
							<div class="am-btn-group am-btn-group-xs">
								<select id="Class" name="cc.Cno"
									data-am-selected="{btnWidth: 100, btnSize: 'sm', btnStyle: 'default'}">
									<option value="class">班级</option>
									<s:iterator value="#session['totalclass']" id="class">
										<option value="<s:property value="#class.cno"/>"><s:property value="#class.cname"/></option>
									</s:iterator>
								</select>
							</div>
						</li>
						<li style="margin-right: 0;"><span
							class="tubiao am-icon-calendar"></span> <input type="text" id="time_start" name="cc.time_start"
							class="am-form-field am-input-sm am-input-zm  am-icon-calendar"
							placeholder="" data-am-datepicker="{theme: 'success',}"
							readonly /></li>
						<li><input type="text" id="keyword" name="cc.keyword"
							class="am-form-field am-input-sm am-input-xm" placeholder="关键词搜索" /></li>
						<li><button type="submit" 
								class="am-btn am-radius am-btn-xs am-btn-success"
								style="margin-top: -1px;">搜索</button></li>
							
					</ul>
					</form>	
				</div>
				 <script>
				    document.getElementById("time_start").value='${condition.time_start}';
				    document.getElementById("keyword").value='${condition.keyword}';
					var select = document.getElementById("Major");
					var arr = select.getElementsByTagName("option");
					for(var i=0;i<arr.length;i++){
						if(arr[i].value=='${condition.major}'){
							arr[i].selected = "selected";
						}
					}
					
					var select1 = document.getElementById("Class");
					var arr1 = select1.getElementsByTagName("option");
					for(var i=0;i<arr.length;i++){
						if(arr1[i].value=='${condition.cno}'){
							arr1[i].selected = "selected";
						}
					}
				</script> 
				
				<form class="am-form am-g">
					<table width="100%"
						class="am-table am-table-bordered am-table-radius am-table-striped">
						<thead>
							<tr class="am-success">
								<th class="table-id">序号</th>
								<th class="table-title">班级编号</th>
								<th class="table-type">班级名称</th>
								<th class="table-author am-hide-sm-only">班级人数</th>
								<th class="table-author am-hide-sm-only">班级时限</th>
								<th class="table-date am-hide-sm-only">所属院系</th>
								<th class="table-date am-hide-sm-only">所属专业</th>
								<th width="130px" class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
						    <s:iterator value="#session['class']" id="class" status="no">
						    	<tr>
									<td><s:property value="(#session['page'].pageNow-1)*#session['page'].pageSize+#no.count"/></td>
									<td><s:property value="#class.Cno"/></td>
									<td><a href="Student-queryStudent.action?className=<s:property value="#class.Cname"/>"><s:property value="#class.Cname"/></a></td>
									<td class="am-hide-sm-only"><s:property value="#class.Cnum"/></td>
									<td class="am-hide-sm-only"><s:property value="#class.Ctime_start"/></td>
									<td class="am-hide-sm-only"><s:property value="#class.Academy"/></td>
									<td class="am-hide-sm-only"><s:property value="#class.Major"/></td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<a href="Student-queryStudent.action?className=<s:property value="#class.Cname"/>&classCount=<s:property value="#class.Cnum"/>" class="am-btn am-btn-default am-btn-xs am-text-success am-round">
													<span class="am-icon-search" title="查看班级详情"></span>
												</a>
											</div>
										</div>
									</td>
								</tr>
						    </s:iterator>
						</tbody>
					</table>
					<ul class="am-pagination am-fr">
						<li><a href="Class-queryAllClass.action"><<</a></li>
						<s:if test="#session['page'].hasPre">
							<li class="am-disabled"><a href="#"><</a></li>
						</s:if>
						<s:else>
							<li><a href="Class-queryAllClass.action?pageNow=<s:property value="#session['page'].pageNow-1"/>"><</a></li>
						</s:else>
						<s:if test="#session['page'].hasNext">
							<li><a
								href="Class-queryAllClass.action?pageNow=<s:property value="#session['page'].pageNow+1"/>">></a></li>
						</s:if>
						<s:else>
							<li class="am-disabled"><a href="#">></a></li>
						</s:else>
						<li><a href="Class-queryAllClass.action?pageNow=<s:property value="#session['page'].totalPage"/>">>></a></li>
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
	<%-- <script type="text/javascript">
		function searchClass(){
			
			var Major = document.getElementById("Major").value;  
			var Cname = document.getElementById("Class").value; 
			var time_start = document.getElementById("time_start").value; 
			var keyword = document.getElementById("keyword").value; 
			var cc={
	                'cc.Major':Major,
	                'cc.Cno':Cname,
	                'cc.time_start':time_start,
	                'cc.keyword':keyword,
	            };
			$.ajax({
				type : "POST",
				async: true,
				url : "Class-queryClass.action",
				data : cc,
				dataType : "json",
				success : function(data) {
					alert(data[0].cno);
				}
			});
		}
	</script> --%>
	<!--<![endif]-->
</body>
</html>