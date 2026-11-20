package ar.edu.utn.diplomatura.clientesbancarios.cliente;


import ar.edu.utn.diplomatura.clientesbancarios.Constants;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Clase concreta que hereda de la clase abstracta Cliente.
 */
public class PersonaFisica extends Cliente {

    // Atributos específicos
    private String apellido;
    private String dni; // Hay documentos de identidad de personas extranjeras que son alfanumericas.
    private LocalDate fechaDeNacimiento;
    private String profesion;
    private BigDecimal ingresosDeclarados;
    private String estadoCivil; // Para análisis de riesgo al solicitar préstamos hipotecarios (Soltero, Casado, Divorciado).
    private String nacionalidad;
    private String situacionLaboral; // Si es empleado en relación de dependencia, monotributista, o jubilado.

    // Constructor
    public PersonaFisica(String nombre, String numeroDni, String domicilio, String telefono, String correoElectronico, LocalDate fechaDeAlta, Boolean activo, String sucursal, String apellido, LocalDate fechaDeNacimiento, String profesion, BigDecimal ingresosDeclarados, String estadoCivil, String nacionalidad, String situacionLaboral) {
        super(nombre, Constants.TIPO_DOCUMENTO_DNI, numeroDni, domicilio, telefono, correoElectronico, fechaDeAlta, activo, sucursal);
        this.apellido = apellido;
        this.dni = numeroDni;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.profesion = profesion;
        this.ingresosDeclarados = ingresosDeclarados;
        this.estadoCivil = estadoCivil;
        this.nacionalidad = nacionalidad;
        this.situacionLaboral = situacionLaboral;
    }

    // Getters
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public LocalDate getFechaDeNacimiento() { return fechaDeNacimiento; }
    public String getProfesion() { return profesion; }
    public BigDecimal getIngresosDeclarados() { return ingresosDeclarados; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getNacionalidad() { return nacionalidad; }
    public String getSituacionLaboral() { return situacionLaboral; }

    // Setters
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDni(String dni) { this.dni = dni; }
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }
    public void setProfesion(String profesion) { this.profesion = profesion; }
    public void setIngresosDeclarados(BigDecimal ingresosDeclarados) { this.ingresosDeclarados = ingresosDeclarados; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public void setSituacionLaboral(String situacionLaboral) { this.situacionLaboral = situacionLaboral; }

    // Implementación obligatoria de métodos (Sobreescritura)
    @Override
    public BigDecimal calcularCreditoPreAprobado() {
        if (!getActivo() || this.ingresosDeclarados == null) {
            return BigDecimal.ZERO;
        }
        return this.ingresosDeclarados.multiply(new BigDecimal("3")); // Si es monotributista o relación de dependencia se otorgan 3 sueldos
    }

    @Override
    public String adquirirServicio() {
        return "\n - Presentarse en la sucursal asignada." +
               "\n - Solicitar por telefono por línea General.";
    }

    @Override
    public String darBajaServicio() {
        return "\n - Presentarse en la sucursal asignada.";
    }
}
