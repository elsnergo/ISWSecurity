package datos;
//Importando nuestras liberias
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

//import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;

public class PoolConexion {
	
	//ATRIBUTOS
	private static PoolConexion INSTANCE = null;
	private static Connection con = null;
	private static BasicDataSource dataSource;
	//SERVIDOR LOCAL
	private static String db = "hr";
	private static String url = "jdbc:postgresql://localhost:5432/"+db;
	private static String user = "postgres";
	private static String password = "Egonzalez87";
	//SERVIDOR REMOTO
//	private static String db = "Mi_BD";
//	private static String url = "jdbc:postgresql://165.98.12.158:5432/"+db;
//	private static String user = "risw";
//	private static String password = "P0$GR3$2021*";
	
	//CONSTRUCTOR
	private PoolConexion(){
		inicializaDataSource();
    }
	
	//METODOS
	private synchronized static void createInstance(){
		if(INSTANCE==null){
			INSTANCE = new PoolConexion();
		}
	}
	
	public static PoolConexion getInstance(){
		if(INSTANCE == null){
			createInstance();
		}
		return INSTANCE;
	}
	
	public final void inicializaDataSource(){
		
		org.apache.commons.dbcp.BasicDataSource basicDataSource = new org.apache.commons.dbcp.BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxActive(50);
        dataSource = basicDataSource;
    }
	
	public static boolean estaConectado(){
    	boolean resp = false;
    	try{
    		if ((con==null) || (con.isClosed()))
    			resp = false;
    		else
    			resp = true;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
    	return resp;
    }
	
	public static Connection getConnection() 
    {	
	   if (estaConectado()==false){
		try {
			con = PoolConexion.dataSource.getConnection();
			System.out.println("se conectó a la bd hr!!!");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	   }
	   return con;
    }
	
	public static void closeConnection(Connection con) {	
    	if (estaConectado()!=false) {
    		try {
    			con.close();
    			System.out.println("Cerró la conexion a la bd hr!!!");
    		} 
    		catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println(e.getMessage());
    		}
    	}
    }
	
	
	public static void main(String[] args) {/*
		// TODO Auto-generated method stub
		
		PoolConexion.getInstance();
		Connection con = null;
        
        try{
	    	con = PoolConexion.getConnection();
	    	if(con!=null){
	    		JOptionPane.showMessageDialog(null, "Conectado a la bd hr!");
	    		System.out.println("Conectado a la bd hr!");
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null, "Error al intentar conectarse a la bd hr!!!");
	    		System.out.println("Error al intentar conectarse a la bd hr!!!");
	    	}
        }
        finally{
            try {
               con.close();
               if ((con==null) || (con.isClosed())) {
            	   JOptionPane.showMessageDialog(null, "Se desconecto de la bd hr!");
            	   System.out.println("Se desconecto de la bd hr!");
               }
               else {
            	   JOptionPane.showMessageDialog(null, "Sigue conectado a la bd hr!!!");
            	   System.out.println("Sigue conectado a la bd hr!!!");
               }
            } 
            catch (SQLException ex) {
            	ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }*/
		

	}

}
