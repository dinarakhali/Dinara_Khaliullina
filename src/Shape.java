public interface Shape {
    String name();

    double perimeter();

    double area();

    String fillColor();

    String borderColor();

    default void printDetails() {
        System.out.println(name());
        System.out.println("Цвет фона: " + fillColor());
        System.out.println("Цвет границ: " + borderColor());
        System.out.println("Периметр: " + perimeter());
        System.out.println("Площадь: " + area() + "\n");
    }
}