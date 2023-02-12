<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Estado final transacción</title>

</head>

<body>
	<label>Estado final de la transacción: </label>

	<script>
		let huboError = "${huboError}";
		if (huboError === "true") {
			document.write("<p>ERROR en la transacción</p>");
			document.write("<p>Error: ${mensajeDeError} </p>");
		} else {
			document.write("<p>La transacción se ha realizado exitosamente</p>");
		}
	</script>
	<br>
	<a href="/MiChaucheritaWebG4/GestionarCuentasController" class="button">Volver</a>

</body>
</html>