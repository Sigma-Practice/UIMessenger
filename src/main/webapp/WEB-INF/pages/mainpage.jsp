<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online chat</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<jsp:include page="header.jspf" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-3 col-md-3">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"><div>
									<h4 class="panel-title">Cars</h4>
								</div> </a>
						</div>
						<div id="collapseOne" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table">
									<tr>
										<td><a href="">Audi</a></td>
									</tr>
									<tr>
										<td><a href="">Ford</a></td>
									</tr>
									<tr>
										<td><a href="">Mercedes</a></td>
									</tr>
									<tr>
										<td><a href="">Lada</a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo"><div>
									<h4 class="panel-title">Health</h4>
								</div> </a>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table">
									<tr>
										<td><a href="">Tooth</a></td>
									</tr>
									<tr>
										<td><a href="">Internals</a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9 col-md-9">
				<div class="well">
					<div class="row">
						<div class="col-lg-4 col-lg-offset-4">
							<input type="search" id="search" value="" class="form-control"
								placeholder="Search chat">
						</div>
					</div>
					<div class="row chat-table">
						<div class="col-lg-12">
							<table class="table" id="table">
								<tbody>
									<tr>
										<td>Chat#1</td>
									</tr>
									<tr>
										<td>Chat#2</td>
									</tr>
									<tr>
										<td>Chat#3</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>