<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:dt="http://thymeleafexamples">
<head
	th:replace="common/head :: common_header(~{::title},~{::script},~{::link})">
<meta charset="utf-8" />
<title>添加</title>
<script type="text/javascript" th:inline="javascript">
$(function() {
    $('#form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'　　　　　　　　
        },
        fields: {
        	<#list list as item>
        	<#if item.accessAdd>
        	<#if item.mandatory>
        	${item.named.lowerCaseFirstName}: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            </#if>
            </#if>
            </#list>
        }
    });
});
</script>
</head>

<body>
	<div class="box box-primary">
		<div class="box-body">
			<form id="form" method="post" role="form">
							<#list list as item>
							<#if item.accessAdd>
							<#if item.elementId=='date'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<input id="${item.named.lowerCaseFirstName}" type="date" class="form-control" name="${item.named.lowerCaseFirstName}">
							</div>
							<#elseif item.elementId=='datetime'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<input id="${item.named.lowerCaseFirstName}" type="datetime" class="form-control" name="${item.named.lowerCaseFirstName}">
							</div>
							<#elseif item.elementId=='file'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<input id="${item.named.lowerCaseFirstName}" type="file" class="form-control" name="${item.named.lowerCaseFirstName}">
							</div>
							<#elseif item.elementId=='image'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<input id="${item.named.lowerCaseFirstName}" type="file" class="form-control" accept="image/x-png" name="${item.named.lowerCaseFirstName}">
							</div>
							<#elseif item.elementId=='lines'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<textarea id="${item.named.lowerCaseFirstName}" rows="3" class="form-control" name="${item.named.lowerCaseFirstName}"></textarea>
							</div>
							<#elseif item.elementId=='number'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<input id="${item.named.lowerCaseFirstName}"  name="${item.named.lowerCaseFirstName}" type="number" class="form-control" >
							</div>
							<#elseif item.elementId=='password'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<input id="${item.named.lowerCaseFirstName}"  name="${item.named.lowerCaseFirstName}" type="password" class="form-control" >
							</div>
							<#elseif item.elementId=='code'>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<dt:html type="select" name="${item.named.lowerCaseFirstName}" class="form-control" code="${item.dtcode}" />
							</div>
							<#else>
							<div class="form-group">
								<label for="${item.named.lowerCaseFirstName}">${item.title}</label>
								<input id="${item.named.lowerCaseFirstName}" name="${item.named.lowerCaseFirstName}" type="text" class="form-control" >
							</div>
							</#if>
							</#if>
           					</#list>
				<div class="form-group">
					<div class="text-align-right">
						<button type="button" class="btn btn-sm btn-default close-layer"><span class="glyphicon glyphicon-remove"></span>&nbsp;取消</button>
						<button type="submit" class="btn btn-sm btn-success "><span class="glyphicon glyphicon-ok"></span>&nbsp;确定</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>