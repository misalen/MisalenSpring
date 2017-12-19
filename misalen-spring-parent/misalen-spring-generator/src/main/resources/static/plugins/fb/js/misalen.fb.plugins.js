var MisalenFbElement = [];
MisalenFbElement['line'] = {
	text: "单行文字",
	html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<input type="text" class="form-control misalen-fb-control-content" disabled="disabled">\n' +
		'</div>\n'
}
MisalenFbElement['password'] = {
		text: "密码",
		html: '<div class="form-group">\n' +
			'<label class="misalen-fb-control-title">标题</label>\n' +
			'<input type="password" class="form-control misalen-fb-control-content" disabled="disabled">\n' +
			'</div>\n'
}
MisalenFbElement['lines'] = {
	text: "多行文字",
	html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<textarea rows="5" class="form-control misalen-fb-control-content" disabled="disabled"></textarea>\n' +
		'</div>\n'
}
MisalenFbElement['date'] = {
	text: "日期",
	html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<input type="date" value="2017-01-01" class="form-control misalen-fb-control-content" disabled="disabled">\n' +
		'</div>\n'
}
MisalenFbElement['datetime'] = {
	text: "时间",
	html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<input type="datetime" value="2017-01-01 01:01:01" class="form-control misalen-fb-control-content" disabled="disabled">\n' +
		'</div>\n'
}
MisalenFbElement['number'] = {
	text: "数字",
	html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<input type="number" class="form-control misalen-fb-control-content" disabled="disabled">\n' +
		'</div>\n'
}
MisalenFbElement['file'] = {
	text: "文件",
	html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<input type="file" class="form-control misalen-fb-control-content" disabled="disabled">\n' +
		'</div>\n'
}
MisalenFbElement['image'] = {
		text: "图片",
		html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<input type="file" class="form-control misalen-fb-control-content" disabled="disabled">\n' +
		'</div>\n'
}
MisalenFbElement['code'] = {
	text: "字典",
	html: '<div class="form-group">\n' +
		'<label class="misalen-fb-control-title">标题</label>\n' +
		'<select class="form-control misalen-fb-control-content" disabled="disabled"></select>\n' +
		'</div>\n'
}
var MisalenFbComp = [];
MisalenFbComp['1'] = {
	code: "title",
	defaultValue: "标题",
	html: '<div class="form-group">' +
		'<label>标题</label>' +
		'<input type="text" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['2'] = {
	code: "placeholder",
	defaultValue: "",
	html: '<div class="form-group">' +
		'<label>提示</label>' +
		'<input type="text" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['3'] = {
	code: "regularity",
	defaultValue: "",
	html: '<div class="form-group">' +
		'<label>正则校验</label>' +
		'<input type="text" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['4'] = {
	code: "length",
	defaultValue: "255",
	html: '<div class="form-group">' +
		'<label>文字长度</label>' +
		'<input type="number" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['5'] = {
	code: "max",
	defaultValue: "",
	html: '<div class="form-group">' +
		'<label>最大值</label>' +
		'<input type="number" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['6'] = {
	code: "min",
	defaultValue: "",
	html: '<div class="form-group">' +
		'<label>最小值</label>' +
		'<input type="number" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['7'] = {
	code: "decimals",
	defaultValue: "",
	html: '<div class="form-group">' +
		'<label>小数位</label>' +
		'<input type="number" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['8'] = {
	code: "dtcode",
	defaultValue: "",
	html: '<div class="form-group">' +
		'<label>字典标识</label>' +
		'<input type="text" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['9'] = {
		code: "suffix",
		defaultValue: "",
		html: '<div class="form-group">' +
		'<label>后缀</label>' +
		'<input type="text" class="form-control input-sm misalen-fb-comp">' +
		'</div>'
}
MisalenFbComp['10000'] = {
	code: "mandatory",
	defaultValue: "false",
	html: '<div class="checkbox">' +
		'<label>' +
		'<input class="misalen-fb-comp" type="checkbox">是否必填' +
		'</label>' +
		'</div>'
}
var MisalenFbRelational = [];
MisalenFbRelational['line'] = ['1', '2', '3', '4', '10000'];
MisalenFbRelational['password'] = ['1', '2', '3', '4', '10000'];
MisalenFbRelational['lines'] = ['1', '2', '3', '4', '10000'];
MisalenFbRelational['date'] = ['1', '10000'];
MisalenFbRelational['datetime'] = ['1', '10000'];
MisalenFbRelational['number'] = ['1', '2', '5', '6', '7', '10000'];
MisalenFbRelational['file'] = ['2', '10000'];
MisalenFbRelational['image'] = ['2', '10000'];
MisalenFbRelational['code'] = ['1', '8', '10000'];