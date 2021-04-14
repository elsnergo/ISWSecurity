package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.RolUser;


public class Dt_RolUser {
	
	//Atributos
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsRolUser = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	// Metodo para llenar el RusultSet
	public void llenaRsRolUser(Connection c){
		try{
			ps = c.prepareStatement("select * from public.rol_usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRolUser = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR rol_usuario "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para asignar rol a usuario
	public boolean guardarRolUser(RolUser ru){
		boolean guardado = false;
		
		try{
			c = PoolConexion.getConnection();
			this.llenaRsRolUser(c);
			rsRolUser.moveToInsertRow();
			rsRolUser.updateInt("iduser", ru.getIduser());
			rsRolUser.updateInt("idrol", ru.getIdrol());
			rsRolUser.insertRow();
			rsRolUser.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL guardarRolUser "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsRolUser != null){
					rsRolUser.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return guardado;
	}

}
