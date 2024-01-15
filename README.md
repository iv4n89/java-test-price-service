# Java test prices

## Cómo levantar el proyecto

### Con Docker

Este proyecto incluye Dockerfile y Makefile. Para levantar el proyecto haciendo uso de estos dos elementos deberemos de lanzar la siguiente instrucción en la consola de comandos:

```bash
make start
```

Podremos igualmente bajar el servidor del proyecto y eliminar tanto contenedor como imagen haciendo uso de la siguiente instrucción:

```bash
make stop
```

### Desde la main class del proyecto

Si no queremos hacer uso de Docker para levantar el proyecto podremos hacerlo desde la propia clase principal.

Ésta se encuentra en:
`/apps/service-presentation/src/main/java/com/test/service/presentation/PriceServiceApplication.java`

_____

## Tests

Este proyecto posee tests para cada una de las capas en las que está dividido.

Podremos testear la aplicación de las siguientes maneras:

### Lanzando los tests unitarios / de integración desde la consola de comandos

Para lanzar los tests unitarios y de integración desde la consola de comandos deberemos de lanzar la siguiente instrucción:

```bash
mvn test
```

En caso de no tener disponible Maven en el sistema, se incluye un wrapper de Maven en el proyecto. Para lanzar los tests haciendo uso de este wrapper deberemos de lanzar la siguiente instrucción:

`Desde Linux / Mac`

```bash
./mvnw test
```

`Desde Windows`

```bash
mvnw.cmd test
```

Además, se incluye la posibilidad de lanzar los tests desde un contenedor de Docker, instrucción que se incluye en el Makefile:

```bash
make test
```

Esto levantará un contenedor con el proyecto, para seguidamente lanzar los tests unitarios y de integración y finalmente eliminar el contenedor y la imagen generados.


### Usando la interfaz de Swagger incluída en el proyecto

Podremos hacer uso de la interfaz gráfica de Swagger para testear el proyecto.

Con la aplicación iniciada, navegar a la url:

`http://localhost:8081/swagger-ui/index.html`

____

## Acceder a la interfaz web de H2

Podremos acceder a la interfaz web de H2 para consultar la base de datos haciendo uso de la siguiente url:

`http://localhost:8081/h2-console`

Para acceder a la base de datos deberemos de introducir los siguientes datos:

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

____

## Estructura del proyecto

### Estructura de carpetas

El presente proyecto ha sido construido usando una arquitectura hexagonal y los principios de Domain Driven Design.

La estructura de carpetas que encontraremos en el proyecto es la siguiente:

