<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>作业安排</title>
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
<link rel="stylesheet" href="../css/core.css">
<link rel="stylesheet" type="text/css"
	href="../jqueryStep/css/jquery.step.css" />
<script src="../assets/js/jquery.min.js"></script>
<script src="../jqueryStep/js/jquery.step.min.js"></script>
<style>
button {
	display: inline-block;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	text-align: center;
	cursor: pointer;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #fff;
	background-color: #5bc0de;
}

.main {
	width: 1000px;
	margin: 0px auto;
}

#step {
	margin-bottom: 20px;
}

.btns {
	float: left;
	margin-left: 200px;
}

.info {
	float: left;
	height: 34px;
	line-height: 34px;
	margin-left: 40px;
	font-size: 28px;
	font-weight: bold;
	color: #928787;
}

.info span {
	color: red;
}

.divv1 {
	margin-top: 10px;
	margin-left: 10px;
	float: left;
	border: 1px solid #dcdcdc;
	border-radius: 5px;
	position: relative;
}

.pp1 {
	padding-left: 8px;
	padding-right: 30px;
	line-height: 30px;
	white-space: nowrap;
}

.pp2 {
	width: 30px;
	line-height: 30px;
	height: 30px;
	text-align: center;
	position: absolute;
	right: 0px;
	top: 0;
	cursor: pointer;
}

