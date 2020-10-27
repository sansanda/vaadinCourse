package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;


public class test1{

public static void main(String [] args){
    String codigo = "260202"; 
    boolean codigoEncontrado = false;
    
    boolean x = false;
    int j = 0;

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try{ 
	    archivo =  new File ("E:\\repos\\vaadinCourse\\test\\src\\test\\test.txt");
	    fr = new FileReader (archivo);
	    br = new BufferedReader (fr);  
	
	    String Linea;
	    JOptionPane.showMessageDialog(null,"Conectado");
	
	    while((Linea = br.readLine()) !=null){
	        if(Linea.indexOf(codigo) != -1){   
	            String[] campos = Linea.split(" ");
	            while(j < campos.length){  
	               System.out.println(" "+campos[j]); 
	                j++;
	            }
	            codigoEncontrado = true;
	            }
	    }   if (!codigoEncontrado)
	    {
	    	   System.out.println("Código no encontrado en el archivo"); 
	    }
} catch(Exception e){
	System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null,"Error de conexion.");
    }finally{
        try{
            if(null != fr){
                fr.close();
            }
        }catch(Exception e2){
         }
        }
       }
      }