<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<link rel="shortcut icon" type="image/png" href="./images/favicon.png"/>
</head>
<body>
	<header>
		<h1>Mi Chaucherita Web</h1>
		<br>
	</header>

	<div class="informacionCuenta row">
		<div class="col-8">
			<h2>Detalle de Cuenta ${estadoDeCuenta.cuenta.nombre}</h2>
			<div>
				<b>Id: </b> ${cuenta.id}
			</div>
			<div>
				<b>Saldo: </b><fmt:setLocale value = "en_US"/><fmt:formatNumber type="currency" minFractionDigits="2" maxFractionDigits="2" value="${estadoCuenta.valorTotal}"/>
			</div>
		</div>
		<div class="col-4">
			<nav class=" d-flex justify-content-center">
				<a href="GestionarCuentasController" class="ghost header"> Menú
					principal</a>
			</nav>
		</div>
	</div>

	<h2>Resumen de movimientos:</h2>

	<div class="container-fluid">
		<div id="grilla" class="row">
			<c:forEach items="${movimientos}" var="movimiento">
				<fieldset id="movimiento" class="col-6">
					<label><b>Id de movimiento: </b></label>
					<p>${movimiento.id}</p>
					<label><b>Cuenta de origen:</b></label>
					<p>${movimiento.cuentaOrigen.nombre}</p>
					<label><b>Cuenta de destino: </b></label>
					<p>${movimiento.cuentaDestino.nombre}</p>
					<label><b>Monto: </b></label>
					<p><fmt:setLocale value = "en_US"/><fmt:formatNumber type="currency" minFractionDigits="2" maxFractionDigits="2" value="${movimiento.cantidad}"/></p>
					<label><b>Fecha: </b></label>
					<p>
						<fmt:formatDate value="${movimiento.fecha}" pattern="dd-MM-yyyy" />
					</p>
					<label><b>Descripción: </b></label>
					<p>${movimiento.descripcion}</p>
				</fieldset>
				<br>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
</body>
</html>
