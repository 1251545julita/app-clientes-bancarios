package ar.edu.utn.diplomatura.clientesbancarios.cliente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Tercera clase que hereda de una clase abstracta denominada Cliente.
 */
public class Preferencial extends Cliente {

    // Atributos específicos
    private BigDecimal limiteDeCreditoEspecial;
    private Long ejecutivoDeCuentaAsignado;
    private List<String> beneficiosAdicionales;
    private LocalDate fechaIngresoSegmento; // Para calcular la antigüedad en el segmento VIP y otorgar premios por fidelidad.
    private boolean tieneComisionesBonificadas; // Si el cliente tiene exención total o un porcentaje de descuento en costos de mantenimiento.

    // Constructor
    public Preferencial(String nombre, String tipoDocumento, String numeroDocumento, String domicilio, String telefono, String correoElectronico, LocalDate fechaDeAlta, Boolean activo, String sucursal, BigDecimal limiteDeCreditoEspecial, Long ejecutivoDeCuentaAsignado, List<String> beneficiosAdicionales, LocalDate fechaIngresoSegmento, boolean tieneComisionesBonificadas) {
        super(nombre, tipoDocumento, numeroDocumento, domicilio, telefono, correoElectronico, fechaDeAlta, activo, sucursal);
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
    public LocalDate getFechaIngresoSegmento() { return fechaIngresoSegmento; }
    public boolean isTieneComisionesBonificadas() { return tieneComisionesBonificadas; }

    // Setters
    public void setLimiteDeCreditoEspecial(BigDecimal limiteDeCreditoEspecial) { this.limiteDeCreditoEspecial = limiteDeCreditoEspecial; }
    public void setEjecutivoDeCuentaAsignado(Long ejecutivoDeCuentaAsignado) { this.ejecutivoDeCuentaAsignado = ejecutivoDeCuentaAsignado; }
    public void setBeneficiosAdicionales(List<String> beneficiosAdicionales) { this.beneficiosAdicionales = beneficiosAdicionales; }
    public void setFechaIngresoSegmento(LocalDate fechaIngresoSegmento) { this.fechaIngresoSegmento = fechaIngresoSegmento; }
    public void setTieneComisionesBonificadas(boolean tieneComisionesBonificadas) { this.tieneComisionesBonificadas = tieneComisionesBonificadas; }

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
