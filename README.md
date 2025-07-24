# Sistema de Reservas Hoteleras

Este proyecto implementa un sistema de reservas para hoteles, dividido por capas, que permite la gestión de habitaciones, clientes, trabajadores, productos, pagos y reservas. Desarrollado en Java, utilizando la arquitectura MVC con Swing como interfaz gráfica y MySQL como base de datos.

Proyecto académico en Java SE
Arquitectura en capas: Presentación, Lógica y Datos
Interfaz gráfica con Java Swing + GUI Builder (NetBeans)
Conexión con MySQL mediante JDBC

> **Estado:** En desarrollo  
> **Stack principal:** Java (Swing/JavaFX si aplica), MySQL, Ant (NetBeans)  
> **Autor:** @NandOwO @HunterMk12 @Lisbeth02pink

---

## Características

- Gestión de **habitaciones** (alta, baja, edición, disponibilidad).
- Gestión de **clientes**.
- **Reservas**: creación, actualización, cancelación.
- (Opcional) **Usuarios y roles** para control de acceso.
- (Opcional) **Reportes** básicos (ocupación, reservas activas, etc.).
- Conexión a **MySQL** usando **MySQL Connector/J**.
- Proyecto **NetBeans + Ant** con archivo `build.xml`.

---

## Estructura del proyecto
```bash
Sistema-Reservas-Hoteleras/
├── nbproject/ # Metadatos de NetBeans
├── lib/ #librerias Utilizadas en el desarrollo del proyecto
├── src/ # Código fuente (paquetes del dominio, DAO, UI, etc.)
├── test/mysql-connector-j-8.3.0/ # Driver MySQL incluido para pruebas/ejecución local
├── build.xml # Script de compilación con Ant
├── manifest.mf
└── .gitignore
```
## Requisitos

- **Java**: 8
- **MySQL**: 8.x
- **Ant**: incluido con NetBeans.
- **MySQL Connector/J**: 9.1.0
- **JCalendar : 1.4

---

## Configuración de la Base de Datos

