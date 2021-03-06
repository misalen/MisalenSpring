<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:dt="http://thymeleafexamples">
<head
	th:replace="common/head :: common_header(~{::title},~{::script},~{::link})">
<meta charset="utf-8" />
<title>列表</title>
<script type="text/javascript"
	th:src="@{/plugins/common/js/list-constant.js}"></script>
<script type="text/javascript" th:inline="javascript">
	$(function() {
		initSubTable();
	});
	var baseUrl= [[@{${path}/}]];
	function addEntry(){
		var index = layer.open({
			type : 2,
			title : '添加',
			shadeClose : true,
			end:function(){
				$('#tb_list').bootstrapTable('refresh');
			},
			shade : 0.8,
			area : [ '650px', '80%' ],
			content :baseUrl+"add"
		});
	}
	function initSubTable() {
		$("#tb_list").bootstrapTable(new MyTable(baseUrl,[
		<#list list as item>
		<#if item.accessList>
		<#if item.elementId=='date'>
		{
			field: '${item.named.lowerCaseFirstName}',
			title: '${item.title}',
			formatter : function(value, row, index) {
				return format(new Date(value), 'yyyy-MM-dd');
			}
		},
		<#elseif item.elementId=='code'>
		{
			field: '${item.named.lowerCaseFirstName}',
			title: '${item.title}',
			formatter : function(value, row, index) {
				return $("#${item.dtcode} input[name='"+value+"']")[0].value;
			}
		},
		<#elseif item.elementId=='image'>
		{
			field: '${item.named.lowerCaseFirstName}',
			title: '${item.title}',
			formatter : function(value, row, index) {
				return '<img src="'+value+'" height="50"/>';
			}
		},
		<#elseif item.elementId=='datetime'>
		{
			field: '${item.named.lowerCaseFirstName}',
			title: '${item.title}',
			formatter : function(value, row, index) {
				return format(new Date(value), 'yyyy-MM-dd hh:mm:ss');
			}
		},
		<#elseif item.elementId=='date'>
		{
			field: '${item.named.lowerCaseFirstName}',
			title: '${item.title}',
			formatter : function(value, row, index) {
				return format(new Date(value), 'yyyy-MM-dd');
			}
		},
		<#else>
		{
			field: '${item.named.lowerCaseFirstName}',
			title: '${item.title}'
		},
		</#if>
		</#if>
		</#list>
		{
			field : 'addTime',
			title : '添加时间',
			formatter : function(value, row, index) {
				return format(new Date(value), 'yyyy-MM-dd hh:mm:ss');
			}
		}
		]).params());
	}
</script>
</head>
<body>
	<div id="toolbar">
		<table class="retrievals table noline">
			<tbody>
				<tr>
					<td>
						
					</td>
					<td style="text-align: right;">
						<button class="btn btn-sm btn-warning">
							<i class="glyphicon glyphicon-search"></i> 搜索
						</button>
						<button class="btn btn-sm btn-warning" onclick="addEntry()">
							<i class="glyphicon glyphicon-plus"></i> 添加
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="box box-primary">
		<div class="box-body">
			<table id="tb_list"></table>
		</div>
	</div>
	<#list list as item>
	<#if item.accessList>
	<#if item.elementId== 'code' >
	<dt:html type="input" code="${item.dtcode}">
	</#if>
	</#if>
	</#list>
</body>
</html>