package entidades;

import java.sql.Timestamp;

public class Usuario {
	
	//Atributos
	private int idUser;
	private String user;
	private String pwd;
	private String nombre;
	private String apellido;
	private Timestamp fCreacion;
	private Timestamp fModificacion;
	private Timestamp fEliminacion;
	private int estado;
	private String url_foto;
	private String email;
	private String cod_verificacion;
	
	//Metodos
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Timestamp getfCreacion() {
		return fCreacion;
	}
	public void setfCreacion(Timestamp fCreacion) {
		this.fCreacion = fCreacion;
	}
	public Timestamp getfModificacion() {
		return fModificacion;
	}
	public void setfModificacion(Timestamp fModificacion) {
		this.fModificacion = fModificacion;
	}
	public Timestamp getfEliminacion() {
		return fEliminacion;
	}
	public void setfEliminacion(Timestamp fEliminacion) {
		this.fEliminacion = fEliminacion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getUrl_foto() {
		return url_foto;
	}
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCod_verificacion() {
		return cod_verificacion;
	}
	public void setCod_verificacion(String cod_verificacion) {
		this.cod_verificacion = cod_verificacion;
	}
	
}
