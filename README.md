# Facturación App
### _1. Resumen ejecutivo_
&nbsp;&nbsp; _1.1 Descripción_   
 
Aplicación en donde generamos códigos QR para mostrar nuestra información de razones sociales y ahorrar tiempo a los usuarios.


&nbsp;&nbsp; _1.2 Problema identificado_  

La problemática a la que buscamos dar solución esta basado en el momento de facturar dentro de una empresa, tienda, negocio, etc. existen muchísimos procesos no uniformes para poder facturar, en la cual la mayoría de estos el usuario debe de facturar por si mismo en sistemas complicados, va involucrado a veces el anotar lo que nos lleva a que exista el error humano, pero en general, al momento de facturar suele ser un proceso muy tardado, lo cual es un problema que termina siendo tanto para el cliente como para la institución. Lo que buscamos es crear una solución en la cual podamos ofrecer un sistema que por medio de una aplicación para el usuario, hacer que si una persona busca facturar, lo pueda lograr en menos de 2 minutos. El traer una solución de esta manera a diferentes emprendimientos, podemos hacer este y muchos otros procesos más fáciles, así como permitirle al usuario almacenar la información fiscal necesaria para sus diferentes razones sociales.  

&nbsp;&nbsp; _1.3 Solución_  

Mi propuesta para darle solución a este problema es por medio de una aplicación. La app consiste en un inicio de sesión donde existen 2 roles, el que analizaremos primero es el de usuario, al ingresar los campos de email y contraseña correctamente, se hará la validación con nuestra base de datos y avanzaremos a la pantalla de bienvenido, nos mostrará 2 botones con 2 opciones, la opción uno que es generar el QR, llevándonos al listado de razones sociales disponibles para que nuestro usuario seleccione los datos que va a necesitar, en caso de querer crear uno nuevo, nos abre la pantalla de un formulario en donde ingresando los datos requeridos, podremos crear uno nuevo que se agregará al listado y asimismo en nuestra base de datos. Al hacer click en uno de las razones registradas, nos aparecerá un QR único con todos los datos necesarios para que alguien del rol administrador pueda leer el QR y llenar todos los datos automáticamente al momento de facturar. Por último una pantalla para editar la información del cliente en caso de que sea necesario.
Desde el rol de administrador, comparten la mayoría de las pantallas con la misma funcionalidad, exceptuando la parte del lector de QR, y que el objetivo de esta es generar la factura, esta factura se generará llevando al usuario a una URL con los parámetros de los datos como variables, para que se llenen de manera más sencilla, sin embargo también está la opción de que facturen sin QR, lo que solo nos estaría llevando al link de facturación. Cuenta también con una pantalla para editar la cuenta del usuario.

&nbsp;&nbsp; _1.4 Arquitectura_  

La primera capa, que es la del cliente, en donde se encuentra la aplicación se conecta a las conexiones externas, en donde podemos encontrar nuestros servicios de APIs, como el generador de QRs. Por último interactuando con ella para regresar info a la capa del cliente tenemos al servidor que sostiene la app así como nuestra base de datos ubicada en la nube.

### _2. Requerimientos_
&nbsp;&nbsp; 2.1 _Servidores de aplicación y bases de datos_  
&nbsp;&nbsp; -  Firebase : Realtime database, Hosting

### _3. Instalación & Configuración_
Descarga de proyecto  
Correr en Android Studio  
Usuario ejemplo: test@test.com  
Contraseña ejemplo: test123

### _4. Uso_
El proyecto consiste en una aplicación desarrollada en Android, dentro de ella existirán 3 módulos principales:  
_i.	Autenticación_  
El módulo de autenticación consiste en el desarrollo de 3 pantallas diferentes, la primera siendo un inicio de sesión, en dónde la lógica será desarrollada para traer la segunda pantalla, esta consiste en un menú que nos desglosará las opciones que tiene para ofrecer al usuario según sea su rol y la última parte de este módulo es la pantalla de cuenta que es en donde el usuario, una vez haya iniciado la sesión, puede cambiar los datos asociados a su cuenta.  
Dentro de este módulo, algunas funciones quedan fuera del alcance como el registro de nuevos usuarios, el flujo de la recuperación de contraseña y de momento la creación de cualquier tipo de usuario tiene que ser mediante la base de datos.  
_ii.	Razones sociales_  
El módulo de razones sociales consiste en 2 pantallas, la primera consiste en la pantalla para crear/editar razones sociales, con esto damos la opción a los usuarios de rol general de crear y editar sus razones sociales, al momento de crear una, se genera de igual manera un QR único, que se muestra en la segunda pantalla mencionada, con los datos de la razón social, y al momento de actualizar, se actualiza también los datos ligados al QR.  
La función de eliminar razones sociales queda fuera del alcance de momento.  
_iii.	Facturación_  
El módulo de facturación es el que está disponible solamente para los usuarios de rol administrador, existen 2 pantallas, una en la que facturación se hace manual y se ingresan los datos directamente en la aplicación y termina llevándonos a un link externo, mientras que la función de generar facturas con QR nos permite abrir el lector y traer los datos y así traer otra vez la función de llevarnos al link externo con los datos.


### _5. Contribución_
&nbsp;&nbsp; _6.1 Guía de contribución_
1. Clona el proyecto en tu propia máquina
2. Confirmar cambios en su propia rama
3. Haz push en su trabajo
4. Envíe una solicitud de extracción para que podamos revisar sus cambios

### _6. Roadmap_  
Funciones de editar/eliminar  
Registro de nuevos usuarios  
Implementación de API con códigos QR  
Rol Administrador

## Producto final
### _Acceso al producto_
Descargar archivo .zip  
Correr en Android Studio
### _Video de demostración_  
Haz click [aquí](https://youtu.be/BlwG2I6ilKI)