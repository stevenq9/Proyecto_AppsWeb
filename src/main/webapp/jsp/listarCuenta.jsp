<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi chaucherita web</title>
</head>

<body>
    <header>
        <h1>Mi chaucherita web</h1>
    </header>
    <a href="GestionarCuentasController?ruta=crearCuenta">Nueva cuenta</a> | <a href="GestionarTransaccionesController?ruta=registrarIngreso">
                Nuevo ingreso</a> | <a href="GestionarTransaccionesController?ruta=registrarTransaccion">
                Transaccion</a> <br> <br> <br>
    <hr>
    <div class="cuentasContainer">
        <div class="ingresosContainer">
            <h2>Ingresos</h2>
            <c:forEach items="${cuentasDeIngresos}" var="cuenta">
                <fieldset>
                    <div class="cuenta">
                        <h3>${cuenta.nombre}</h3>
                        <a href="GestionarCuentasController?ruta=modificarCuenta&id=${cuenta.id}">Modificar cuenta</a> <a
                            href="GestionarTransaccionesController?ruta=detallarCuenta&id=${cuenta.id}">Mostrar
                            detalles</a>
                    </div>
                </fieldset>
            </c:forEach>

        </div>
        <hr>
        <div class="ingresosGastosContainer">
            <h2>Ingresos y Gastos</h2>
            <c:forEach items="${cuentaDeIngresosYGastos}" var="cuenta">
                <fieldset>
                    <div class="cuenta">
                        <h3>${cuenta.nombre}</h3>
                        <a href="GestionarCuentasController?ruta=modificarCuenta&id=${cuenta.id}">Modificar cuenta</a> <a
                            href="GestionarTransaccionesController?ruta=detallarCuenta&id=${cuenta.id}">Mostrar
                            detalles</a>
                    </div>
                </fieldset>
            </c:forEach>

        </div>
        <hr>
        <div class="gastosContainer">
            <h2>Gastos</h2>
            <c:forEach items="${cuentasDeGastos}" var="cuenta">
                <fieldset>
                    <div class="cuenta">
                        <h3>${cuenta.nombre}</h3>
                        <a href="GestionarCuentasController?ruta=modificarCuenta&id=${cuenta.id}">Modificar cuenta</a> <a
                            href="GestionarTransaccionesController?ruta=detallarCuenta&id=${cuenta.id}">Mostrar
                            detalles</a>
                    </div>
                </fieldset>
            </c:forEach>

        </div>
    </div>

    <hr>
    <div>
        <h2>Obtener estado contable</h2>
        <fieldset>
            <form action="GestionarTransaccionesController?ruta=mostrarEstado" method="POST">
                <label>Fecha inicial: </label><input type="date" name="fechaInicial" required><br>
                <br> <label>Fecha final: </label><input type="date" name="fechaFinal" required><br> <br> <input
                    type="submit" value="Filtrar movimientos">
            </form>
        </fieldset>
    </div>
    <hr>

</body>

</html>