```
- apps -> Aplicaciones del servicio. Contendrán las capas de presentación
| -- price-presentation
|  | -- src
|  |  | -- main
|  |  |  | -- java
|  |  |  |  | -- com.test.price.presentation
|  |  |  |  |  | -- controller -> Clases referentes a controladores para el servicio de 'price'
|  |  |  |  |  | -- exception.handler -> Clases referentes a controller advice para el servicio de 'price'
|  |  | -- test -> Tests de integración para el servicio de 'price'
|  | -- pom.xml -> pom.xml del módulo 'price-presentation'
| -- service-presentation
|  | -- src
|  |  | -- main
|  |  |  | -- java
|  |  |  |  | -- com.test.service.presentation
|  |  |  |  |  | -- config -> Clases de configuración general para el proyecto
|  |  |  |  |  | -- PriceServiceApplication.java -> Clase principal del proyecto, usado para levantarlo
|  |  |  | -- resources -> application.yaml y script sql inicial del proyecto
|  | -- pom.xml -> pom.xml del módulo 'service-presentation'
| -- pom.xml -> pom.xml del módulo apps

- contexts -> Bounded contexts del servicio
| -- price -> Bounded context de Price
|  | -- price-application
|  |  | -- src
|  |  |  | -- main
|  |  |  |  | -- java
|  |  |  |  |  | -- com.test.price.application
|  |  |  |  |  |  | -- adapters.input
|  |  |  |  |  |  |  | -- service -> Implemetación de los puertos service definidos en la capa de dominio
|  |  |  |  |  |  |  | -- usecases -> Implementación de los puertos de usecase definidos en la capa de dominio
|  |  |  |  |  |  | -- dto -> Clases de dto para request y response
|  |  |  |  |  |  | -- exception -> Clases de exception concretas para la capa de aplicación
|  |  |  |  | -- test -> Test unitarios para la capa de aplicación
|  |  | -- pom.xml -> pom.xml del módulo 'price-application'
|  | -- price-domain
|  |  | --src
|  |  |  | -- main
|  |  |  |  | -- java
|  |  |  |  |  | -- com.test.price.domain
|  |  |  |  |  |  | -- exception -> Clases de exception concretas para la capa de dominio
|  |  |  |  |  |  | -- model -> Clases de modelo, aggregates
|  |  |  |  |  |  | -- ports
|  |  |  |  |  |  |  | -- input
|  |  |  |  |  |  |  |  | -- service -> Definición de puertos service
|  |  |  |  |  |  |  |  | -- usecases -> Definición de puertos usecase
|  |  |  |  |  |  |  | -- output
|  |  |  |  |  |  |  |  | -- repository -> Definición de puerto repository
|  |  |  |  |  |  | -- valueobject -> Value objects concretos del bounded context de 'price'
|  |  |  | -- test -> Tests unitarios para la capa de dominio
|  |  | -- pom.xml -> pom.xml del módulo 'price-domain'
|  | -- price-infrastructure
|  |  | -- src
|  |  |  | -- main
|  |  |  |  | -- java
|  |  |  |  |  | -- com.test.price.infrastructure
|  |  |  |  |  |  | -- adapter.output.repository -> Implemetación del puerto repository definido en la capa de dominio
|  |  |  |  |  |  | -- entity -> Entidades jpa
|  |  |  |  |  |  | -- mapper -> Clases de mapeo modelo de dominio <-> entidad jpa
|  |  |  |  |  |  | -- repository -> Repositorio jpa
|  |  |  | -- test -> Tests unitarios de la capa de infrastructura
|  |  | -- pom.xml -> pom.xml del módulo 'price-infrastructure'
|  | -- pom.xml -> pom.xml del módulo price
| -- shared -> Elementos compratidos por diferentes contextos
|  | -- shared-application
|  |  | -- src
|  |  |  | -- main
|  |  |  |  | -- java
|  |  |  |  |  | -- com.test.shared.application -> Clases de DTO compartidas por los contextos
|  |  | -- pom.xml -> pom.xml del módulo 'shared-application'
|  | -- shared-domain
|  |  | -- src
|  |  |  | -- main
|  |  |  |  | -- java
|  |  |  |  |  | -- com.test.shared.domain
|  |  |  |  |  |  | -- exceptions -> Clase base de las exception de dominio definidas en los contextos
|  |  |  |  |  |  | -- valueobject -> Value objects compartidos por los dominios y bases de éstos
|  |  |  |  |  |  | -- AggregateRoot.java -> Interfaz de definición de tipo AggregateRoot
|  |  |  | -- test -> Clases generadoras de datos ficticios, a usar en los contextos
|  |  | -- pom.xml -> pom.xml del módulo 'shared-domain'
|  | -- shared-infrastructure
|  |  | -- src
|  |  |  | -- main
|  |  |  |  | -- java
|  |  |  |  |  | -- com.test.shared.infrastructure
|  |  |  |  |  |  | -- exception.handler
|  |  |  |  |  |  |  | -- GlobalExceptionHandler.java -> Clase base de los controller advice de los contextos
|  |  | -- pom.xml -> pom.xml del módulo 'shared-infrastructure'
|  | -- pom.xml -> pom.xml del módulo 'shared'
| -- pom.xml -> pom.xml del módulo 'contexts'
-- pom.xml -> pom.xml base del proyecto
-- Dockerfile -> Dockerfile para la generación de contenedor
-- Makefile -> Makefile para la generación de contenedor
```

Como se puede observar de la estructura del proyecto, cada capa está encapsulada, con lo que tendremos en cada una de ellas sólo las dependencias estrictamente necesarias.

De esta manera cada módulo puede ser independiente, teniendo como dependencias los módulos necesarios.

Se ha optado además por crear una estructura con la que sea fácil escalar el proyecto, añadiendo nuevos contextos, sin que éstos tengan dependencias con otros contextos.

Esto queda reflejado en:

- Contexto shared:
    
    Los elementos compartidos por los diferentes contextos se encuentran en el contexto shared. De esta manera, si un contexto necesita hacer uso de un elemento compartido, sólo tendrá que añadir como dependencia el módulo shared correspondiente.

