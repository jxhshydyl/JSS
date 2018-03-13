<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>新增题目</title>
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
					<li><a class="am-btn am-btn-default am-radius am-btn-xs"
						href="4.html" target="rightFrame">首页</a></li>
				</ul>
			</div>
			<div class="admin-biaogelist" style="positioin:absolute;top:30px;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-users">试题上传
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="Question-queryAllQuestion.action?isFirst=0">试题列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="fbneirong">
					<form class="am-form" action="Question-addQuestion.action" method="post">
						<div class="am-form-group am-cf">
							<div class="zuo">所属课程：</div>
							<div class="you">
								<select name="qp.Cname"
									data-am-selected="{btnWidth: 190, btnSize: 'sm', btnStyle: 'default' }">
									<option value="course">课程名称</option>
									<s:iterator value="#session['totalcourse']" id="course">
										<option value='<s:property value="#course.cid"/>'><s:property value="#course.course_name"/></option>
									</s:iterator>
								</select>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">所属章：</div>
							<div class="you" style="width:400px;">
								<input type="text" class="am-input-sm" id="doc-ipt-email-1" name="qp.Qchapter"
									placeholder="请输入章（如：第一章）" required>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">所属节：</div>
							<div class="you" style="width:400px;">
								<input type="text" class="am-input-sm" id="doc-ipt-email-1" name="qp.Qparagraph"
									placeholder="请输入节（如：第一节）" required>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">试题类型：</div>
							<div class="you">
								<select onChange="shitileixing()" id="leixing" name="qp.Qtype"
									data-am-selected="{btnWidth: 190, btnSize: 'sm', btnStyle: 'default' }">
									<option value="1">单选题</option>
									<option value="2">多选题</option>
									<option value="3">判断题</option>
									<option value="4">填空题</option>
									<option value="5">简答题</option>
									<option value="6">代码题</option>
								</select>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">试题题目：</div>
							<div class="you">
								<textarea id="shiti" name="qp.Qcontent" style="width:680px;height:200px"></textarea>
							</div>
						</div>
						<div class="am-form-group am-cf" id="danxuanti">
							<div class="zuo">试题选项：</div>
							<div class="you">
								<table class="am-table am-table-bordered" style="width:680px;" id="zengjiaxuanxiang">
									     <tr bgcolor=#f0f0f0 align="center">
									         <td id="fontinputbefore">选项</td>
									         <td id="fontinputbefore">内容</td>
									         <td id="fontinputbefore">答案</td>
									         <td style="width:80px" id="fontinputbefore">操作</td>
									     </tr>
									     <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">A</td>
									         <td><input type="text" style="border:0;resize:none" name="aqp.answer1"></td>
									         <td><input type="radio" name="daan" value="A" ></td>
									         <td >
									             <a onClick="deletexuanxiang('A')">
													<span class="am-icon-trash-o"></span> 删除
												  </a>
											 </td>
									     </tr>
									    <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">B</td>
									         <td><input type="text" style="border:0;resize:none" name="aqp.answer2"></td>
									         <td ><input type="radio" name="daan" value="B" ></td>
									         <td>
									             <a onClick="deletexuanxiang('B')">
													<span class="am-icon-trash-o"></span> 删除
												  </a>
											 </td>
									     </tr>
									     <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">C</td>
									         <td><input type="text" style="border:0;resize:none" name="aqp.answer3"></td>
									         <td><input type="radio" name="daan" value="C" ></td>
									         <td>
									             <a onClick="deletexuanxiang('C')">
													<span class="am-icon-trash-o"></span> 删除
												  </a>
											 </td>
									     </tr>
									     <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">D</td>
									         <td><input type="text" style="border:0;resize:none" name="aqp.answer4"></td>
									         <td><input type="radio" name="daan" value="D" ></td>
									         <td >
									             <a onClick="deletexuanxiang('D')">
													<span class="am-icon-trash-o"></span> 删除
												  </a>
											 </td>
									     </tr>
                                     </table>
                                      <button type="button" class="am-btn am-btn-default" onClick="addxuanxiang()">
									         <span class="am-icon-plus"></span>
								      </button> 
								</div>
						</div>
