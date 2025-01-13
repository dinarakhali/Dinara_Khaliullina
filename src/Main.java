public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Снежок");
        Dog dog1 = new Dog("Рекс");

        cat1.run(8);
        dog1.run(561);
        cat1.swim(1);
        dog1.swim(9);

        System.out.println("\nВсего животных: " + Animal.getAnimalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount() + "\n");

        Cat[] cats = {
                new Cat("Черныш"),
                new Cat("Барсик"),
                new Cat("Персик")};

        Bowl bowl1 = new Bowl(31);

        for (Cat cat : cats) {
            cat.eatFood(bowl1, 15);
        }
        for (Cat cat : cats) {
            String satietyStatus = cat.isSatiety() ? "сыт :)" : "голоден :(";
            System.out.println(cat.getName() + " " + satietyStatus);
        }

        bowl1.addFood(41);

        //2 задание:

        Circle circle = new Circle("Круг.", "красный", "черный", 10);
        Rectangle rectangle = new Rectangle("Прямоугольник.", "синий", "белый", 15, 4);
        Triangle triangle = new Triangle("Треугольник.", "зеленый", "желтый", 16, 10, 11);

        circle.printDetails();
        rectangle.printDetails();
        triangle.printDetails();
    }
}