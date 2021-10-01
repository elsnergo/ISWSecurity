package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuario;
import datos.Dt_Usuario;
import datos.Dt_enviarEmail;
import negocio.Ng_Usuario;

/**
 * Servlet implementation class Sl_GestionUsuario
 */
@WebServlet("/Sl_GestionUsuario")
public class Sl_GestionUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_GestionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int idUser =0;
		idUser = Integer.parseInt(request.getParameter("idU"));
		Dt_Usuario dtu = new Dt_Usuario();
		
		if(dtu.eliminarUser(idUser)) {
        	response.sendRedirect("tblUsuarios.jsp?msj=5");
        }
        else {
        	response.sendRedirect("tblUsuarios.jsp?msj=6");
        }
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		//obtenemos el valor de opcion
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		//CONSTRUIR EL OBJETO USUARIO
		Usuario user = new Usuario();
		Dt_Usuario dtu = new Dt_Usuario();
		Ng_Usuario ngu = new Ng_Usuario();
		Dt_enviarEmail dtem = new Dt_enviarEmail();
		
		user.setNombre(request.getParameter("txtNombres"));
		user.setApellido(request.getParameter("txtApellidos"));
		user.setUser(request.getParameter("txtUserName"));
		user.setPwd(request.getParameter("txtPwd"));
		user.setEmail(request.getParameter("txtEmail"));
		//GENERAMOS EL CODIGO DE VERIFICACION Y LO ASIGNAMOS AL OBJETO
		user.setCod_verificacion(dtu.randomAlphaNumeric(10)); // 10 PORQUE ES LA CANTIDAD DE CARACTERES QUE SOPORTA LA BD
		
		switch (opc){
			case 1:{
				
			        try {
			        	//PARA GUARDAR LA FECHA Y HORA DE CREACION
				        Date fechaSistema = new Date();
				        user.setfCreacion(new java.sql.Timestamp(fechaSistema.getTime()));
				        System.out.println("user.getFechaCreacion(): "+user.getfCreacion());
				        if(ngu.existeUser(user.getUser())) {
				        	response.sendRedirect("newUsuario.jsp?msj=existe");
				        }
				        else {
				        	if(dtu.guardarUser(user)) {
				        		if(dtem.enviarEmailVerificacion(user.getUser(), user.getPwd(), user.getEmail(), user.getCod_verificacion())) {
				        			response.sendRedirect("tblUsuarios.jsp?msj=1");
				        		}
					        }
					        else {
					        	response.sendRedirect("tblUsuarios.jsp?msj=2");
					        }
				        }
				        
			        }
			        catch(Exception e) {
			        	System.out.println("Sl_GestionUsuario, el error es: " + e.getMessage());
						e.printStackTrace();
			        }
			        
					break;
				}
			
			case 2:{
					
				try {
					user.setIdUser(Integer.parseInt(request.getParameter("idUsuario")));
		        	//PARA GUARDAR LA FECHA Y HORA DE MODIFICACION
			        Date fechaSistema = new Date();
			        user.setfModificacion(new java.sql.Timestamp(fechaSistema.getTime()));
			        System.out.println("user.getfModificacion(): "+user.getfModificacion());
			        if(dtu.modificarUser(user)) {
			        	response.sendRedirect("tblUsuarios.jsp?msj=3");
			        }
			        else {
			        	response.sendRedirect("tblUsuarios.jsp?msj=4");
			        }
			        	
		        	
		        }
		        catch(Exception e) {
		        	System.out.println("Sl_GestionUsuario, el error es: " + e.getMessage());
					e.printStackTrace();
		        }
					break;
					
				}
			
			default:
				response.sendRedirect("tblUsuarios.jsp?msj=7");	
				break;
		}
		
		
	}

}
