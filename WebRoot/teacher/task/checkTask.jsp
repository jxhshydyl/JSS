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
					<ul class="am-icon-users">作业批阅
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a>作业列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
				<form action="ArrangedTask-queryArrangedTaskByCondition.action" method="post">
					<ul>
						<li>
							<div class="am-btn-group am-btn-group-xs">
								<select name="cno" id="cno"
									data-am-selected="{btnWidth: 90, btnSize: 'sm', btnStyle: 'default'}">
									<option value="class">班级</option>
									<s:iterator value="#session['totalclass']" id="class">
										<option value="<s:property value="#class.cno"/>"><s:property value="#class.cname"/></option>
									</s:iterator>
								</select>
							</div>
						</li>
						<li> 
							<div class="am-btn-group am-btn-group-xs">
								<select name="course" id="course"
									data-am-selected="{btnWidth: 90, btnSize: 'sm', btnStyle: 'default'}">
									<option value="course">课程名称</option>
									<s:iterator value="#session['totalcourse']" id="course">
										<option value='<s:property value="#course.cid"/>'><s:property value="#course.course_name"/></option>
									</s:iterator>
								</select>
							</div>
						</li>
						<li><input type="text" name="key" id="key"
							class="am-form-field am-input-sm am-input-xm" placeholder="关键词搜索" /></li>
						<li><button type="submit"
								class="am-btn am-radius am-btn-xs am-btn-success"
								style="margin-top: -1px;">搜索</button></li>
					</ul>
					</form>
				</div>
				<script>
				    document.getElementById("key").value='${key}';
					var select = document.getElementById("course");
					var arr = select.getElementsByTagName("option");
					for(var i=0;i<arr.length;i++){
						if(arr[i].value=='${course}'){
							arr[i].selected = "selected";
						}
					}
					var select1 = document.getElementById("cno");
					var arr1 = select1.getElementsByTagName("option");
					for(var i=0;i<arr.length;i++){
						if(arr1[i].value=='${cno}'){
							arr1[i].selected = "selected";
						}
					}
				</script>
				<form class="am-form am-g">
					<table width="100%"
						class="am-table am-table-bordered am-table-radius am-table-striped">
						<thead>
							<tr class="am-success">
								<th class="table-check"><input type="checkbox" id="chooseAll" onClick="checkAll()"/></th>
								<th class="table-type">班级名称</th>
								<th class="table-author am-hide-sm-only">作业名称</th>
								<th  class="table-set">所属课程</th>
								<th  class="table-set">所属章节</th>
							
								<th class="table-date am-hide-sm-only">截止时间</th>
								
								<th  class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request['arrangedTask']" id="arrangedTask">
								<tr>
								<td><input type="checkbox" name="choose" onClick="check()" value="<s:property value="#arrangedTask.tid"/>,<s:property value="#arrangedTask.cno"/>" /></td>
								<td><a href="ClassTaskDetail-queryClassTaskDetail.action?tid=<s:property value="#arrangedTask.tid"/>&cno=<s:property value="#arrangedTask.cno"/>" target="rightFrame"><s:property value="#arrangedTask.class_name"/></a></td>
								<td><s:property value="#arrangedTask.task_name"/></td>
								<td><s:property value="#arrangedTask.course_name"/></td>
								<td><s:property value="#arrangedTask.chapter"/></td>
								
								<td class="am-hide-sm-only"><s:property value="#arrangedTask.end_time"/></td>
								
								<td>
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">									
											<button
												class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												data-am-modal="{target: '#my-popups'}" type="button" title="批阅">
												<span class="am-icon-pencil-square-o"></span>
											</button>
											<button onClick="deleteArrangedTask('<s:property value="#arrangedTask.Tid"/>,<s:property value="#arrangedTask.cno"/>')" type="button"
												class="am-btn am-btn-default am-btn-xs am-text-success am-round">
												<span class="am-icon-trash-o" title="删除"></span>
											</button>
										</div>
									</div>
								</td>
							</tr>
						</s:iterator>		
					</tbody>
				</table>
				<div class="am-btn-group am-btn-group-xs">
					<button type="button" class="am-btn am-btn-default" onClick="deleteArrangedTask(this)">
						<span class="am-icon-plus"></span> 删除
					</button>
				</div>
				<ul class="am-pagination am-fr">
						<li><a href="ArrangedTask-queryArrangedTask.action"><<</a></li>
						<s:if test="#request['page'].hasPre">
							<li class="am-disabled"><a href="#"><</a></li>
						</s:if>
						<s:else>
							<li><a href="ArrangedTask-queryArrangedTask.action?pageNow=<s:property value="#request['page'].pageNow-1"/>"><</a></li>
						</s:else>
						<s:if test="#request['page'].hasNext">
							<li><a
								href="ArrangedTask-queryArrangedTask.action?pageNow=<s:property value="#request['page'].pageNow+1"/>">></a></li>
						</s:if>
						<s:else>
							<li class="am-disabled"><a href="#">></a></li>
						</s:else>
						<li><a href="ArrangedTask-queryArrangedTask.action?pageNow=<s:property value="#request['page'].totalPage"/>">>></a></li>
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
	<script type="text/javascript">
		function deleteArrangedTask(obj){
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
						console.log("成功删除");
						window.location.href="ArrangedTask-deleteArrangedTask.action?Tid="+str;
					}
				}
			}else{
				str[0]=obj;
				console.log(str);
				if(window.confirm('您确定要删除吗？')){
					console.log("删除成功");
					window.location.href="ArrangedTask-deleteArrangedTask.action?Tid="+str;
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
</body>
</html>