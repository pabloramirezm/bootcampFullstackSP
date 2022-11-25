package Reto1;

import java.util.ArrayList;
import java.util.Scanner;

public class Challenge1 {
	 public static void main(String[] args) 
	    { 
	       	
	       	System.out.println("Bootcamp Fullstack Portales" + "\n"); 
	       	System.out.println("PABLO RAMIREZ - pablorame@gmail.com" + "\n"); 
	       	System.out.println("Mini Reto punto 2.a" + "\n");  
	       	System.out.println("Digite A o B según el tipo que desee"+"\n");
	        @SuppressWarnings("resource")
			Scanner t = new Scanner(System.in);
	        String tipo = t.next();
	       	
	        System.out.println(getRandomString(tipo)+"\n");
	    
	    	System.out.println("Mini Reto punto 2.b" + "\n");
	    	System.out.println("Ingese un apellido y Comience siempre con Mayúscula"+"\n");
	    	
	    	@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
	       	String apellido = s.next();
	   		ArrayList<String> apellidos = new ArrayList<>();
	       	apellidos.add("Calle");
	       	apellidos.add("Salazar");
	       	apellidos.add("Ortiz");
	       	apellidos.add("Valencia");
	       	apellidos.add("Ramirez");
	       	apellidos.add("Guzman");
	       	apellidos.add("Carmona");
	       	apellidos.add("Mejia");
	       	apellidos.add("Carvajal");
	       	apellidos.add("Castillo");
	       	apellidos.add("Mendoza");  
	       	apellidos.add("Rodriguez");
	       	apellidos.add("Perez");
	       	apellidos.add("Angarita");
	    	
	       	System.out.println(getApellidoBoolean(apellido,apellidos)+"\n");
	    	System.out.println("MUCHAS GRACIAS");
	    	}
	    
	  public static String getRandomString(String tipo) {
		  
        String Numbers;
        StringBuilder builder;
        
        String var = null;
        Numbers = "1234567890"; 
        switch(tipo) {
     	case "A":
    	var = "54";
     	break;
     	case "B":    
        var = "08";
     	break;
     	}   
        
        //create the StringBuffer
        builder = new StringBuilder(); 

        for (int m = 0; m < 8; m++) { 

            // generate numeric
            int myindex 
                = (int)(Numbers.length() 
                        * Math.random()); 

            // add the characters
            builder.append(Numbers 
                        .charAt(myindex)); 
        } 

        return var+builder.toString(); 
    } 
	
   	public static Boolean getApellidoBoolean(String apellido,ArrayList<String> apellidos)
   	{
   		
       	
        boolean existe = apellidos.contains(apellido);
    	if (existe) {
    		return false;
    	} else {
    		return true;
    	}
   		
   	}
   	
}

	   
	    

