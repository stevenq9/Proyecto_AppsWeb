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
        <!--RUTA POR DEFINIR!!!-->
        <form method="POST" action="GestionarCuentasController?ruta=guardar">
         	<c:if test="${cuenta != null}">
				<input type="hidden" name="txtId" value="<c:out value='${cuenta.id}'/>">
			</c:if>
            <label>NOMBRE:</label><br>
            <div>
                <input type="text" name="txtNombre">
            </div>

            <br>
            <input type="button" value="Guardar">
        </form>
    </div>

</body>

</html>