<!--判断题-->						
						<div class="am-form-group am-cf" id="panduanti" style="display:none;">
							<div class="zuo">试题答案：</div>
							<div class="you">
								<input type="radio" name="daan1" value="true" style="width:17px;height:17px;">
                                <label style="font-size:23px">√</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="daan1" value="false" style="width:17px;height:17px;">
                                <label style="font-size:23px">×</label> 
							</div>
						</div>
		<!--代码题-->				
						<div class="am-form-group am-cf" id=daimati style="display:none;">
							<div class="zuo">测试用例：</div>
							<div class="you">
							   <div class="you" style="height: 45px;">
									<input type="file" id="doc-ipt-file-1">
									<p class="am-form-help">请选择要上传的文件...</p>
							    </div>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="you" style="margin-left: 11%;">
								<button type="submit" class="am-btn am-btn-success am-radius">保存</button>
							</div>
						</div>
					</form>
				</div>
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
		//增加选项
		var key=0;
	 function addxuanxiang(){
		 key=key+1;
		 var table = document.getElementById("zengjiaxuanxiang");
/*		 alert(table.rows.length);*/
		 var leixing = document.getElementById("leixing");
		 var tr=document.getElementsByName("xuanxianghangshu");
		 var xuanxiang=new Array(["A"],["B"],["C"],["D"],["E"],["F"],["G"],["H"],["I"]);
		 var row=tr.length;
		 var newrow = table.insertRow(row+1);
		 newrow.setAttribute("name","xuanxianghangshu");
		 var newtd0 = newrow.insertCell(0);
		 newtd0.innerHTML = ""+xuanxiang[row]+"";
		 newtd0.setAttribute("align","center"); //设置位置
		 newtd0.setAttribute("id","fontinputbefore");
		 var newtd1 = newrow.insertCell(1);
		 newtd1.innerHTML = '<input type="text" style="border:0;resize:none" name="aqp.answer5"/>';
		 var newtd2 = newrow.insertCell(2);
		 if(leixing.value=="1"){
			 newtd2.innerHTML = '<input type="radio" name="daan" value="E">';
		 }else if(leixing.value=="2"){
			 newtd2.innerHTML = '<input type="checkbox" name="daan" value="E">';
		 }
		 newtd2.setAttribute("align","center"); //设置位置
		 var newtd3 = newrow.insertCell(3);
		 newtd3.innerHTML = '<a onClick="deletexuanxiang()"><span class="am-icon-trash-o"></span> 删除</a>';
		 newtd3.setAttribute("align","center"); //设置位置
	 }
//删除选项并重新排序（A,B,C,D,E,F,G,H）
	 function deletexuanxiang(obj){
		 key=key-1;
		var flag=window.confirm("是否删除？");
		 if(flag==true){
			 var table = document.getElementById("zengjiaxuanxiang");
			 var tr=document.getElementsByName("xuanxianghangshu");
			 var xuanxiang=new Array(["A"],["B"],["C"],["D"],["E"],["F"],["G"],["H"],["I"]);
			 for(var i=0;i<xuanxiang.length;i++){
				 if(obj==xuanxiang[i]){
					 var key=i;
				 }else{
					 var key=tr.length-1;
				 }
			 }
			 table.deleteRow(key+1);
			 for(var i=1;i<tr.length+1;i++){
				 table.rows[i].cells[0].innerHTML=xuanxiang[i-1];     
				 }
		 }
	 }
	 function shitileixing(){
		 var leixing=document.getElementById("leixing");
		 var danxuanti=document.getElementById("danxuanti");
		 var panduanti=document.getElementById("panduanti");
		 var daimati=document.getElementById("daimati");
		 var name=document.getElementsByName("daan");
		 if(leixing.value=="1"){
			 danxuanti.style.display="";
			 panduanti.style.display="none";
			 daimati.style.display="none";
			 for(var i=0;i<name.length;i++){
				 name[i].type="radio";
				 name[i].required="";
			 }
		 }else if(leixing.value=="2"){
			 danxuanti.style.display="";
			 panduanti.style.display="none";
			 daimati.style.display="none";
			 for(var i=0;i<name.length;i++){
				 name[i].type="checkbox";
				 name[i].required="";
			 }
		 }else if(leixing.value=="3"){
			 danxuanti.style.display="none";
			 panduanti.style.display="";
			 daimati.style.display="none";
			 for(var i=0;i<name.length;i++){
				 name[i].required="";
			 }
		 }else if(leixing.value=="4"){
			 danxuanti.style.display="none";
			 panduanti.style.display="none";
			 daimati.style.display="none";
			 for(var i=0;i<name.length;i++){
				 name[i].required="";
			 }
		 }else if(leixing.value=="5"){
			 danxuanti.style.display="none";
			 panduanti.style.display="none";
			 daimati.style.display="none";
			 for(var i=0;i<name.length;i++){
				 name[i].required="";
			 }
		 }else if(leixing.value=="6"){
			 danxuanti.style.display="none";
			 panduanti.style.display="none";
			 daimati.style.display="";
			 for(var i=0;i<name.length;i++){
				 name[i].required="";
			 }
		 }
	 }
	</script>
</body>
</html>