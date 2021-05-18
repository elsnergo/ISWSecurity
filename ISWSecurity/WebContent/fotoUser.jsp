<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="vistas.*, entidades.*, datos.*, java.util.*;"%>

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
                    
                    <div class="col-lg-12">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Registrar Foto de Usuario</h1>
                            </div>
                            <div id="foto" class="panel">
								<form class="form" name="frm-foto" method="post" action="./Sl_FotoUser" enctype="multipart/form-data">
								<%
									int iduser = 0;
									Usuario us = new Usuario();
									Dt_Usuario dtu = new Dt_Usuario();
									iduser = Integer.parseInt(request.getParameter("idUsuario"));
									us = dtu.getUsuario(iduser);
								%>
								<div class="cuadro-fotoNima" align="center">
									<img id="preview" src="<%=us.getUrl_foto()==null?"#":us.getUrl_foto()%>" name="preview"  alt="Foto Usuario"
										style="width: 400px; height: 400px; border-bottom-color: white; margin: 2px;" />
								</div>
										
								<input type="file" id="foto" name="foto" onchange="Test.UpdatePreview(this)" required="required">&nbsp; 
								
								<input type="hidden" name="iduser" value="<%=iduser%>">
	
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

    </div>



<!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    
    
    <script>
	    $(document).ready(function() 
		{
//////////////////////////////////FUNCION PARA PREVISUALIZAR LA FOTO QUE SUBE EL USUARIO//////////////////////////
	    	Test = {
	    	        UpdatePreview: function(obj)
	    	        {
	    	          // if IE < 10 doesn't support FileReader
	    	          if(!window.FileReader)
	    	          {
	    	             
	    	          } 
	    	          else 
	    	          {
	    	             var reader = new FileReader();
	    	             var target = null;
	    	             
	    	             reader.onload = function(e) 
	    	             {
	    	              target =  e.target || e.srcElement;
	    	               $("#preview").prop("src", target.result);
	    	             };
	    	              reader.readAsDataURL(obj.files[0]);
	    	          }
	    	        }
	    	    };
		});
	</script>
    
</body>
</html>