- Capa de presentación:

    La capa de presentación (módulo 'apps') se ha dividido de manera que tengamos un módulo principal - service presentation - el cual define la clase principal de la aplicación, y cada uno de los módulos de presentación restantes que esté presentes corresponderán a los diferentes bounded contexts del servicio.

    De esta manera el módulo que contiene nuestra clase principal no conoce los detalles de implementación de los diferentes bounded contexts, quedando encapsulado tanto la implementación de la presentación como sus tests en sus correspondientes módulos, y haciendo posible la inclusión de nuevos módulos muy fácilmente.

De manera general, cada bounded context estará dividido en cada una de las 3 capas a las que se suele hacer referencia en una arquitectura hexagonal:

- Capa de dominio:

  Será la capa más pura, sin acoplamiento con el framework. Su módulo sólo deberá tener como dependencia la capa de dominio de Shared.

  En esta capa se definirán los elementos del dominio y los contratos a cumplir por el resto de capas.

- Capa de aplicación:

  Se comunica con la capa de dominio, implementando los casos de uso y dto, además del servicio de aplicación para este contexto.

  Se encarga de definir la lógica de negocio, sin entrar a detalles sobre la implementación concreta de repositorios o interacciones con bases de datos.

- Capa de infraestructura:

  Será la capa que más pueda acoplarse al framework y librerías.

  Se encarga de definir las interacciones con el exterior, entre las que se incluye la implementación concreta de repositorios e interacciones con bases de datos.

- Capa de presentación:

  Podría ser parte de la capa de infraestructura; sin embargo, se ha optado por separarlo de la definición del resto de capas, como se ve reflejado en la concepción de DDD (bounded contexts y aplicaciones).

  Se encarga de definir las clases de controlador, que dictarán las interacciones con el usuario. Así como establecer los puntos de entrada hacia la aplicación (clase principal).

A destacar además de esta estructura las dependencias entre capas, que siempre será la siguiente:

