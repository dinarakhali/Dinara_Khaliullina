public class Circle implements Shape {
    private String name;
    private String fillColor;
    private String borderColor;
    private double radius;

    public Circle(String name, String fillColor, String borderColor, double radius) {
        this.name = name;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.radius = radius;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double perimeter() {
        return Math.PI * radius * 2;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String fillColor() {
        return fillColor;
    }

    @Override
    public String borderColor() {
        return borderColor;
    }

    @Override
    public void printDetails() {
        Shape.super.printDetails();
    }
}