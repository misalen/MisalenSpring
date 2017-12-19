<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:dt="http://thymeleafexamples">
<head th:replace="common/head :: common_header(~{::title},~{::script},~{::link})">
<meta charset="utf-8" />
<title>列表</title>
<script type="text/javascript" th:src="@{/plugins/common/js/list-constant.js}"></script>
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
		<#if item.enumerationCode ?? >
		{
			field: '${item.name.lowerCaseFirstName}',
			title: '${item.comment}',
			formatter : function(value, row, index) {
				return $("#${item.enumerationCode} input[name='"+value+"']")[0].value;
			}
		},
		<#elseif item.filedType=='Date'>
		{
			field: '${item.name.lowerCaseFirstName}',
			title: '${item.comment}',
			formatter : function(value, row, index) {
				return format(new Date(value), 'yyyy-MM-dd hh:mm:ss');
			}
		},
		<#else>
		{
			field: '${item.name.lowerCaseFirstName}',
			title: '${item.comment}'
		},
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
	<div class="box box-primary">
		<div class="box-body">
			<table class="table noline">
				<tbody>
					<#list list as item>
					<#if item_index%2==0>
					<tr>
					</#if>
						<td>
							<#if item.enumerationCode ?? >
							<div class="input-group">
								<span class="input-group-addon explain">${item.comment}</span>
								<dt:html type="select" name="${item.name.lowerCaseFirstName}" class="form-control" code="${item.enumerationCode}" />
							</div>
							<#elseif item.filedType=='Date'>
							<div class="input-group">
								<span class="input-group-addon explain">${item.comment}</span>
								<div class="row">
									<div class="col-xs-6">
										<input type="date" data-condition="gte" name="${item.name.lowerCaseFirstName}" class="form-control">
									</div>
									<div class="col-xs-6">
										<input type="date" data-condition="lte" name="${item.name.lowerCaseFirstName}" class="form-control">
									</div>
								</div>
							</div>
							<#elseif item.filedType=='Integer'||item.filedType=='Long'>
							<div class="input-group">
								<span class="input-group-addon explain">${item.comment}</span>
								<div class="row">
									<div class="col-xs-6">
										<input type="number" data-condition="gte" data-ratio="1" name="${item.name.lowerCaseFirstName}" class="form-control">
									</div>
									<div class="col-xs-6">
										<input type="text" data-condition="lte" data-ratio="1" name="${item.name.lowerCaseFirstName}" class="form-control">
									</div>
								</div>
							</div>
							<#else>
							<div class="input-group">
								<span class="input-group-addon explain">${item.comment}</span> <input
									type="text" data-condition="like" name="${item.name.lowerCaseFirstName}" class="form-control">
							</div>
							</#if>
							
						</td>
					<#if item_index%2==1>
					</tr>
					</#if>
					</#list>
					<#if list?size%2==0>
					<tr>
					<td colspan="2" style="text-align: right;">
					<#elseif list?size%2==1>
					<td style="text-align: right;">
					</#if>
							<div class="btn-group">
								<button style="background-color: #009688;" type="button" class="btn btn-primary btn-sm" onclick="addEntry()">
									<i class="glyphicon glyphicon-plus"></i> 添加
								</button>
								<button type="button" class="btn btn-info btn-sm">
									<i class="glyphicon glyphicon-search"></i> 搜索
								</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="box box-primary">
		<div class="box-body">
			<table id="tb_list"></table>
		</div>
	</div>
	<#list list as item>
	<#if item.enumerationCode ?? >
	<dt:html type="input" code="${item.enumerationCode}">
	</#if>
	</#list>

</body>
</html>