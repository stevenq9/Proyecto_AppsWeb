<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicia Sesión</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<h1 class="title">Chaucherita Web</h1>
	<div class="container" id="container">
		<div class="form-container register-container">
			<form action="LoginController?ruta=registro" method="POST">
				<h1>Registrar</h1>
				<input type="text" name="txtNombreRegistro" placeholder="Nombre" required>
				<input type="text" name="txtApellidoRegistro" placeholder="Apellido" required>
				<input type="text" name="txtUsuarioRegistro" placeholder="Usuario" required>
				<input type="password" name="txtClaveRegistro" placeholder="Clave" required>
				<button type="submit">Registrar</button>
			</form>
		</div>
		<div class="form-container login-container">
			<form action="LoginController?ruta=login" method="POST">
				<h1>Login</h1>
				<input type="text" name="txtUsuarioLogin" placeholder="Usuario" required>
				<input type="password" name="txtClaveLogin" placeholder="Clave" required>
				<button type="submit">Login</button>
			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1 class="title">¿ Ya eres parte ?</h1>
					<p>
						¿Ya tienes una chaucherita? <br> Inicia sesión aquí
					</p>
					<button class="ghost" id="login">Login</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1 class="title">
						Empieza<br> ahora
					</h1>
					<p>
						Si no tienes una chaucherita aún, <br> únete e inicia hoy!
					</p>
					<button class="ghost" id="register">Registrarse</button>
				</div>
			</div>
		</div>
		<p><c:out value='${mensaje}'/></p>
	</div>

	<script type="text/javascript" src="./js/app.js"></script>

</body>
</html>