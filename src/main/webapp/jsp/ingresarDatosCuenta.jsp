<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<c:choose>
    	<c:when test="${cuenta == null}"><title>Crear cuenta</title></c:when>
    	<c:otherwise><title>Modificar cuenta</title></c:otherwise>
    </c:choose>
 	<link rel="stylesheet" type="text/css" href="./css/inputsStyle.css">
 	<link rel="shortcut icon" type="image/png" href="./images/favicon.png"/>
</head>


<body>
    <c:choose>
    	<c:when test="${cuenta == null}"><h1>Nueva Cuenta</h1></c:when>
    	<c:otherwise><h1>Modificar cuenta: ${cuenta.nombre}</h1></c:otherwise>
    </c:choose>
    

    <div>
        <form method="POST" action="GestionarCuentasController?ruta=guardar">
            
            <c:if test="${cuenta != null}">
                <input type="hidden" name="txtId" value="<c:out value='${cuenta.id}'/>">
                <input type="hidden" name="txtRuta" value="modificarCuenta">
            </c:if>
             <c:if test="${cuenta == null}">
                <input type="hidden" name="txtRuta" value="crearCuenta">
            </c:if>
            
            <label>Nombre:</label>
            <div>
                <input type="text" name="txtNombre" value="<c:out value='${cuenta.nombre}'></c:out>" class="input">
            </div>
            <br>
            <c:if test="${cuenta == null}">
            <label>Tipo:</label>
	            <select name="txtTipo">
	                <option value="modelo.CuentaDeIngresos"> Cuenta de ingreso </option>
	                <option value="modelo.CuentaDeGastos"> Cuenta de gastos </option>
	                <option value="modelo.CuentaDeIngresosYGastos"> Cuenta de ingreso y gastos </option>
	            </select>
            </c:if>
            <input type="submit" value="Guardar" class="btnSubmit ghost">
        </form>
    </div>

</body>

</html>
