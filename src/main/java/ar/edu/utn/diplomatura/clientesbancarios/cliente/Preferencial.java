package ar.edu.utn.diplomatura.clientesbancarios.cliente;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Tercera clase que hereda de una clase abstracta denominada Cliente.
 */
public class Preferencial extends Cliente {

    // Atributos específicos
    private final BigDecimal limiteDeCreditoEspecial;
    private final Long ejecutivoDeCuentaAsignado;
    private final List<String> beneficiosAdicionales;
    private final Date fechaIngresoSegmento; // Para calcular la antigüedad en el segmento VIP y otorgar premios por fidelidad.
    private final boolean tieneComisionesBonificadas; // Si el cliente tiene exención total o un porcentaje de descuento en costos de mantenimiento.

    // Constructor
    public Preferencial(String id, String nombre, String domicilio, String telefono, String correoElectronico, Date fechaDeAlta, Boolean activo, String sucursal, BigDecimal limiteDeCreditoEspecial, Long ejecutivoDeCuentaAsignado, List<String> beneficiosAdicionales, Date fechaIngresoSegmento, boolean tieneComisionesBonificadas) {
        super(id, nombre, domicilio, telefono, correoElectronico, fechaDeAlta, activo, sucursal);
        this.limiteDeCreditoEspecial = limiteDeCreditoEspecial;
        this.ejecutivoDeCuentaAsignado = ejecutivoDeCuentaAsignado;
        this.beneficiosAdicionales = beneficiosAdicionales;
        this.fechaIngresoSegmento = fechaIngresoSegmento;
        this.tieneComisionesBonificadas = tieneComisionesBonificadas;
    }

    // Getters
    public BigDecimal getLimiteDeCreditoEspecial() { return limiteDeCreditoEspecial; }

    public Long getEjecutivoDeCuentaAsignado() { return ejecutivoDeCuentaAsignado; }

    public List<String> getBeneficiosAdicionales() { return beneficiosAdicionales; }

    public Date getFechaIngresoSegmento() { return fechaIngresoSegmento; }

    public boolean isTieneComisionesBonificadas() { return tieneComisionesBonificadas; }

    // Método particular de la subclase
    // Para Verificar si posee un beneficio en particular
    public boolean tieneBeneficio(String beneficioBuscado) {
        if (this.beneficiosAdicionales == null) return false;
        return this.beneficiosAdicionales.stream()
                .anyMatch(b -> b.equalsIgnoreCase(beneficioBuscado));
    }

    // Implementación obligatoria de métodos (Sobreescritura)
    @Override
    public BigDecimal calcularCreditoPreAprobado() {
        if (!getActivo() || this.limiteDeCreditoEspecial == null) {
            return BigDecimal.ZERO;
        }
        return this.limiteDeCreditoEspecial;
    }

    @Override
    public String adquirirServicio() {
        return "\n - Presentarse en la sucursal." +
               "\n - Solicitar por teléfono por linea Preferencial." +
               "\n - Solicitar desde banca online.";
    }

    @Override
    public String darBajaServicio() {
        return "\n - Presentarse en la sucursal." +
               "\n - Comunicarse por teléfono por linea Preferencial." +
               "\n - Solicitar desde banca online.";
    }
}
