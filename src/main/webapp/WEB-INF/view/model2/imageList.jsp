<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script defer src="../../../resources/js/imageList.js"></script>
<body>
	<form action="<%=request.getContextPath() %>/image/streaming.do">
		<select name="image" onchange='this.form.submit()'>
		<%=request.getAttribute("options") %>
		</select>
	</form>
</body>
</html>