1. Crea la base de datos:

   ```sql
   CREATE DATABASE IF NOT EXISTS sistemareservahotel;
   use sistemareservahotel;
   
2. Crea las tablas necesarias:

   ```sql
   
	-- Estructura de tabla para `persona`
	--
	CREATE TABLE persona (
	  idpersona INT NOT NULL AUTO_INCREMENT,
	  nombre VARCHAR(20),
	  apaterno VARCHAR(20),
	  amaterno VARCHAR(20),
	  tipo_documento VARCHAR(15),
	  num_documento VARCHAR(8),
	  direccion VARCHAR(100),
	  telefono VARCHAR(8),
	  email VARCHAR(25),
	  PRIMARY KEY (idpersona)
	);

	--
	-- Estructura de tabla para `trabajador`
	--
	CREATE TABLE trabajador (
	  idpersona INT NOT NULL,
	  sueldo DECIMAL(7,2),
	  acceso VARCHAR(15),
	  login VARCHAR(15),
	  password VARCHAR(20),
	  estado VARCHAR(20),
	  PRIMARY KEY (idpersona),
	  FOREIGN KEY (idpersona) REFERENCES persona (idpersona)
	);
   
	--
	-- Estructura de tabla para `cliente`
	--
	CREATE TABLE cliente (
	  idpersona INT NOT NULL,
	  codigo_cliente VARCHAR(10),
	  PRIMARY KEY (idpersona),
	  FOREIGN KEY (idpersona) REFERENCES persona (idpersona)
	);

	--
	-- Estructura de tabla para `producto`
	--
	CREATE TABLE producto (
	  idproducto INT NOT NULL AUTO_INCREMENT,
	  nombre VARCHAR(45),
	  descripcion VARCHAR(255),
	  unidad_medida VARCHAR(20),
	  precio_venta DECIMAL(7,2),
	  PRIMARY KEY (idproducto)
	);

	--
	-- Estructura de tabla para `habitacion`
	--
	CREATE TABLE habitacion (
	  idhabitacion INT NOT NULL AUTO_INCREMENT,
	  numero VARCHAR(4),
	  piso VARCHAR(2),
	  descripcion VARCHAR(255),
	  caracteristicas VARCHAR(512),
	  precio_diario DECIMAL(7,2),
	  estado VARCHAR(15),
	  tipo_habitacion VARCHAR(20),
	  PRIMARY KEY (idhabitacion)
	);

	--
	-- Estructura de tabla para `reserva`
	--
	CREATE TABLE reserva (
	  idreserva INT NOT NULL AUTO_INCREMENT,
	  idhabitacion INT NOT NULL,
	  idcliente INT NOT NULL,
	  idtrabajador INT NOT NULL,
	  tipo_reserva VARCHAR(20),
	  fecha_reserva DATE,
	  fecha_ingresa DATE,
	  fecha_salida DATE,
	  costo_alojamiento DECIMAL(7,2),
	  estado VARCHAR(15),
	  PRIMARY KEY (idreserva),
	  FOREIGN KEY (idhabitacion) REFERENCES habitacion (idhabitacion),
	  FOREIGN KEY (idcliente) REFERENCES cliente (idpersona),
	  FOREIGN KEY (idtrabajador) REFERENCES trabajador (idpersona)
	);

	--
	-- Estructura de tabla para `consumo`
	--
	CREATE TABLE consumo (
	  idconsumo INT NOT NULL AUTO_INCREMENT,
	  idreserva INT NOT NULL,
	  idproducto INT NOT NULL,
	  cantidad DECIMAL(7,2),
	  precio_venta DECIMAL(7,2),
	  estado VARCHAR(15),
	  PRIMARY KEY (idconsumo),
	  FOREIGN KEY (idreserva) REFERENCES reserva (idreserva),
	  FOREIGN KEY (idproducto) REFERENCES producto (idproducto)
	);

	--
	-- Estructura de tabla para `pago`
	--
	CREATE TABLE pago (
	  idpago INT NOT NULL AUTO_INCREMENT,
	  idreserva INT NOT NULL,
	  tipo_comprobante VARCHAR(20),
	  num_comprobante VARCHAR(20),
	  igv DECIMAL(4,2),
	  total_pago DECIMAL(7,2),
	  fecha_emision DATE,
	  fecha_pago DATE,
	  PRIMARY KEY (idpago),
	  FOREIGN KEY (idreserva) REFERENCES reserva (idreserva)
	);
   ```
   
3. Configura las credenciales de conexion en la clase conexion:
  ```java
	String usuario =  "root";
	String contraseña = "tucontraseña";
	String bd = "sistemareservahotel";
	String ip = "localhost";
	String puerto = "3306" //puerto por defecto;
	String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
  ```

## Ejecucion del sistema


### Opcion A - Abrir el Proyecto con Netbeans

1. Clona este repositorio o descarga el ZIP.
2. Abre el proyecto en NetBeans (`Archivo > Abrir Proyecto`).
3. Asegúrate que tienes configurado el JDK
4. Importar las [librerias](/lib/) necesarias (las encuentras en la carpeta lib)
5. Haz clic derecho en el proyecto > Ejecutar.

### Opcion B - Con Ant: 
```bash
cd SistemaDeReservaHotel
ant clean
ant compile
ant run  # si está configurado el target run en build.xml
```

## Capas del Sistema: 

| Capa | Descripcion |
|---|---|
| Datos | Contiene los VO (Value Object) de cada entidad (vcliente, vreserva, etc.) |
| Logica | Contiene la lógica y acceso a datos (fcliente, freserva, etc.). Realiza conexiones JDBC. |
| Presentacion | Formularios Swing diseñados con NetBeans para interacción con el usuario. |

## Mejoras futuras (RoadMap)

- Añadir validaciones más robustas en la UI.
- Incluir encriptación para contraseñas de usuarios.
- Exportar reportes en PDF.
- Dockerizar el sistema para despliegue rápido.
- Añadir tests unitarios con JUnit.

## Autores
- Fernando Díaz (NandOwO)
>🔗 GitHub: [@NandOwO](https://github.com/NandOwO)
-  Heidy Lozano
> 🔗 GitHub: [@Lisbeth02pink](https://github.com/Lisbeth02pink)
- Jhonatan Coronel
> 🔗 GitHub: [@HunterMk12](https://github.com/HunterMk12)

## Licencias
```markdown
Distribuido bajo la Licencia MIT. Ver [LICENSE](LICENSE) para más detalles.
```

## Capturas
- Ventana de Inicio de Sesion
<img width="1003" height="648" alt="Captura de pantalla 2025-07-23 231226" src="https://github.com/user-attachments/assets/cdc39457-0d99-410a-91dd-7c8d7abf71e4" />

- Ventana de Menu Principal
<img width="716" height="453" alt="Captura de pantalla 2025-07-24 030512" src="https://github.com/user-attachments/assets/a142c049-388f-4608-9278-19a7b3f472cd" />

- Ventana de Registro de Habitaciones
<img width="1104" height="731" alt="Captura de pantalla 2025-07-23 231337" src="https://github.com/user-attachments/assets/89756df5-4d85-4d5d-9254-d970e1e08a73" />

- Ventana de Registro de Productos
<img width="1151" height="693" alt="Captura de pantalla 2025-07-23 231400" src="https://github.com/user-attachments/assets/1f665ddf-2443-4cb6-8437-6157611b8948" />

- Ventana de Reservas
<img width="1124" height="496" alt="Captura de pantalla 2025-07-23 231423" src="https://github.com/user-attachments/assets/798c4c05-f355-4cb4-a71e-8acfa9eec675" />

- Ventana de Registro de Clientes
<img width="1102" height="592" alt="Captura de pantalla 2025-07-23 231441" src="https://github.com/user-attachments/assets/aeba22c7-6d3b-418b-a68c-2ab0a3c52a05" />

- Ventana de Pago
<img width="587" height="341" alt="Captura de pantalla 2025-07-23 231500" src="https://github.com/user-attachments/assets/bcfbf309-5a1e-41ba-b91d-cf9314716ae0" />

- Ventana de Registro de trabajadores y control de usuarios
<img width="1327" height="573" alt="Captura de pantalla 2025-07-23 231527" src="https://github.com/user-attachments/assets/601904b6-b865-4ba3-90fb-80b2601111b1" />





 
