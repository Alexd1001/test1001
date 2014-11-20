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
public abstract class Cuentas {
    private int ctaNo;
    private int saldo;

    public int getCtaNo() {
        return ctaNo;
    }

    public void setCtaNo(int ctaNo) {
        this.ctaNo = ctaNo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    
    public abstract int intereses();
    
    
}
