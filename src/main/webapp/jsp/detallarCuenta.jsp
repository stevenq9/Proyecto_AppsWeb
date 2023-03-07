<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detallar Cuenta</title>
<link rel="stylesheet" type="text/css" href="./css/detalleCuenta.css">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<h1>Mi Chaucherita Web</h1>
		<br>
	</header>

	<div class="informacionCuenta row">
		<div class="col-8">
			<h2>Detalle de Cuenta ${cuenta.nombre}</h2>
			<div>
				<b>Id: </b> ${cuenta.id}
			</div>
			<c:if test="${saldo ne '-1'}">
				<div>
					<b>Saldo: </b> $ ${saldo}
				</div>
			</c:if>
		</div>
		<div class="col-4">
			<nav class=" d-flex justify-content-center">
				<a href="GestionarCuentasController"
					class="ghost header"> Men� principal</a>
			</nav>
		</div>
	</div>

	<br>

	<h2>Resumen de movimientos:</h2>

	<div class="container-fluid">
		<div id="grilla" class="row">
			<c:forEach items="${transacciones}" var="transaccion">
				<fieldset id="movimiento" class="col-6">
					<label><b>Id de transacci�n: </b></label>
					<p>${transaccion.id}</p>
					<label><b>Cuenta de origen:</b></label>
					<p>${transaccion.cuentaOrigen.nombre}</p>
					<label><b>Cuenta de destino: </b></label>
					<p>${transaccion.cuentaDestino.nombre}</p>
					<label><b>Monto: </b></label>
					<p>$ ${transaccion.cantidad}</p>
					<label><b>Fecha: </b></label>
					<p>${transaccion.fecha}</p>
					<label><b>Descripci�n: </b></label>
					<p>${transaccion.descripcion}</p>
				</fieldset>
				<br>
				<br>
				<br>
			</c:forEach>
		</div>

	</div>

</body>
</html>