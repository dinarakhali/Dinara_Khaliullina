public class Bowl {
    private int foodAmount = 0;

    public Bowl(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("\nТеперь в миске вот столько корма - " + foodAmount + "\n");
    }

    public void decreaseFood(int decrease) {
        if (foodAmount >= decrease) {
            foodAmount -= decrease;
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}
