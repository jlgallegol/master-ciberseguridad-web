package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;
import play.i18n.Messages;


public class PublicContentBase extends Controller {

    public static void register(){
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type){
		
		int longitud_user = username.length();
		int longitud_pass = password.length();
		int longitud_passchk = passwordCheck.length();
		if ((longitud_user>4) && (longitud_pass>7)){
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
