<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>学生作业详情</title>
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
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/app.js"></script>
</head>
<body>
	<!--[if lte IE 9]><p class="browsehappy">升级你的浏览器吧！ <a href="http://se.360.cn/" target="_blank">升级浏览器</a>以获得更好的体验！</p><![endif]-->
</head>

<body>
	<header class="am-topbar admin-header">
		<div style="margin-top: 15px;margin: 0 auto;width:300px;height:40px;">
			<b style="font-size: 30px"><s:property
					value="#request['studentTaskInformation'].taskName" /></b>
		</div>
	</header>
	<div class="nav-navicon admin-main admin-sidebar"
		style="margin-top:-45px;background-color: #f0f0f0">
		<div style="background-color:#2a3542 ;border-radius:5px;height:35px;">
			<label style="font-size: 20px;margin:0px 0px 0px 55px ;color: white">页面导航</label>
		</div>
		<div style="background-color: #f0f0f0">
			<label style="font-size:18px;margin-top: 10px">学生信息</label>
			<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		</div>
		<div style="background-color: #f0f0f0">
			<ul style="margin-left: -20px;margin-bottom: 10px">
					<li style="list-style-type: none;margin-top: 10px">
						<font style="font-size:15px;font-weight: bolder;"> 
							学号&nbsp;&nbsp;&nbsp;
						</font> 
						<font style="font-size:15px;">
							<s:property value="#request['studentTaskInformation'].sno" />
						</font> 
					</li>
					<li style="list-style-type: none;margin-top: 10px">
						<font style="font-size:15px;font-weight: bolder;">
							姓名&nbsp;&nbsp;&nbsp;
						</font>
						<font style="font-size:15px;">
							<s:property value="#request['studentTaskInformation'].sname" />
							
						</font>
					</li>
					<li style="list-style-type: none;margin-top: 10px">
						<font style="font-size:15px;font-weight: bolder;"> 
							班级&nbsp;&nbsp;&nbsp;
						</font>
						<font style="font-size:15px;">
							<s:property value="#request['studentTaskInformation'].sclass" />
							
						</font> 
					</li>
			</ul>
		</div>
		<div style="background-color: #f0f0f0;margin-top: 20px">
			<label style="font-size:18px;">作业信息</label>
			<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		</div>
		<div style="background-color: #f0f0f0">
			<li style="list-style-type: none;font-size:15px;color:	#1E90FF;">所属课程：<s:property value="#request['studentTaskInformation'].courseName" /></li>
			<li style="list-style-type: none;font-size:15px;color:	#1E90FF;">所属章节：<s:property value="#request['studentTaskInformation'].taskChapter" /></li>
		</div>
		<div style="text-align: right;margin-top: 10%">
			<p style="font-size: 20px;font-weight: bold;margin-right: 10%">分数：<span id="totalScore"><s:property value="#request['studentTaskInformation'].taskGrade"/></span></p>
		</div>
	</div>
	<div class=" admin-content">
		<div class="admin-biaogelist" style="background-color: white">
			<div class="fbneirong">
				<s:iterator value="#request['studentTaskDetail']" id="question"
					status="no">
					<div name="replaceQuestionDiv"
						id="replaceQuestion<s:property value="#no.getCount()"/>">
						<div class="am-panel am-panel-default"
							style="max-width:800px;margin-left: 100px">
							<div id="Question<s:property value="#no.getCount()"/>">
								<div class="am-panel-hd">
									<b style="font-size: 18px"> <s:property
											value="#no.getCount()" />.
									</b> (
									<s:property value="#question.qtype" />
									)
									<s:property value="#question.Qcontent" />
									(
									<s:property value="#question.score" />
									分)
								</div>
								<div class="am-panel-bd">
									<s:if test="#question.Qtype=='单选题' || #question.Qtype=='多选题'">
										<s:iterator value="#question.choice" id="choice">
											<s:if test='#choice.trim()!="" && #choice!="\n"'>
												<li type=A><s:property value="#choice" /><br></li>
											</s:if>
										</s:iterator>
										<a onCilck="" href="">答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案：</a>
										<b style="font-size: 15px"> <s:property
												value="#question.Qanswer" />
										</b>
										<br>
										<a onCilck="" href="">学生答案：</a>
										<b style="font-size: 15px"> <s:property
												value="#question.Answer" />
										</b>
										<s:if test="#question.Qanswer==#question.Answer">
											<img alt="" src="../images/true.jpg" width=30px heigth=20px>
											<strong>得分：<s:property value="#question.score" /></strong>
										</s:if>
										<s:else>
											<img alt="" src="../images/false.jpg" width=30px heigth=20px>
											<strong>得分：0.0</strong>
										</s:else>
									</s:if>
									<s:elseif test='#question.Qtype=="判断题"'>
										<a onCilck="" href="">答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案：</a>
										<b style="font-size: 15px"> <s:property
												value="#question.Qanswer" />
										</b>
										<br>
										<a onCilck="" href="">学生答案：</a>
										<b style="font-size: 15px"> <s:property
												value="#question.Answer" />
										</b>
										<s:if test="#question.Qanswer==#question.Answer">
											<img alt="" src="../images/true.jpg" width=30px heigth=20px>
											<strong>得分：<s:property value="#question.score"/></strong>
										</s:if>
										<s:else>
											<img alt="" src="../images/false.jpg" width=30px heigth=20px>
											<strong>得分：0.0</strong>
										</s:else>
									</s:elseif>
									<s:elseif test='#question.Qtype=="简答题"'>
									    <input type="hidden" value="<s:property value="#question.qid"/>" name="qid">
										<a onCilck="" href="">学生答案：</a>
										<div
											style="border:1px thin red;display: inline-block;margin-bottom: 20px">
											<b style="font-size: 15px"> <s:property
													value="#question.Answer" />
												fd东方舵手fd东方舵fd东方舵fd东方舵fd东方舵fdd东方舵手fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵
												d东方舵手fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵d东方舵手fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵
												d东方舵手fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵d东方舵手fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵fd东方舵
												东方舵fd东方舵
											</b>
										</div>
										<div>
											<s:if test="#question.grade!=''">
												<strong>得分：<s:property value="#question.grade"/></strong>
											</s:if>
											<s:else>
											<b>评分：</b> 
												<input type="number" name="short_answer_score"
													onBlur="short_answer_score(<s:property value="#question.score"/>,this)">
											</s:else>
											
										</div>
									</s:elseif>
								</div>
							</div>
						</div>
						<br>
					</div>
				</s:iterator>
				<div style="margin-left: 30%">
					<button type="button" class="am-btn am-btn-primary am-round" id="saveTaskGrade">确定</button>
					<button type="button" class="am-btn am-btn-primary am-round">取消</button>
				</div>
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
	<%-- <script src="../assets/js/amazeui.min.js"></script> --%>
	<!--<![endif]-->
	<script language="javascript" src="../dayin/jquery.jqprint-0.3.js"></script>
	<script language="javascript" src="../js/huixian.js"></script>
	<script language="javascript" src="../js/jquery.js"></script>
	<script language="javascript" src="../js/amazeui.js"></script>
	<script type="text/javascript">
		function short_answer_score(score,obj){
			var newScore=obj.value*1;
			var total=0;
			if(newScore<0){
				obj.value=0;
			}else if(newScore>score*1){
				obj.value=score;
			}
			var arr=document.getElementsByName("short_answer_score");
			for(var i=0;i<arr.length;i++){
				total=total+arr[i].value*1;
			}
			document.getElementById("totalScore").innerText=total+${studentTaskInformation.taskGrade}*1;
		}
	</script>
	<script type="text/javascript">
		$("#saveTaskGrade").click(function(){
			var qids=new Array();
			var arrIndex=new Array();
			var scores=new Array();
			var totalScore=$("#totalScore").text();
			var id="${studentTaskInformation.id}";
			
			var arrScore=$("input[name='short_answer_score']").each(function(index,el){
				if(el.value!=""){
					scores.push(el.value);
					arrIndex.push(index);
				}
				
			});
			var arrQid=$("input[name='qid']").each(function(index,el){
				for(var i=0;i<arrIndex.length;i++){
					if(arrIndex[i]==index){
						qids.push(el.value);
					}
				}
			});
			var qids = JSON.stringify(qids);
			var scores = JSON.stringify(scores);
			$.ajax({
				type : "POST",
				url : "ClassTaskDetail-saveSubmitTaskDetail.action",
				data : {"qids":qids,"scores":scores,"id":id,"totalScore":totalScore},
				dataType : "json",
				success:function(data){
					
				}
			});
		});
	</script>
</body>
</html>