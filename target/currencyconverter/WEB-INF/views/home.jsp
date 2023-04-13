<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" %>
<%@page contentType="text/html;charset=UTF-8"%>

<c:set var="localeCode" value="${pageContext.response.locale}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Currency converter">
    <meta name="author" content="Dragobrath">

    <title>Currency converter</title>

    <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet">
    <link href="<c:url value="/css/datepicker3.css" />" rel="stylesheet">
    <link href="<c:url value="/css/chosen.min.css" />" rel="stylesheet">
	
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">

</head>
<body>

<div class="container">

	<div class="language-bar">
	    <ul class="nav nav-pills language-ul">
			<c:choose>
				<c:when test="${localeCode == 'ee'}">
					<li class="active"><a href="?lang=ee">Eesti keeles</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="?lang=ee">Eesti keeles</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${localeCode == 'en'}">
					<li class="active"><a href="?lang=en">In English</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="?lang=en">In English</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>

    <form id="form-currency" class="form-currency" role="form">

        <div class="row">
            <div class="col-xs-6">
                <label for="currency-from"><spring:message code="ui.from" /></label>
            </div>

            <div class="col-xs-6">
                <label for="currency-to"><spring:message code="ui.to" /></label>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6">
                <select id="currency-from" name="convert-from" data-placeholder="Choose a Currency..." class="chosen-select">
                    <c:forEach items="${feeds}" var="feed">
                    	<option <c:if test="${feed.currencyCode == defaultOrigin}">selected</c:if>><c:out value="${feed.currencyCode}" /> - <spring:message code="${feed.currencyCode}" /></option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-xs-6">
                <select id="currency-to" name="convert-to" data-placeholder="Choose a Currency..." class="chosen-select">
                    <c:forEach items="${feeds}" var="feed">
                    	<option <c:if test="${feed.currencyCode == defaultDestination}">selected</c:if>><c:out value="${feed.currencyCode}" /> - <spring:message code="${feed.currencyCode}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div class="input-group date">
                    <input type="text" name="convert-date" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-8">
                <input type="text" name="convert-amount" class="form-control" placeholder="<spring:message code="ui.amount" />">
            </div>
            <div class="col-xs-4">
                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="ui.convert" /></button>
            </div>
        </div>

        <div class="row result">
        </div>

    </form>

<script type="text/mustache" id="convert-result">

    <div class="col-xs-12">
        <hr />
    </div>

    {{#results}}
    <div class="col-xs-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">{{bank}}</h3>
            </div>
            <div class="panel-body">
                {{amount}}
            </div>
        </div>
    </div>
    {{/results}}

</script>
</div>

<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/chosen.proto.min.js" />"></script>
<script src="<c:url value="/js/chosen.jquery.min.js" />"></script>
<script src="<c:url value="/js/moment.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/js/mustache.js" />"></script>
<script src="<c:url value="/js/app.js" />"></script>

</body>
</html>