# LABORATORIO 4
## CRUD DE TAREAS
### Integrantes

- Santiago Diaz Rojas
- David Felipe Velasquez
- Sebastian Velasquez
- Santiago Naranjo

## 1. Generar scaffolding del protyecto.
![img.png](img.png)
## 2. Generar pruebas.
Para poder aplicar TDD lo primero que se hará serán las pruebas unitarias,
de tal forma que no las creemos para que pasen a conveniencia, aqui se pueden 
ver algunas pruebas y como estas fallan

Adicionalmente usamos herramientas como Mock, InjectMocks y BeforeEach para 
facilitar la creación de pruebas unitarias, ya que esto nos permite simular 
objetos, estas anotaciones vienen de la libreria mockito, adicionalmente se 
hará un setup para las cosas que se usan en cada prueba poniendo la anotación 
BeforeEach

![alt text](image-1.png)
![alt text](image.png)

## 3. Generacion de directorios.
Creamos los directorios necesarios para el correcto funcionamiento del proyecto, además de las implementaciones de sonar y jacoco en la carpeta target mediante los plugin puestos en el pom
![alt text](image-2.png)

## 4. Configuracion de validacion de codigo.
Sonar y jacoco plugins
![alt text](image-3.png)

## 5. 