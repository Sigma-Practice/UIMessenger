<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Online chat</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <script src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</head>
<body>
<%@ include file="header.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-sm-3 col-md-3">
            <div class="panel-group" id="accordion">
                <c:forEach items="${topics}" var="topic">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                <div>
                                    <h4 class="panel-title">${topic.name}</h4>
                                </div>
                            </a>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <div class="panel-body">
                                <table class="table">
                                    <c:forEach items="${chats}" var="chat">
                                        <c:if test="${chat.topicId==topic.id}">
                                            <tr>
                                                <td>
                                                    <a href="<c:url value="/welcome?chat=${chat.id}"/>">${chat.name}</a>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-sm-9 col-md-9">
            <c:choose>
                <c:when test="${messages == null}">
                    <div class="well">
                        <h1>WELCOME</h1>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="well">
                        <div class="panel panel-info">
                            <div class="panel-heading">CHAT NAME</div>
                            <div class="panel-body">
                                <ul class="media-list">
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    Text of message <br/>
                                                    <small class="text-muted">Name
                                                        of sender | 31.02.2015 12:35
                                                    </small>
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    Text of message <br/>
                                                    <small class="text-muted">Name
                                                        of sender | 31.02.2015 12:35
                                                    </small>
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    Text of message <br/>
                                                    <small class="text-muted">Name
                                                        of sender | 31.02.2015 12:35
                                                    </small>
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    Text of message <br/>
                                                    <small class="text-muted">Name
                                                        of sender | 31.02.2015 12:35
                                                    </small>
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    Text of message <br/>
                                                    <small class="text-muted">Name
                                                        of sender | 31.02.2015 12:35
                                                    </small>
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    Text of message <br/>
                                                    <small class="text-muted">Name
                                                        of sender | 31.02.2015 12:35
                                                    </small>
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    Text of message <br/>
                                                    <small class="text-muted">Name
                                                        of sender | 31.02.2015 12:35
                                                    </small>
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                </ul>
                            </div>
                            <div class="panel-footer">
                                <div class="input-group">
                                    <input type="text" class="form-control"
                                           placeholder="Enter Message"/> <span class="input-group-btn">
									<button class="btn btn-info" type="button">SEND</button>
								</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>