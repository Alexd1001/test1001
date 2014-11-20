/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author adavila
 */
public class Clientes {
    
    private int documentoId;
    private String nombre;
    private String apellido;
    private int telefono;
    private String direccion;
    private Cuentas cuenta;

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }
	
    public int getTelefono() {
        
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
	
	public String getDireccion() {
        return direccion;
    }
    
    
        
        
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
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
    
    
   /* public static void main(String [] poei)
    {
        
        
      Clientes p = new Clientes();    
      
      //p.setCuenta(new Cuenta);
      //p.cuenta.setSaldo(25000);
      
      
      
      //p.getCuenta() y = new CuentaCte();
      
     p.setCuenta(new CuentaAhorros());
     p.getCuenta().setSaldo(87123);
      
      
      System.out.println(p.getCuenta().getSaldo());
        
    }*/
}
