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
<title>消息列表</title>
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
					<li><a class="am-btn am-btn-default am-radius am-btn-xs" href="homepage.html" target="rightFrame">首页</a></li>
				</ul>
			</div>
			<div class="admin-biaogelist"
				style="positioin:absolute;top:30px;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-users">消息管理
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a>消息列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
					<ul>
						<li><input type="text" id="condition"
							class="am-form-field am-input-sm am-input-xm" placeholder="关键词搜索" /></li>
						<li><button type="submit" onClick="queryMessage()"
								class="am-btn am-radius am-btn-xs am-btn-success"
								style="margin-top: -1px;">搜索</button></li>
					</ul>
				</div>
				<script>
				    document.getElementById("condition").value='${condition}';
				</script> 
				<form class="am-form am-g">
				<div >
					<table width="100%"
						class="am-table">
						<thead>
							<tr >
								<th class="table-check"><input type="checkbox" id="chooseAll" onClick="checkAll()"/></th>
								<th >学号</th>
								<th >姓名</th>
								<th >内容</th>
								<th class="table-date am-hide-sm-only">时间</th>
								<th class="table-date am-hide-sm-only">状态</th>
								<th  class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:if test="#session['message']==null">
						<div>
						暂无消息
						</div>
						      
						</s:if>
						<s:else>
						<s:property value="#session['message']==[]"/>
							<s:iterator value="#session['message']" id="message" status="no">
								<tr>
									<td><input type="checkbox" value="<s:property value="#message.Ano"/>" name="choose" onClick="check(<s:property value="#session['page'].pageSize"/>)"/></td>
									<td><s:property value="#message.Sno"/></td>
									<td><s:property value="#message.Sname"/></td>
									<td class="am-hide-sm-only" style="max-width:450px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><s:property value="#message.Acontent"/></td>
									<td class="am-hide-sm-only"><s:property value="#message.Atime"/></td>
									<td class="am-hide-sm-only"><s:property value="#message.Astate"/></td>
									<td>
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<a href="Message-queryMessageDetail.action?Ano=<s:property value="#message.Ano"/>"
												class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												type="button" title="回复信息">
												<span class="am-icon-pencil-square-o"></span>
											</a>
											<button onClick="deleteQuestion(<s:property value="#message.Ano"/>)"
												class="am-btn am-btn-default am-btn-xs am-text-secondary am-round"
												 type="button" title="删除信息">
													<span class="am-icon-trash-o"></span>
												</button>
										</div>
									</div>
								</td>
								</tr>
							</s:iterator>	
						</s:else>
										
						</tbody>
					</table>
					</div>
					<div class="am-btn-group am-btn-group-xs">
						<button type="button" class="am-btn am-btn-default" onClick="deleteQuestion(this)">
							<span class="am-icon-plus"></span> 删除
						</button>
					</div>
					<ul class="am-pagination am-fr">
						<li><a href="Message-queryAllMessage.action"><<</a></li>
						<s:if test="#session['page'].hasPre">
							<li class="am-disabled"><a href="#"><</a></li>
						</s:if>
						<s:else>
							<li><a href="Message-queryAllMessage.action?pageNow=<s:property value="#session['page'].pageNow-1"/>"><</a></li>
						</s:else>
						<s:if test="#session['page'].hasNext">
							<li><a
								href="Message-queryAllMessage.action?pageNow=<s:property value="#session['page'].pageNow+1"/>">></a></li>
						</s:if>
						<s:else>
							<li class="am-disabled"><a href="#">></a></li>
						</s:else>
						<li><a href="Message-queryAllMessage.action?pageNow=<s:property value="#session['page'].totalPage"/>">>></a></li>
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
		function queryMessage(){
			var condition = document.getElementById("condition").value;
			window.location.href="Message-queryMessage.action?condition="+condition;
		}
	</script>
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
						window.location.href="Message-deleteMessage.action?Ano="+str;
					}
				}
			}else{
				str[0]=obj;
				if(window.confirm('您确定要删除吗？')){
					window.location.href="Message-deleteMessage.action?Ano="+str;
				}
			}
		}
		function check(obj){
			var choose=document.getElementsByName("choose");
			var chooseAll=document.getElementById("chooseAll");
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