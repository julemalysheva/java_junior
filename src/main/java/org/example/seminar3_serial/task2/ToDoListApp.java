package org.example.seminar3_serial.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Сериализация в данном коде происходит с использованием библиотеки Jackson для работы с JSON и XML форматами,
 * а также стандартного механизма сериализации и десериализации объектов для бинарного формата.
 * Для JSON сериализации и десериализации используется объект ObjectMapper из библиотеки Jackson.
 * При сохранении списка задач в JSON файл (fileName.endsWith(".json")), вызывается метод writeValue()
 * объекта objectMapper и передается в качестве параметров файл и список задач. Метод writeValue()
 * производит сериализацию списка задач в формат JSON с помощью определенных правил, включая вложенные поля объектов ToDo.
 *
 * Аналогичным образом, для XML сериализации и десериализации используется объект xmlMapper из библиотеки Jackson.
 * При сохранении списка задач в XML файл (fileName.endsWith(".xml")), вызывается метод writeValue() объекта
 * xmlMapper и передается файл и список задач в качестве параметров. Метод writeValue() производит сериализацию
 * списка задач в формат XML.
 *
 * Для бинарной сериализации и десериализации используется стандартный механизм Java для работы с объектами.
 * При сохранении списка задач в бинарный файл (fileName.endsWith(".bin")), создается объект ObjectOutputStream,
 * который использует метод writeObject() для записи списка задач в бинарный формат.
 *
 * В обратном порядке при загрузке файлов происходит десериализация с использованием соответствующих методов
 * (readValue() для JSON и XML, readObject() для бинарного формата) объектов objectMapper, xmlMapper и
 * ObjectInputStream. Результат десериализации сохраняется в список задач tasks, который возвращается
 * из метода loadTasksFromFile().
 */
public class ToDoListApp {

    public static final String FILE_JSON = "tasks.json";
    public static final String FILE_BIN = "tasks.bin";
    public static final String FILE_XML = "tasks.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void addNewTask(Scanner scanner, List<ToDo> tasks) {
        System.out.println("Введите название новой задачи:");
        String newTaskTitle = scanner.nextLine();
        tasks.add(new ToDo(newTaskTitle));
        saveTasksToFile(FILE_JSON, tasks);
        saveTasksToFile(FILE_BIN, tasks);
        saveTasksToFile(FILE_XML, tasks);
        System.out.println("Новая задача добавлена.");
    }

    public static void saveTasksToFile(String fileName, List<ToDo> tasks) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), tasks);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(tasks);
                }
            } else if (fileName.endsWith(".xml")) {
                //String s = xmlMapper.writeValueAsString(tasks);
                xmlMapper.writeValue(new File(fileName), tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ToDo> loadTasksFromFile(String fileName) {
        List<ToDo> tasks = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    tasks = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ToDo.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        tasks = (List<ToDo>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    tasks = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, ToDo.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }

    public static void markTaskAsDone(Scanner scanner, List<ToDo> tasks) {
        System.out.println("Введите порядковый номер задачи для отметки как выполненной:");
        String input = scanner.nextLine();

        try {
            int taskNumber = Integer.parseInt(input) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                tasks.get(taskNumber).setDone(true);
                saveTasksToFile(FILE_JSON, tasks);
                saveTasksToFile(FILE_BIN, tasks);
                saveTasksToFile(FILE_XML, tasks);
                System.out.println("Задача отмечена как выполненная.");
            } else {
                System.out.println("Некорректный номер задачи. Попробуйте снова.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Попробуйте снова.");
        }
    }

    public static void displayTasks(List<ToDo> tasks) {
        System.out.println("Список задач:");
        for (int i = 0; i < tasks.size(); i++) {
            ToDo task = tasks.get(i);
            String status = task.isDone() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + task.getTitle());
        }
    }

}

/**
 * если у меня
 * public class ToDo implements Externalizable
 * а сериализовать я хочу список объектов типа ToDo, то есть коллекцию ArrayList -
 * в этом случае я смогу обращаться к методам writeObject встроенной сериализации, т.к.
 * класс ArrayList имплементрирует Serializable? даже если элементы этого списка имплементируют
 * не Serializable, а Externalizable?
 *
 * Верно, если вы сериализуете коллекцию ArrayList, в которой элементы имплементируют Externalizable,
 * то встроенные методы сериализации, предоставляемые интерфейсом Serializable, будут использованы для
 * сериализации списка, включая его элементы.
 *
 * Класс ArrayList сам по себе имплементирует интерфейс Serializable, и его встроенные методы сериализации
 * и десериализации используются по умолчанию. В процессе сериализации ArrayList будет проверять, реализуют
 * ли его элементы интерфейс Serializable, и в случае, если это так, использует встроенный механизм сериализации
 * для сериализации каждого элемента.
 *
 * Даже если элементы ArrayList имплементируют Externalizable, встроенный механизм сериализации все равно
 * будет использоваться для сериализации ArrayList в целом. Однако, если элементы ArrayList имплементируют
 * и Serializable, и Externalizable, то при использовании стандартных методов сериализации и десериализации,
 * предоставляемых интерфейсами, методы writeExternal() и readExternal() не будут вызываться для элементов коллекции.
 *
 * Таким образом, если элементы коллекции ArrayList имплементируют Externalizable, то для корректной
 * сериализации элементов вам следует явно вызвать методы writeExternal() и readExternal() для каждого
 * элемента при сериализации и десериализации, чтобы логика, определенная в этих методах, была применена к элементам.
 */
