<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ingreso de datos de deposito/transaccion</title>
<link rel="stylesheet" type="text/css" href="./css/inputsStyle.css">
<link rel="shortcut icon" type="image/png" href="./images/favicon.png"/>
</head>
<body>
	<form method="POST"
		action="${ruta}" id="formTransaccion">
		<c:if test="${ruta eq 'DepositarController?ruta=confirmar'}">
			<h1>Ingreso</h1>
		</c:if>
		<c:if test="${ruta eq 'RetirarController?ruta=confirmar'}">
			<h1>Retiro</h1>
		</c:if>
		
		<c:if test="${ruta eq 'TransferirController?ruta=confirmar'}">
			<h1>Transferencia entre cuentas</h1>
		</c:if>
		
		<input type="hidden" name="txtRuta" value='<c:out value="${ruta}"></c:out>'/>
		
		<label for="cantidad">Cantidad a ingresar:</label> <input
			id="cantidad" type="number" min="0.01" step="0.01" name="nmbCantidad" required class="input"/><br>

		<label for="cuentasOrigen">Cuenta de origen:</label> 
		<select id="cuentasOrigen" name="selCuentaOrigen" form="formTransaccion">
			<c:forEach items="${cuentasOrigen}" var="cuentaO">
				<option value="${cuentaO.id}">${cuentaO.nombre}</option>
			</c:forEach>
		</select><br>
		
		<label for="cuentasDestino">Cuenta de destino:</label>
		<select id="cuentasDestino" name="selCuentaDestino" form="formTransaccion">
			<c:forEach items="${cuentasDestino}" var="cuentaD">
				<option value="${cuentaD.id}">${cuentaD.nombre}</option>
			</c:forEach>
		</select><br>
		
		<label for="descripcion">Descripci�n:</label>	
		<input type="text" name="txtDescripcion" placeholder="Descripci�n" class="input"/>
		<br>
		<label for="fecha">Fecha:</label>
		<input id="fecha" type="date" name="dateFecha" required/>

		<br> <input id="descripcion" type="submit" value="Guardar" class="btnSubmit ghost">
	</form>
</body>
</html>