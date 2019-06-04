package edu.utn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
	
	public static Date String_a_Fecha(String fecha) throws ParseException
    {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(fecha);
    }
    
    public static String Fecha_a_String(Date fecha)
    {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(fecha);
    }
    
    public static Date StringToDateTime(String fecha) throws ParseException
    {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(fecha);
    }
}
