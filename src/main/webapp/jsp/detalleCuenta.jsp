<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle de cuenta</title>
</head>
<body>
	<h1>Detalle de cuenta</h1>
	<c:forEach items="${transacciones}" var="transaccion">
		<fieldset>
			<label><b>Id de transacción</b></label>
			<p>${transaccion.id}</p>
			<label><b>Cuenta de origen</b></label>
			<p>${transaccion.cuentaOrigen.nombre}</p>
			<label><b>Cuenta de destino: </b></label>
			<p>${transaccion.cuentaDestino.nombre}</p>
			<label><b>Monto: </b></label>
			<p>${transaccion.cantidad}</p>
		</fieldset>
		<br>
	</c:forEach>
</body>
</html>