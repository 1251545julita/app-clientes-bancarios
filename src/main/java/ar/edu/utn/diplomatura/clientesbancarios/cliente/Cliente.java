package ar.edu.utn.diplomatura.clientesbancarios.cliente;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * La Clase principal y Abstracta llamada Cliente, que engloba a todos los tipos de cliente.
 */
public abstract class Cliente {

    private final String id;
    private final String nombre;
    private final String domicilio;
    private final String telefono;
    private final String correoElectronico;
    private final Date fechaDeAlta;
    private final Boolean activo;
    private final String sucursal;

    // Constructor
    protected Cliente(String id, String nombre, String domicilio, String telefono, String correoElectronico, Date fechaDeAlta, Boolean activo, String sucursal) {

        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaDeAlta = fechaDeAlta;
        this.activo = activo;
        this.sucursal = sucursal;
    }

    /** ---------- Métodos abstractos ----------
     * Van sin cuerpo: terminan en ';' y no en '{ }'.
     * Cada subclase está obligada a implementarlos.
     */

    public abstract BigDecimal calcularCreditoPreAprobado();

    public abstract String adquirirServicio();

    public abstract String darBajaServicio();

    /** ---------- Métodos concretos ----------
     * Estos sí tienen código, y las hijas los heredan sin escribir nada.
     * Es justo lo que una interfaz no puede hacer, y el motivo principal para
     * elegir clase abstracta en vez de interfaz.
     */
    // Getters
    public String getId() {
        return this.id;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getDomicilio() {
        return this.domicilio;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public String getCorreoElectronico() { return this.correoElectronico; }
    public Date getFechaDeAlta() { return this.fechaDeAlta; }
    public Boolean getActivo() { return activo; }
    public String getSucursal() { return sucursal; }

    SimpleDateFormat formatoFechaNormalizada = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    // Métodos concretos de la Clase Padre
    public String presentarse() {
        return "\n  - Cliente ID " + this.id +
               "\n  - Nombre: " + this.nombre +
               "\n  - Domicilio: " + this.domicilio +
               "\n  - Telefono: " + this.telefono +
               "\n  - Correo Electrónico: " + this.correoElectronico +
               "\n  - Fecha de Alta: " + formatoFechaNormalizada.format(this.fechaDeAlta) +
               "\n  - Estado: " + (this.activo ? "ACTIVO" : "INACTIVO") +
               "\n  - Sucursal: " + this.sucursal;
    }

    public boolean esClienteAntiguo() {
        if (this.fechaDeAlta == null) return false;
        long unAnioEnMilisegundos = 365L * 24 * 60 * 60 * 1000;
        return (new Date().getTime() - this.fechaDeAlta.getTime()) >= unAnioEnMilisegundos;
    }
}
