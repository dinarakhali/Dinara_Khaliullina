public class PersonNew {
    private String fullName;
    private String post;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public PersonNew(String fullName,
                     String post,
                     String email,
                     String phoneNumber,
                     int salary,
                     int age) {
        this.fullName = fullName;
        this.post = post;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
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
    public void printPersonNew(){
        System.out.println("ФИО: " + fullName +
                    "\nДолжность: " + post +
                    "\nEmail: " + email +
                    "\nТелефон: " + phoneNumber +
                    "\nЗарплата: " + salary +
                    "\nВозраст: " + age + "\n");
        }
    }