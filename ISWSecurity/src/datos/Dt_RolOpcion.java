package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.Vw_RolOpciones;

public class Dt_RolOpcion {
	
	//Atributos
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
//	private ResultSet rsRol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Metodo para visualizar las opciones de un rol
	public ArrayList<Vw_RolOpciones> listaRolOpc(int idRol){
		ArrayList<Vw_RolOpciones> listropc = new ArrayList<Vw_RolOpciones>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.vw_rol_opciones where idrol=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idRol);
			rs = ps.executeQuery();
			while(rs.next()){
				Vw_RolOpciones vwrop = new Vw_RolOpciones();
				vwrop.setIdrol_opc(rs.getInt("idrol_opc"));
				vwrop.setIdrol(rs.getInt("idrol"));
				vwrop.setRol(rs.getString("rol"));
				vwrop.setIdopc(rs.getInt("idopc"));
				vwrop.setOpcion(rs.getString("opcion"));
				listropc.add(vwrop);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN listaRolOpc "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listropc;
	}
		

}
