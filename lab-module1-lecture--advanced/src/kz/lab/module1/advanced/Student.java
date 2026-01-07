package kz.lab.module1.advanced;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

public final class Student {
    private final int id;
    private final String name;
    private final String phone;

    public static int foo = 10;

    public Student(int id, String name, String phone) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student[")
                .append("id=").append(id)
                .append(", name=").append(name)
                .append(", phone=").append(phone)
                .append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(name, student.name) &&
                Objects.equals(phone, student.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    // просто было интересно посмотреть на рефлексию в java
    public String toStringReflection() {
        Field[] fields = this.getClass().getDeclaredFields();
        String output = String.format("%s[", this.getClass().getSimpleName());

        for (int i = 0; i < fields.length; i++) {
            int m = fields[i].getModifiers();
            if (Modifier.isStatic(m)) {
                continue;
            }

            if (i > 0)
                output += ", ";

            String name = fields[i].getName();
            Object val = null;
            fields[i].setAccessible(true);

            try {
                val = fields[i].get(this);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (val != null) {
                output += String.format("%s=%s", name, val.toString());
            } else {
                output += String.format("%s=%s", name, "null");
            }
        }

        return output + "]";
    }
}
