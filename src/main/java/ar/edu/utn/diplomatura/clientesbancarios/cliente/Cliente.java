package ar.edu.utn.diplomatura.clientesbancarios.cliente;

import ar.edu.utn.diplomatura.clientesbancarios.Constants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * La Clase principal y Abstracta llamada Cliente, que engloba a todos los tipos de cliente.
 */
public abstract class Cliente {

    private final String id;
    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;
    private String domicilio;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaDeAlta;
    private Boolean activo;
    private String sucursal;

    // Constructor
    protected Cliente(String nombre, String tipoDocumento, String numeroDocumento, String domicilio, String telefono, String correoElectronico, LocalDate fechaDeAlta, Boolean activo, String sucursal) {

        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
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
    public String getTipoDocumento() { return tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public String getDomicilio() {
        return this.domicilio;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public String getCorreoElectronico() { return this.correoElectronico; }
    public LocalDate getFechaDeAlta() { return this.fechaDeAlta; }
    public Boolean getActivo() { return activo; }
    public String getSucursal() { return sucursal; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public void setNumeroDocumento(String nunmeroDocumento) { this.numeroDocumento = nunmeroDocumento; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public void setFechaDeAlta(LocalDate fechaDeAlta) { this.fechaDeAlta = fechaDeAlta; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }

    private DateTimeFormatter formatoFechaNormalizada = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    // Métodos concretos de la Clase Padre
    public String presentarse() {
        return "\n  - Cliente ID " + this.id +
               "\n  - Nombre: " + this.nombre +
               "\n  - Tipo Documento: " + this.tipoDocumento +
               "\n  - Número Documento: " + this.numeroDocumento +
               "\n  - Domicilio: " + this.domicilio +
               "\n  - Telefono: " + this.telefono +
               "\n  - Correo Electrónico: " + this.correoElectronico +
               "\n  - Fecha de Alta: " + this.fechaDeAlta.format(formatoFechaNormalizada) +
               "\n  - Estado: " + (this.activo ? "ACTIVO" : "INACTIVO") +
               "\n  - Sucursal: " + this.sucursal;
    }

    public boolean esClienteAntiguo() {
        if (this.fechaDeAlta == null) return false;

        LocalDate haceUnAnio = LocalDate.now().minusYears(1);
        return !this.fechaDeAlta.isAfter(haceUnAnio);
    }
}
