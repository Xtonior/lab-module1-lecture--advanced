package kz.lab.module1.advanced;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class Main {
    @FunctionalInterface
    public interface EmailSender {
        void send(Email email);
    }

    public static void main(String[] args) {
        // taskImmutable();
        // taskRecord();
        taskLambda();
    }

    private static void taskImmutable() {
        // todo: необходимо класс Student сделать immutable без использования record
        // поведение должно быть похоже на record:
        // https://docs.oracle.com/en/java/javase/17/language/records.html#GUID-6699E26F-4A9B-4393-A08B-1E47D4B2D263

        // не должно работать - после исправления закомментировать
        // Student student = new Student();
        // Student student = new Student();

        // не должно работать - после исправления закомментировать
        // student.setName("Ivan");

        RecordStudent r1 = new RecordStudent(0, "John", "555");
        RecordStudent r2 = new RecordStudent(0, "Doe", "555");
        System.out.println(r1.toString());
        System.out.println(r1.equals(r2));
        System.out.println(r1.hashCode());

        // должно работать - после исправления раскомментировать
        Student student1 = new Student(1, "Ivan", "70022244505");
        Student student2 = new Student(1, "Ivan", "70022244505");

        // должно выводить в читаемом виде
        System.out.println(student1.toString());
        System.out.println(student1.equals(student2));
        System.out.println(student1.hashCode());

        // не должно работать
        // наследование запрещено чтобы избежать проблем с equals()
        // в данном случае мы рассматриваем проблему того,
        // что в наследнике можно сделать поля вновь редактируемыми

        // class GreatStudent extends Student {
        // private String name;

        // public GreatStudent(int id, String name, String phone) {
        // super(id, name, phone);
        // }

        // public void setName(String name) {
        // this.name = name;
        // }
        // public String getName() {
        // return name;
        // }
        // }

        // GreatStudent greatStudent = new GreatStudent(1, "Ivan", "70022244505");
        // greatStudent.setName("Vova");
        // System.out.println(greatStudent.getName());
    }

    private static void taskRecord() {
        // todo: сделать проверку в рекорде:
        // цена больше 0,
        // дата должна быть в прошлом
        // поля должны быть immutable

        Set<String> items = new HashSet<>(Set.of("a", "b", "c"));
        Order order = new Order(100, 30, LocalDateTime.now().minusDays(10), items); // можно ввести некорректные данные
        System.out.println(order);
        // order.items().add("d"); // теперь вызывает исключение
        System.out.println(order);
    }

    private static void taskLambda() {
        String title = "hi ";
        String content = "happy new year!";

        List<Customer> customers = List.of(
                new Customer("Bob", "bob@gmail.com"),
                new Customer("Alice", "alice@gmail.com"));

        // todo: реализовать отправку имейлов вставив имена в заголовок
        // sendEmails(...);

        sendEmails(customers, (customer) -> {
            String fullTitle = title + customer.name();
            Email email = new Email(customer.email(), fullTitle, content);
            System.out.println(email);
        });

        // Вывод в консоль
        // Email[to=bob@gmail.com, title=hi Bob, body=happy new year!]
        // Email[to=alice@gmail.com, title=hi Alice, body=happy new year!]
    }

    public static void sendEmails(List<Customer> customers, Consumer<Customer> action) {
        customers.forEach(action);
    }

    public static void sendEmails(String title, String content, List<Customer> customers, EmailSender emailSender) {
        // customers.stream()
        // .map(...)
        // .forEach(...);
    }
}