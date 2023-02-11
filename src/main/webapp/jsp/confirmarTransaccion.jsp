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
	<!--DEFINIR SE REQUIERE EL PARÁMETRO PARA COPARARÁ PARA  QUE JAVASCRIPT MUESTRE EL ESTADO DE LA TRANSACCIÓN-->
	<p id="estadoTransaccion">${huboError}</p>
	<input type="submit" value="Volver">

	<script>
        function estadoTransaccion() {
            let estado = document.getElementById("estadoTransaccion").value;
            let parrafo = document.querySelector('p');

    //DEFINIR Dependiendo del valor  que reciba se realiza la comparativa
            if (huboError === true) {
            	parrafo.textContent = 'ERROR en la transacción';
            } else {
            	parrafo.textContent = 'La transacción ha sido satisfactoria';
            }
        }
    </script>
</body>

</html>
