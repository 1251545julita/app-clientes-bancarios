package ar.edu.utn.diplomatura.clientesbancarios;

import ar.edu.utn.diplomatura.clientesbancarios.cliente.Cliente;
import ar.edu.utn.diplomatura.clientesbancarios.cliente.PersonaFisica;
import ar.edu.utn.diplomatura.clientesbancarios.cliente.PersonaJuridica;
import ar.edu.utn.diplomatura.clientesbancarios.cliente.Preferencial;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        demoClientesBancarios();
    }

    // ------------------------------------------------------------------
    // Clientes bancarios
    // ------------------------------------------------------------------
    private static void demoClientesBancarios() {
        titulo("Herencia y Modelado de Datos - Clientes bancarios");

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        LocalDate fechaAhora = LocalDate.now();

        Cliente cliente1 = new PersonaFisica(
                "Juan",
                "30123456",
                "Calle 123, CABA",
                "11-5555-4444",
                "juan.perez@email.com",
                fechaAhora,
                true,
                "Sucursal 045 - Belgrano",
                "Perez",
                LocalDate.of(1990, 6, 15),
                "Ingeniero en Sistemas",
                new BigDecimal("750000.5024"),
                "Soltero",
                "Argentina",
                "Relación de Dependencia"
        );

        Cliente cliente2 = new PersonaJuridica(
                "DG Solutions SA",
                "30-12345678-9",
                "Av. Corrientes 456, CABA",
                "11-4444-5555",
                "contacto@dgsolutions.com",
                fechaAhora,
                true,
                "Sucursal 110 - Centro Corporativo",
                "DG Solutions Sociedad Anónima",
                "Desarrollo de Software",
                "Marta González",
                "Responsable Inscripto",
                LocalDate.of(2017, 4, 20),
                new BigDecimal("45000000.008")
        );

        List<String> beneficios = Arrays.asList("Acceso a VIP en aeropuertos", "Atención 24/7", "Cero comisiones de mantenimiento");
        Cliente cliente3 = new Preferencial(
                "Laura",
                "dni",
                "36852753",
                "Av. del Libertador 474, CABA",
                "11-3333-2222",
                "laura.vip@email.com",
                fechaAhora,
                true,
                "Sucursal 001 - Casa Central VIP",
                new BigDecimal("5000000.0064"),
                98765L,
                beneficios,
                LocalDate.of(1997, 1, 22),
                true
        );

        Cliente[] clientesDelBanco = { cliente1, cliente2, cliente3 };

        System.out.println("=== Sistema de Gestión de Clientes bancarios ===\n");

        for (Cliente cliente : clientesDelBanco) {
            // Imprime qué tipo de clase hija es (PersonaFisica, PersonaJuridica o Preferencial)
            System.out.println("--- TIPO DE CLIENTE: " + cliente.getClass().getSimpleName() + " ---");

            // presentarse() está implementado UNA sola vez, en el padre abstracto,
            // y usa los métodos que cada hija define a su manera.
            System.out.println("DATOS BASE: " + cliente.presentarse());

            System.out.println(" ATRIBUTOS ESPECÍFICOS:");

            if (cliente instanceof PersonaFisica personaFisica) {

                System.out.println("  - Apellido: " + personaFisica.getApellido());
                System.out.println("  - Fecha de nacimiento: " + personaFisica.getFechaDeNacimiento().format(formatoFecha));
                System.out.println("  - Profesión: " + personaFisica.getProfesion());
                System.out.println("  - Ingresos declarados: $" + fmt(personaFisica.getIngresosDeclarados()));
                System.out.println("  - Estado Civil: " + personaFisica.getEstadoCivil());
                System.out.println("  - Nacionalidad: " + personaFisica.getNacionalidad());
                System.out.println("  - Situación laboral: " + personaFisica.getSituacionLaboral());

            } else if (cliente instanceof PersonaJuridica personaJuridica) {

                System.out.println("  - Razón Social: " + personaJuridica.getRazonSocial());
                System.out.println("  - Rubro: " + personaJuridica.getRubro());
                System.out.println("  - Representante legal: " + personaJuridica.getRepresentanteLegal());
                System.out.println("  - Fecha de Constitución: " + personaJuridica.getFechaConstitucion().format(formatoFecha));
                System.out.println("  - Facturación Anual: $" + fmt(personaJuridica.getFacturacionAnual()));
                System.out.println("  - Categoría de la Empresa: " + personaJuridica.categorizarEmpresa());

            } else if (cliente instanceof Preferencial preferencial) {

                System.out.println("  - Límite de crédito especial: $" + fmt(preferencial.getLimiteDeCreditoEspecial()));
                System.out.println("  - ID de Ejecutivo de Cuenta: " + preferencial.getEjecutivoDeCuentaAsignado());
                System.out.println("  - Beneficios adicionales: "  + String.join(" | ", preferencial.getBeneficiosAdicionales()));
                System.out.println("  - Fecha de Ingreso VIP: " + preferencial.getFechaIngresoSegmento().format(formatoFecha));
                System.out.println("  - Comisiones bonificadas: " + (preferencial.isTieneComisionesBonificadas() ? "ACTIVO" : "INACTIVO"));

                System.out.println("=== PRUEBAS DE BENEFICIOS VIP ===");

                // Caso 1: Beneficio existente
                String busqueda1 = "Acceso a VIP en aeropuertos";
                System.out.println("  Tiene '" + busqueda1 + "' ?: " + (preferencial.tieneBeneficio(busqueda1)? "ACTIVO" : "INACTIVO"));
                // Resultado esperado: ACTIVO

                // Caso 2: Beneficio existente con deferencia de escritura
                String busqueda2 = "atención 24/7";
                System.out.println("  Tiene '" + busqueda2 + "' ?: " + (preferencial.tieneBeneficio(busqueda2)? "ACTIVO" : "INACTIVO"));
                // Resultado esperado: ACTIVO

                // Caso 3: Beneficio que NO existe
                String busqueda3 = "Tasa 0% en Préstamos";
                System.out.println("  Tiene '" + busqueda3 + "' ?: " + (preferencial.tieneBeneficio(busqueda3)? "ACTIVO" : "INACTIVO"));
                // Resultado esperado: INACTIVO
            }

            // Imprime el resultado de los métodos abstractos que cada clase hija implementó de forma distinta
            System.out.println("CREDITO PREAPROBADO: $" + fmt(cliente.calcularCreditoPreAprobado()));
            System.out.println("ALTA DE SERVICIO: " + cliente.adquirirServicio());
            System.out.println("BAJA DE SERVICIO: " + cliente.darBajaServicio());
            System.out.println("------------------------------------------------------\n");
        }
    }

    // ------------------------------------------------------------------
    // Métodos adicionales
    // ------------------------------------------------------------------

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  " + texto);
        System.out.println("=".repeat(70));
    }

    /** Locale.US para que el separador decimal no dependa de la máquina. */
    private static String fmt(BigDecimal valor) {
        if (valor == null) {
            return "0.00";
        }
        return String.format(Locale.US, "%.2f", valor);
    }
}
