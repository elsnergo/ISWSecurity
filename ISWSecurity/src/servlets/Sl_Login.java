package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.Dt_Usuario;
import vistas.Vw_RolUser;

/**
 * Servlet implementation class Sl_Login
 */
@WebServlet("/Sl_Login")
public class Sl_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		Dt_Usuario dtu = new Dt_Usuario();
		Vw_RolUser vwru = new Vw_RolUser();
		String usuario = "";
		String clave = "";
		String codigoV = "";
		int rolId = 0;
		
		usuario = request.getParameter("userName");
		clave = request.getParameter("pwd");
				
		
		rolId = Integer.parseInt(request.getParameter("cbxRol"));
		codigoV= request.getParameter("codVerificacion");
		
		try{
			if(dtu.dtverificarLogin(usuario, clave, rolId)){
				vwru = dtu.dtGetRU(usuario);
				HttpSession hts = request.getSession(true);
				hts.setAttribute("acceso", vwru);
				response.sendRedirect("index.jsp");
			}
			else{
				if(dtu.dtverificarLogin2(usuario, clave, rolId, codigoV)){
					vwru = dtu.dtGetRU(usuario);
					HttpSession hts = request.getSession(true);
					hts.setAttribute("acceso", vwru);
					response.sendRedirect("index.jsp");
				}
				else{
					response.sendRedirect("login.jsp?msj=403");
				}	
			}
		}
		catch(Exception e)
		{
			System.out.println("Servlet: El error es: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}

}
