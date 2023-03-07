<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi chaucherita web</title>
<link rel="stylesheet" type="text/css" href="./css/listarStyle.css">
<link rel="shortcut icon" type="image/png" href="./images/favicon.png"/>
</head>

<body>

	<header>
		<h1>Mi chaucherita web</h1>
	</header>
	<nav>
		<a href="GestionarCuentasController?ruta=crearCuenta"
			class="ghost header">Nueva cuenta</a> <a
			href="DepositarController?ruta=registrarIngreso" class="ghost header">
			Nuevo ingreso</a><a href="RetirarController?ruta=registrarGasto"
			class="ghost header"> Nuevo gasto</a><a
			href="TransferirController?ruta=registrarTransferencia"
			class="ghost header"> Transaccion</a> <a
			href="LoginController?ruta=salir" class="ghost header">Cerrar
			sesión</a>
	</nav>

	<div>
		<form action="GestionarCuentasController?ruta=listarCuenta"
			method="POST">
			<label>Fecha inicial: </label><input type="date" name="fechaInicial"
				value="${fechaInicio}" required> <label>Fecha final:
			</label><input type="date" name="fechaFinal" value="${fechaFin}" required>
			<input type="submit" value="Filtrar movimientos"
				class="filtrarMovimientos ghost">
		</form>
	</div>

	<div class="cuentasContainer">
		<div class="ingresosContainer">
			<h2>Ingresos</h2>
			<c:forEach items="${cuentasDeIngresos}" var="estadoCuenta">
				<div class="container">
					<div class="line">
						<h3>${estadoCuenta.cuenta.nombre}</h3>
						<h3>$ ${estadoCuenta.valorTotal}</h3>
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
						href="GestionarCuentasController?ruta=modificarCuenta&id=${estadoCuenta.cuenta.id}"
						class="reduce">Modificar cuenta</a> <a
						href="DetallarCuentaController?id=${estadoCuenta.cuenta.id}"
						class="ghost reduce">Mostrar detalles</a>
					<div class="cover">
						<h3>${estadoCuenta.cuenta.nombre}
							<br>
						</h3>

					</div>
				</div>
			</c:forEach>
		</div>
		<div class="ingresosGastosContainer">
			<h2>Ingresos y Gastos</h2>
			<c:forEach items="${cuentaDeIngresosYGastos}" var="estadoCuenta">
				<div class="container">
					<div class="line">
						<h3>${estadoCuenta.cuenta.nombre}</h3>
						<h3>$ ${estadoCuenta.valorTotal}</h3>
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
						href="GestionarCuentasController?ruta=modificarCuenta&id=${estadoCuenta.cuenta.id}"
						class="reduce">Modificar cuenta</a> <a
						href="DetallarCuentaController?id=${estadoCuenta.cuenta.id}"
						class="ghost reduce">Mostrar detalles</a>
					<div class="cover">
						<h3>${estadoCuenta.cuenta.nombre}
							<br>
						</h3>

					</div>
				</div>
			</c:forEach>
		</div>


		<div class="gastosContainer">
			<h2>Gastos</h2>
			<c:forEach items="${cuentasDeGastos}" var="estadoCuenta">
				<div class="container">
					<div class="line">
						<h3>${estadoCuenta.cuenta.nombre}</h3>
						<h3 style="color: red;">$- ${estadoCuenta.valorTotal}</h3>
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
						href="GestionarCuentasController?ruta=modificarCuenta&id=${estadoCuenta.cuenta.id}"
						class="reduce">Modificar cuenta</a> <a
						href="DetallarCuentaController?id=${estadoCuenta.cuenta.id}"
						class="ghost reduce">Mostrar detalles</a>
					<div class="cover">
						<h3>${estadoCuenta.cuenta.nombre}
							<br>
						</h3>

					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>