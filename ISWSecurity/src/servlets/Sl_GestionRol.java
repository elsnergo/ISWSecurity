package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.Dt_Rol;
import entidades.Rol;

/**
 * Servlet implementation class Sl_GetionRol
 */
@WebServlet("/Sl_GestionRol")
public class Sl_GestionRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_GestionRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		//obtenemos el valor de opcion
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		//CONSTRUIR EL OBJETO ROL
		Dt_Rol dtr = new Dt_Rol();
		Rol r = new Rol();
		r.setIdRol(Integer.parseInt(request.getParameter("idRol")));
		r.setRol(request.getParameter("txtRol"));
		r.setDesc_rol(request.getParameter("txtRolDesc"));
		
		switch (opc){
		case 1:{
			
		        try {
		        	/*PARA GUARDAR LA FECHA Y HORA DE CREACION
			        Date fechaSistema = new Date();
			        user.setfCreacion(new java.sql.Timestamp(fechaSistema.getTime()));
			        System.out.println("user.getFechaCreacion(): "+user.getfCreacion());
			        if(dtu.guardarUser(user)) {
			        	response.sendRedirect("tblUsuarios.jsp?msj=1");
			        }
			        else {
			        	response.sendRedirect("tblUsuarios.jsp?msj=2");
			        }*/
			        	
		        	
		        }
		        catch(Exception e) {
		        	System.out.println("Sl_GestionRol, el error es: " + e.getMessage());
					e.printStackTrace();
		        }
		        
				break;
			}
		
		case 2:{
				
			try {
		        if(dtr.modificarRol(r)) {
		        	response.sendRedirect("tblRol.jsp?msj=3");
		        }
		        else {
		        	response.sendRedirect("tblRol.jsp?msj=4");
		        }
	        }
	        catch(Exception e) {
	        	System.out.println("Sl_GestionRol, el error es: " + e.getMessage());
				e.printStackTrace();
	        }
				break;
				
			}
		
		default:
			response.sendRedirect("tblRol.jsp?msj=5");	
			break;
	}
		
	}

}
