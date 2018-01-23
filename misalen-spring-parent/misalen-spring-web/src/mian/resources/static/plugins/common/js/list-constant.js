var defaultTableParams = {
	method : 'post',
	toolbarAlign:'right',
	toolbar : '#toolbar', // 工具按钮用哪个容器
	clickToSelect:true,
	queryParamsType : "nolimit",
	//showColumns: true,
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sidePagination : "server", // 服务端分页
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 10, // 每页的记录行数（*）
	pageList : [],
	queryParams : function queryParams(params) {
		var retrievals=getRetrievals();
		var param = {
			"page" : parseInt(params.pageNumber),
			"rows" : params.limit,
			"desc" : ["addTime"],
			"retrievals":retrievals
		};
		return param;
	},
	responseHandler : function(res) {
		return {
			"total" : res.data.total,
			"rows" : res.data.list
		};
	}
};
function getRetrievals(){
	var retrievals=new Array();
	$("table.retrievals input.form-control").each(function(){
		var conditionA=$(this).data("condition");
		var valueA=$(this).val();
		var type=$(this).prop("type");
		if(valueA&&type=='number'&&$(this).data("ratio")){
			valueA=parseInt(valueA*$(this).data("ratio"));
		}
		var nameA=$(this).prop("name");
		if(conditionA&&valueA&&nameA){
			retrievals.push({
				"condition":conditionA,
				"name":nameA,
				"value":valueA
			});
		}
	});
	$("table.retrievals select.form-control").each(function(){
		var valueA=$(this).val();
		var nameA=$(this).prop("name");
		if(valueA&&nameA){
			retrievals.push({
				"condition":"eq",
				"name":nameA,
				"value":valueA
			});
		}
	});
	return retrievals;
}

function defaultEvent(baseUrl, tableId) {
	return {
		'click .BtnOfUpdate' : function(e, value, row, index) {
			var index = layer.open({
				type : 2,
				title : '编辑',
				shadeClose : true,
				end : function() {
					$('#' + tableId).bootstrapTable('refresh');
				},
				shade : 0.8,
				area : [ '650px', '80%' ],
				content : baseUrl + "update/" + row.primaryKey
			});
		},
		'click .BtnOfDel' : function(e, value, row, index) {
			layer.confirm('确定删除?', {
				btn : [ '删除', '取消' ]
			}, function() {
				$.get(baseUrl + "del/" + row.primaryKey, function(result) {
					if (result.success) {
						$('#' + tableId).bootstrapTable('refresh');
						layer.msg('删除成功', {
							icon : 1,
							time : 1000
						});
					}else{
						layer.msg('删除失败', {
							icon : 1,
							time : 1000
						});
					}
				}, "json");
			}, function() {
				layer.closeAll('dialog');
			});
		}
	}
}

var MyTable = function(baseUrl, columns, operate, tableParams, tableId) {
	if (!tableId) {
		tableId = 'tb_list';
	}
	if (tableParams == null) {
		tableParams = {};
	}
	if (tableParams.url == null) {
		tableParams.url = baseUrl + "list";
	}
	if (operate == null) {
		operate = {
			field : 'operate',
			title : '操作',
			align : 'center',
			events : defaultEvent(baseUrl,tableId),
			formatter : function(value, row, index) {
				return [
					'<button type="button" class="BtnOfUpdate pointer btn btn-warning btn-xs"><i class="glyphicon glyphicon-edit"></i>&nbsp;编辑</button>',
					'<button type="button" class="BtnOfDel pointer btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash"></i>&nbsp;删除</button>', ]
						.join('');
			}
		};
	}
    columns.splice(0,0,{
    	width:20,
        title: '序号',
        formatter: function (value, row, index) {  
            return index+1;  
        }  
    });
	columns.push(operate);
	tableParams.columns = columns;
	var myTableParams = $.extend({}, defaultTableParams, tableParams);
	this.params = function() {
		return myTableParams;
	};
	return this;
}

function format(date,fmt){
	var o = {
	        "M+": date.getMonth() + 1, // 月份
	        "d+": date.getDate(), // 日
	        "h+": date.getHours(), // 小时
	        "m+": date.getMinutes(), // 分
	        "s+": date.getSeconds(), // 秒
	        "q+": Math.floor((date.getMonth() + 3) / 3), // 季度
	        "S": date.getMilliseconds() // 毫秒
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
}
$(function(){
$('body').on('click', '.layui-layer-btn0', function(e) {
	 $(this).attr("disabled", true); 
});
$('body').on('click', '.loading', function(e) {
	 $(this).attr("disabled", true); 
});
$('body').on('click', '.close-layer', function(e) {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
});
}); 