public class Park {
class Info {
    String name;
    String time;
    int price;

    public Info(String name, String time, int price) {
        this.name = name;
        this.time = time;
        this.price = price;
        }
    public void print() {
        System.out.println("Название: " + name + "\nВремя работы: " + time + "\nСтоимость: " + price + "\n");
    }
}
public static void main(String[] ars) {
    Park park = new Park();
    Park.Info park1 = park.new Info("Американские горки", "10:00-19:00", 150);
    Park.Info park2 = park.new Info("Карусель", "09:00-18:00", 120);
    Park.Info park3= park.new Info("Катамараны", "09:00-17:00", 100);
    park1.print();
    park2.print();
    park3.print();
}
}
