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
<title>问题列表</title>
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
					<ul class="am-icon-users">试题列表
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a>试题列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
					<ul>
						 <form action="Question-queryQuestion.action" method="post">
							<li> 
								<div class="am-btn-group am-btn-group-xs">
									<select name="cq.Cno" id="Cno"
										data-am-selected="{btnWidth: 90, btnSize: 'sm', btnStyle: 'default'}">
										<option value="course">课程</option>
										<s:iterator value="#session['totalcourse']" id="course">
											<option value="<s:property value="#course.cid"/>"><s:property value="#course.course_name"/></option>
										</s:iterator>
									</select>
								</div>
							</li>
							<li><input type="text" id="keyword" name="cq.keyword"
								class="am-form-field am-input-sm am-input-xm" placeholder="关键词搜索" /></li>
							<li><button type="submit" 
									class="am-btn am-radius am-btn-xs am-btn-success"
									style="margin-top: -1px;">搜索</button></li>
						</form>
					</ul>
				</div>
				<script>
				    document.getElementById("keyword").value='${condition.keyword}';
					var select = document.getElementById("Cno");
					var arr = select.getElementsByTagName("option");
					for(var i=0;i<arr.length;i++){
						if(arr[i].value=='${condition.cno}'){
							arr[i].selected = "selected";
						}
					}
				</script>
				<form class="am-form am-g">
					<table width="100%"
						class="am-table am-table-bordered am-table-radius am-table-striped">
						<thead>
							<tr class="am-success">
								<th class="table-check"><input type="checkbox" id="chooseAll" onClick="checkAll()"/></th>
								<th class="table-type">试题序号</th>
								<th class="table-author am-hide-sm-only">试题内容</th>
								<th class="table-author am-hide-sm-only">试题选项</th>
								<th class="table-date am-hide-sm-only">试题答案</th>
								<th class="table-type">试题类型</th>
								<th  class="table-set">所属课程</th>
								<th  class="table-set">所属章</th>
								<th  class="table-set">所属节</th>
								<th  class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="#session['question']" id="question" status="no">
						    	<tr>
						    	    <td class="table-check"><input type="checkbox" value=<s:property value="#question.Qid"/> name="choose" onClick="check()"/></td>
									<td><s:property value="(#session['page'].pageNow-1)*#session['page'].pageSize+#no.count"/></td>
									<td style="max-width:250px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><s:property value="#question.Qcontent"/></td>
									<td style="max-width:200px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><s:property value="#question.Qchoice"/></td>
									<td class="am-hide-sm-only"><s:property value="#question.Qanswer"/></td>
									<td class="am-hide-sm-only"><s:property value="#question.Qtype"/></td>
									<td class="am-hide-sm-only"><s:property value="#question.Cname"/></td>
									<td class="am-hide-sm-only"><s:property value="#question.Qchapter"/></td>
									<td class="am-hide-sm-only"><s:property value="#question.Qparagraph"/></td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<a href="Question-checkQuestionDetail.action?qp.Qid=<s:property value="#question.Qid"/>" class="am-btn am-btn-default am-btn-xs am-text-success am-round">
													<span class="am-icon-search" title="查看问题详情"></span>
												</a>
												<a href="Question-queryModifyQuestionDetail.action?qp.Qid=<s:property value="#question.Qid"/>"
												class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												data-am-modal="{target: '#my-popups'}" type="button" title="修改问题信息">
													<span class="am-icon-pencil-square-o"></span>
												</a>
												<button onClick="deleteQuestion(<s:property value="#question.Qid"/>)"
												class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												data-am-modal="{target: '#my-popups'}" type="button" title="删除问题信息">
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
					    <a type="button" class="am-btn am-btn-default" href="../corpus/addQuestion.jsp">
							<span class="am-icon-plus"></span> 增加
						</a>
						<button type="button" class="am-btn am-btn-default" onClick="deleteQuestion(this)">
							<span class="am-icon-trash-o"></span> 删除
						</button>
						<a  class="am-btn am-btn-default" href="../corpus/uploadQuestion.jsp">
							<span class="am-icon-archive"></span> 导入
						</a>
						<button type="button" class="am-btn am-btn-default" >
							<span class="am-icon-archive"></span> 导出
						</button>
					</div>
						<ul class="am-pagination am-fr">
							<li><a href="Question-queryAllQuestion.action"><<</a></li>
							<s:if test="#session['page'].hasPre">
								<li class="am-disabled"><a href="#"><</a></li>
							</s:if>
							<s:else>
								<li><a href="Question-queryAllQuestion.action?pageNow=<s:property value="#session['page'].pageNow-1"/>"><</a></li>
							</s:else>
							<s:if test="#session['page'].hasNext">
								<li><a
									href="Question-queryAllQuestion.action?pageNow=<s:property value="#session['page'].pageNow+1"/>">></a></li>
							</s:if>
							<s:else>
								<li class="am-disabled"><a href="#">></a></li>
							</s:else>
							<li><a href="Question-queryAllQuestion.action?pageNow=<s:property value="#session['page'].totalPage"/>">>></a></li>
						</ul>
					<hr/>
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
					if(window.confirm('您确定要删除吗？')){
						window.location.href="Question-deleteQuestion.action?Qid="+str;
					}
				}
			}else{
				str[0]=obj;
				if(window.confirm('您确定要删除吗？')){
					window.location.href="Question-deleteQuestion.action?Qid="+str;
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