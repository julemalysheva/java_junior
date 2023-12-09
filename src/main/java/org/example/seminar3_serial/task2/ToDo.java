package org.example.seminar3_serial.task2;

import java.io.*;

/**
 * Externalizable интерфейс, в чем его отличие от Serializable и смысл методов writeExternal и readExternal
 * Интерфейс Externalizable является альтернативой интерфейсу Serializable в Java для сериализации объектов.
 * В отличие от Serializable, который автоматически сериализует и десериализует объекты без необходимости
 * явного указания, Externalizable требует явной реализации двух методов: writeExternal() и readExternal().
 *
 * Отличия:
 * 1. Контроль над процессом сериализации и десериализации: При использовании Serializable процесс сериализации
 * и десериализации полностью автоматизирован, в то время как при использовании Externalizable разработчик имеет
 * полный контроль над этим процессом, поскольку он самостоятельно реализует нужную логику в методах writeExternal()
 * и readExternal().
 * 2. Размер сериализованного объекта: При использовании Externalizable можно более гибко контролировать размер
 * сериализованных данных, так как вы можете определить собственный формат и выбрать только необходимые данные для
 * сериализации.
 * 3. Сериализация кроссплатформенных объектов: Externalizable предоставляет более полный контроль над сериализацией
 * объектов и может использоваться при работе с кроссплатформенными объектами, поскольку формат сериализации может
 * быть настроен для соблюдения специфичных требований.
 *
 * Методы writeExternal() и readExternal():
 * - writeExternal(ObjectOutput out): В этом методе разработчик определяет логику записи объекта во внешний источник
 * данных. Объект ObjectOutput предоставляет методы для записи данных.
 * - readExternal(ObjectInput in): В этом методе разработчик определяет логику чтения объекта из внешнего источника
 * данных. Объект ObjectInput предоставляет методы для чтения данных.
 *
 * При реализации Externalizable необходимо явно вызывать методы writeExternal() и readExternal(), чтобы сериализовать
 * и десериализовать объекты.
 *
 * Выбор между Serializable и Externalizable зависит от требований вашего приложения. Если вы хотите больше контроля
 * над процессом сериализации и десериализации, а также более гибко управлять размером данных, Externalizable может
 * быть более подходящим вариантом. Однако, если вам нужна автоматическая сериализация/десериализация объектов без
 * необходимости явного указания, то вы можете использовать Serializable.
 */
public class ToDo implements Externalizable {

    //region Поля

    /**
     * Наименование задачи
     */
    private String title;

    /**
     * Статус задачи
     */
    private boolean isDone;

    //endregion

    //region Конструкторы

    public ToDo() {
    }

    public ToDo(String title) {
        this.title = title;
        isDone = false;
    }

    //endregion

    //region Методы

    /**
     * Получить наименование задачи
     * @return наименование задачи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Получить статус выполнения задачи
     * @return статус выполнения задачи
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Изменить статус выполнения задачи
     * @param done новый статус задачи
     */
    public void setDone(boolean done) {
        isDone = done;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeBoolean(isDone);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String)in.readObject();
        isDone = in.readBoolean();
    }

    //endregion

}

/**
 * Если вы реализуете интерфейс Externalizable, но не вызываете явно методы writeExternal() и readExternal(),
 * то при попытке сериализации или десериализации объекта будет возникать исключение NotSerializableException.
 *
 * В случае использования Externalizable, встроенные методы сериализации и десериализации, которые предоставляет
 * интерфейс Serializable, не используются. Вместо этого, разработчик должен явно реализовать и вызывать методы
 * writeExternal() и readExternal() для определения собственной логики сериализации и десериализации объектов.
 *
 * Таким образом, если вы реализуете Externalizable, но не реализуете или не вызываете явно методы writeExternal()
 * и readExternal(), то процесс сериализации и десериализации будет прерываться с исключением NotSerializableException,
 * поскольку не будет найдено реализации сериализации и десериализации для объекта.
 */
