package ar.edu.utn.diplomatura.clientesbancarios.cliente;


import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase concreta que hereda de la clase abstracta Cliente.
 */
public class PersonaFisica extends Cliente {

    // Atributos específicos
    private final String apellido;
    private final String dni; // Hay documentos de identidad de personas extranjeras que son alfanumericas.
    private final Date fechaDeNacimiento;
    private final String profesion;
    private final BigDecimal ingresosDeclarados;
    private final String estadoCivil; // Para análisis de riesgo al solicitar préstamos hipotecarios (Soltero, Casado, Divorciado).
    private final String nacionalidad;
    private final String situacionLaboral; // Si es empleado en relación de dependencia, monotributista, o jubilado.

    // Constructor
    public PersonaFisica(String id, String nombre, String domicilio, String telefono, String correoElectronico, Date fechaDeAlta, Boolean activo, String sucursal, String apellido, String dni, Date fechaDeNacimiento, String profesion, BigDecimal ingresosDeclarados, String estadoCivil, String nacionalidad, String situacionLaboral) {
        super(id, nombre, domicilio, telefono, correoElectronico, fechaDeAlta, activo, sucursal);
        this.apellido = apellido;
        this.dni = dni;
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

    public Date getFechaDeNacimiento() { return fechaDeNacimiento; }

    public String getProfesion() { return profesion; }

    public BigDecimal getIngresosDeclarados() { return ingresosDeclarados; }

    public String getEstadoCivil() { return estadoCivil; }

    public String getNacionalidad() { return nacionalidad; }

    public String getSituacionLaboral() { return situacionLaboral; }

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
