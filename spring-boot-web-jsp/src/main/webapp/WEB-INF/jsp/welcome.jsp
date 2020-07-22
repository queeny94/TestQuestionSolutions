<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style type="text/css">
th, td {
	border: 1px solid black;
}

.upload {
	
}

.bulkupload {
	margin-top: 10px;
}

.details {
	margin-top: 10px;
}
span{
	margin-left: 10px;
}
</style>
</head>
<body>

	<div class="upload">
		<form action="/addDetails" method="post">
			Bank: <input type="text" name="bankName" placeholder="Bank Name">
			Card Number: <input type="text" name="cardnumber"
				placeholder="xxxx-xxxx-xxxx-xxxx"> Expiry date: <input
				type="text" name="expirydate" placeholder="MMM-YYYY"> <input
				type="submit" value="Submit">
				
				<c:if test="${status=='Invalid'}">Not a valid card number</c:if>
		</form><form action="/logout" method="post"><input type="submit" value="logout">
		</form>
	</div>
	<div class="bulkupload">
		<form action="/addBulkDetails" method="post" enctype="multipart/form-data">
			Choose File: <input type="file" name="file" placeholder="Bank Name">
			<input type="submit" value="Submit"><span>${fileuploadstatus}</span>
		</form>
	</div>
	<div class="details">
		<table style="width: 50%;">
			<thead>
				<tr>
					<th>Bank</th>
					<th>Card number</th>
					<th>Expiry date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="k" items="${userDetails}">
					<tr>
						<td>${k.bankName}</td>
						<td>${k.cardNumber}</td>
						<td>${k.expiryDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>