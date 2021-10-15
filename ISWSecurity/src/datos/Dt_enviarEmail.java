package datos;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
import java.util.*;

//import javax.activation.DataHandler;
//import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.mail.util.ByteArrayDataSource;

//import Datos.DT_enviarCorreo.SMTPAuthenticator;



public class Dt_enviarEmail {
	
	//ATRIBUTOS
	
	/*---------------------- Configuraci�n Localhost------------------------------*/
		private static final String SMTP_HOST_NAME = "smtp.gmail.com";
		private static final String SMTP_AUTH_USER = "malulacorporacion@gmail.com";
		private static final String SMTP_AUTH_PWD = "Malula123.";
		
		//Enlace de verificaci�n de correo
	    String linkHR = "http://localhost:8080/ISWSecurity/login.jsp";
	    //Enlace de recuperaci�n de pwd
	    String linkHR2 = "http://localhost:8080/ISWSecurity/reset-pwd.jsp";
	    
	    //DECLARAMOS UNA CLASE PRIVADA COMO ATRIBUTO QUE HEREDA JAVAX.MAIL.AUTHENTICATOR
	    private class SMTPAuthenticator extends javax.mail.Authenticator 
		{
			public PasswordAuthentication getPasswordAuthentication() 
			{
				String username = SMTP_AUTH_USER;
				String password = SMTP_AUTH_PWD;
				return new PasswordAuthentication(username, password);
			}
		}
	    
    /*----------------------------------------------------------------------------*/
	
