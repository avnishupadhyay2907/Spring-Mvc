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
		<sf:hidden path="pageNo" />
		<table>
			<tr>
				<td><sf:input path="productName" />&nbsp; &nbsp;</td>
				<td><sf:select path="id">
						<sf:option value="0">-----------select------------</sf:option>
						<sf:options items="${productList}" itemValue="id"
							itemLabel="productName" />
					</sf:select>&nbsp; &nbsp;</td>
				<td><input type="submit" name="operation" value="search">
				</td>
			</tr>
		</table>
		<table style="width: 100%" border="1">
			<tr>
				<th>Select</th>
				<th>ID</th>
				<th>Product Name</th>
				<th>Product Ammount</th>				
				<th>Purchase Date</th>
				<th>Product Category</th>
				<th>Edit</th>
			</tr>
			<c:forEach items="${list}" var="product">
				<tr>
					<td align="center"><sf:checkbox path="ids" value="${product.id}" /></td>
					<td align="center"><c:out value="${product.id }"></c:out></td>
					<td align="center"><c:out value="${product.productName }"></c:out></td>
					<td align="center"><c:out value="${product.productAmmount }"></c:out></td>					
					<td align="center"><c:out value="${product.purchaseDate }"></c:out></td>
					<td align="center"><c:out value="${product.productCategory }"></c:out></td>
					<td align="center"><a
						href="<c:url value="/Product?id=${product.id}" />">edit</a></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<table style="width: 100%">
			<tr>
				<td style="width: 30%"><input type="submit" name="operation"
					value="previous" ${form.pageNo == 1 ? 'disabled="disabled"' : ''} /></td>
				<td style="width: 30%"><input type="submit" name="operation"
					value="add"></td>
				<td style="width: 25%"><input type="submit" name="operation"
					value="delete"></td>
				<td style="text-align: right;"><input type="submit"
					name="operation" value="next"
					${list.size() < 5 ? 'disabled="disabled"' : ''}></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>