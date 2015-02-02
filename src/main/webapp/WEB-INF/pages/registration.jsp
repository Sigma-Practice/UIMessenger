<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="registration.css">
</head>
<body>
	<div id="content-body" class="content">
		<div class="languageselect">
			<button class="btn btn-primary langbutton">ENG</button>
			<button class="btn btn-primary langbutton">PYC</button>
		</div>
		<h1>Registration</h1>
		<form action="controller" method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-4">
					<div class="form-group">
						<input type="text" name="first_name" id="first_name"
							class="form-control input-lg" placeholder="First Name"
							tabindex="1">
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<div class="form-group">
						<input type="text" name="last_name" id="mid_name"
							class="form-control input-lg" placeholder="Middle Name"
							tabindex="2">
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<div class="form-group">
						<input type="text" name="last_name" id="last_name"
							class="form-control input-lg" placeholder="Last Name"
							tabindex="3">
					</div>
				</div>
			</div>
			<div class="form-group">
				<input type="text" name="display_name" id="display_name"
					class="form-control input-lg" placeholder="Display Name"
					tabindex="4">
			</div>
			<div class="form-group">
				<input type="email" name="email" id="email"
					class="form-control input-lg" placeholder="Email Address"
					tabindex="5">
			</div>
			<div class="form-group">
				<input type="text" name="email" id="phone"
					class="form-control input-lg" placeholder="Phone" tabindex="6">
			</div>
			<div class="form-group">
				<input type="text" name="email" id="login"
					class="form-control input-lg" placeholder="Login" tabindex="7">
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="password" name="password" id="password"
							class="form-control input-lg" placeholder="Password" tabindex="8">
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<div class="form-group">
						<input type="password" name="password_confirmation"
							id="password_confirmation" class="form-control input-lg"
							placeholder="Confirm Password" tabindex="9">
					</div>
				</div>
			</div>
			<div class="form-group">
				<textarea name="comentary" id="comentary"
					class="form-control input-lg" rows="3"
					placeholder="Type your comentary here" tabindex="10"></textarea>
			</div>
			<button class="btn btn-lg btn-block btn-primary" type="submit">Create
				new account</button>
		</form>
	</div>

</body>
</html>