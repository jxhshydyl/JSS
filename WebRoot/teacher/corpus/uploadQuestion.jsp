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
<title>批量上传题目</title>
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
					<ul class="am-icon-users">导入试题
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="Question-queryAllQuestion.action?isFirst=0">试题列表</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="fbneirong">
					<form class="am-form" action="add.action" enctype="multipart/form-data" method="post">
						<div class="am-form-group am-cf">
							<div class="you">
								<div class="am-btn-group doc-js-btn-1" data-am-button>
								<label class="am-btn am-btn-primary"> <input type="radio"
									name="format_type" value="Txt" id="option1"> Txt
								</label> <label class="am-btn am-btn-primary"> <input
									type="radio" name="format_type" value="Word" id="option2">
									Word
								</label> <label class="am-btn am-btn-primary"> <input
									type="radio" name="format_type" value="Excel" id="option3">
									Excel
									</label>
								</div>
								<div class="zuo">
									<a href="Download-downloadTemplate.action">下载模板</a>
							    </div>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="you">
								<div class="am-btn-group doc-js-btn-1" data-am-button>
									<label class="am-btn am-btn-primary"> 
										<input type="radio" name="question_type" value="code" id="option1"> 编程题型
									</label> 
									<label class="am-btn am-btn-primary"> 
										<input type="radio" name="question_type" value="noCode" id="option2"> 其他题型
									</label> 
								</div>
								<div class="zuo">
									<a>导入题型</a>
							    </div>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo"></div>
							<div class="you">
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo"></div>
							<div class="you">
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">试题附件：</div>
							<div class="you" style="height: 45px;">
								<input type="file" id="doc-ipt-file-1" name="file" >
								<p class="am-form-help">请选择要上传的文件...</p>
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="you" style="margin-left: 11%;">
								<button type="submit" class="am-btn am-btn-success am-radius">保存</button>
								&nbsp;  &nbsp;
								<a  class="am-btn am-btn-secondary am-radius">取消</a>
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
	<script>
		// 获取选中的值
		$(function() {
			var $radios = $('[name="options"]');
			$radios.on('change', function() {
				console.log('单选框当前选中的是：', $radios.filter(':checked').val());
			});
		});
	</script>
</body>
</html>