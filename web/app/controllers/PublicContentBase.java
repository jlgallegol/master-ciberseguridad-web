package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;
import play.i18n.Messages;
import javax.swing.JOptionPane;

public class PublicContentBase extends Controller {

    public static void register(){
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type){
		
		int longitud_user = username.length();
		int longitud_pass = password.length();
		int longitud_passchk = passwordCheck.length();
		int indice1=0;
		int indice2=0;
		int indice3=0;
		int indice4=0;
		boolean mayus=false; 
		boolean minus=false;
		boolean numes=false;
		boolean complex = false;
		// Comprobacion de Mayusculas en cadena
		//defino la cadena copn las letras mayusculas de abecedario internacional
		String mayu ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String minu ="abcdefghijklmnopqrstuvwxyz";
		String nume ="0123456789";		
		int long_may =mayu.length();
		int long_min =minu.length();
		int long_num =nume.length();
	
		//Ejecuto un bucle tantas veces como letras tenga la cadena (legth)
		
		for( indice1=0; indice1<longitud_pass; indice1 ++)
		// tomo cada una de las letras 
			
			for( indice2=0; indice2< long_may; indice2 ++) 
				// si alguna de las letras de la cadena letra es mayuscula lo indico en la varuable mayus
				if (mayu.charAt( indice2 )== password.charAt( indice1 )) 
				mayus= true; // Es una letra mayuscula 
			
		for( indice1=0; indice1<longitud_pass; indice1 ++)
			// tomo cada una de las letras 	
			for( indice3=0; indice3< long_min; indice3 ++) 
				// si alguna de las letras de la cadena letra es mayuscula lo indico en la varuable mayus
				if (minu.charAt( indice3 )== password.charAt( indice1 )) 
				minus= true; // Es una letra minuscula 
			
		for( indice1=0; indice1<longitud_pass; indice1 ++)
			// tomo cada una de las letras 		
			for( indice4=0; indice4< long_num; indice4 ++) 
				// si alguna de las letras de la cadena letra es mayuscula lo indico en la varuable mayus
				if (nume.charAt( indice4 )== password.charAt( indice1 )) 
				numes= true; // Es un numero 
		
		complex = mayus && minus && numes;
		if ((longitud_user>4) && (longitud_pass>7) && complex){
            User u = new User(username, HashUtils.getMd5(password), type, -1);
			u.save();
			registerComplete();
        }else{
            flash("error", Messages.get("Public.login.error.passlenght"));
			Secure.login();
        }
		
    }

    public static void registerComplete(){
        render();
    }
}
