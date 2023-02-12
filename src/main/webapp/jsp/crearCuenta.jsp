<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Cuenta</title>
 
</head>


<body>
    <h1>Nueva Cuenta</h1>

    <div>
        <form method="POST" action="GestionarCuentasController?ruta=guardar">
            
            <c:if test="${cuenta != null}">
                <input type="hidden" name="txtId" value="<c:out value='${cuenta.id}'/>">
                <input type="hidden" name="txtRuta" value="modificarCuenta">
            </c:if>
             <c:if test="${cuenta == null}">
                <input type="hidden" name="txtRuta" value="crearCuenta">
            </c:if>
            
            <label>NOMBRE:</label><br>
            <div>
                <input type="text" name="txtNombre">
            </div>
            <br>
            <label>TIPO:</label><br>
            <select name="txtTipo">
                <option value="modelo.CuentaDeIngresos"> Cuenta de ingreso </option>
                <option value="modelo.CuentaDeGastos"> Cuenta de gastos </option>
                <option value="modelo.CuentaDeIngresosYGastos"> Cuenta de ingreso y gastos </option>
            </select>
            <br>
            <br>
            <input type="submit" value="Guardar">
        </form>
    </div>

</body>

</html>