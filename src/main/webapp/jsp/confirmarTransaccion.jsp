<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Estado final transacción</title>

</head>

<body>
	<label>Estado de la estado final de la Transacción: </label>

	<p id="estadoTransaccion">${huboError}</p>
	<script>
		var paragraph = document.getElementById("estadoTransaccion");
		var text = paragraph.textContent;

		if (text === true) {
			document.write("ERROR en la transacción");
		} else {
			document.write("La transacción ha sido satisfactoria");
		}
	</script>

	<br>
	<br>
	<a href="/MiChaucheritaWebG4/GestionarCuentasController" class="button">Volver</a>

</body>

</html>