</style>
</head>
<body>
	<div class="am-cf admin-main">
		<div class=" admin-content">
			<div class="daohang" style="positioin: absolute; top: 0px;">
				<ul>
					<li><a class="am-btn am-btn-default am-radius am-btn-xs"
						href="../index/homepage.html" target="rightFrame">首页</a></li>
				</ul>
			</div>
			<div class="admin-biaogelist" style="positioin: absolute; top: 30px;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-users">作业安排
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="Task-queryAllTask.action">作业列表</a>
						<s:property value="#session['teacher'].tname" />
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="fbneirong">
					<div class="main">
						<div id="step"></div>
					</div>
					<form class="am-form" id="form" action="Task-arrangeTask.action"
						enctype="multipart/form-data" method="post"
						onsubmit="return verify()">
						<div id="corpus" style="margin-left: 100px">
							<div style="display: inline-block; width: 710px;">
								<div class="am-form-group am-cf">
									<div class="zuo">作业名称：</div>
									<div class="you" style="width: 300px;">
										<input type="text" class="am-input-sm" id="taskname"
											name="tp.task_name" placeholder="请输入标题" required>
									</div>
									<div class="you" style="width: 5px;">&nbsp;</div>
									<div class="you" style="width: 300px;">
										<button type="button" class="am-btn am-btn-primary am-round"
											id="oldTask" data-am-modal="{target: '#my-popup'}">选择作业</button>
									</div>
								</div>
								<input type="hidden"
									value='<s:property value="#session['teacher'].tname"/>'
									name="tp.tname">
								<div class="am-form-group am-cf">
									<div class="zuo">作业类型：</div>
									<select id="ttype" autocomplete="off"
										data-am-selected="{btnWidth: 190, btnSize: 'sm', btnStyle: 'default'}">
										<option value="1">题型一（学生题目相同）</option>
										<option value="0">题型二（学生题目不同）</option>
									</select>
								</div>
								<div class="am-form-group am-cf">
									<div class="zuo">所属课程：</div>
									<div class="you">
										<select id="course" name="tp.cno" changed.selected.amui
											data-am-selected="{btnWidth: 190, btnSize: 'sm', btnStyle: 'default'}">
											<option value="course">课程名称</option>
											<s:iterator value="#session['totalcourse']" id="course">
												<option value='<s:property value="#course.cid"/>'><s:property
														value="#course.course_name" /></option>
											</s:iterator>
										</select>
									</div>
								</div>
								<div class="am-form-group am-cf">
									<div class="zuo">所属章节：</div>
									<div class="you">
										<div style="background: #f4f4f4; background-color: red"
											id="selectedChapter">
											<div class="divv1">
												<p class="pp1">第一章 第一节</p>
												<p class="pp2">
													<span onClick="deleteChapter()" id="dealChapter"><i
														style="background-position: -18px -430px; display: block;">&times;</i></span>
												</p>
											</div>

											<div class="divv1">
												<p class="pp1">第一章</p>
												<p class="pp2">
													<span><i
														style="background-position: -18px -430px; display: block;">&times;</i></span>
												</p>
											</div>
											<div class="divv1">
												<p class="pp1">第一章</p>
												<p class="pp2">
													<span><i
														style="background-position: -18px -430px; display: block;">&times;</i></span>
												</p>
											</div>
										</div>
										<input type="text" class="am-input-sm" id="taskchapter"
											name="tp.tchapter" value="第一章" placeholder="请选择开始章"
											onClick="chooseChapter()" required>
									</div>
								</div>
								<input type="hidden"
									value="<s:property value="#session['teacher'].tno"/>"
									name="tp.tno"> <input type="hidden"
									value="<s:property value="#session['teacher'].tname"/>"
									name="tp.tname">
								<div class="am-form-group am-cf">
									<div class="zuo">作业附件：</div>
									<div class="you" style="height: 45px;">
										<input type="file" id="file" name="upload">
										<p class="am-form-help">请选择要上传的文件...</p>
									</div>
								</div>
								<div>
									<div style="border:"></div>
								</div>
							</div>
							<div style="display: inline; background-color: blue">
								章节 <select
									data-am-selected="{btnWidth: 190, btnSize: 'sm', btnStyle: 'default'}"
									multiple="multiple" size="6">
									<option value="1">第一章</option>
									<option value="2">第二章</option>
									<option value="3">第三章</option>
									<option value="4">第四章</option>
									<option value="5">第五章</option>
									<option value="6">第六章</option>
								</select>
							</div>
						</div>

						<div id="type" style="display: none; min-height: 200px">
							<div class="am-form-group am-cf">
								<div class="zuo">题型设置：</div>
								<div class="you"height:300px;">
									<div class="div1">
										<ul class="ul1" id="user_ul">
										</ul>
									</div>
									<div class="div4" id="most_tixing">
										<dl class="dl1">
											<dd class="dd1" id="ddbutton">
												<input type="button" id="tixing_danxuan"
													class="am-btn am-btn-primary input1" value="单选题"> <input
													type="button" id="tixing_duoxuan"
													class="am-btn am-btn-primary input1" value="多选题"> <input
													type="button" id="tixing_tiankong"
													class="am-btn am-btn-primary input1" value="填空题"> <input
													type="button" id="tixing_panduan"
													class="am-btn am-btn-primary input1" value="判断题"> <input
													type="button" id="tixing_jianda"
													class="am-btn am-btn-primary input1" value="简答题"> <input
													type="button" id="tixing_daima"
													class="am-btn am-btn-primary input1" value="编程题">
											</dd>
										</dl>
									</div>
									<div style="margin-left: 180px">
										<b style="font-size: 20px" id="total_score"></b>
									</div>
								</div>
							</div>
						</div>
						<div id="class"
							style="display: none; min-height: 200px; max-width: 600px; margin: 0 auto">
							<table class="am-table am-table-striped am-table-hover">
								<tbody>
									<s:iterator value="#session['totalclass']" id="class">
										<tr>
											<td><input type="checkbox" name="ampp.cno"
												onClick="cancel()" value='<s:property value="#class.cno"/>'></td>
											<td><s:property value="#class.cname" /></td>
											<td><input type="text"
												id="<s:property value="#class.cno"/>" name="ampp.end_time"
												class="am-form-field am-input-sm am-input-zm "
												style="height: 25px; width: 90px; display: none;"
												placeholder="截止时间" data-am-datepicker="{theme: 'success',}"
												readonly /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<div class="am-form-group am-cf">
							<div class="btns">
								<button id="prevBtn" type="button">上一步</button>
								<button id="nextBtn" type="button">下一步</button>
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

	<div class="am-popup" id="my-popup">
		<div class="am-popup-inner">
			<div class="am-popup-hd">
				<h4 class="am-popup-title">请选择作业</h4>
				<span data-am-modal-close class="am-close">&times;</span>
			</div>
			<div class="am-popup-bd">
				<table class="am-table">
					<thead>
						<th>作业名称</th>
						<th>作业类型</th>
						<th>所属课程</th>
						<th>所属章节</th>
						<th>操作</th>
					</thead>
					<tbody id="taskByCourse">
					</tbody>
				</table>
				<br>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var ifm = document.getElementById("my-popup");
		/* width:600px;
		margin-left:400px;
		heigth:200px;
		margin-top: 20px;
		margin-bottom: 20px;*/
		//console.log(document.documentElement.clientWidth);//1920*0.44 1366*0.44 0.293
		//console.log(document.documentElement.clientHeight);//1080*0.26 768*0.26
		//console.log(screen.height);
		if (screen.height == 768) {
			ifm.style.width = document.documentElement.clientWidth * 0.44
					+ "px";
			ifm.style.height = document.documentElement.clientHeight * 0.8
					+ "px";
			ifm.style.marginLeft = document.documentElement.clientWidth * 0.2
					+ "px";
			ifm.style.top = document.documentElement.clientHeight * 0.015
					+ "px";
		}if(screen.height==1080){
			var tixing_button = document.getElementById("most_tixing");
			tixing_button.style.marginLeft=800+"px";
		}
	</script>

	<script type="text/javascript">
		var $step = $("#step");
		var $index = $("#index");
		$step.step({
			index : 0,
			time : 500,
			title : [ "填写作业资料", "确认题型", "确认班级", "完成" ]
		});
		$index.text($step.getIndex());
		$("#prevBtn").on("click", function() {
			if ($("#nextBtn").attr("label") == "3") {
				$step.prevStep();
				$index.text($step.getIndex());
				$("#nextBtn").attr("type", "button");
				$("#corpus").css('display', 'none');
				$("#type").css('display', '');
				$("#class").css('display', 'none');
				$("#nextBtn").attr("label", "2");
				$("#nextBtn").text("下一步");
			} else {
				$step.prevStep();
				$index.text($step.getIndex());
				$("#nextBtn").attr("type", "button");
				$("#corpus").css('display', '');
				$("#type").css('display', 'none');
				$("#class").css('display', 'none');
				$("#nextBtn").attr("label", "1");
				$("#nextBtn").text("下一步");
			}
		});
		$("#nextBtn")
				.on(
						"click",
						function() {
							if ($("#nextBtn").attr("label") == null
									|| $("#nextBtn").attr("label") == '1') {
								if (!$("#taskname").val().trim() == ""
										&& !$("#taskchapter").val().trim() == ""
										&& $("#course").val() != "course") {
									$step.nextStep();
									$index.text($step.getIndex());
									$("#nextBtn").attr("label", "2");
									$("#corpus").css('display', 'none');
									$("#type").css('display', '');
									$("#class").css('display', 'none');
									$("#nextBtn").text("下一步");
								}
							} else {
								if ($("#nextBtn").text() == "完成") {
									//$step.nextStep();
									//$index.text($step.getIndex());
									uploadForm();
									$("#nextBtn").attr("type", "submit");
									//window.location.href="../task/taskList.jsp";
								} else {
									var arr1 = document
											.getElementsByName("ampp.count");
									var arr2 = document
											.getElementsByName("ampp.score");
									if ($("#file").val().trim() != ""
											|| ((arr1.length != 0))) {
										$step.nextStep();
										$index.text($step.getIndex());
										$("#corpus").css('display', 'none');
										$("#type").css('display', 'none');
										$("#class").css('display', '');
										$("#nextBtn").attr("label", "3");
										$("#nextBtn").text("完成");
									}
								}
							}
						});
	</script>
	<script type="text/javascript">
		var score = 0;
		$(function() {
			var str = new Array("单选题", "多选题", "填空题", "判断题", "简答题", "编程题");
			$("input[id^='tixing_']")
					.each(function() {//获取所有的id为tochannel_开头的Button  
						$(this).click(
									function() {//绑定当前点击的按钮  
										var ids = $(this).val();//获取它的id属性值 
										console.log(ids);
										var cname = document
												.getElementById("course").value;//获取输入的课程名 
										console.log(cname);
										var chapter = document
												.getElementById("taskchapter").value;//获取输入的章节
										console.log(chapter);
										score++;
										var index = 0;
										for (var i = 0; i < str.length; i++) {
											if (str[i] == ids) {
												index = i;
											}
										}
										var num = "";
										$
												.ajax({
													type : "POST",
													url : "QueryQuestion-queryQuestionCount.action",
													data : {
														"type" : ids,
														"cno" : cname,
														"chapter" : chapter
													},
													dataType : "json",
													success : function(
															data) {
														var newdata = JSON
																.parse(data);
														num = newdata[0].num;
														if ((num * 1) > 999) {
															num = "999+";
														}
														$("#user_ul").append(
																		"<li class='li1' id="+ids+">"
																				+ "<b class='b1'>"
																				+ num
																				+ "</b>"
																				+ "<span class='span1'>"
																				+ ids
																				+ "</span>"
																				+ "<div class='div2'>"
																				+ "<input type='text' onChange='totalscore1(this,"
																				+ num
																				+ ")' onkeypress='keyPress(this)' onkeyup='keyUp(this)' onblur='onBlur(this)' name='ampp.count' value='1' style='height: 18px;border: 1px solid #dcdcdc;padding: 3px;width: 50px;margin-right: 5px;text-align: center;float: left;margin-top: 10px;color: #1b92e2;font-size: 14px;font-weight: 700;'>题"
																				+ "</div>"
																				+ "<input type='hidden' name='ampp.types' value="+index+">"
																				+ "<div class='div3'>"
																				+ "<input type='text' onChange='totalscore2(this)' onkeypress='keyPress(this)' onkeyup='keyUp(this)' onblur='onBlur(this)' name='ampp.score' value='1' style='height: 18px;border: 1px solid #dcdcdc;padding: 3px;width: 50px;margin-right: 5px;text-align: center;float: left;margin-top: 10px;color: #1b92e2;font-size: 14px;font-weight: 700;'>分"
																				+ "</div>"
																				+ "<a class='a1' onClick='deletetixing(this)' name="
																				+ ids
																				+ "><i class='am-icon-trash-o' style='display: inline-block;margin-left: 16px;margin-top: -10px;height: 12px;width: 12px;'></i></a>"
																				+ "</li>");
													}
												});
										$(this).attr("disabled","false");
										$("#total_score").text("总分：" + score);
									});
							});
		});
		function deletetixing(obj) {
			var tixing = $(obj).attr("name");
			$("#" + tixing).remove();
			$("#ddbutton").find("input[type='button'][value=" + tixing + "]")
					.removeAttr("disabled");
			score--;
			var arr1 = document.getElementsByName("ampp.count");
			var arr2 = document.getElementsByName("ampp.score");
			var tatal_score = 0 * 1;
			for (var i = 0; i < arr1.length; i++) {
				tatal_score += (arr1[i].value * 1) * (arr2[i].value * 1);
			}
			if (score == 0) {
				$("#total_score").text("");
			} else {
				$("#total_score").text("总分：" + tatal_score);
			}
		}
		function totalscore1(obj, num) {
			var arr1 = document.getElementsByName("ampp.count");
			var arr2 = document.getElementsByName("ampp.score");
			var tatal_score = 0 * 1;
			if (num == 0) {
				obj.value = 0;
			} else if (obj.value > num) {
				obj.value = num;
			}
			console.log(obj.value);
			for (var i = 0; i < arr1.length; i++) {
				tatal_score += (arr1[i].value * 1) * (arr2[i].value * 1);
			}
			$("#total_score").text("总分：" + tatal_score);
		}
		function totalscore2(obj) {
			var arr1 = document.getElementsByName("ampp.count");
			var arr2 = document.getElementsByName("ampp.score");
			var tatal_score = 0 * 1;
			for (var i = 0; i < arr1.length; i++) {
				if (arr2[i].value < 1) {
					arr2[i].value = 0;
				}
				tatal_score += (arr1[i].value) * (arr2[i].value);
			}
			$("#total_score").text("总分：" + tatal_score);
		}
		function uploadForm() {
			var count = document.getElementsByName("ampp.count");
			var score = document.getElementsByName("ampp.score");
			var type = document.getElementsByName("ampp.types");
			var form = document.getElementById('form');
			var fd = new FormData(form);
			for (var i = 0; i < count.length; i++) {
				strs = count[i].value + "," + score[i].value;
				fd.append('123', strs);
			}
			console.log(fd);
		}
	</script>
	<script type="text/javascript">
		function cancel() {
			var choose = document.getElementsByName("ampp.cno");
			var time = document.getElementsByName("ampp.end_time");
			for (var i = 0; i < choose.length; i++) {
				if (choose[i].checked == "") {
					time[i].value = "";
					time[i].style.display = "none";
				} else {
					time[i].style.display = "";
				}
			}
		}
		var task_tid = "";
		function arrangeTask(obj) {
			var choose = document.getElementsByName("ampp.cno");
			var time = document.getElementsByName("ampp.end_time");
			for (var i = 0; i < choose.length; i++) {
				choose[i].checked = "";
				time[i].value = "";
			}
			task_tid = obj;
		}
		function verify() {
			var choose = document.getElementsByName("ampp.cno");
			var time = document.getElementsByName("ampp.end_time");
			var num = 0;
			var key = 0;
			var times = "";
			var cno = "";
			for (var i = 0; i < choose.length; i++) {
				if (choose[i].checked) {
					num++;
				}
				if (time[i].value != "") {
					key++;
					times += "," + time[i].value;
					cno += "," + time[i].id;
				}
			}
			if (num != 0 && num != key) {
				alert("请选择截止时间！");
				return false;
			} else {
				return true;
			}
		}
	</script>
	<script type="text/javascript">
		function keyPress(ob) {
			if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) {
				ob.value = "0";
			}
			if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) {
				ob.o_value = ob.value;
			}
		}
		function keyUp(ob) {
			if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) {
				ob.value = ob.t_value;
			} else {
				ob.t_value = ob.value;
			}
			if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) {
				ob.o_value = ob.value;
			}
		}
		function onBlur(ob) {
			if (!ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/)) {
				ob.value = ob.o_value;
			} else {
				if (ob.value.match(/^\.\d+$/)) {
					ob.value = 0 + ob.value;
				}
				if (ob.value.match(/^\.$/)) {
					ob.value = 0;
					ob.o_value = ob.value;
				}
			}
		}
		function deleteChapter() {

		}
		var selected_task = new Array();
		$("#oldTask")
				.click(
						function() {
							$
									.ajax({
										type : "POST",
										url : "Task-queryTaskByCourse.action",
										data : {},
										dataType : "json",
										success : function(data) {
											var d = eval("(" + data + ")");
											selected_task = d;
											var html = "";
											$("#taskByCourse").html(html);
											for (var i = 0; i < d.task.length; i++) {
												html = "<tr>"
														+ "<td>"
														+ d.task[i].task_name
														+ "</td>"
														+ "<td>"
														+ d.task[i].cname
														+ "</td>"
														+ "<td>"
														+ d.task[i].cname
														+ "</td>"
														+ "<td>"
														+ d.task[i].tchapter
														+ "</td>"
														+ "<td><button type='button' onClick='selectedTask("
														+ i
														+ ")'>选择</button></td>"
														+ "</tr>";
												$("#taskByCourse").append(html);
											}
											//$("#taskByCourse").html(html);
											/* var newdata = JSON.parse(data);
											var str1 = newdata[0].hidden;
											if (str1 === "1") {
												alert("用户名或密码错误！");
											}else{
												location.href = "./index.jsp";
											} */
										}
									});
						});
		function selectedTask(select_id) {

			selected_task.task[select_id];
			console.log(selected_task.task[select_id]);
			$("#taskname").val(selected_task.task[select_id].task_name);
			$("#ttype").val(selected_task.task[select_id].ttype);
			$("#ttype").trigger('changed.selected.amui');
			//type1.changed.selected.amui;
			$("#course").val(selected_task.task[select_id].cno);
			console.log($("#course").val());
			$("#course").trigger('changed.selected.amui');
			/* $("#ttype option").each(function(){//遍历所有option  
			      var txt = $(this).val();   //获取option值   
			      if(txt==selected_task.task[select_id].ttype){
			    	  $(this).attr("selected","selected");
			      }
			 }); */
			/* 			$("#course").val(selected_task.task[select_id].cno);
			 $("#course option").each(function(){  //遍历所有option  
			 var txt = $(this).val();   //获取option值   
			 console.log(txt);
			 if(txt==selected_task.task[select_id].cno){
			 console.log("zhixing");
			 $(this).attr("selected",true);
			 // $(this).selected="selected";
			 }  
			 });
			 $("#course").find("option:contains("+selected_task.task[select_id].cno+")").attr("selected","selected"); */
			/* for(var i=0;i<selected_task.task[select_id].){
				
			}
			var html=""; */
			$("#selectedChapter").html();
			$("#ttype").val(selected_task.task[select_id].ttype);
			$("#ttype").val(selected_task.task[select_id].ttype);
			var $modal = $('#my-popup');
			$modal.modal('close');
			var type1 = document.getElementById("course");
			var arr2 = type1.getElementsByTagName("option");
			for (var i = 0; i < arr2.length; i++) {
				if (arr2[i].value == selected_task.task[select_id].cno) {
					arr2[i].selected = true;
				}
			}
		}
	</script>
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/app.js"></script>
	<%-- <script src="../assets/js/amazeui.min.js"></script> --%>
	<script language="javascript" src="../js/jquery.js"></script>
	<script language="javascript" src="../js/amazeui.js"></script>
	<!--<![endif]-->
</body>
</html>