var misalenProcessDefaults = {
	processData : {}, // 步骤节点数据
	canvasDbClick : function(x, y) {
	},
	fnDbClick : function(row) {
	},
	canvasMenus : {},
	processMenus : {},
	/* 右键菜单样式 */
	menuStyle : {
		border : '1px solid #5a6377',
		minWidth : '150px',
		padding : '5px 0'
	},
	itemStyle : {
		fontFamily : 'verdana',
		color : '#333',
		border : '0',
		padding : '5px 40px 5px 20px'
	},
	itemHoverStyle : {
		border : '0',
		color : '#fff',
		backgroundColor : '#5a6377'
	},
	connectorPaintStyle : {
		strokeWidth : 3,
		stroke : "#49afcd"
	},
	connectorHoverStyle : {
		strokeWidth : 3,
		stroke : "#da4f49"
	}
}; /* defaults end */

var misalenControlsDefaults = {
	controls : function(row) {
		var id = row.primaryKey ? row.primaryKey : uuid();
		var nodeId = "misalen" + id;
		if (row.nodeType == 'start') {
			var nodeDiv = document.createElement('div');
			$(nodeDiv)
					.addClass("mi_square mi_effect mi_target")
					.attr("id", nodeId)
					.attr("style", row.style)
					.html(
							'<img class="mi_start mi_source" src="../../../plugins/designer/images/start.png" />');
			return nodeDiv;
		} else if (row.nodeType == 'process') {
			var nodeDiv = document.createElement('div');
			$(nodeDiv).addClass("mi_rectangles mi_effect mi_target").attr("id",
					nodeId).attr("style", row.style).html(
					'<div class="mi_source mi_arrow"></div><div class="mi_text">'
							+ row.nodeName + '</div>');
			return nodeDiv;
		} else if (row.nodeType == 'differences') {
			var nodeDiv = document.createElement('div');
			$(nodeDiv)
					.addClass("mi_diamond mi_effect mi_target")
					.attr("id", nodeId)
					.attr("style", row.style)
					.html(
							'<img class="mi_move mi_source" src="../../../plugins/designer/images/move.png" />');
			return nodeDiv;
		} else if (row.nodeType == 'stop') {
			var nodeDiv = document.createElement('div');
			$(nodeDiv)
					.addClass("mi_shape mi_effect mi_target")
					.attr("id", nodeId)
					.attr("style", row.style)
					.html(
							'<img class="mi_stop" src="../../../plugins/designer/images/stop.png" />');
			return nodeDiv;
		}
	}
}
$.fn.MisalenProcess = function(options) {
	var map = new Map();
	var _canvas = $(this);
	$.each(options, function(i, val) {
		if (typeof val == 'object' && misalenProcessDefaults[i]) {
			$.extend(misalenProcessDefaults[i], val);
		} else {
			misalenProcessDefaults[i] = val;
		}
	});
	var instance = jsPlumb.getInstance({
		DragOptions : {
			cursor : 'pointer'
		},
		EndpointStyle : {
			fillStyle : '#225588'
		},
		Endpoint : [ "Dot", {
			radius : 1
		} ],
		ConnectionOverlays : [ [ "Arrow", {
			location : 1
		} ], [ "Label", {
			location : 0.5,
			id : "label",
			cssClass : "mi_label"
		} ] ],
		Anchor : 'Continuous',
		Connector : 'Bezier',
		ConnectorZIndex : 5,
		HoverPaintStyle : misalenProcessDefaults.connectorHoverStyle,
		Container : "canvas"
	});
	// 连线单击
	instance.bind("click", function(c) {
		instance.deleteConnection(c);
	});
	// 删除连线事件
	instance.bind("connectionDetached", function(info) {
		var sourceId = info.sourceId.replace("misalen", "");
		var targetId = info.targetId.replace("misalen", "");
		if (map.containsKey(sourceId)) {
			var source = map.get(sourceId);
			if (source.sysFlowNodeAttas) {
				$.each(source.sysFlowNodeAttas, function(ii, process) {
					if (process.targetId == targetId) {
						source.sysFlowNodeAttas.splice(ii, 1);
					}
				});
			}
		}
	});
	// 连线连接事件
	instance.bind("connection", function(info) {
		var sourceId = info.sourceId.replace("misalen", "");
		var targetId = info.targetId.replace("misalen", "");
		if (map.containsKey(sourceId)) {
			var source = map.get(sourceId);
			var flag = true;
			if (source.sysFlowNodeAttas) {
				$.each(source.sysFlowNodeAttas, function(ii, process) {
					if (process.targetId == targetId) {
						if (source.nodeType == 'differences') {
							info.connection.getOverlay("label").setLabel(
									process.nodeAttaName);
						}
						flag = false;
						return true;
					}
				});
			}
			if (flag) {
				var process = {
					"nodeAttaName" : "下一步",
					"nodeId" : sourceId,
					"targetId" : targetId
				};
				if (!source.sysFlowNodeAttas) {
					source.sysFlowNodeAttas = [];
				}
				source.sysFlowNodeAttas.push(process);
				if (source.nodeType == 'differences') {
					info.connection.getOverlay("label").setLabel("下一步");
				}
			}
		}
	});
	jsPlumb.fire("jsPlumbDemoLoaded", instance);
	// 初始化节点
	var initNode = function(el, row) {
		instance.draggable(el);
		if (row.nodeType != 'stop') {
			var max = 1;
			if (row.nodeType == 'differences') {
				max = -1;
			}
			instance.makeSource(el, {
				filter : ".mi_source",
				anchor : "Continuous",
				endpoint : [ "Dot", {
					radius : 1
				} ],
				connector : [ "Flowchart", {
					stub : [ 10, 20 ],
					gap : 0,
					cornerRadius : 5,
					alwaysRespectStubs : true
				} ],
				connectorStyle : misalenProcessDefaults.connectorPaintStyle,
				hoverPaintStyle : misalenProcessDefaults.connectorHoverStyle,
				dragOptions : {},
				maxConnections : max
			});
		}
		if (row.nodeType != 'start') {
			instance.makeTarget(el, {
				dropOptions : {
					hoverClass : "hover",
					activeClass : "active"
				},
				anchor : "Continuous",
				maxConnections : -1,
				endpoint : [ "Dot", {
					radius : 1
				} ],
				paintStyle : {
					fillStyle : "#ec912a",
					radius : 1
				},
				hoverPaintStyle : this.connectorHoverStyle,
				beforeDrop : function(params) {
					if (params.sourceId == params.targetId) {
						return false;
					} else {
						var sourceId = params.sourceId.replace("misalen", "");
						var targetId = params.targetId.replace("misalen", "");
						if (map.containsKey(sourceId)) {
							var source = map.get(sourceId);
							if (source.sysFlowNodeAttas) {
								var flag = true;
								$.each(source.sysFlowNodeAttas, function(ii,
										process) {
									if (process.targetId == targetId) {
										flag = false;
										return true;
									}
								});
								return flag;
							}
						}
						return true;
					}

				}
			});
		}
		instance.fire("jsPlumbDemoNodeAdded", el);
	};
	// 删除节点
	var delNode = function(id) {
		map.removeByKey(id);
		var keys = map.keys();
		for (var ii = 0; ii < keys.length; ii++) {
			var attas = map.get(keys[ii]).sysFlowNodeAttas;
			if (attas) {
				for (var iii = 0; iii < attas.length; iii++) {
					if (attas[iii].targetId == id) {
						attas.splice(iii, 1);
					}
				}
			}
		}
		$('#misalen' + id).remove();
		init(MisalenProcess.data());
	}
	// 添加节点
	var newNode = function(row, x, y) {
		var nodeDiv = misalenControlsDefaults.controls(row);
		$(nodeDiv).mousedown(function(e) {
			if (e.which == 3) {
				contextmenu.bindings = misalenProcessDefaults.processMenus
				$(this).contextMenu('processMenu', contextmenu);
			}
		});
		instance.getContainer().appendChild(nodeDiv);
		initNode(nodeDiv, row);
		return nodeDiv;
	};
	// 连接连线
	var attachment = function(row) {
		if (row && row.sysFlowNodeAttas && row.primaryKey) {
			var process_to = row.sysFlowNodeAttas;
			if (process_to) {
				$.each(process_to, function(ii, process) {
					if (map.containsKey(process.targetId)) {
						instance.connect({
							source : "misalen" + row.primaryKey,
							target : "misalen" + process.targetId
						});
					}
				});
			}
		}

	}
	// 初始化数据
	var init = function(arry) {
		_canvas.empty();
		$.each(arry, function(i, row) {
			map.removeByKey(row.primaryKey);
			map.put(row.primaryKey, row);
			newNode(row);
		});
		$.each(arry, function(i, row) {
			attachment(row);
		});
	}

	// 画板双击
	var canvas = document.getElementById("canvas");
	jsPlumb.on(canvas, "dblclick", function(e) {
		misalenProcessDefaults.canvasDbClick(e.offsetX, e.offsetY);
	});
	/* 画布右键绑定 */
	var contextmenu = {
		bindings : misalenProcessDefaults.canvasMenus,
		menuStyle : misalenProcessDefaults.menuStyle,
		itemStyle : misalenProcessDefaults.itemStyle,
		itemHoverStyle : misalenProcessDefaults.itemHoverStyle
	}
	$(this).contextMenu('canvasMenu', contextmenu);
	// 节点双击
	$(this).on(
			'dblclick',
			'.mi_target',
			function(e) {
				misalenProcessDefaults.fnDbClick(map.get($(this).attr("id")
						.replace("misalen", "")));
			});
	var uuid = function() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 36; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4";
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
		s[8] = s[13] = s[18] = s[23];
		var uuid = s.join("");
		return uuid;
	}
	init(misalenProcessDefaults.processData.list);
	var MisalenProcess = {
		newNode : function(row) {
			newNode(row);
			attachment(row);
			map.put(row.primaryKey, row);
		},
		delNode : function(id) {
			id = id.replace("misalen", "");
			delNode(id);
		},
		getNode : function(id) {
			id = id.replace("misalen", "");
			return map.get(id);
		},
		data : function() {
			var keys = map.keys();
			for (var ii = 0; ii < keys.length; ii++) {
				map.get(keys[ii]).style = $('#misalen' + keys[ii])
						.attr('style');
			}
			return map.values();
		}
	}
	return MisalenProcess
}
