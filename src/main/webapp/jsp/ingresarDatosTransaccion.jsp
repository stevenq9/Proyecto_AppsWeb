<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ingreso de datos de dep�sito/transaccion</title>
</head>
<body>
	<form method="POST"
		action="GestionarTransaccionesController?ruta=confirmar"
		id="formIngreso">
		<h1>Ingreso</h1>

		<label for="cantidad">Cantidad a ingresar:</label><br> <input
			id="cantidad" type="number" min="0.01" step="0.01" name="nmbCantidad" required/><br>

		<label for="cuentasDestino">Cuenta de destino:</label><br> <select
			id="cuentasDestino" name="selCuentaDestino" form="formIngreso">
			<c:forEach items="${cuentasDestino}" var="cuentaD">
				<option value="${cuentaD.id}">${cuentaD.nombre}</option>
			</c:forEach>
		</select><br>
		<input type="hidden" value="false" name="selCuentaOrigen">
		
		<br> <input type="submit" type="submit" value="Guardar">
	</form>

	<form method="POST"
		action="GestionarTransaccionesController?ruta=confirmar" id="formTransaccion">
		<h1>Transaccion</h1>

		<label for="cantidad">Cantidad a ingresar:</label><br> <input
			id="cantidad" type="number" min="0.01" step="0.01" name="nmbCantidadT" /><br>

		<label for="cuentasOrigen">Cuenta de origen:</label><br> 
		<select id="cuentasOrigen" name="selCuentaOrigen" form="formTransaccion">
			<c:forEach items="${cuentasOrigen}" var="cuentaO">
				<option value="${cuentaO.id}">${cuentaO.nombre}</option>
			</c:forEach>
			<!--   <option value="1">Cuenta 1</option>
			<option value="2">Cuenta 2</option>
			<option value="3">Cuenta 3</option>-->
		</select><br>
		
		<label for="cuentasDestino">Cuenta de destino:</label><br><select
			id="cuentasDestino" name="selCuentaDestinoT" form="formTransaccion">
			<c:forEach items="${cuentasDestino}" var="cuentaD">
				<option value="${cuentaD.id}">${cuentaD.nombre}</option>
			</c:forEach>
		</select><br>
		<br> <input type="submit" type="submit" value="Guardar">
	</form>
</body>
</html>