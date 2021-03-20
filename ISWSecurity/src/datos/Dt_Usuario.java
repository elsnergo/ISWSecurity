package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Usuario;

public class Dt_Usuario {
	
	//Atributos
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	// Metodo para llenar el RusultSet
	public void llenaRsUsuario(Connection c){
		try{
			ps = c.prepareStatement("SELECT * from Usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUsuario = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para visualizar usuarios registrados y activos
	public ArrayList<Usuario> listaUserActivos(){
		ArrayList<Usuario> listUser = new ArrayList<Usuario>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.\"Usuario\"", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Usuario user = new Usuario();
				user.setUsuarioID(rs.getInt("UsuarioID"));
				user.setNombre(rs.getString("Nombre"));
				user.setApellido(rs.getString("Apellido"));
				user.setNombreUsuario(rs.getString("Nombreusuario"));
				user.setPwd(rs.getString("Pwd"));
				user.setEstado(rs.getInt("Estado"));
//				user.setFechaCreacion(rs.getTimestamp("Fechacreacion"));
				listUser.add(user);
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
		return listUser;
	}

}
