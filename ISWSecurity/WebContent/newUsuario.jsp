<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
import="vistas.*, entidades.*, datos.*, java.util.*;"%>
    
<%
	response.setHeader( "Pragma", "no-cache" );
	response.setHeader( "Cache-Control", "no-store" );
	response.setDateHeader( "Expires", 0 );
	response.setDateHeader( "Expires", -1 );
	
	Vw_RolUser vru = new Vw_RolUser();
	Dt_RolOpcion dtro = new Dt_RolOpcion();
	ArrayList<Vw_RolOpciones> listOpc = new ArrayList<Vw_RolOpciones>();
	
	//OBTENEMOS LA SESION
	vru = (Vw_RolUser) session.getAttribute("acceso");
	if(vru==null){
		response.sendRedirect("login.jsp?msj=401");
	}
	else{
		//OBTENEMOS LA LISTA DE OPCIONES ASIGNADAS AL ROL
		listOpc = dtro.listaRolOpc(vru.getIdrol());
		
		//RECUPERAMOS LA URL = MI OPCION ACTUAL
		int index = request.getRequestURL().lastIndexOf("/");
		String miPagina = request.getRequestURL().substring(index+1);
		boolean permiso = false; //VARIABLE DE CONTROL
		
		//VALIDAR SI EL ROL CONTIENE LA OPCION ACTUAL DENTRO DE LA MATRIZ DE OPCIONES
		for(Vw_RolOpciones vrop : listOpc){
			if(vrop.getOpcion().trim().equals(miPagina.trim())){
				permiso = true; //ACCESO CONCEDIDO
				break;
			}
		}
		
		if(!permiso){
			response.sendRedirect("401.jsp");
		}	
	}
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <meta name="description" content="">
 <meta name="author" content="">
<title>Nuevo Usuario</title>
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

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Registrar Nuevo Usuario</h1>
                            </div>
                            <form class="user" method="post" action="./Sl_GestionUsuario" >
								<!-- El valor de este input es para el Servlet opcion guardar -->
                            	<input name="opcion" type="hidden" value="1" />
                            	<div class="form-group row">
                                    <div class="col-sm-12 mb-3">
                                        <input type="text" class="form-control form-control-user" name="txtNombres" id="txtNombres"
                                            placeholder="Nombres" required>
                                    </div>
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control form-control-user" name="txtApellidos" id="txtApellidos"
                                            placeholder="Apellidos" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-12 mb-3">
                                        <input type="text" class="form-control form-control-user" name="txtUserName" id="txtUserName"
                                            placeholder="Nombre de Usuario" required>
                                    </div>
                                    <div class="col-sm-12 mb-3">
                                        <input type="password" class="form-control form-control-user" name="txtPwd" id="txtPwd"
                                            placeholder="Contraseña" required>
                                    </div>
                                    <div class="col-sm-12">
                                        <input type="password" class="form-control form-control-user" name="txtPwd2" id="txtPwd2"
                                            placeholder="Repetir Contraseña" required>
                                    </div>
                                </div>
	                            <hr>
	                            <div class="text-center">
	                                <input class="btn btn-primary btn-user btn-block" type="submit" value="Guardar" />
	                            </div>
	                            <div class="text-center">
	                                <input class="btn btn-google btn-user btn-block" type="reset" value="Cancelar" />
	                            </div>
                            </form>
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