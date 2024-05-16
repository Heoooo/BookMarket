<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>도서 목록</title>
</head>
<body>
	<div class="container">
		<div class="row" align="center">
			<c:forEach items="${bookList }" var="book">
				<div class="col-md-4">
					<c:choose>
						<c:when test="${book.getBookImage()==null }">
							<img src="<c:url value="C:\\upload\\${book.fileName }"/>" style="width:60%"/>
						</c:when>
						<c:otherwise>
							<img src="<c:url value="C:\\upload\\${book.fileName }"/>" style="width:60%"/>
						</c:otherwise>
					</c:choose>
					<h3>${book.name }</h3>
					<p>${book.author }
					<br>${book.publisher } | ${book.releaseDate }
					<p align="left">${fn:substring(book.description,0,100) }...
					<p>${book.unitPrice }원
					<p><a href="<c:url value="/books/book?id=${book.bookId }"/>" class="btn btn-Secondary" role="button">상세정보 &raquo;</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>