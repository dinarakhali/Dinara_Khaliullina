public class Triangle implements Shape {
    private String name;
    private String fillColor;
    private String borderColor;
    private double lengthA;
    private double lengthB;
    private double lengthC;

    public Triangle(String name, String fillColor, String borderColor, double lengthA, double lengthB, double lengthC) {
        this.name = name;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.lengthA = lengthA;
        this.lengthB = lengthB;
        this.lengthC = lengthC;

    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double perimeter() {
        return lengthA + lengthB + lengthC;
    }

    @Override
    public double area() {
        double p = (lengthA + lengthB + lengthC) / 2;
        return Math.sqrt(p * (p - lengthA) * (p - lengthB) * (p - lengthC));
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