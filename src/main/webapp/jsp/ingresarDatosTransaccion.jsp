<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingreso de datos de ingreso/transaccion</title>
</head>
<body>
    <form method="POST" action="GestionarTransaccionesController?ruta=confirmar" id="formIngreso">
        <h1>Ingreso</h1>
        
        <label for="cantidad">Cantidad a ingresar:</label><br>
        <input id="cantidad" type="number" min="0.01" step="0.01" name="nmbCantidad"/><br>

        <label for="cuentasDestino">Cuenta de destino:</label><br>
        <select id="cuentasDestino" name="selCuentaDestino" form="formIngreso">
            <option value="1">Nombre de cuenta 1</option>
            <option value="2">Nombre de cuenta 2</option>
            <option value="3">Nombre de cuenta 3</option>
            <option value="4">Nombre de cuenta 4</option>
        </select><br><br>

        <input type="submit" type="submit" value="Guardar">
    </form>

    <form method="POST" action="GestionarTransaccionesController?ruta=confirmar">
        <h1>Transaccion</h1>

        <label for="cantidad">Cantidad a ingresar:</label><br>
        <input id="cantidad" type="number" min="0.01" step="0.01" name="nmbCantidad"/><br>

        <label for="cuentasOrigen">Cuenta de origen:</label><br>
        <select id="cuentasOrigen" name="selCuentaOrigen" form="formIngreso">
            <option value="1">Nombre de cuenta 1</option>
            <option value="2">Nombre de cuenta 2</option>
            <option value="3">Nombre de cuenta 3</option>
            <option value="4">Nombre de cuenta 4</option>
        </select><br>

        <label for="cuentasDestino">Cuenta de destino:</label><br>
        <select id="cuentasDestino" name="selCuentaDestino" form="formIngreso"><br><br>
            <option value="1">Nombre de cuenta 1</option>
            <option value="2">Nombre de cuenta 2</option>
            <option value="3">Nombre de cuenta 3</option>
            <option value="4">Nombre de cuenta 4</option>
        </select><br><br>

        <input type="submit" type="submit" value="Guardar">
    </form>
</body>
</html>