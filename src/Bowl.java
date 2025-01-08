public class Bowl {
    private int foodAmount = 0;
    public Bowl(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("\nТеперь в миске вот столько корма - " + foodAmount);
    }
    public void decreaseFood(int decrease) {
        if(foodAmount >= decrease) {
            foodAmount -= decrease;
                   }
        else{
            // Недостаточно еды, ничего не делаем
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}
