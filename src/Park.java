public class Park {
class Attraction {
    private String name;
    private String time;
    private int price;

    public Attraction(String name, String time, int price) {
        this.name = name;
        this.time = time;
        this.price = price;
        }
    public String getName() {
        return name;
    }
    public String getTime() {
        return time;
    }
    public int getPrice() {
        return price;
    }
    public void printAttraction() {
        System.out.println("Название: " + name + "\nВремя работы: " + time + "\nСтоимость: " + price + "\n");
    }
}
}