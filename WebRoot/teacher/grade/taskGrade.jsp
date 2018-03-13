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
<title>作业成绩统计</title>
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
						href="../index/homepage.html" target="rightFrame">首页</a></li>
				</ul>
			</div>
			<div class="admin-biaogelist" style="positioin:absolute;top:30px;">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-users">班级管理
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a>作业成绩统计</a>
					</dl>
					<!--这里打开的是新页面-->
				</div>
				<div class="am-btn-toolbars am-btn-toolbar am-kg am-cf">
					<form method="post">
						<ul>
							<li>
								<div class="am-btn-group am-btn-group-xs">
									<select id="Class" name="cc.Cno"
										data-am-selected="{btnWidth: 100, btnSize: 'sm', btnStyle: 'default'}">
										<option value="class">班级</option>
										<s:iterator value="#session['totalclass']" id="class">
											<option value="<s:property value="#class.cno"/>"><s:property
													value="#class.cname" /></option>
										</s:iterator>
									</select>
								</div>
							</li>
							<li style="margin-right: 0;"><span
								class="tubiao am-icon-calendar"></span> <input type="text"
								id="time_start" name="cc.time_start"
								class="am-form-field am-input-sm am-input-zm  am-icon-calendar"
								placeholder="" data-am-datepicker="{theme: 'success',}" readonly /></li>
							<li><button type="submit"
									class="am-btn am-radius am-btn-xs am-btn-success"
									style="margin-top: -1px;">搜索</button></li>

						</ul>
					</form>
				</div>

				<form class="am-form am-g">
					<div id="main" style="width: 100%;height:400px;"></div>
					<div id="main1" style="width: 100%;height:400px;"></div>
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
	<script type="text/javascript" src="../js/echarts.js"></script>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			color : [ '#3398DB' ],
			title : {
				text : '作业成绩',
				subtext : '纯属虚构'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '作业成绩' ]
			},
			toolbox : {
				show : true,
				feature : {
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : [ '第一次作业', '第二次作业', '第三次作业', '第四次作业', '第五次作业', '第六次作业',
						'第七次作业', '第八次作业', '第九次作业', '第十次作业' ]
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '作业成绩',
				type : 'bar',
				data : [ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 100.6, 23.3, 45.6,
						34.6 ],
				markPoint : {
					data : [ {
						type : 'max',
						name : '最高成绩'
					}, {
						type : 'min',
						name : '最低成绩'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均成绩'
					} ]
				}
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

		myChart.on('click', function(params) {
			if (params.componentType === 'series') {
				console.log(params.dataIndex);
				console.log(params.name);
				var data1=[ '第一次作业', '第二次作业', '第三次作业', '第四次作业', '第五次作业', '第六次作业',
							'第七次作业', '第八次作业', '第九次作业', '第十次作业' ];
				var data2=[ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 100.6, 23.3, 45.6,
							34.6 ]
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main1'));
				// 指定图表的配置项和数据
				var option = {
					color : [ '#3398DB' ],
					title : {
						text : '作业成绩',
						subtext : '纯属虚构'
					},
					tooltip : {
						trigger : 'axis',
						axisPointer : { // 坐标轴指示器，坐标轴触发有效
							type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					legend : {
						data : [ '作业成绩' ]
					},
					toolbox : {
						show : true,
						feature : {
							dataView : {
								show : true,
								readOnly : false
							},
							magicType : {
								show : true,
								type : [ 'line', 'bar' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : true,
					xAxis : [ {
						type : 'category',
						data :data1
					} ],
					yAxis : [ {
						type : 'value'
					} ],
					series : [ {
						name : '作业成绩',
						type : 'bar',
						data : data2,
						markPoint : {
							data : [ {
								type : 'max',
								name : '最高成绩'
							}, {
								type : 'min',
								name : '最低成绩'
							} ]
						},
						markLine : {
							data : [ {
								type : 'average',
								name : '平均成绩'
							} ]
						}
					} ]
				};
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			}
		});
	</script>
</body>
</html>