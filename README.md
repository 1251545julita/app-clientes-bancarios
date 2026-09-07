# Application — Clientes Bancarios — Herencia y  Modelado de Clases

** Diplomatura en Desarrollo de Software FinTech: IA y Microservicios — UTN / Codeki**
** Student: Julita, Jesica R. **

El objetivo de esta aplicación es aplicar conceptos de Programación Orientada a Objetos (POO),
particularmente herencia y modelado de clases, a partir de un caso simplificado inspirado en
el funcionamiento de un sistema bancario.

Una aplicación simplificada que permita:
1. Comprender cómo se modelan entidades del mundo real dentro de un sistema informático.
2. Identificar atributos comunes y atributos específicos de distintos tipos de clientes.
3. Implementar una jerarquía de clases utilizando el concepto de herencia en Java.
4. Desarrollar código claro y correctamente estructurado.

---

## 1. Qué se necesita antes de empezar

- **JDK 21** instalado.
- **IntelliJ IDEA** (Community alcanza).

---

## 2. Cómo abrirlo en IntelliJ IDEA

1. Descomprimir el `.zip` en una carpeta **sin espacios ni acentos en la ruta**
   (por ejemplo `C:\dev\app-clientes-bancarios`).
2. **File > Open** → elegir la carpeta `app-clientes-bancarios`, la que contiene el `pom.xml`.
   No abrir el `pom.xml` directamente.
3. Cuando pregunte, aceptar **Trust Project**.
4. Verificar el JDK en `File > Project Structure > Project > SDK`: tiene que apuntar a un **JDK 21**. Si no aparece ninguno, elegir `Add SDK > Download JDK` y descargar una distribución 21.
5. Si no ve el panel de Maven: `View > Tool Windows > Maven`, y tocar el botón de recargar.

**Si el `pom.xml` aparece en gris o no hay carpeta `External Libraries`:** clic derecho
sobre `pom.xml` → **Add as Maven Project**.

---

## 3. Cómo ejecutarlo

### Desde IntelliJ

Abrí `src/main/java/ar/edu/utn/diplomatura/clientesbancarios/Main.java` y hacé clic en
la flecha verde ▶ que está al lado de `public static void main`.
Se ejecutan las nueve demos seguidas y la salida aparece en la consola de abajo.

### Desde la terminal, con Maven

```bash
mvn compile
java -cp target/classes ar.edu.utn.diplomatura.clientesbancarios.Main
```

### Desde la terminal, solo con el JDK

```bash
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
java -cp out ar.edu.utn.diplomatura.clientesbancarios.Main
```

En PowerShell (Windows), esas dos líneas son:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src\main\java | ForEach-Object { $_.FullName })
java -cp out ar.edu.utn.diplomatura.clientesbancarios.Main
```

---

## 4. Cómo probar los endpoints

El `Main` contiene un método para clientes bancarios, el mismo crea un ejemplo de cada tipo de cliente e
imprime por consola de la información de los clientes creados.

En la consola vas a ver, en este orden:

```
======================================================================
  Herencia y Modelado de Datos - Clientes bancarios
======================================================================
=== Sistema de Gestión de Clientes bancarios ===

--- TIPO DE CLIENTE: PersonaFisica ---
DATOS BASE: 
  -
  -
  ...
 ATRIBUTOS ESPECÍFICOS:
  -
  -
  ...
CREDITO PREAPROBADO: $
  -
  -
  ...
ALTA DE SERVICIO: 
  -
  -
  ...
BAJA DE SERVICIO: 
  -
  -
  ...
------------------------------------------------------
...
```

Se pueden observar los 3 clientes (Un cliente tipo Persona Física, otro tipo Persona Jurídica, otro tipo Preferencial)
con todos sus datos, el cálculo del crédito pre aprobado y el modo de adquirir o darse de baja de un servicio. 
También, la ejecución de los métodos particulares de cada clase PersonaJuridica y Preferencial. Está última, abre 
una serie de pruebas para verificar si tiene un beneficio VIP particular en los beneficios correspondientes al
cliente Preferencial en particular.

---

## 5. Estructura del proyecto

```
app-clientes-bancarios/
├── pom.xml
├── README.md
└── src/main/java/ar/edu/utn/diplomatura/clientesBancarios/
    ├── Main.java                    # ejecuta la carga y la impresión de los datos de los clientes
    └── cliente/
        └── Cliente.java          # clase abstracta, atributos, constructores, getters/setters
        ├── PersonaFisica.java    # subclase, atributos, constructores, getters/setters, métodos especificos
        ├── PersonaJuridica.java  # subclase, atributos, constructores, getters/setters, métodos especificos
        └── Preferencial.java     # subclase, atributos, constructores, getters/setters, métodos especificos

```

---

## 7. Problemas frecuentes

**Los acentos se ven como `?` o `�` en la consola.** Pasa en `cmd` y en PowerShell, porque la consola de Windows usa un codepage viejo en lugar de UTF-8. Es un problema de la terminal, no del código. Corré `chcp 65001` antes de ejecutar, o usá directamente la consola de IntelliJ, que muestra bien los acentos.

**`error: invalid source release: 21`.** El JDK configurado es anterior a 21. Revisá el punto 2.

**El botón ▶ no aparece al lado del `main`.** IntelliJ todavía no terminó de indexar, o no reconoció la carpeta como proyecto Maven. Esperá a que termine la barra de progreso de abajo, o recargá el proyecto desde el panel de Maven.
