package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.RolUser;
import vistas.Vw_RolUser;


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
	
	
	//Metodo para visualizar LA LISTA DE ROLES ASIGNADOS A LOS USUARIOS
	public ArrayList<Vw_RolUser> listaRolUser(){
		ArrayList<Vw_RolUser> listRU = new ArrayList<Vw_RolUser>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM public.vw_user_rol", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Vw_RolUser vwru = new Vw_RolUser();
				vwru.setIdrol_usuario(rs.getInt("idrol_usuario"));
				vwru.setIduser(rs.getInt("iduser"));
				vwru.setIdrol(rs.getInt("idrol"));
				vwru.setUser(rs.getString("user"));
				vwru.setRol(rs.getString("rol"));
				vwru.setPwd(rs.getString("pwd"));
				vwru.setNombres(rs.getString("nombres"));
				vwru.setApellidos(rs.getString("apellidos"));
				listRU.add(vwru);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS "+ e.getMessage());
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
		return listRU;
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
	
	// Metodo para eliminar RolUsuario
	public boolean eliminaRU(int idRU)
	{
		boolean eliminado=false;	
		try
		{
			c = PoolConexion.getConnection();
			this.llenaRsRolUser(c);
			rsRolUser.beforeFirst();
			while (rsRolUser.next())
			{
				if(rsRolUser.getInt(1)==idRU)
				{
					rsRolUser.deleteRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("ERROR AL ELIMINAR ROL_USER "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
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
		return eliminado;
	}

}
