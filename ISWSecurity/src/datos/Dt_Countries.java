package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Countries;

public class Dt_Countries {
	//Atributos
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Metodo para visualizar los Paises
	public ArrayList<Countries> comboPais(){
		ArrayList<Countries> listaPais = new ArrayList<Countries>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.countries", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Countries c = new Countries();
				c.setCountry_id(rs.getString("country_id"));
				c.setCountry_name(rs.getString("country_name"));
				c.setRegion_id(rs.getInt("region_id"));
				listaPais.add(c);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN comboPais() "+ e.getMessage());
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
		return listaPais;
	}
	
	//Metodo para filtrar los Paises por region
	public ArrayList<Countries> getPais(int region){
		ArrayList<Countries> listaPais = new ArrayList<Countries>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.countries where region_id=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, region);
			rs = ps.executeQuery();
			while(rs.next()){
				Countries c = new Countries();
				c.setCountry_id(rs.getString("country_id"));
				c.setCountry_name(rs.getString("country_name"));
				c.setRegion_id(rs.getInt("region_id"));
				listaPais.add(c);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN getPais() "+ e.getMessage());
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
		return listaPais;
	}
	
	

}
