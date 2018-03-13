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
					<ul class="am-icon-users">消息回复
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="Message-queryAllMessage.action?isFirst=0">消息列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-form am-g" >
					<div class="am-panel am-panel-secondary" style="width:700px;margin:0 auto">
						<div class="am-panel-hd" id="Qcontent"><s:property value="#request['messgeDetail'][0].Qcontent"/></div>
						<div class="am-panel-bd">面板内容</div>
					</div>
					<br>

					<ul class="am-comments-list am-comments-list-flip" style="width:800px;margin:0 auto" id="ul">
						<li class="am-comment am-comment-highlight">
							<a href="#link-to-user-home">
								<img src="" alt="" class="am-comment-avatar" width="48" height="48" />
							</a>
							<div class="am-comment-main">
									<header class="am-comment-hd">
										<!--<h3 class="am-comment-title">评论标题</h3>-->
										<div class="am-comment-meta">
											<a href="#link-to-user" class="am-comment-author"><s:property value="#request['messgeDetail'][0].Sname"/></a> 提问于
											<time datetime="2013-07-27T04:54:29-07:00"><s:property value="#request['messgeDetail'][0].Ask_time"/></time>
										</div>
									</header>
									<div class="am-comment-bd"><s:property value="#request['messgeDetail'][0].Ask_content"/></div>
							</div>
						</li>
						<s:if test="#request['answer'].ask_no==''">
						</s:if>
						<s:else>
							<s:iterator id="answer" value="#request['answer']">
								<li class="am-comment am-comment-flip">
									<a href="#link-to-user-home">
										<img src="" alt="" class="am-comment-avatar" width="48" height="48" />
									</a>
									<div class="am-comment-main">
										<header class="am-comment-hd">
											<!--<h3 class="am-comment-title">评论标题</h3>-->
											<div class="am-comment-meta">
												<a href="#link-to-user" class="am-comment-author"><s:property value="#answer.tname"/></a> 回答于
												<time datetime="2013-07-27T04:54:29-07:00"><s:property value="#answer.atime"/></time>
											</div>
										</header>
										<div class="am-comment-bd"><s:property value="#answer.acontent"/></div>
									</div>
								</li>
							</s:iterator>
						</s:else>
						
					</ul>
					<br>
					<div class="you" style="margin:0 auto;width:700px">
						<textarea id="replay" style="width:700px;margin:0 auto;" id="replay" required></textarea>
					</div>
					<br>
					<div style="margin:0 auto;width:300px">
						<button type="button" onClick="replayAnswer()" class="am-btn am-btn-success am-radius">回复</button>
						&nbsp;  &nbsp;
						<button type="button" class="am-btn am-radius">取消</button>
					</div>
					<br>
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
	<script type="text/javascript" src="../kd/kindeditor.js"></script>
	<script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/app.js"></script>
	<script src="../assets/js/amazeui.min.js"></script>
	<script>
			KindEditor.ready(function(K) {
				editor = K.create('#replay',{
					resizeType : 0,
					allowPreviewEmoticons : false,
					allowImageUpload : true,
					items : [
					 		'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					 		'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					 		'justifyfull', 'insertorderedlist', 'insertunorderedlist',  'subscript',
					 		'superscript', 'clearhtml', 'selectall','image', 'multiimage',
					 		  'table','|', '/','fontname', 'fontsize','forecolor', 'hilitecolor', 'bold',
					 		'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|'],
				});
			});
	</script>
	<script type="text/javascript">
		function replayAnswer(){
			editor.sync();//同步编辑器
			var d = new Date();
			var Atime = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate() + " " +d.getHours()+ ":" + d.getMinutes()+":"+d.getSeconds();
			var Acontent=document.getElementById("replay").value;
			var ask_no='${request.messgeDetail[0].ano}';
			var tno='${request.messgeDetail[0].tno}';
			var tname='${request.messgeDetail[0].tname}';
			var qid='${request.messgeDetail[0].qid}';
			var tid='${request.messgeDetail[0].tid}';
			var obj = new Object();
			if(Acontent.trim()!=""){
				document.getElementById("ul").innerHTML+="<li class='am-comment am-comment-flip'>"
                    +"<a href='#link-to-user-home'>"
                    +"<img src='' alt='' class='am-comment-avatar' width='48' height='48' />"
                    +"</a>"
                    +"<div class='am-comment-main'>"
					 +"<header class='am-comment-hd'>"
					 +"<div class='am-comment-meta'>"
					 +"<a href='#link-to-user' class='am-comment-author'>"+tname+"</a> 回答于"
					 +"<time datetime='2013-07-27T04:54:29-07:00'>"+Atime+"</time>"
					 +"</div>"
					 +"</header>"
					 +"<div class='am-comment-bd'>"+Acontent+"</div>"
					 +"</div>"
					 +"</li>";
				editor.html('');//把编辑器中的内容赋为空值
				obj.ask_no=ask_no;
				obj.atime=Atime;
				obj.acontent=Acontent;
				obj.tno=tno;
				obj.tname=tname;
				obj.qid=qid;
				obj.tid=tid;
				obj = JSON.stringify(obj);
				console.log(obj);
				$.ajax({
				type : "POST",
				url : "Answer-addAnswer.action",
				data : {"obj":obj},
				dataType : "json",
				success : function(data) {
				var newdata = JSON.parse(data);
				var str1 = newdata[0].hidden;
				if (str1 === "1") {
				alert("用户名或密码错误！");
				}else{
				location.href = "./index.jsp";
				}
				}
				});
			}
			
		}
	</script>
</body>
</html>