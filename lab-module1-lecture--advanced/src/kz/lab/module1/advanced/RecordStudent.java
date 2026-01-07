package kz.lab.module1.advanced;

// эталонный класс для проверки логики
public record RecordStudent(int id, String name, String phone) {
    public static int foo = 10;

    public RecordStudent(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String phone() {
        return phone;
    }
}
