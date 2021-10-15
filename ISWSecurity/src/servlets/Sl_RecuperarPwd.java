package servlets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.*;
import entidades.*;

/**
 * Servlet implementation class Sl_RecuperarPwd
 */
@WebServlet("/Sl_RecuperarPwd")
public class Sl_RecuperarPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_RecuperarPwd() {
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
		Dt_enviarEmail dtem = new Dt_enviarEmail();
		Dt_Usuario dtu = new Dt_Usuario();
		Usuario us = new Usuario();
		int opc = 0;
		String usuario = "";
		String email = "";
		String newPwd = "";
		
		opc = Integer.parseInt(request.getParameter("opcion"));
		
		
		
		
		switch (opc){
		case 1:
			try {
				usuario = request.getParameter("user");
				email = request.getParameter("email");
				us = dtu.getUserPwd(usuario, email);
				if(us.getIdUser()!=0){
					dtem.recuperaPwd(us.getUser(), us.getEmail());
					response.sendRedirect("recupera-pwd.jsp?msj=1");
				}
				else {
					response.sendRedirect("recupera-pwd.jsp?msj=2");
				}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.getMessage();
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				newPwd = request.getParameter("newPwd1");
				usuario = request.getParameter("user");
				email = request.getParameter("email");
				us = dtu.getUserPwd(usuario, email);
				if(dtu.updatePwd(us, newPwd)){
					response.sendRedirect("login.jsp?msj=1");
					//REENVIAMOS AL LOGIN INDICANDO QUE EL PROCESO DE ACTUALIZACION DE CONTRASEÑA FUE EXITOSO
				}
				else {
					response.sendRedirect("recupera-pwd.jsp?msj=3");
					//ESTE MENSAJE INDICA QUE FALLO EL PROCESO DE ACTUALIZACION DE CONTRASEÑA
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
				e.printStackTrace();
			}
			
			break;
		default:
			response.sendRedirect("recupera-pwd.jsp?msj=4");
			//VERIFIQUE LOS DATOS E INTENTE NUEVAMENTE
			break;
				
		
		}
		
		
		
		
		
	}

}
