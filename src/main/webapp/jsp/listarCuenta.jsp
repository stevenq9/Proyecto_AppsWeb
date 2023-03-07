<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi chaucherita web</title>
<link rel="stylesheet" type="text/css" href="./css/listarStyle.css">
</head>

<body>
	<header>
		<h1>Mi chaucherita web</h1>
	</header>
	<nav>
		<a href="GestionarCuentasController?ruta=crearCuenta"
			class="ghost header">Nueva cuenta</a> <a
			href="GestionarTransaccionesController?ruta=registrarIngreso"
			class="ghost header"> Nuevo ingreso</a><a
			href="GestionarTransaccionesController?ruta=registrarTransaccion"
			class="ghost header"> Transaccion</a>
	</nav>
	<div class="cuentasContainer">
		<div class="ingresosContainer">
			<h2>Ingresos</h2>
			<c:forEach items="${cuentasDeIngresos}" var="cuenta">
				<div class="container">
					<div class="line">
						<h3>${cuenta.nombre}</h3>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
					</div>
					<a
						href="GestionarCuentasController?ruta=modificarCuenta&id=${cuenta.id}"
						class="reduce">Modificar cuenta</a> <a
						href="GestionarTransaccionesController?ruta=detallarCuenta&id=${cuenta.id}"
						class="ghost reduce">Mostrar detalles</a>
					<div class="cover">
						<h3>${cuenta.nombre}</h3>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="ingresosGastosContainer">
			<h2>Ingresos y Gastos</h2>
			<c:forEach items="${cuentaDeIngresosYGastos}" var="cuenta">
				<div class="container">
					<div class="line">
						<h3>${cuenta.nombre}</h3>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
					</div>
					<a
						href="GestionarCuentasController?ruta=modificarCuenta&id=${cuenta.id}"
						class="reduce">Modificar cuenta</a> <a
						href="GestionarTransaccionesController?ruta=detallarCuenta&id=${cuenta.id}"
						class="ghost reduce">Mostrar detalles</a>
					<div class="cover">
						<h3>${cuenta.nombre}</h3>
					</div>
				</div>
			</c:forEach>
		</div>


		<div class="gastosContainer">
			<h2>Gastos</h2>
			<c:forEach items="${cuentasDeGastos}" var="cuenta">
				<div class="container">
					<div class="line">
						<h3>${cuenta.nombre}</h3>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
						<hr>
					</div>
					<a
						href="GestionarCuentasController?ruta=modificarCuenta&id=${cuenta.id}"
						class="reduce">Modificar cuenta</a> <a
						href="GestionarTransaccionesController?ruta=detallarCuenta&id=${cuenta.id}"
						class="ghost reduce">Mostrar detalles</a>
					<div class="cover">
						<h3>${cuenta.nombre}</h3>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="estadoContable">
		<h2>Obtener estado contable</h2>
			<form action="GestionarTransaccionesController?ruta=mostrarEstado"
				method="POST">
				<label>Fecha inicial: </label><input type="date" name="fechaInicial"
					required><br> <br> <label>Fecha final: </label><input
					type="date" name="fechaFinal" required><br> <br>
				<input type="submit" value="Filtrar movimientos" class="filtrarMovimientos ghost">
			</form>
	</div>
</body>

</html>