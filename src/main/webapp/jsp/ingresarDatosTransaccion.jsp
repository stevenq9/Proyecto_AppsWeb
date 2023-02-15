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
</head>
<body>
	<form method="POST"
		action="GestionarTransaccionesController?ruta=confirmar" id="formTransaccion">
		<c:if test="${ruta eq 'ingreso'}">
			<h1>Ingreso</h1>
		</c:if>
		<c:if test="${ruta eq 'transaccion'}">
			<h1>Transaccion</h1>
		</c:if>
		
		<input type="hidden" name="txtRuta" value='<c:out value="${ruta}"></c:out>'/>
		
		<label for="cantidad">Cantidad a ingresar:</label><br> <input
			id="cantidad" type="number" min="0.01" step="0.01" name="nmbCantidad" required/><br>

		<label for="cuentasOrigen">Cuenta de origen:</label><br> 
		<select id="cuentasOrigen" name="selCuentaOrigen" form="formTransaccion">
			<c:forEach items="${cuentasOrigen}" var="cuentaO">
				<option value="${cuentaO.id}">${cuentaO.nombre}</option>
			</c:forEach>
		</select><br>
		
		<label for="cuentasDestino">Cuenta de destino:</label><br><select
			id="cuentasDestino" name="selCuentaDestino" form="formTransaccion">
			<c:forEach items="${cuentasDestino}" var="cuentaD">
				<option value="${cuentaD.id}">${cuentaD.nombre}</option>
			</c:forEach>
		</select><br>
		
		<label for="descripcion">Descripcion:</label><br>	
		<input type="text" name="txtDescripcion" placeholder="Descripci�n"/>
		<br>
		<br> <input id="descripcion" type="submit" type="submit" value="Guardar">
	</form>
</body>
</html>