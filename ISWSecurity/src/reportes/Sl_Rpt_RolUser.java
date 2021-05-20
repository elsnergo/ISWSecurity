package reportes;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.PoolConexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * Servlet implementation class Sl_Rpt_RolUser
 */
@WebServlet("/Sl_Rpt_RolUser")
public class Sl_Rpt_RolUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sl_Rpt_RolUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try 
		{
			/*
			PoolConexion pc = PoolConexion.getInstance(); 
			Connection c = PoolConexion.getConnection();
			
			Connection con = new Conexion().getConnection();
			
			String nima = "";
			nima = request.getParameter("NIMA");
			nima = nima==null?"codNIMA":nima;
			System.out.println("nima: "+nima);
			
			String usuario = "";
			usuario = request.getParameter("us");
			usuario = usuario == null?"0":usuario;
			System.out.println("user: "+usuario);
			
			HashMap<String, Object>hm = new HashMap<>();
			hm.put("codNIMA", nima);
			
			*/	
			PoolConexion pc = PoolConexion.getInstance(); 
			Connection c = PoolConexion.getConnection();
			HashMap hm = new HashMap();
			OutputStream otps = response.getOutputStream();
			ServletContext context = getServletContext();
			String path = context.getRealPath("/");
			System.out.println("Path: "+path);
			String template = "reportes\\RolUser.jasper";
			Exporter exporter = new JRPdfExporter();
			JasperPrint jasperPrint = JasperFillManager.fillReport(path+template, hm, c);
			response.setContentType("application/pdf");
//			response.setHeader("Content-Disposition", "inline; filename=\"RptRolUser_"+nima+".pdf\"");
			response.setHeader("Content-Disposition", "inline; filename=\"RptRolUser.pdf");
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(otps));
			exporter.exportReport();
			
//			System.out.println("RUTA " + new File("Resultados.jasper").getAbsolutePath());
//			System.out.println("EXISTE " + new File("Resultados.jasper").exists());  
//			System.out.println("LEER " + new File("Resultados.jasper").canRead());
//			System.out.println("RUTA: " + path+template);
//			
//			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(path);
//			JasperPrint reportFilled = JasperFillManager.fillReport(report, hm, con);
//			
//			JasperExportManager.exportReportToPdfFile(reportFilled, path);
//			JasperCompileManager.compileReport(path);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("REPORTE: ERROR AL GENERAR REPORTE " + e.getMessage());
		}
	}
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
