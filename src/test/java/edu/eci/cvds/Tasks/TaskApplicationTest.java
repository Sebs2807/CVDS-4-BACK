package edu.eci.cvds.Tasks;

import edu.eci.cvds.Tasks.model.Task;
import edu.eci.cvds.Tasks.model.Difficulty;
import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.repository.TaskRepository;
import edu.eci.cvds.Tasks.repository.TokenRepository;
import edu.eci.cvds.Tasks.repository.UserRepository;
import edu.eci.cvds.Tasks.service.AuthService;
import edu.eci.cvds.Tasks.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskApplicationTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TaskService taskService;
    @InjectMocks
    private AuthService authService;

    private Task task1;
    private Task task2;
    private User user;
    private Token token;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User("SantiagoDiazR", "a4nt14g0");
        user.setIdUser("1");

        token = new Token();
        token.setIdToken("1");
        token.setIdUser("1");
    
        task1 = new Task();
        task1.setIdTarea("1");
        task1.setNombreTarea("Tarea 1");
        task1.setFinalizada(false);
        task1.setDescTarea("Descripción de la tarea 1");
        task1.setPrioridadTarea(2);
        task1.setDificultadTarea(Difficulty.ALTO);
        task1.setTiempoTarea(2);
        task1.setIdUser("1");
    
        task2 = new Task();
        task2.setIdTarea("2");
        task2.setNombreTarea("Tarea 2");
        task2.setFinalizada(true);
        task2.setDescTarea("Descripción de la tarea 2");
        task2.setPrioridadTarea(1);
        task2.setDificultadTarea(Difficulty.BAJO);
        task2.setTiempoTarea(1);
        task2.setIdUser("1");
    }
    

    @Test
    public void shouldGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks("1");
        assertEquals(2, tasks.size());
        assertEquals("Tarea 1", tasks.get(0).getNombreTarea());
    }

    @Test
    public void shouldGetTaskById() {
        when(taskRepository.findById("1")).thenReturn(Optional.of(task1));

        Optional<Task> task = taskService.getTaskById("1");
        assertTrue(task.isPresent());
        assertEquals("Tarea 1", task.get().getNombreTarea());
    }

    @Test
    public void shouldCreateTask() {
        when(taskRepository.save(task1)).thenReturn(task1);

        Task savedTask = taskService.createTask(task1);
        assertNotNull(savedTask);
        assertEquals("Tarea 1", savedTask.getNombreTarea());
    }

    @Test
    public void shouldUpdateTask() {
        when(taskRepository.save(task1)).thenReturn(task1);

        task1.setNombreTarea("Tarea Actualizada");
        Task updatedTask = taskService.updateTask("1", task1);

        assertEquals("Tarea Actualizada", updatedTask.getNombreTarea());
        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    public void shouldDeleteTask() {
        doNothing().when(taskRepository).deleteById("1");

        taskService.deleteTask("1");
        verify(taskRepository, times(1)).deleteById("1");
    }

    @Test
    public void shouldReturnDescTask(){
        String expected = "Descripción de la tarea 1";
        assertEquals(expected, task1.getDescTarea());
    }

    @Test
    public void shouldReturnIDTarea(){
        String expected = "1";
        assertEquals(expected, task1.getIdTarea());
    }

    @Test
    public void shouldReturnIfTaskIsFinished(){
        assertEquals(false, task1.isFinalizada());
        assertEquals(true, task2.isFinalizada());
    }

    @Test
    public void shouldSetAndReturnPrioridadTarea() {
        task1.setPrioridadTarea(3);
        assertEquals(3, task1.getPrioridadTarea());
    }

    @Test
    public void shouldSetAndReturnDificultadTarea() {
        task1.setDificultadTarea(Difficulty.MEDIO);
        assertEquals(Difficulty.MEDIO, task1.getDificultadTarea());
    }

    @Test
    public void shouldSetAndReturnTiempoTarea() {
        Integer expectedDuration = 5;
        task1.setTiempoTarea(expectedDuration);
        assertEquals(expectedDuration, task1.getTiempoTarea());
    }

    @Test
    public void givenOneTaskWhenQueriedByIdThenReturnTaskSuccessfully() {
        // Dado que tengo 1 tarea registrada
        when(taskRepository.findById("1")).thenReturn(Optional.of(task1));

        // Cuándo la consulto a nivel de servicio
        Optional<Task> result = taskService.getTaskById("1");

        // Entonces la consulta será exitosa validando el campo id
        assertTrue(result.isPresent());
        assertEquals("1", result.get().getIdTarea());
    }

    @Test
    public void givenNoTasksWhenQueriedByIdThenReturnNoResult() {
        // Dado que no hay ninguna tarea registrada
        when(taskRepository.findById("1")).thenReturn(Optional.empty());

        // Cuándo la consulto a nivel de servicio
        Optional<Task> result = taskService.getTaskById("1");

        // Entonces la consulta no retornará ningún resultado
        assertFalse(result.isPresent());
    }

    @Test
    public void givenNoTasksWhenTaskIsCreatedThenCreationIsSuccessful() {
        // Dado que no hay ninguna tarea registrada
        when(taskRepository.save(task1)).thenReturn(task1);

        // Cuándo lo creo a nivel de servicio
        Task createdTask = taskService.createTask(task1);

        // Entonces la creación será exitosa
        assertNotNull(createdTask);
        assertEquals("1", createdTask.getIdTarea());
    }

    @Test
    public void givenOneTaskWhenDeletedThenDeletionIsSuccessful() {
        // Dado que tengo 1 tarea registrada
        doNothing().when(taskRepository).deleteById("1");

        // Cuándo la elimino a nivel de servicio
        taskService.deleteTask("1");

        // Entonces la eliminación será exitosa
        verify(taskRepository, times(1)).deleteById("1");
    }

    @Test
    public void givenOneTaskWhenDeletedAndQueriedThenNoResultIsReturned() {
        // Dado que tengo 1 tarea registrada
        doNothing().when(taskRepository).deleteById("1");
        when(taskRepository.findById("1")).thenReturn(Optional.empty());

        // Cuándo la elimino
        taskService.deleteTask("1");

        // Y consulto a nivel de servicio
        Optional<Task> result = taskService.getTaskById("1");

        // Entonces el resultado de la consulta no retornará ningún resultado
        assertFalse(result.isPresent());
    }

    public void testTaskGenerator() {
        taskService.taskGenerator();
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository, atLeast(100)).save(taskCaptor.capture()); // Verificar que se llama save() al menos 100 veces
        verify(taskRepository, atMost(1000)).save(taskCaptor.capture()); // Verificar que se llama save() como máximo 1000 veces
        List<Task> capturedTasks = taskCaptor.getAllValues();
        assertTrue(capturedTasks.size() >= 100 && capturedTasks.size() <= 1000);
    }
}
