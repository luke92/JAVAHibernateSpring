package edu.utn.util;

public abstract class Mensaje {
	
	public static String Error(String dato)
	{
		return "<div class='alert alert-danger'>" + dato + "</div>";
	}
	
	public static String Exito(String dato)
	{
		return "<div class='alert alert-success'>" + dato + "</div>";
	}

}
