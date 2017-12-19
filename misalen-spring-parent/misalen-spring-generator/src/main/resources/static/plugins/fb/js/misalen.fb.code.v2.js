var defaultOptions = {
	itemClick : function(row) {
		return true;
	},
	controlGroupClass : 'misalen-fb-control-group',
	elementGroupClass : 'misalen-fb-element-group',
	compGroupClass : 'misalen-fb-comp-group'
};
$.fn.MisalenFb = function(options) {
	var main = this;
	var controlMap = new Map();
	var newOptions = $.extend({}, defaultOptions, options);
	for (key in MisalenFbElement) {
		var plugin = MisalenFbElement[key];
		$('.' + newOptions.elementGroupClass).append(
				'<div class="misalen-fb-element" misalen-fb-element-id="' + key
						+ '">' + plugin.text + '</div>');
	}
	/** 合并参数 * */
	$(this).find('.misalen-fb-element').draggable({
		helper : "clone",
		connectToSortable : '.' + newOptions.controlGroupClass,
		revert : "invalid",
		cursor : "move"
	});
	/** 拖拽和排序 * */
	$(this).find('.' + newOptions.controlGroupClass).sortable({
		axis : "y",
		placeholder : "misalen-state-highlight",
		stop : function(event, ui) {
			var item = ui.item;
			if (!item.hasClass('misalen-fb-element')) {
				return true;
			}
			var elementId = item.attr('misalen-fb-element-id');
			var controlId = addControl(item, elementId);
			controlData(elementId, controlId);
		},
	});
	$(this).find('.' + newOptions.controlGroupClass).disableSelection();
	/** 元素点击 * */
	$(this).on('click', '.misalen-fb-element', function(e) {
		var item = $(this);
		var elementId = item.attr('misalen-fb-element-id');
		var controlId = addControl(null, elementId);
		controlData(elementId, controlId);
	});
	/** control点击 * */
	$(this).on('click', '.misalen-fb-control', function(e) {
		var control = $(this);
		controlElectStyle(control);
		if (newOptions.itemClick()) {
			var elementId = control.attr('misalen-fb-element-id');
			var controlId = control.attr('misalen-fb-control-id');
			resetComp(elementId, controlId);
		}
	});

	/** control删除按钮点击事件 * */
	$(this).on('click', '.misalen-fb-remove', function(e) {
		e.stopPropagation();
		controlMap.removeByKey($(this).parent().attr('misalen-fb-control-id'));
		$(this).parent().remove();
	});
	/** control选中样式 * */
	var controlElectStyle = function(control) {
		$('.misalen-fb-control-click').removeClass('misalen-fb-control-click');
		$("div").remove(".misalen-fb-remove");
		control
				.append('<div class="misalen-fb-remove"><i class="glyphicon glyphicon-trash "></i></div>')
		control.addClass('misalen-fb-control-click');
	}
	/** 重置组件区域 * */
	var resetComp = function(elementId, controlId) {
		var comps = MisalenFbRelational[elementId];
		var compGroup = $("." + newOptions.compGroupClass);
		compGroup.empty();
		compGroup.attr('misalen-fb-control-id', controlId)
		if (!comps) {
			return;
		}
		for (var i = 0; i < comps.length; i++) {
			var compId = comps[i];
			var html = $(MisalenFbComp[compId].html);
			compGroup.append(html);
			var controlMapItem = controlMap.get(controlId);
			if (controlMapItem) {
				var item = html.find('.misalen-fb-comp');
				var code = MisalenFbComp[compId].code;
				item.attr("misalen-fb-comp-code", code);
				if (item.is('select')) {
					item.find("option[value='" + controlMapItem[code] + "']")
							.attr("selected", true);
				} else if (item.is('input')) {
					if (item.attr('type') == 'checkbox') {
						item.attr("checked", controlMapItem[code]);
					} else {
						item.val(controlMapItem[code]);
					}
				}
			}
		}
	}
	/** 元素被修改时 * */
	$(this).on(
			'change',
			'.misalen-fb-comp',
			function(e) {
				var controlId = $("." + newOptions.compGroupClass).attr(
						'misalen-fb-control-id');
				saveControlData($(this), controlId);
				showControl(controlId);
			});
	/** 保存数据 * */
	var saveControlData = function(input, controlId) {
		var code = input.attr("misalen-fb-comp-code");
		var controlMapItem = controlMap.get(controlId);
		if (input.is('select')) {
			controlMapItem[code] = input.val();
		} else if (input.is('input')) {
			if (input.attr('type') == 'checkbox') {
				controlMapItem[code] = input.is(':checked');
			} else {
				controlMapItem[code] = input.val();
			}
		}
	}
	var showControl = function(controlId) {
		var controlMapItem = controlMap.get(controlId);
		for (key in controlMapItem) {
			if (key == 'title') {
				var parent = $('[misalen-fb-control-id=' + controlId + ']');
				parent.find('.misalen-fb-control-title').text(
						controlMapItem[key]);
			} else if (key == 'placeholder') {
				var parent = $('[misalen-fb-control-id=' + controlId + ']');
				parent.find('.misalen-fb-control-content').attr('placeholder',
						controlMapItem[key]);
			}
		}
	}
	/** 把 control的默认值保存到map中* */
	var controlData = function(elementId, controlId) {
		var controlMapItem = controlMap.get(controlId);
		if (!controlMapItem) {
			controlMapItem = {
				elementId : elementId
			};
			var parent = $('[misalen-fb-control-id=' + controlId + ']');
			var input = parent.find('.misalen-fb-control-content');
			if (input.is('select')) {
				controlMapItem['type'] = 'select';
			} else if (input.is('textarea')) {
				controlMapItem['type'] = 'textarea';
			} else if (input.is('input')) {
				controlMapItem['type'] = input.attr('type');
			}
			var comps = MisalenFbRelational[elementId];
			if (comps) {
				for (var i = 0; i < comps.length; i++) {
					var compId = comps[i];
					controlMapItem[MisalenFbComp[compId].code] = MisalenFbComp[compId].defaultValue;
				}
			}
			controlMap.put(controlId, controlMapItem);
		}
	}
	/** 添加一个control * */
	var addControl = function(parent, elementId) {
		if (!elementId) {
			if (parent) {
				parent.remove();
			}
			return;
		}
		if (parent) {
			parent.removeClass();
			parent.removeAttr("style");
		} else {
			parent = $('<div></div>');
			main.find('.' + newOptions.controlGroupClass).append(parent);
		}
		parent.addClass('misalen-fb-control');
		parent.attr('misalen-fb-element-id', elementId);
		var controlId = uuid();
		parent.attr('misalen-fb-control-id', controlId);
		parent.html(MisalenFbElement[elementId].html);
		return controlId;
	}
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
	/** 提供外部调用方法 * */
	var MisalenFB = {
		data : function() {
			var returnMap = new Array();
			$(main).find(".misalen-fb-control").each(
					function(index) {
						var element = controlMap.get($(this).attr(
								'misalen-fb-control-id'));
						element.index = index;
						returnMap.push(element);
					});
			return returnMap;
		},
		addControl : function(arry) {
			for (var i = 0; i < arry.length; i++) {
				var controlId = addControl(null, arry[i].elementId);
				controlMap.put(controlId, arry[i]);
				showControl(controlId);
			}
		}
	}
	return MisalenFB;
};