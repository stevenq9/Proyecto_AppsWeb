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
	
	<h2>Estado contable entre <c:out value="${estadoContableIngresos.fechaInicio}"/> y <c:out value="${estadoContableIngresos.fechaFin}"/></h2>
	
	<!-- Cuentas de ingreso -->
	<div id="ContenedorIngreso">
		<fieldset>
			<h2>Cuentas de Ingreso</h2>
			(El cálculo es entre fechas)
			<div>
				<table>
					<c:forEach items="${estadoContableIngresos.estadosDeCuenta}"
						var="cuentasIngreso">
						<tr>
							<td>${cuentasIngreso.cuenta.nombre}:</td>
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
					<c:forEach items="${estadoContableIngresosYGastos.estadosDeCuenta}"
						var="cuentasIngresoSalida">
						<tr>
							<td>${cuentasIngresoSalida.cuenta.nombre}:</td>
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
					<c:forEach items="${estadoContableGastos.estadosDeCuenta}"
						var="cuentasSalida">
						<tr>
							<td>${cuentasSalida.cuenta.nombre}:</td>
							<td>Total: - ${cuentasSalida.valorTotal}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>	
</body>
</html>