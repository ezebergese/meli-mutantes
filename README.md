# MELI-MUTANTES

[![Build Status](https://travis-ci.com/ChrisExFS/meli-mutantes.svg?token=uWfxykicNNrS8grywrnv&branch=master)](https://travis-ci.com/ChrisExFS/meli-mutantes)

Ejercicio práctico de Mercado Libre.

# Resolución del problema
Para resolver el problema se crearon los siguientes objetos, cada uno con responsabilidades diferentes:
  - Sequence: Secuencia de ADN que puede ser recorrida. Su representación (en esta implementación) es una matriz representada con un array de strings (como en el enunciado).
  - DNA: Objeto que contiene la secuencia. Se puede consultar si el ADN es mutante o no.
  - MatrixPatternDetector: Este objeto permite detectar patrones en una matriz.
  - LinealPatternDetector: Permite detectar patrones en una dimensión (columnas, filas, diagonales). Cada uno de los detectores consiste de un conjunto de objetos PatternDetector. Cada uno de ellos está asociado a un índice específico (dependiendo del tipo de detector será el índice de columna, row o diagonal).
  - PatternDetector: Permite detectar patrones en una posición específica. La estrategia de esta implementación es que tiene qu ehaber 4 elementos iguales consecutivos, pero la lógica del patrón se puede cambiar creando un objeto diferente con una lógica diferente.

La solución está pensada para matrices cuadradas y para que el recorrido se haga por fila-columna. Por ejemplo, si tenemos esta matriz:
A  B  C

D  E  F

G  H  I


El recorrido se hará en este orden: A-B-C-D-E-F-G-H-I

A continuación se presentan diagramas UML que muestran el diseño y un ejemplo de llamada a isMutant.

### Diagrama de clases:

![alt uml_class](/uml/uml_class.png)

### Diagramas de secuencia

##### Consulta si un ADN es mutante

![alt uml_sequence_1](/uml/uml_sequence_1.png)

##### Consulta si un ADN es mutante
![alt uml_sequence_2](/uml/uml_sequence_2.png)

##### Agregar elemento a ColumnPatternDetector
![alt uml_sequence_3](/uml/uml_sequence_3.png)

##### Agregar elemento a RowPatternDetector
![alt uml_sequence_4](/uml/uml_sequence_4.png)

##### Agregar elemento a DiagonalPatternDetector
![alt uml_sequence_5](/uml/uml_sequence_5.png)

# Herramientas, lenguajes y servicios utilizados

  - Java (con Spring Boot)
  - JUnit para tests automatizados
  - Python, para algunos scritps
  - Travis-CI para la integración continua y deploy continuo (CI/CD)
  - Docker y docker-compose, para armar una imagen con la api que se pueda subir a aws y además correr localmente
  - Localstack, permite emular ciertos servicios de Amazon localmente
  - AWS Fargate, es el servicio que levanta contenedores en base a la imagen creada en el punto anterior
  - AWS DynamoDb, es el servicio de base de datos utilizado para persistir los adns que se envían a analizar y mantener las estadísticas
  - AWS Lambda, se utiliza para actualizar las estadísitcas. Se ejecuta cada vez que se modifica la tabla de adns.

# Ejecutar localmente

Para ejecutar localmente es necesario tener instalado docker y docker-compose.
En una línea de comandos ejecutar:
```sh
$ docker-compose up
```
Se descargan 3 imágenes:
  - meli-mutantes-api: Contiene la api con los servicios /mutant y /stats
  - meli-mutantes-lambda: Contiene el código de la lambda y un script para crear los recursos necesarios en los servicios emulados por localstack (tablas de dynamodb, función lambda, etc)
  - localstack: Imagen que contiene el servicio de localstack

La descarga en instalación puede llevar unos minutos, debido a que la imagen de localstack es bastante pesada.
Una vez que la imagen lambda termina de ejecutar los scripts (en la consola aparecerá "meli-mutantes_lambda_1 exited with code 0") se puede utilizar la api localmente llamando a los servicios de mutants y stats de la siguiente manera:

Para verificar si una cadena de adn es mutante:
```sh
$ curl -H "Content-Type: application/json" \
-X POST -d '{"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}' \
http://localhost:8080/mutant
```
Para obtener las estadísticas:
```sh
curl http://localhost:8080/stats
```

# Utilizar API hosteada en AWS
La API está deployada en el servicio Fargate. La opción de precio elegida es por demanda, por lo tanto se cobra por tiempo que el servicio esté levantado. Para evitar incurrir en gastos innecesarios el servicio se encuentra bajo. Al momento de probar avisar para levantar el servicio.

Una vez que se levante el servicio la api se consume de la siguiente manera:
```sh
$ curl -H "Content-Type: application/json" \
-X POST -d '{"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}' \
meli-lb-a0c8f6fe60f7e637.elb.us-east-1.amazonaws.com/mutant
```
Para obtener las estadísticas:
```sh
curl meli-lb-a0c8f6fe60f7e637.elb.us-east-1.amazonaws.com/stats
```

# Integración Continua y Deploy continuo
El proyecto contiene dos jobs de travis, uno para el branch develop y otro para el branch master.
El job del branch develop realiza un build del proyecto que, compila, testea y arma el proyecto.
El job del branch master también realiza el build y además arma las imágenes docker, las sube a un repositorio de dockerhub y hace los deploys en los servicios correspondientes utilizando el cli de aws.
  

