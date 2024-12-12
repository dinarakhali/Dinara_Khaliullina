public class Person {
    private String fullName;
    private String post;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;
    public Person() {
        fullName = "Иванов Иван Иванович";
        post = "Руководитель ОП";
        email = "example@mail.com";
        phoneNumber = "+7 999 999 99 99";
        salary = 150000;
        age = 37;
    }
    public String getFullName() {
        return fullName;
    }
    public String getPost() {
        return post;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getSalary() {
        return salary;
    }
    public int getAge() {
        return age;
    }
    public void printPerson() {
        System.out.println("ФИО: " + fullName +
                "\nДолжность: " + post +
                "\nEmail: " + email +
                "\nТелефон: " + phoneNumber +
                "\nЗарплата: " + salary +
                "\nВозраст: " + age);
    }
}