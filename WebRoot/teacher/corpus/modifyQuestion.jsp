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
<title>修改题目</title>
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
					<ul class="am-icon-users">试题详情
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="Question-queryAllQuestion.action?isFirst=0">试题列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="fbneirong">
					<form class="am-form" action="Question-modifyQuestion.action?qp.Qid=<s:property value="#session['QPojo'].Qid"/>" method="post">
						<div class="am-form-group am-cf">
							<div class="zuo">所属课程：</div>
							<div class="you">
								<select name="qp.Cname" id="Cname"
									data-am-selected="{btnWidth: 190, btnSize: 'sm', btnStyle: 'default' }" >
									<option value="course">课程名称</option>
									<s:iterator value="#session['totalcourse']" id="course">
										<option value='<s:property value="#course.cid"/>'><s:property value="#course.course_name"/></option>
									</s:iterator>
								</select>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">所属章节：</div>
							<div class="you" style="width:400px;">
								<input type="text" class="am-input-sm" id="doc-ipt-email-1" name="qp.Qchapter"
									placeholder="请输入章节（如：第一章）" required>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">试题类型：</div>
							<div class="you">
								<select onChange="shitileixing()" id="leixing" name="qp.Qtype" 
									data-am-selected="{btnWidth: 190, btnSize: 'sm',btnStyle: 'default' }" >
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
								<textarea id="shiti" name="qp.Qcontent" style="width:680px;height:200px" ></textarea>
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
									     </tr>
									     <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">A</td>
									         <td><textarea  name="aqp.answer1" style="width:550px;height:60px" id="1" ></textarea></td>
									         <td><input type="radio" name="daan" value="A" ></td>
									     </tr>
									    <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">B</td>
									         <td><textarea  name="aqp.answer2" style="width:550px;height:60px" id="2" ></textarea></td>
									         <td ><input type="radio" name="daan" value="B" ></td>
									     </tr>
									     <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">C</td>
									         <td><textarea  name="aqp.answer3" style="width:550px;height:60px" id="3" ></textarea></td>
									         <td><input type="radio" name="daan" value="C" ></td>
									     </tr>
									     <tr align="center" valign="middle" name="xuanxianghangshu">
									         <td id="fontinputbefore">D</td>
									         <td><textarea  name="aqp.answer5" style="width:550px;height:60px" id="4" ></textarea></td>
									         <td><input type="radio" name="daan" value="D" ></td>
									     </tr>
                                     </table>
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
	</script>
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
		 newtd1.innerHTML = '<textarea  name="aqp.answer5" style="width:550px;height:60px" id="5">';
		 var newtd2 = newrow.insertCell(2);
		 if(leixing.value=="1"){
			 newtd2.innerHTML = '<input type="radio" name="daan" value="E" >';
		 }else if(leixing.value=="2"){
			 newtd2.innerHTML = '<input type="checkbox" name="daan" value="E" >';
		 }
		 newtd2.setAttribute("align","center"); //设置位置
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
	<script type="text/javascript">
	    document.getElementById("doc-ipt-email-1").value='${QPojo.qchapter}';
	    document.getElementById("shiti").value='${QPojo.qcontent}';
		var select = document.getElementById("Cname");
		var arr = select.getElementsByTagName("option");
		for(var i=0;i<arr.length;i++){
			if(arr[i].text=='${QPojo.cname}'){
				arr[i].selected = "selected";
			}
		}
		var select1 = document.getElementById("leixing");
		var arr1 = select1.getElementsByTagName("option");
		for(var i=0;i<arr1.length;i++){
			if(arr1[i].text=='${QPojo.qtype}'){
				arr1[i].selected = "selected";
			}
		}
		 if('${QPojo.qchoice}' !=""){//如果为选择题
			var reg = /[A-Z][|:|：|、|.]/g;
			var str='${QPojo.qchoice}'.split(reg);
			var reg1 ="：";
			str[0]=str[0].split(reg1)[1];
			var key=0;
			var str2=new Array();
			for(var i=0;i<str.length;i++){
				if(str[i] !="" && str[i] !=" " && str[i] !=undefined){
					str2[key]=str[i];
					key=key+1;
				}
			}
			if(str2.length>4){
				for(var i=0;i<str2.length-4;i++){
					addxuanxiang();
				}
			}
			var arr=document.getElementsByName("daan");
			var answer='${QPojo.qanswer}'.split(",");
			for(var i=0;i<str2.length;i++){
				for(var j=0;j<answer.length;j++){
					if(arr[i].value.trim()==answer[j].trim()){
						arr[i].checked="checked";
					}
				}
				document.getElementById(i+1).value=str2[i];
			}
		}else if('${QPojo.qanswer}'.trim()=="true"){
			document.getElementsByName("daan1")[0].checked="checked";
		}else if('${QPojo.qanswer}'.trim()=="false"){
			document.getElementsByName("daan1")[1].checked="checked";
		}
	</script>
</body>
</html>