![hex arch](https://codigoencasa.com/content/images/2022/03/image.png)
___

### Patrones de diseño usados en el proyecto

- Patrón de puertos y adaptadores

Inherente a la arquitectura hexagonal con la que se ha enfocado el proyecto.

Tendremos los puertos definidos en la capa de dominio y su posterior adaptador implementado en la capa correspondiente.

- Patrón builder

Tendremos varias clases que estarán usando este patrón, como los modelos de dominio y las entidades.

Este patrón nos ayuda a construir correctamente las clases. Siendo que Java es un lenguaje donde los argumentos de los constructores de sus clases son posicionales, podría llevar a errores si tenemos constructores con muchos argumentos.

Esto se soluciona con el patrón builder, donde podemos seguir el orden que necesitemos ya que sabemos qué argumento estamos pasando en todo momento o construir por partes el objeto.

- Patrón value object

Nuestras clases de modelo de dominio usarán Value Objects como tipo para sus propiedades.

Esto nos permite encapsular la lógica necesaria para la validación de los valores sin necesidad de establecer reglas en la propia clase de modelo o realizar validaciones en servicios o repositorios.

- Patrón composite

Principalmente usado para nuestras clases de Value Objects. Éstas estarán basadas en una clase base `ValueObject<T>`, a partir de la cual nacerán diferentes ValueObjects, que a su vez pueden ser base para otros ValueObject. De esta manera las clases hijas no necesitan conocer todos los detalles de la implementación de la lógica para cada tipo de ValueObject, sino sólo aquello que necesiten para su propia implementación.

Por ejemplo, tenemos el caso del ValueObject `Priority`, el cual hereda de la clase abstracta `PositiveNumericValueObject<Integer>`, siendo este que a su vez hereda de `ValueObject<Integer>`.

___

### Dependencias del proyecto

Las librerías usadas para el presente proyecto, de manera resumida, fueron las siguientes:

- spring-boot - versión 3.1.5 → Framework usado para la aplicación
- javafaker - versión 1.0.2 → Librería faker para generar datos falsos aleatorios, usado en tests
- lombok
- plugins:
    - jacoco: Análisis de cobertura de tests
    - maven-jar-plugin: Usado para generar paquetes jar de las clases de tests, a compartir entre módulos
    - maven-shade-plugin: Usado para generar un único jar que contenga todos los módulos, a usar para dockerización del proyecto

___

## Elementos a tener en cuenta sobre el proyecto

Durante esta sección del presente Readme se tratará de explicar, a grandes rasgos, las decisiones tomadas en el proyecto.

- Sólo entidad Price:

  Aun cuando el ejercicio señala que esta entidad se relaciona con otras - como Brand - se puede entender que este proyecto se trata de un microservicio.

  De esta manera, de cara a hacer que sea lo más independiente posible, se ha optado por no incluir más entidades que la de Price, sin constraints reales en cuanto a claves foráneas.

  De cara a suplir las necesidades que puedan generarse de esta decisión, como puede ser obtener el nombre de la cadena a la que hace referencia su ID o la tarifa aplicable al precio, podría hacerse uso un gateway que recoja los datos de estos otros microservicios y permita al usuario final obtener los datos completos.

- Uso de Primary Key compuesto en la tabla `prices`:

  Se ha optado por una _clave primaria compuesta_ para la tabla de `prices`.

  Debido a que las búsquedas que se realizarán sobre la tabla siempre necesitarán de `brand_id`, `product_id`, `start_date` y `end_date`, añadiendo `priority` como elemento de desambiguación, tendremos siempre datos suficientes para crear un identificador compuesto único que identifique inequívocamente una entrada concreta.

  Para evitar complejidad innecesaria, se evitará crear campos extras innecesarios para el uso del servicio.

- Bounded context 'shared':

  Como ya se expuso durante la explicación de la estructura del proyecto, se ha optado por crear un bounded context 'shared' para incluir elementos compartidos por los diferentes contextos.

  Aun cuando estamos ante un microservicio, se ha querido dar una visión más abierta a posibles cambios y adiciones futuras, de manera que se pueda escalar el proyecto de manera más sencilla.

  El hecho de tener un bounded context 'shared' para compartir clave entre diferentes contextos hace que evitemos dependencias entre contextos o código duplicado entre ellos.

  Como ejemplo tenemos las diferentes clases base de ValueObject del proyecto, los ValueObject que se compartirán entre contextos, como 'ProductId' o 'BrandId', o marker interfaces como 'AggregateRoot'.

- No uso de Tomcat como servidor web:

  Se ha optado por no utilizar el servidor web Tomcat que provee la librería `spring-boot-starter-web`, para hacer uso de un servidor web más ligero: `spring-boot-starter-undertow`.

  Tenemos en el proyecto un servicio pequeño, por lo que un servidor ligero es más que suficiente para levantarlo, con lo que conseguimos ahorrar algunos recursos del sistema en el proceso.

- Maven Shade para generar un único jar:

  Se ha usado el plugin de Maven Shade para generar un único paquete jar que contenga toda la aplicación completa, con todos los módulos, de manera que fácilmente tengamos acceso a un paquete para utilizar en la dockerización del proyecto.

- Paquetes jar de test:

  Cada módulo del proyecto estará generando dos paquetes jar a la hora de su construcción: el propio paquete del módulo y un paquete adicional con los tests. Esto es posible gracias al plugin de Maven jar.

  Ya que estamos dividiendo el proyecto en capas / módulos, en cada uno de ellos podemos establecer una responsabilidad diferente, de manera que obtengamos tests más concretos para cada capa y haciendo posible reutilizar elementos entre capas.

  Como ejemplo, tenemos los generadores de datos ficticios desde el paquete de test de shared-domain, que se usará en la capa de dominio del contexto para generar una clase de generación aleatoria para la clase de modelo, que a su vez ésta será usada por el resto de capas para generar datos válidos aleatorios de manera más cómoda, rápida y fácil de leer.

- Proyecto mantenible y escalable:

  Siguiendo con uno los puntos anteriores, aun cuando sólo tenemos una clase de modelo para este proyecto, se ha optado por establecer un bounded context 'shared' y establecer una estructura de carpetas que permita la escalabilidad del proyecto.

  Otra visión válida sería establecer toda lógica dentro del propio contexto de 'price', lo cual haría más estático el proyecto y con necesidad de realizar mayores cambios al mismo de cara una ampliación.

  Se ha optado por establecer esta estructura del proyecto para dar una visión más abierta a posibles cambios y adiciones.

  El hecho además de estar dividido por módulos hace que cada capa sea independiente. Si en un futuro necesitamos reemplazar una de las capas de un contexto bastará con cambiar las dependencias hacia éste en el resto de módulos.

  Adicionalmente, encontraremos, concretamente en la capa de infraestructura, que las implementaciones poseen en el nombre la clase el detalle del tipo de implementación al que ha llegado. Por ejemplo, PriceJpaRepository - para el repositorio jpa - o H2PriceRepository - para la implementación concreta del puerto de repositorio. Si en un futuro necesitásemos hacer uso de, por ejemplo, una base de datos MongoDB bastará con crear una implementación MongoPriceRepository.

