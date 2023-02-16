<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detallar Cuenta</title>
</head>
<body>
	<header>
		<h1>Mi Chaucherita Web</h1>
		<br>
	</header>
	
	<h2>Detalle de Cuenta</h2>
	<br>
	<div>Nombre: ${cuenta.nombre}
	</div>
	<div>Id: ${cuenta.id}
	</div>
	<c:if test="${saldo ne '-1'}">
			<div>Saldo:$ ${saldo}</div>
	</c:if>
	
	<br>
	
	<c:forEach items="${transacciones}" var="transaccion">
		<fieldset>
			<label><b>Id de transacción: </b></label>
			<p>${transaccion.id}</p>
			<label><b>Cuenta de origen:</b></label>
			<p>${transaccion.cuentaOrigen.nombre}</p>
			<label><b>Cuenta de destino: </b></label>
			<p>${transaccion.cuentaDestino.nombre}</p>
			<label><b>Monto: </b></label>
			<p>$ ${transaccion.cantidad}</p>
			<label><b>Descripción: </b></label>
			<p>${transaccion.descripcion}</p>
		</fieldset>
		<br>
	</c:forEach>
</body>
</html>