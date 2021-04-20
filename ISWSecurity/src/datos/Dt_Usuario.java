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
			ps = c.prepareStatement("select * from public.\"usuario\"", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
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
			ps = c.prepareStatement("select * from public.\"usuario\"", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Usuario user = new Usuario();
				user.setIdUser(rs.getInt("idUser"));
				user.setUser(rs.getString("user"));
				user.setPwd(rs.getString("pwd"));
				user.setNombre(rs.getString("nombres"));
				user.setApellido(rs.getString("apellidos"));
				user.setfCreacion(rs.getTimestamp("fcreacion"));
				user.setEstado(rs.getInt("estado"));
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
	
	// Metodo para visualizar los datos de un usuario específico
	public Usuario getUsuario(int idUsuario)
	{
		Usuario user = new Usuario();
		try
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.\"usuario\" where estado <> 3 and \"idUser\"=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idUsuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				user.setIdUser(idUsuario);
				user.setUser(rs.getString("user"));
				user.setPwd(rs.getString("pwd"));
				user.setNombre(rs.getString("nombres"));
				user.setApellido(rs.getString("apellidos"));
				user.setUrl_foto(rs.getString("url_foto"));
				user.setEstado(rs.getInt("estado"));
			}
		}
		catch (Exception e)
		{
			System.out.println("DATOS ERROR getNIMA(): "+ e.getMessage());
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
		
		return user;
	}
	
	
	//Metodo para almacenar nuevo usuario
	public boolean guardarUser(Usuario user){
		boolean guardado = false;
		
		try{
			c = PoolConexion.getConnection();
			this.llenaRsUsuario(c);
			rsUsuario.moveToInsertRow();
//			rsUsuario.updateInt("UsuarioID", 2);
			rsUsuario.updateString("user", user.getUser());
			rsUsuario.updateString("pwd", user.getPwd());
			rsUsuario.updateString("nombres", user.getNombre());
			rsUsuario.updateString("apellidos", user.getApellido());
			rsUsuario.updateTimestamp("fcreacion", user.getfCreacion());
			rsUsuario.updateInt("Estado", 1);
			rsUsuario.insertRow();
			rsUsuario.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR Usuario "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsUsuario != null){
					rsUsuario.close();
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
	
	// Metodo para modificar usuario
	public boolean modificarUser(Usuario user)
	{
		boolean modificado=false;	
		try
		{
			c = PoolConexion.getConnection();
			this.llenaRsUsuario(c);
			rsUsuario.beforeFirst();
			while (rsUsuario.next())
			{
				if(rsUsuario.getInt(1)==user.getIdUser())
				{
					rsUsuario.updateString("user", user.getUser());
					rsUsuario.updateString("pwd", user.getPwd());
					rsUsuario.updateString("nombres", user.getNombre());
					rsUsuario.updateString("apellidos", user.getApellido());
					rsUsuario.updateTimestamp("fmodificacion", user.getfModificacion());
					rsUsuario.updateInt("estado", 2);
					rsUsuario.updateRow();
					modificado=true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("ERROR AL ACTUALIZAR USUARIO "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsUsuario != null){
					rsUsuario.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return modificado;
	}
	
	// Metodo para guardar la foto del Usuario
	public boolean guardarFotoUser(int idUser, String urlFoto)
	{
		boolean actualizado = false;
		
		try
		{
			c = PoolConexion.getConnection();
			this.llenaRsUsuario(c);	
			rsUsuario.beforeFirst();
			while(rsUsuario.next())
			{
				if(rsUsuario.getInt(1)==idUser)
				{
					
					rsUsuario.updateString("url_foto", urlFoto);
					rsUsuario.updateInt("estado", 2);
					rsUsuario.updateRow();
					actualizado = true;
					break;
				}
			}
		}
		catch (Exception e) 
		{
			System.err.println("ERROR AL GUARDAR FOTO "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsUsuario != null){
					rsUsuario.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return actualizado;
	}
	
	
	
}
