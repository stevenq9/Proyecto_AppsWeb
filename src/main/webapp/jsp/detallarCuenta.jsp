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
	<a href="LoginController?ruta=salir">Cerrar sesión</a>
	<h2>Detalle de Cuenta</h2>
	<br>
	<div>Nombre: ${estadocuenta.cuenta.nombre}</div>
	<div>Id: ${estadocuenta.cuenta.id}</div>
	<div>Valor total: ${estadocuenta.valorTotal}</div>


	<br>

	<c:forEach items="${estadocuenta.movimientos}" var="movimiento">
		<fieldset>
			<label><b>Id de movimiento: </b></label>
			<p>${movimiento.id}</p>
			<label><b>Cuenta de origen:</b></label>
			<p>${movimiento.cuentaOrigen.nombre}</p>
			<label><b>Cuenta de destino: </b></label>
			<p>${movimiento.cuentaDestino.nombre}</p>
			<label><b>Monto: </b></label>
			<p>$ ${movimiento.cantidad}</p>
			<label><b>Fecha: </b></label>
			<p>${movimiento.fecha}</p>
			<label><b>Descripción: </b></label>
			<p>${movimiento.descripcion}</p>
		</fieldset>
		<br>
	</c:forEach>
</body>
</html>