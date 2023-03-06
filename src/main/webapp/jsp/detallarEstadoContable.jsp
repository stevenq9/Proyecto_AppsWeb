<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Estado contable</title>
</head>
<body>
	<header>
		<h1>Mi Chaucherita Web</h1>
	</header>
	<a href="LoginController?ruta=salir">Cerrar sesión</a>
	
	<h2>Estado contable entre <c:out value="${fechaInicial}"/> y <c:out value="${fechaFinal}"/></h2>
		
	<!-- Cuentas de ingreso -->
	<div id="ContenedorIngreso">
		<fieldset>
			<h2>Cuentas de Ingreso</h2>
			(El cálculo es entre fechas)
			<div>
				<table>
					<c:forEach items="${estadoContableIngresos}"
						var="cuentasIngreso" varStatus="bandera">
						<tr>
							<td><a href="EstadoContableController?ruta=mostrarDetalleCuenta&index=${bandera.index}&tipo=estadoContableIngresos">${cuentasIngreso.cuenta.nombre}:</a></td>
							<td>Total:  ${cuentasIngreso.valorTotal}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>
	
	<!-- Cuentas de Ingreso y salida -->
	<div id="ContenedorIngresoSalida">
		<fieldset>
			<h2>Cuentas de Entrada y Salida</h2>
			(Considera todos los movimientos)
			<div>
				<table>
					<c:forEach items="${estadoContableIngresosYGastos}"
						var="cuentasIngresoSalida" varStatus="bandera">
						<tr>
							<td><a href="EstadoContableController?ruta=mostrarDetalleCuenta&index=${bandera.index}&tipo=estadoContableIngresosYGastos">${cuentasIngresoSalida.cuenta.nombre}:</a></td>
							<td>Total: ${cuentasIngresoSalida.valorTotal}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>

	<!--<!-- Cuentas de salida -->
	<div id="ContenedorSalida">
		<fieldset>
			<h2>Cuentas de Gasto</h2>
			(El cálculo es entre fechas)
			<div>
				<table>
					<c:forEach items="${estadoContableGastos}"
						var="cuentasSalida" varStatus="bandera">
						<tr>
							<td><a href="EstadoContableController?ruta=mostrarDetalleCuenta&index=${bandera.index}&tipo=estadoContableGastos">${cuentasSalida.cuenta.nombre}:</a></td>
							<td>Total: ${cuentasSalida.valorTotal}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>	
</body>
</html>