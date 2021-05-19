package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Regions;

public class Dt_Regions {
	//Atributos
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Metodo para visualizar las regiones
	public ArrayList<Regions> comboRegion(){
		ArrayList<Regions> listaRegion = new ArrayList<Regions>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.regions", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Regions r = new Regions();
				r.setRegion_id(rs.getInt("region_id"));
				r.setRegion_name(rs.getString("region_name"));
				listaRegion.add(r);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN comboRegion() "+ e.getMessage());
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
		return listaRegion;
	}
	
	
	
	

}
