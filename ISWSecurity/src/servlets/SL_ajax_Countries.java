package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import datos.Dt_Countries;
import entidades.Countries;

/**
 * Servlet implementation class SL_ajax_Countries
 */
@WebServlet("/SL_ajax_Countries")
public class SL_ajax_Countries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_ajax_Countries() {
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
		// doGet(request, response);
		
		String idR=request.getParameter("regId");
		idR = idR==null?"0":idR;
		String salida = "";
		try
		{
			Dt_Countries dtc = new Dt_Countries();
			ArrayList<Countries> listC = new ArrayList<Countries>();
			listC = dtc.getPais(Integer.parseInt(idR));
			salida += "<option value='0'>SELECCIONE</option>";
			for (Countries c : listC)
			{
				salida +="<option value=\""+c.getCountry_id()+"\">"+StringEscapeUtils.escapeHtml4(c.getCountry_name())+"</option>";
			}
			PrintWriter pw = response.getWriter();
			pw.write(salida);
			pw.flush();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("SL AJAX: EL ERROR ES: "+e.getMessage());
		}
		
	}

}
