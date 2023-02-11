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
	<!-- Cuentas de Ingreso y salida -->
	<div id="ContenedorIngresoSalida">
		<fieldset>
			<h2>Cuentas de Entrada y Salida</h2>
			(Considera todos los movimientos)
			<div>
				<table>
					<c:forEach items="${estadoContableIngresosYGastos.cuenta}"
						var="cuentasIngresoSalida">
						<tr>
							<td>${cuentasIngresoSalida.nombre}:</td>
							<td>Total: ${cuentasIngresoSalida.saldo}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>

	<!-- Cuentas de salida -->
	<div id="ContenedorSalida">
		<fieldset>
			<h2>Cuentas de Gasto</h2>
			(El cálculo es entre fechas)
			<div>
				<table>
					<c:forEach items="${estadoContableGastos.cuenta}"
						var="cuentasSalida">
						<tr>
							<td>${cuentasSalida.nombre}:</td>
							<td>Total: - ${cuentasSalida.saldo}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>
	
		<!-- Cuentas de ingreso -->
	<div id="ContenedorIngreso">
		<fieldset>
			<h2>Cuentas de Ingreso</h2>
			(El cálculo es entre fechas)
			<div>
				<table>
					<c:forEach items="${estadoContableIngresos.cuenta}"
						var="cuentasIngreso">
						<tr>
							<td>${cuentasIngreso.nombre}:</td>
							<td>Total:  ${cuentasIngreso.saldo}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</div>
	
	
</body>
</html>