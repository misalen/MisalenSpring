<?xml version="1.0"?>
<thlogic>
	<attr sel="#form" th:action="@{${path}/update}">
	</attr>
	<attr sel="#primaryKey" th:value="${r'${model.primaryKey}'}">
	</attr>
	<#list list as item>
	<#if item.accessUpdate>
	<#if item.enumerationCode ?? >
	<#else>
	<attr sel="#${item.named.lowerCaseFirstName}" th:value="${r'${model.'}${item.named.lowerCaseFirstName}}">
	</attr>
	</#if>
	</#if>
	</#list>
</thlogic>