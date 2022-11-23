package Reto1;
import java.util.*;

public class Challenge1 {
	  
	   
	public static String getRandomString(int i) 
	  
	    { 
	        String Numbers;
	        StringBuilder builder;
	        
	        Numbers = "1234567890"; 

	        //create the StringBuffer
	        builder = new StringBuilder(i); 

	        for (int m = 0; m < i; m++) { 

	            // generate numeric
	            int myindex 
	                = (int)(Numbers.length() 
	                        * Math.random()); 

	            // add the characters
	            builder.append(Numbers 
	                        .charAt(myindex)); 
	        } 

	        return builder.toString(); 
	    } 

	    public static void main(String[] args) 
	    { 
	       	int n = 9;
	       	int j = 9;
	       	String w = getRandomString(n);
	       	String q = getRandomString(j);
	      
	       	 int a = Integer.parseInt(w);
	       	 int b = Integer.parseInt(q);
	       	System.out.println("Bootcamp Fullstack Portales" + "\n"); 
	       	System.out.println("PABLO RAMIREZ - pablorame@gmail.com" + "\n"); 
	       	System.out.println("Mini Reto punto 2.a" + "\n");  
	       	System.out.println("Aleatorio 1:  "  +  a); 
	    	System.out.println("Aleatorio 2:  "  +  b); 
	    	System.out.println("Si Aleatorio 1 < Aleatorio 2: TIPO A ");
	    	System.out.println("Si Aleatorio 1 > Aleatorio 2: TIPO B " + "\n");
	    	
	    	if ( a < b ) {
	    		
	    		int i = 8; 
	    	System.out.println("STRING TIPO A: " + "54" +  getRandomString(i) + "\n");
	    	}    	else {
	    		int m = 8;
	        // output 
	        System.out.println("String TIPO B: " + "08" +  getRandomString(m) + "\n"); 
	       
	    }
	    	System.out.println("Mini Reto punto 2.b" + "\n");
	    	String apellido;
	       	Scanner s = new Scanner(System.in);
	       	System.out.println("Ingese un apellido Comience siempre con Mayúscula"+"\n");
	        apellido = s.next();
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
	       	
	        boolean existe = apellidos.contains(apellido);
	    	if (existe) {
	    		System.out.println(false + " El elemento SÍ existe en la lista"+"\n");
	    	} else {
	    		System.out.println(true + " El elemento NO existe en la lista"+"\n");
	    	}
	       	
	       	System.out.println("MUCHAS GRACIAS");
	       	
	    }
	    }
	    
	   
	    

