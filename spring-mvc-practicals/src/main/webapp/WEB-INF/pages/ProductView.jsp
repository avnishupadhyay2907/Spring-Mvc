<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<sf:form method="post" modelAttribute="form">
		<div align="center">
			<c:if test="${form.id>0}">
				<h1 style="color: navy">Update Product</h1>
			</c:if>
			<c:if test="${form.id==0}">
				<h1 style="color: navy">Add Product</h1>
			</c:if>
			<table>
				<tr>
					<th align="left">Product Name :</th>
					<td><sf:input path="productName" /></td>
				</tr>
				<tr>
					<th align="left">Product Amount :</th>
					<td><sf:input path="productAmmount" /></td>
				</tr>
				
				<tr>
					<th align="left">Purchase Date :</th>
					<td><sf:input path="purchaseDate" /></td>
				</tr>
				<tr>
					<th align="left">Product Category :</th>
					<td><sf:input path="productCategory" /></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="save"></td>
				</tr>
			</table>
		</div>
	</sf:form>
</body>
</html>