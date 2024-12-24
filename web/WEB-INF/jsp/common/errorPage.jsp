<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
</head>
<body>
<h1>Error</h1>
<p>${errorMessage}</p>
<a href="<%= request.getContextPath() %>/catalog">Return to Catalog</a>
</body>
</html>
