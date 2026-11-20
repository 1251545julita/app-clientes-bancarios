package ar.edu.utn.diplomatura.clientesbancarios.cliente;

import ar.edu.utn.diplomatura.clientesbancarios.Constants;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Segunda subclase del mismo padre abstracto denominado Cliente.
 */
public class PersonaJuridica extends Cliente {

    // Atributos específicos
    private String razonSocial;
    private String cuit;
    private String rubro;
    private String representanteLegal;
    private String condicionIva; // Para la facturación de comisiones e impuestos bancarios (Responsable Inscripto, Exento, etc.).
    private LocalDate fechaConstitucion; // Para habilitar líneas de crédito corporativas según la antigüedad de la empresa.
    private BigDecimal facturacionAnual; // Para categorizar a la empresa como PyME, Mediana o Corporativa.

    // Constructor
    public PersonaJuridica(String nombre, String numeroCuit, String domicilio, String telefono, String correoElectronico, LocalDate fechaDeAlta, Boolean activo, String sucursal, String razonSocial, String rubro, String representanteLegal, String condicionIva, LocalDate fechaConstitucion, BigDecimal facturacionAnual) {
        super(nombre, Constants.TIPO_DOCUMENTO_CUIL, numeroCuit, domicilio, telefono, correoElectronico, fechaDeAlta, activo, sucursal);
        this.razonSocial = razonSocial;
        this.cuit = numeroCuit;
        this.rubro = rubro;
        this.representanteLegal = representanteLegal;
        this.condicionIva = condicionIva;
        this.fechaConstitucion = fechaConstitucion;
        this.facturacionAnual = facturacionAnual;
    }

    // Getters
    public String getRazonSocial() { return razonSocial;}
    public String getCuit() { return cuit; }
    public String getRubro() { return rubro; }
    public String getRepresentanteLegal() { return representanteLegal; }
    public String getCondicionIva() { return condicionIva; }
    public LocalDate getFechaConstitucion() { return fechaConstitucion; }
    public BigDecimal getFacturacionAnual() { return facturacionAnual; }

    // Setters
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public void setCuit(String cuit) { this.cuit = cuit; }
    public void setRubro(String rubro) { this.rubro = rubro; }
    public void setRepresentanteLegal(String representanteLegal) { this.representanteLegal = representanteLegal; }
    public void setCondicionIva(String condicionIva) { this.condicionIva = condicionIva; }
    public void setFechaConstitucion(LocalDate fechaConstitucion) { this.fechaConstitucion = fechaConstitucion; }
    public void setFacturacionAnual(BigDecimal facturacionAnual) { this.facturacionAnual = facturacionAnual; }

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
