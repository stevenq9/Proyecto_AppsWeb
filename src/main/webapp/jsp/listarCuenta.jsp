<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>Listar Cuenta</title>
</head>

<body>
    <h1>Listado de Cuentas</h1>

    <div>
        <div>
            <a href="GestionarCuentasController?ruta=listarCuenta"></a>
        </div>
        <table class="texto-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cuentas}" var="cuenta">
                    <tr>
                        <td>${cuenta.id}</td>
                        <td>${cuenta.nombre}</td>
                        <td>
                            <!--POR DEFINIR LA RUTA-->
                            <a href="GestionarTransaccionesController?ruta=Depositar">
                                <input type="button" value="Depositar">
                            </a>
                            |
                             <!--POR DEFINIR LA RUTA-->
                            <a href="GestionarTransaccionesController?ruta=Retirar">
                                <input type="button" value="Retirar">
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>

    </script>
</body>

</html>