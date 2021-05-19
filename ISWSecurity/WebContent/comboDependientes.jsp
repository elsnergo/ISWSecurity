<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*;"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <meta name="description" content="">
 <meta name="author" content="">
<title>Combobox-Dependientes</title>
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
                                <h1 class="h4 text-gray-900 mb-4">Combobox Dependientes</h1>
                            </div>
                            <div id="foto" class="panel">
								<form class="form" name="frm-foto" method="post" action="./#">
									<div class="form-group row">
                                    <div class="col-sm-12 mb-3">
                                    	<%
		                                	ArrayList<Regions> listR = new ArrayList<Regions>();
		                                	Dt_Regions dtr = new Dt_Regions();
											listR = dtr.comboRegion();
                                		%>
                                    	<select class="form-control" name="cbxRegion" id="cbxRegion" required>
                                    	<option value="0">Seleccione...</option>
                                    	<%
                                    		for(Regions r: listR){
                                    	%>	
                                    		<option value="<%=r.getRegion_id()%>"><%=r.getRegion_name()%></option>
                                    	<%
                                    		}
                                    	%>
                                    	
                                    	</select>
                                    </div>
                                    <div class="col-sm-12 mb-3">
                                    	<%
		                                	ArrayList<Countries> listC = new ArrayList<Countries>();
		                                	Dt_Countries dtc = new Dt_Countries();
											listC = dtc.comboPais();
                                		%>
                                    	<select class="form-control" name="cbxCountries" id="cbxCountries" required>
                                    	<option value="0">Seleccione...</option>
                                    	<%
                                    		for(Countries c: listC){
                                    	%>	
                                    		<option value="<%=c.getCountry_id()%>"><%=c.getCountry_name()%></option>
                                    	<%
                                    		}
                                    	%>
                                    	
                                    	</select>
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
			//EVENTOS QUE CONTROLAN EL COMBOBOX DE PAIS //
			$("#cbxRegion").change(function()
			{
				var reg = $("#cbxRegion").val();
				if (reg == "0"){
					$("#cbxCountries").val("0");
					$("#cbxCountries").prop('disabled', true);
				}
				else {
					$("#cbxCountries").prop('disabled', false);
					$.ajax({		    
				          url: "SL_ajax_Countries",
				          type: "post",
				          datatype:"html",
				          data: {'regId':reg},
				          success: function(data) {
				        		$('#cbxCountries').html(data);  
				          }
				        });
				};			
			});
  
		});
	</script>
    
</body>
</html>