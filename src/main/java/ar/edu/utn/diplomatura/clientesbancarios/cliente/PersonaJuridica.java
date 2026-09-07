package ar.edu.utn.diplomatura.clientesbancarios.cliente;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Segunda subclase del mismo padre abstracto denominado Cliente.
 */
public class PersonaJuridica extends Cliente {

    // Atributos específicos
    private final String razonSocial;
    private final String cuil;
    private final String rubro;
    private final String representanteLegal;
    private final String condicionIva; // Para la facturación de comisiones e impuestos bancarios (Responsable Inscripto, Exento, etc.).
    private final Date fechaConstitucion; // Para habilitar líneas de crédito corporativas según la antigüedad de la empresa.
    private final BigDecimal facturacionAnual; // Para categorizar a la empresa como PyME, Mediana o Corporativa.

    // Constructor
    public PersonaJuridica(String id, String nombre, String domicilio, String telefono, String correoElectronico, Date fechaDeAlta, Boolean activo, String sucursal, String razonSocial, String cuil, String rubro, String representanteLegal, String condicionIva, Date fechaConstitucion, BigDecimal facturacionAnual) {
        super(id, nombre, domicilio, telefono, correoElectronico, fechaDeAlta, activo, sucursal);
        this.razonSocial = razonSocial;
        this.cuil = cuil;
        this.rubro = rubro;
        this.representanteLegal = representanteLegal;
        this.condicionIva = condicionIva;
        this.fechaConstitucion = fechaConstitucion;
        this.facturacionAnual = facturacionAnual;
    }

    // Getters
    public String getRazonSocial() { return razonSocial;}

    public String getCuil() { return cuil; }

    public String getRubro() { return rubro; }

    public String getRepresentanteLegal() { return representanteLegal; }

    public String getCondicionIva() { return condicionIva; }

    public Date getFechaConstitucion() { return fechaConstitucion; }

    public BigDecimal getFacturacionAnual() { return facturacionAnual; }

    // Método de la subclase
    public String categorizarEmpresa() {
        if (this.facturacionAnual == null) return "DESCONOCIDO";

        BigDecimal limitePyme = new BigDecimal("50000000.00");
        BigDecimal limiteMediana = new BigDecimal("200000000.00");

        if (this.facturacionAnual.compareTo(limitePyme) <= 0) {
            return "PyME";
        } else if (this.facturacionAnual.compareTo(limiteMediana) <= 0) {
            return "Mediana empresa";
        } else {
            return "Corporación";
        }
    }

    // Implementación obligatoria de métodos (Sobreescritura)
    @Override
    public BigDecimal calcularCreditoPreAprobado() {
        if (!getActivo() || this.facturacionAnual == null) {
            return BigDecimal.ZERO;
        }
        return this.facturacionAnual.multiply(new BigDecimal("0.10"));
    }

    @Override
    public String adquirirServicio() {
        return "\n - Presentarse en la sucursal." +
               "\n - Solicitar por teléfono por linea Empresas." +
               "\n - Solicitar desde banca online.";
    }

    @Override
    public String darBajaServicio() {
        return "\n - Presentarse en la sucursal." +
               "\n - Comunicarse por teléfono por linea Empresas.";
    }
}