	    //METODO QUE ENVIA EL EMAIL DE VERIFICACION
  		public boolean enviarEmailVerificacion(String usuario, String correo, String codigo) throws MessagingException{
  		
  			boolean debug=false;
  			
  		   // Correo del solicitante
  			String email_solicitante = correo;
  		
  		   // Correo del remitente
  			String email_remitente = SMTP_HOST_NAME;
  		
  		   // Obtener propiedades del sistema
  		   Properties properties = new Properties();
  		   
  		   
  		   /*---------------------- Configuraci�n del servidor de correo---------------------------*/ 
	  		   properties.setProperty("mail.smtp.host", SMTP_HOST_NAME);
	  		   properties.put("mail.smtp.auth", "true");
	  		   properties.setProperty("mail.smtp.port", "587");
	  		   properties.put("mail.smtp.starttls.enable", "true");
  		   /*--------------------------------------------------------------------------------------*/
  		   
  		   Authenticator auth = new SMTPAuthenticator();

  		     Session session = Session.getInstance(properties,auth);
  			 session.setDebug(debug);
  		   
  		      // Create a default MimeMessage object.
  		      	MimeMessage message = new MimeMessage(session);
  		
  		      // Establecer De (remitente)
  		      	message.setFrom(new InternetAddress(email_remitente));
  		
  		      // Establecer Para (solicitante)
  		      	message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_solicitante));
  		      
  		      // Asunto: encabezado del archivo
  		        message.setSubject("PROCESO DE VERIFICACION DEL CORREO UCA");
  		      
  		        
  		      //Cuerpo del correo  
  		        String myMsg = "<strong>PROCESO DE VERIFICACION DEL CORREO INSTITUCIONAL UCA</strong><br><br>";
  		      	myMsg += "Estimado usuario, usted ha solicitado el ingreso al Sistema ...,";
  		      	myMsg += "a continuaci&oacute;n se detallan los datos enviados: <br><br>";
  		      	myMsg += "<strong><u> DATOS DEL USUARIO/ESTUDIANTE </u></strong> <br><br>";
  		      	myMsg += "<strong>USUARIO / CARNET: </strong> "+usuario+"<br>";
  		      	myMsg += "<strong>Correo electr&oacute;nico: </strong> "+email_solicitante+"<br>";
  		      	myMsg += "Enlace de verificaci&oacute;n: "  + linkHR + "?codverif="+ codigo + "<br>";
  		      	myMsg += "Aseg&uacute;rate de hacer clic en el enlace de verificaci&oacute;n que has recibido para que podamos activar tu cuenta, en caso de no haber solicitado una cuenta, por favor hacer caso omiso al presente correo.";
  		      	myMsg += "<br>----------------------------------------------------------<br>";
  		      	myMsg += "ARMANDO J. LOPEZ L.<br>";
  		      	myMsg += "Celular: +505 8888-8888 <br>";
  		      	myMsg += "Coordinador de ISI<br>";
  		      	myMsg += "Universidad Centroamericana";
  		      
  		      message.setContent(myMsg, "text/html");
  		      
  		      // Enviar Correo
  		      Transport transport = session.getTransport("smtp");
  		      transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
  		      Transport.send(message);
  		      debug = true;
  		      System.out.println("El mensaje fue enviado con �xito");
  		      return debug;
  		}
  		
  		
  		/*----------------------------------------------------------------------------*/
  		
  		//METODO QUE ENVIA EL EMAIL PARA RECUPERAR CONTRASE�A
  		public boolean recuperaPwd(String usuario, String correo) throws MessagingException{
  		
  			boolean debug=false;
  			
  		   // Correo del solicitante
  			String email_solicitante = correo;
  		
  		   // Correo del remitente
  			String email_remitente = SMTP_HOST_NAME;
  		
  		   // Obtener propiedades del sistema
  		   Properties properties = new Properties();
  		   
  		   
  		   /*---------------------- Configuraci�n del servidor de correo---------------------------*/ 
	  		   properties.setProperty("mail.smtp.host", SMTP_HOST_NAME);
	  		   properties.put("mail.smtp.auth", "true");
	  		   properties.setProperty("mail.smtp.port", "587");
	  		   properties.put("mail.smtp.starttls.enable", "true");
  		   /*--------------------------------------------------------------------------------------*/
  		   
  		   Authenticator auth = new SMTPAuthenticator();

  		     Session session = Session.getInstance(properties,auth);
  			 session.setDebug(debug);
  		   
  		      // Create a default MimeMessage object.
  		      	MimeMessage message = new MimeMessage(session);
  		
  		      // Establecer De (remitente)
  		      	message.setFrom(new InternetAddress(email_remitente));
  		
  		      // Establecer Para (solicitante)
  		      	message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_solicitante));
  		      
  		      // Asunto: encabezado del archivo
  		        message.setSubject("PROCESO PARA RECUPERAR LA CONTRASE�A - SISTEMA HR");
  		      
  		        
  		      //Cuerpo del correo  
  		        String myMsg = "<strong>PROCESO PARA RECUPERAR LA CONTRASE�A - SISTEMA HR</strong><br><br>";
  		      	myMsg += "Estimado usuario, usted ha solicitado recuperar su contrase�a,";
  		      	myMsg += "a continuaci&oacute;n se detallan los datos enviados: <br><br>";
  		      	myMsg += "<strong><u> DATOS DEL USUARIO/ESTUDIANTE </u></strong> <br><br>";
  		      	myMsg += "<strong>USUARIO / CARNET: </strong> "+usuario+"<br>";
  		      	myMsg += "<strong>Correo electr&oacute;nico: </strong> "+email_solicitante+"<br>";
  		      	myMsg += "Enlace de recuperaci&oacute;n: "  + linkHR2 + "?us="+usuario+ "&em="+email_solicitante+"<br>";
  		      	myMsg += "Aseg&uacute;rate de hacer clic en el enlace de recuperaci&oacute;n que has recibido para que podamos reactivar tu cuenta.";
  		      	myMsg += "<br>----------------------------------------------------------<br>";
  		      	myMsg += "Administrador del Sistema<br>";
  		      	myMsg += "email: soporte@hr.com <br>";
  		      	myMsg += "Sistema HR<br>";
  		      	myMsg += "Universidad Centroamericana";
  		      
  		      message.setContent(myMsg, "text/html");
  		      
  		      // Enviar Correo
  		      Transport transport = session.getTransport("smtp");
  		      transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
  		      Transport.send(message);
  		      debug = true;
  		      System.out.println("El mensaje fue enviado con �xito");
  		      return debug;
  		}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
