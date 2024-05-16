<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>도서 상세 정보</title>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-4">
			<img src="<c:url value="c:/upload/${book.fileName }"></c:url>" alt="image" style="width:100%"/>
		</div>
		<div class="col-md-7">
			<form:form modelAttribute="updateBook" action="./update?${_csrf.parameterName }=${_csrf.token }" class="form-horizontal" enctype="multipart/form-data">
				<fieldset>
					<div class="form-group row">
						<label class="col-sm-2 control-label">도서 ID</label>
						<div class="col-sm-6" style="padding-top:10px">
							<form:input id="bookId" path="bookId" type="hidden" class="form-control" value="${book.bookId }"/>
							<span class="badge badge-info">${book.bookId }</span>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">도서명</label>
						<div class="col-sm-6">
							<form:input path="name" class="form-control" value="${book.name }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">가격</label>
						<div class="col-sm-6">
							<form:input path="unitPrice" class="form-control" value="${book.unitPrice }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">저자</label>
						<div class="col-sm-6">
							<form:input path="author" class="form-control" value="${book.author }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">상세정보</label>
						<div class="col-sm-6">
							<form:textarea path="description" cols="50" rows="2" class="form-control" value="${book.description }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">출판사</label>
						<div class="col-sm-6">
							<form:input path="publisher" class="form-control" value="${book.publisher }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">분류</label>
						<div class="col-sm-6">
							<form:input path="category" class="form-control" value="${book.category }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">재고수</label>
						<div class="col-sm-6">
							<form:input path="unitsInStock" class="form-control" value="${book.unitsInStock }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">출판일</label>
						<div class="col-sm-6">
							<form:input path="releaseDate" class="form-control" value="${book.releaseDate }"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">상태</label>
						<div class="col-sm-6">
							<form:radiobutton path="condition" value="New"/>New
							<form:radiobutton path="condition" value="Old"/>Old
							<form:radiobutton path="condition" value="E-Book"/>E-Book
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 control-label">이미지</label>
						<div class="col-sm-10">
							<form:input path="bookImage" type="file" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value="수정"/>
							<a href="<c:url value="/books/"/>" class="btn btn-primary">취소</a>
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>