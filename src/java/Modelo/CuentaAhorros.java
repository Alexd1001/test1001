/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Mao
 */
public class CuentaAhorros extends Cuentas{

   private static final double interes= 0.012;
    
  
    public int intereses() {
        return (int) (getSaldo()-getSaldo()*interes);
    }
 
}
