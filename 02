public class Person2 {
    String ФИО;
    String должность;
    String email;
    String телефон;
    int зарплата;
    int возраст;

    public Person2(String ФИО,
                   String должность,
                   String email,
                   String телефон,
                   int зарплата,
                   int возраст) {
        this.ФИО = ФИО;
        this.должность = должность;
        this.email = email;
        this.телефон = телефон;
        this.зарплата = зарплата;
        this.возраст = возраст;
    }
    public static void main(String[] args) {
        Person2[] person = new Person2[5];
        person[0] = new Person2("Иванов Иван Иванович",
                                "директор",
                                "example@mail.com",
                                "88003000600",
                                200000,
                                51);
        person[1] = new Person2("Петров Петр Петрович",
                "зам. директора",
                "example@mail.com",
                "88003000600",
                150000,
                45);
        person[2] = new Person2("Сидоров Сидор Сидорович",
                "руководитель ОП",
                "example@mail.com",
                "88003000600",
                140000,
                40);
        person[3] = new Person2("Иванова Иванна Ивановна",
                "HR",
                "example@mail.com",
                "88003000600",
                120000,
                32);
        person[4] = new Person2("Петрова Петра Петровна",
                "менеджер",
                "example@mail.com",
                "88003000600",
                100000,
                21);
        printMethod(person);
        }
        public static void printMethod(Person2[] pers){
        for(Person2 i : pers) {
            System.out.println("ФИО: " + i.ФИО +
                    "\nДолжность: " + i.должность +
                    "\nEmail: " + i.email +
                    "\nТелефон: " + i.телефон +
                    "\nЗарплата: " + i.зарплата +
                    "\nВозраст: " + i.возраст + "\n");
        }
        }
    }
