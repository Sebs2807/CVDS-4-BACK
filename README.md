# LABORATORIO 4

## CRUD DE TAREAS BACKEND

### Integrantes

- Santiago Díaz Rojas
- David Felipe Velásquez
- Sebastián Velásquez
- Santiago Naranjo

![Integrantes](images/img_3.png)

## 1. Generación del scaffolding del proyecto.

![Scaffolding](images/img.png)

## 2. Generación de pruebas.

Para poder aplicar TDD, lo primero que haremos será crear pruebas unitarias, de tal forma que no las ajustemos para que pasen de forma conveniente. Aquí se pueden ver algunas pruebas y cómo fallan.

Adicionalmente, utilizamos herramientas como `Mock`, `InjectMocks` y `BeforeEach` para facilitar la creación de pruebas unitarias, ya que esto nos permite simular objetos. Estas anotaciones provienen de la librería Mockito. También se hará un setup para los elementos que se usan en cada prueba, utilizando la anotación `BeforeEach`.

![Pruebas Unitarias 1](images/image-1.png)
![Pruebas Unitarias 2](images/image.png)

## 3. Generación de directorios.

Creamos los directorios necesarios para el correcto funcionamiento del proyecto, además de implementar Sonar y Jacoco en la carpeta `target` mediante los plugins incluidos en el `pom.xml`.

![Directorios](images/image-2.png)

## 4. Configuración de validación de código.

Utilizamos los plugins de Sonar y Jacoco, con una cobertura del 80%. Estas validaciones se aplican sobre las clases `TaskController` y `TaskService`.

![Cobertura de Código](images/image-3.png)

## 5. Implementación de clases.

Después de las configuraciones en el `pom.xml`, procedemos a implementar cada una de las clases influyentes dentro de la inversión de control e inyección de dependencias.

## 6. Ejecución de pruebas unitarias y validación estática de código.

![Ejecución de Pruebas 1](images/img_1.png)
![Ejecución de Pruebas 2](images/img_2.png)

Tambien quedo la prueba realizada en sonar

![Sonar](image.png)

## 7. Creación del cluster en MongoDB Atlas.

![Cluster en MongoDB Atlas](images/img_4.png)

## 8. Creación de clase de configuración.

Implementación de una clase para permitir realizar solicitudes HTTP desde un puerto local.

![Configuración HTTP](images/img_5.png)

## 9. Pruebas en Postman

- Solicitud GET para obtener todas las tareas existentes:
  http://localhost:8080/tasks
  ![GET todas las tareas](images/img_6.png)

- Solicitud GET para obtener una tarea existente por su `idTarea`:
  http://localhost:8080/tasks/{idTarea}
  ![GET tarea por id](images/img_7.png)

- Solicitud PUT para actualizar una tarea dado su `idTarea`:
  http://localhost:8080/tasks/{idTarea}
  ![PUT actualizar tarea](images/img_8.png)

- Solicitud POST para la creación de nuevas tareas:
  http://localhost:8080/tasks
  ![POST crear tarea](images/img_9.png)

- Solicitud DELETE para eliminar una tarea dado su `idTarea`:
  http://localhost:8080/tasks/{idTarea}
  ![DELETE eliminar tarea](images/img_10.png)
