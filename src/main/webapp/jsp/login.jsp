<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicia Sesi�n</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
	<h1 class="title">Chaucherita Web</h1>
	<div class="container" id="container">
		<div class="form-container register-container">
			<form action="#" method="">
				<h1>Registrar</h1>
				<input type="text" placeholder="Nombre"> <input type="text"
					placeholder="Apellido"> <input type="text"
					placeholder="Usuario"> <input type="email"
					placeholder="Clave">
				<button type="submit">Registrar</button>
			</form>
		</div>
		<div class="form-container login-container">
			<form action="" method="">
				<h1>Login</h1>
				<input type="text" placeholder="Usuario"> <input
					type="password" placeholder="Clave">
				<button type="submit">Login</button>
			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1 class="title">� Ya eres parte ?</h1>
					<p>
						�Ya tienes una chaucherita? <br> Inicia sesi�n aqu�
					</p>
					<button class="ghost" id="login">Login</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1 class="title">
						Empieza<br> ahora
					</h1>
					<p>
						Si no tienes una chaucherita a�n, <br> �nete e inicia hoy!
					</p>
					<button class="ghost" id="register">Registrarse</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../js/app.js"></script>

</body>
</html>