<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*;"%>
<%
	response.setHeader( "Pragma", "no-cache" );
	response.setHeader( "Cache-Control", "no-store" );
	response.setDateHeader( "Expires", 0 );
	response.setDateHeader( "Expires", -1 );
	
	String mensaje = request.getParameter("msj");
	mensaje=mensaje==null?"":mensaje;
	
	String codigo = request.getParameter("codverif");
	codigo=codigo==null?"":codigo;
	
	HttpSession hts = request.getSession(false);
	hts.removeAttribute("acceso");
	hts.invalidate();

%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login HR</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary">
    <div class="container">
        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Inicio de Sesión Sist. "HR"</h1>
                                    </div>
                                    <form class="user" method="post" action="./Sl_Login">
                                        <div class="form-group">
                                        	<input type="hidden" name="codVerificacion" value="<%=codigo%>">
                                            <input type="text" class="form-control form-control-user"
                                                id="userName" name="userName" placeholder="Usuario" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user"
                                                id="pwd" name="pwd" placeholder="Contraseña" required>
                                        </div>
                                        <div class="form-group">
                                            <%
			                                	ArrayList<Rol> listRol = new ArrayList<Rol>();
			                                	Dt_Rol dtr = new Dt_Rol();
												listRol = dtr.listaRolActivos();
                                			%>
                                    		<select class="form-control" name="cbxRol" id="cbxRol" required>
		                                    	<option value="">Seleccione...</option>
		                                    	<%
		                                    		for(Rol r: listRol){
		                                    	%>	
		                                    		<option value="<%=r.getIdRol()%>"><%=r.getRol()%></option>
		                                    	<%
		                                    		}
		                                    	%>
                                    		</select>
                                        </div>
                                        <hr>
                                        <input type="submit" value="Entrar" class="btn btn-primary btn-user btn-block" />
                                        <input type="submit" value="Cancelar" class="btn btn-google btn-user btn-block" />
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.html">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="register.html">Create an Account!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>
</html>