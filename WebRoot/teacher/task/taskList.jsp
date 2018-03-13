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
<title>作业列表</title>
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
					<ul class="am-icon-users">作业管理
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a>作业列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
					<ul>
						<form action="Task-queryAllTask.action" method="post">
							<li><input type="text" name="condition" id="condition"
								class="am-form-field am-input-sm am-input-xm" placeholder="关键词搜索" /></li>
							<li><button type="submit"
									class="am-btn am-radius am-btn-xs am-btn-success"
									style="margin-top: -1px;">搜索</button></li>
						</form>
					</ul>
				</div>
				<script type="text/javascript">
					document.getElementById("condition").value='${request.condition}';
				</script>
				<form class="am-form am-g">
					<table width="100%"
						class="am-table am-table-bordered am-table-radius am-table-striped">
						<thead>
							<tr class="am-success">
								<th class="table-check"><input type="checkbox" id="chooseAll" onClick="checkAll()"/></th>
								<th class="table-type">作业名称</th>
								<th class="table-author am-hide-sm-only">作业附件</th>
								<th class="table-author am-hide-sm-only">创建时间</th>
								<th  class="table-set">所属课程</th>
								<th  class="table-set">所属章节</th>
								<th  class="table-set">状态</th>
								<th  class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request['task']" id="task" status="no">
						    	<tr>
									<td><input type="checkbox"  value=<s:property value="#task.Tid"/> name="choose" onClick="check()"/></td>
									<td><a href="TaskDetail-queryTaskDetail.action?tid=<s:property value="#task.Tid"/>" target="_blank"><s:property value="#task.task_name"/></a></td>
									<td><s:property value="#task.tappendixes"/></td>
									<td><s:property value="#task.subtime"/></td>
									<td class="am-hide-sm-only"><s:property value="#task.cname"/></td>
									<td class="am-hide-sm-only"><s:property value="#task.tchapter"/></td>
									<td class="am-hide-sm-only"><s:property value="#task.tstate"/></td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<a href="TaskDetail-queryTaskDetail.action?tid=<s:property value="#task.Tid"/>" target="_blank"
													class="am-btn am-btn-default am-btn-xs am-text-success am-round">
													<span class="am-icon-search" title="查看作业详情"></span>
												</a>
												<button data-am-modal="{target: '#doc-modal-1'}" onClick="arrangeTask(<s:property value="#task.Tid"/>)"
													class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												   type="button" title="发布作业">
													<span class="am-icon-share-alt"></span>
												</button>
												<button onClick="deleteQuestion(<s:property value="#task.Tid"/>)"
													class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												   type="button" title="删除作业信息">
													<span class="am-icon-trash-o"></span>
												</button>
											</div>
										</div>
									</td>
								</tr>
						    </s:iterator>
						</tbody>
					</table>
					<div class="am-btn-group am-btn-group-xs">
						<button type="button" class="am-btn am-btn-default" onClick="deleteQuestion(this)">
							<span class="am-icon-trash-o"></span> 删除
						</button>
						<a href="../task/arrangeTask.jsp" class="am-btn am-btn-default">
							<span class="am-icon-plus"></span> 增加
						</a>
					</div>
					<ul class="am-pagination am-fr">
							<li><a href="Task-queryAllTask.action?condition=<s:property value="#request['condition']"/>"><<</a></li>
							<s:if test="#request['page'].hasPre">
								<li class="am-disabled"><a href="#"><</a></li>
							</s:if>
							<s:else>
								<li><a href="Task-queryAllTask.action?pageNow=<s:property value="#request['page'].pageNow-1"/>&condition=<s:property value="#request['condition']"/>"><</a></li>
							</s:else>
							<s:if test="#request['page'].hasNext">
								<li><a
									href="Task-queryAllTask.action?pageNow=<s:property value="#request['page'].pageNow+1"/>&condition=<s:property value="#request['condition']"/>">></a></li>
							</s:if>
							<s:else>
								<li class="am-disabled"><a href="#">></a></li>
							</s:else>
							<li><a href="Task-queryAllTask.action?pageNow=<s:property value="#request['page'].totalPage"/>&condition=<s:property value="#request['condition']"/>">>></a></li>
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
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				发布作业 <a href="javascript: void(0)"
					class="am-close am-close-spin" data-am-modal-close>&times;</a>
			</div>
			<hr style="border: 1px thin ;">
			<form id="form">
			<div style="max-height: 450px;max-width:500px;margin: 0 auto">
					<table class="am-table am-table-striped am-table-hover">
						<tbody>
							<s:iterator value="#session['totalclass']" id="class">
								<tr>
									<td><input type="checkbox" name="choose_class" onClick="cancel()" value='<s:property value="#class.cno"/>'></td>
									<td><s:property value="#class.cname"/> </td>
									<td><input type="text" id="<s:property value="#class.cno"/>" name="end_time" 
									class="am-form-field am-input-sm am-input-zm " style="height:25px;width:90px;display:none;"
									placeholder="截止时间" data-am-datepicker="{theme: 'success',}"
									readonly /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<input type="button" onClick="tj()" class="am-btn am-btn-primary" value="确定">
					<input type="button" onClick="abolish()" class="am-btn am-btn-primary" value="取消">
			</div>
			</form>
			<br>
		</div>
	</div>
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/app.js"></script>
	<script src="../assets/js/amazeui.min.js"></script>
	<!--<![endif]-->
	<script type="text/javascript">
		function deleteQuestion(obj){
			var str=new Array();
			if(typeof obj == "object"){
				var choose=document.getElementsByName("choose");
				var key=0;
				var choose=document.getElementsByName("choose");
				for(var i=0;i<choose.length;i++){
					if(choose[i].checked){
						str[key]=choose[i].value;
						key++;
					}
				}
				if(key>0){
					console.log(str);
					if(window.confirm('您确定要删除吗？')){
						window.location.href="Task-deleteTask.action?Tid="+str;
					}
				}
			}else{
				str[0]=obj;
				console.log(str);
				if(window.confirm('您确定要删除吗？')){
					window.location.href="Task-deleteTask.action?Tid="+str;
				}
			}
		}
		function check(){
			var choose=document.getElementsByName("choose");
			var chooseAll=document.getElementById("chooseAll");
			var obj=choose.length;
			var num=0;
			for(var i=0;i<obj;i++){
				if(choose[i].checked){
					num++;
				}
			}
			if(num==obj){
				chooseAll.checked="checked";
			}else{
				chooseAll.checked="";
			}
		}
		function checkAll(obj){
			var choose=document.getElementsByName("choose");
			var chooseAll=document.getElementById("chooseAll");
			if(chooseAll.checked){
				for(var i=0;i<choose.length;i++){
					choose[i].checked="checked";
				}
			}else{
				for(var i=0;i<choose.length;i++){
					choose[i].checked="";
				}
			}
		}
	</script>
	<script type="text/javascript">
		function cancel(){
			var choose=document.getElementsByName("choose_class");
			var time=document.getElementsByName("end_time");
			for(var i=0;i<choose.length;i++){
				if(choose[i].checked==""){
					time[i].value="";
					time[i].style.display="none";
				}else{
					time[i].style.display="";
				}
			}
		}
		var task_tid="";
		function arrangeTask(obj){
			var choose=document.getElementsByName("choose_class");
			var time=document.getElementsByName("end_time");
			for(var i=0;i<choose.length;i++){
				choose[i].checked="";
				time[i].value="";
			}
			task_tid=obj;
		}
		function abolish(){
			var choose=document.getElementsByName("choose_class");
			var time=document.getElementsByName("end_time");
			for(var i=0;i<choose.length;i++){
				choose[i].checked="";
				time[i].value="";
				time[i].style.display="none";
			}
		}
		function tj(){
			var choose=document.getElementsByName("choose_class");
			var time=document.getElementsByName("end_time");
			var num=0;
			var key=0;
			var times="";
			var cno="";
			for(var i=0;i<choose.length;i++){
				if(choose[i].checked){
					num++;
				}
				if(time[i].value!=""){
					key++;
					times+=","+time[i].value;
					cno+=","+time[i].id;
				}
			}
			if(num!=0 && num==key){
				window.location.href="IssueTask-issueTask.action?tid="+task_tid+"&cno="+cno+"&end_time="+times;
			}else{
				alert("请选择班级或时间！");
			}
		}
	</script>
</body>
</html>