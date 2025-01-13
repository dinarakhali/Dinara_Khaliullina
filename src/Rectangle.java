public class Rectangle implements Shape {
    private String name;
    private String fillColor;
    private String borderColor;
    private double width;
    private double length;

    public Rectangle(String name, String fillColor, String borderColor, double width, double length) {
        this.name = name;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.width = width;
        this.length = length;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double perimeter() {
        return width * 2 + length * 2;
    }

    @Override
    public double area() {
        return width * length;
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