package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Rol;

public class Dt_Rol {
	
	//Atributos
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsRol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	
	// Metodo para llenar el RusultSet
	public void llenaRsRol(Connection c){
		try{
			ps = c.prepareStatement("select * from public.rol", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRol = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR ROLES "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para visualizar usuarios registrados y activos
	public ArrayList<Rol> listaRolActivos(){
		ArrayList<Rol> listRol = new ArrayList<Rol>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.rol", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Rol r = new Rol();
				r.setIdRol(rs.getInt("idrol"));
				r.setRol(rs.getString("rol"));
				r.setDesc_rol(rs.getString("desc_rol"));
				r.setEstado(rs.getInt("estado"));
				listRol.add(r);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR ROLES "+ e.getMessage());
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
		return listRol;
	}
	
	// Metodo para visualizar los datos de un rol específico
		public Rol getRol(int idRol)
		{
			Rol r = new Rol();
			try
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("select * from public.rol where estado <> 3 and idrol=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idRol);
				rs = ps.executeQuery();
				if(rs.next())
				{
					r.setIdRol(idRol);
					r.setRol(rs.getString("rol"));
					r.setDesc_rol(rs.getString("desc_rol"));
					r.setEstado(rs.getInt("estado"));
				}
			}
			catch (Exception e)
			{
				System.out.println("DATOS ERROR getRol(): "+ e.getMessage());
				e.printStackTrace();
			}
			finally
			{
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
			
			return r;
		}
	

}
