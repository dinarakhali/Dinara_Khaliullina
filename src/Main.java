public class Main {
    public static void main(String[] args) {
        Park attraction = new Park();
        Park.Attraction attraction1 = attraction.new Attraction("Американские горки", "10:00-19:00", 150);
        Park.Attraction attraction2 = attraction.new Attraction("Карусель", "09:00-18:00", 120);
        Park.Attraction attraction3= attraction.new Attraction("Катамараны", "09:00-17:00", 100);
        attraction1.printAttraction();
        attraction2.printAttraction();
        attraction3.printAttraction();

        Person person1 = new Person();
        person1.printPerson();

        PersonNew[] person = new PersonNew[5];
        person[0] = new PersonNew("Иванов Иван Иванович",
                "директор",
                "example@mail.com",
                "88003000600",
                200000,
                51);
        person[1] = new PersonNew("Петров Петр Петрович",
                "зам. директора",
                "example@mail.com",
                "88003000600",
                150000,
                45);
        person[2] = new PersonNew("Сидоров Сидор Сидорович",
                "руководитель ОП",
                "example@mail.com",
                "88003000600",
                140000,
                40);
        person[3] = new PersonNew("Иванова Иванна Ивановна",
                "HR",
                "example@mail.com",
                "88003000600",
                120000,
                32);
        person[4] = new PersonNew("Петрова Петра Петровна",
                "менеджер",
                "example@mail.com",
                "88003000600",
                100000,
                21);
        for(PersonNew i : person) {
            i.printPersonNew();
        }
    }
}