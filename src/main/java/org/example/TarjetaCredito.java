
package org.example;

public class TarjetaCredito {
    private int numTarjeta;
    private String titular;
    private int cvv;
    private int idUsuario;

    public TarjetaCredito(int numTarjeta, String titular, int cvv, int idUsuario) {
        this.numTarjeta = numTarjeta;
        this.titular = titular;
        this.cvv = cvv;
        this.idUsuario = idUsuario;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}

