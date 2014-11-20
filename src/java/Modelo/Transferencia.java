/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mao
 */
public class Transferencia implements Transaccion {

    private Date fecha;
    private Cuentas objeto;
    private int saldoOrigen;
    private int saldoDestino;
    private int montoTransferencia;
    
    public Transferencia(Cuentas objeto, int saldoOrigen,int saldoDestino,int montoTransferencia)
    {   
        this.objeto=objeto;
        this.saldoOrigen=saldoOrigen;
        this.saldoDestino=saldoDestino;
        this.montoTransferencia=montoTransferencia;
    }
    public Date getFecha() {
        
        Date now = new Date();
        return fecha=now;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    public ArrayList<Object> tipodetransaccion() {
        
      ArrayList<Object> lista = new ArrayList<>();    
      lista.add(0,saldoOrigen-objeto.intereses());
      lista.add(1,saldoDestino+montoTransferencia);
      lista.add(getFecha());
      return lista;
    }
    
    
    
    
}
