<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<hr>
	<div class="cuentasContainer">
		<div class="ingresosContainer">
			<h2>Ingresos</h2>
			<a href="#">Nueva cuenta</a> | <a
				href="GestionarTransaccionesController?ruta=registrarIngreso">
				Nuevo ingreso</a> <br> <br> <br> 
			<fieldset>
				<div class="cuenta">
					<h3>Nómina</h3>
					<a href="">Modificar cuenta</a> <a href="GestionarTransaccionesController?ruta=detallarCuenta&id=1">Mostrar detalles</a>
				</div>
			</fieldset>
		</div>
		<hr>
		<div class="ingresosGastosContainer">
			<h2>Ingresos y Gastos</h2>
			<a href="#">Nueva cuenta</a> | <a
				href="GestionarTransaccionesController?ruta=registrarIngreso">
				Nuevo ingreso</a> <br> <br> <br>
			<fieldset>
				<div class="cuenta">
					<h3>Banco</h3>
					<a href="">Modificar cuenta</a> <a href="GestionarTransaccionesController?ruta=detallarCuenta&id=2">Mostrar detalles</a>
				</div>
			</fieldset>
			<fieldset>
				<div class="cuenta">
					<h3>Efectivo</h3>
					<a href="">Modificar cuenta</a> <a href="GestionarTransaccionesController?ruta=detallarCuenta&id=3">Mostrar detalles</a>
				</div>
			</fieldset>
		</div>
		<hr>
		<div class="gastosContainer">
			<h2>Gastos</h2>
			<a href="#">Nueva cuenta</a> | <a
				href="GestionarTransaccionesController?ruta=registrarIngreso">
				Nuevo ingreso</a> <br> <br>  <br> 
			<fieldset>
				<div class="cuenta">
					<h3>Universidad</h3>
					<a href="">Modificar cuenta</a> <a href="GestionarTransaccionesController?ruta=detallarCuenta&id=4">Mostrar detalles</a>
				</div>
			</fieldset>
			<fieldset>
				<div class="cuenta">
					<h3>Regalos</h3>
					<a href="">Modificar cuenta</a> <a href="GestionarTransaccionesController?ruta=detallarCuenta&id=5">Mostrar detalles</a>
				</div>
			</fieldset>
		</div>
	</div>

	<hr>
	<div>
		<h2>Obtener estado contable</h2>
		<fieldset>
			<form action="GestionarTransaccionesController?ruta=mostrarEstado" method="POST">
				<label>Fecha inicial: </label><input type="date" name="fechaInicial" required><br>
				<br> <label>Fecha final: </label><input type="date"
					name="fechaFinal" required><br> <br> <input type="submit"
					value="Filtrar movimientos">
			</form>
		</fieldset>
	</div>
	<hr>



</body>
</html>