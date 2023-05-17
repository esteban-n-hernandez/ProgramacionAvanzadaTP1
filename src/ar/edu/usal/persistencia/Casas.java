package ar.edu.usal.persistencia;

import ar.edu.usal.persistencia.enums.OrientacionENUM;

import java.io.Serializable;

public class Casas extends Construcciones implements Serializable {

    private static final long serialVersionUID = 3276682438954876504L;

    int ambientes;

    //Norte, Sur, Este u Oeste
    OrientacionENUM orientacion;


    public Casas(double superficiePorM2, String direccion, double precioPorM2, int ambientes, OrientacionENUM orientacion) {
        super(superficiePorM2, direccion, precioPorM2);
        this.ambientes = ambientes;
        this.orientacion = orientacion;
    }

    @Override
    public void mostrarCaracteristicas() {
        super.mostrarCaracteristicas();
        System.out.printf("Ambientes: %s%nOrientación: %s%n", ambientes, orientacion);
    }

    public String toString() {
        return "Superficie: " + superficiePorM2 +
                ", Precio: " + getPrecioPorM2() +
                ", Dirección: " + getDireccion() +
                ", Ambientes: " + ambientes +
                ", Orientacion: " + orientacion;
    }


    public void updateAmbientes(int nuevosAmbientes) {
        this.ambientes = nuevosAmbientes;
    }

    public void updateOrientacion(OrientacionENUM nuevaOrientacion) {
        this.orientacion = nuevaOrientacion;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public OrientacionENUM getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionENUM orientacion) {
        this.orientacion = orientacion;
    }
}

