package kz.example.demo.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min=2,max = 100,message = "Имя должно содержать от 2 до 100 символов")
    private String fullName;
    @Min(value = 6,message = "Минимальный возраст 6 лет")
    private int age;
    @NotEmpty(message = "Поле адреса почты не должно быть пустым")
    @Email(message = "Вы ввели не адрес почты")
    private String email;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",message = "Правильный ввод данных в поле адреса:Страна, Город, Индекс(6 цифр)")
    private String address;
    public Person() {
    }
    public Person(int id, String fullName, int age, String email, String address) {
        this.id = id;
        this.fullName=fullName;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName=fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
