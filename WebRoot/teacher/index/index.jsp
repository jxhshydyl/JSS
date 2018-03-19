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
<title>作业提交系统</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="/teacher/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="/teacher/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="../assets/css/amazeui.min.css" />
<link rel="stylesheet" href="../assets/css/admin.css">
</head>
<body>
</head>
<body scrolling="no">
	<s:if test="#session['teacher']==null">
		<script type="text/javascript">
			window.location = "../index/login.html";
		</script>
	</s:if>
	<header class="am-topbar admin-header">
		<div class="am-topbar-brand">
			<img src="../assets/i/logo.png">
		</div>
		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
			<ul class="am-nav am-nav-pills am-topbar-nav admin-header-list">
				<li class="am-dropdown tognzhi" data-am-dropdown>
					<button
						onclick="javascript:rightFrame.location.href='Message-queryAllMessage.action?isFirst=0'"
						class="am-btn am-btn-primary am-dropdown-toggle am-btn-xs am-radius am-icon-bell-o"
						data-am-dropdown-toggle>
						消息管理<span class="  am-round">&nbsp;</span>
					</button>
				</li>
				<li class="kuanjie"><a href="#">个人中心</a> <a href="#">系统设置</a></li>
			</ul>
		</div>
	</header>
	<div class="am-cf admin-main">
		<div class="nav-navicon admin-main admin-sidebar">
			<div class="sideMenu am-icon-dashboard"
				style="color: #aeb2b7; margin: 10px 0 0 0;">欢迎教师：***</div>
			<div class="sideMenu">
				<h3 class="am-icon-users">
					<em></em> <a href="#">班级管理</a>
				</h3>
				<ul>
					<li><a href="Class-queryAllClass.action?isFirst=0"
						target="rightFrame">班级列表</a></li>
				</ul>
				<h3 class="am-icon-flag">
					<em></em> <a href="#">作业管理</a>
				</h3>
				<ul id="work-list">
					<li><a href="Task-queryAllTask.action" target="rightFrame">作业列表</a></li>
					<li><a href="../task/arrangeTask.jsp" target="rightFrame">作业安排</a></li>
					<li><a href="ArrangedTask-queryArrangedTask.action?isFirst=0"
						target="rightFrame">在线批阅</a></li>
				</ul>
				<h3 class="am-icon-cart-plus">
					<em></em> <a href="#">资料管理</a>
				</h3>
				<ul>
					<li><a href="Question-queryAllQuestion.action?isFirst=0"
						target="rightFrame">试题列表</a></li>
					<li><a href="../corpus/uploadCorpus.html" target="rightFrame">资料上传</a></li>
				</ul>
				<h3 class="am-icon-volume-up">
					<em></em> <a href="#">成绩管理</a>
				</h3>
				<ul>
					<li><a href="../grade/taskGrade.jsp" target="rightFrame">作业成绩统计</a></li>
					<li>课程成绩统计</li>
				</ul>
			</div>
			<div style="color: #2a3542; height: 200px"></div>
			<!-- sideMenu End -->
		</div>
		<iframe src="homepage.html" frameborder=0 scrolling="no" name="rightFrame" id="external-frame" onload="changeFrameHeight()"></iframe>
	</div>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/app.js"></script>
	<script src="../assets/js/amazeui.min.js"></script>
	<script type="text/javascript">
		jQuery(".sideMenu").slide({
			titCell : "h3", //鼠标触发对象
			targetCell : "ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
			effect : "slideDown", //targetCell下拉效果
			delayTime : 200, //效果时间
			triggerTime : 10, //鼠标延迟触发时间（默认150）
			defaultPlay : false,//默认是否执行效果（默认true）
			returnDefault : false
		//鼠标从.sideMen移走后返回默认状态（默认false）
		});
		function changeFrameHeight() {
			var ifm = document.getElementById("external-frame");
			ifm.height = document.documentElement.clientHeight-70;
			ifm.width = document.documentElement.clientWidth;

		}
		window.onresize = function() {
			changeFrameHeight();

		}
	</script>
	<script type="text/javascript">
		var $menu = $('.sideMenu');
		$menu.click(function(event) {
			var $target = $(event.target);
			if ($('.selected')) {
				$('.selected').removeClass('selected');
			}
			$target.parents('ul').prev('h3').addClass('selected');
		});
		$menu.mouseleave(function(event) {
			var $selected = $('.selected');
			if ($selected.length !== 0) {
				var $temp = $('.on');
				if ($temp[0] !== $selected[0]) {
					$temp.next().css('display', 'none');
					$temp.removeClass('on');
					$selected.addClass('on');
					$selected.next().slideToggle("200", function() {
						$selected.next().css('display', 'block');
					});
				}
			} else {
				var $temp = $('.on');
				$temp.removeClass('on');
				$temp.next().css('display', 'none');
			}
		});
	</script>
</body>
</html>