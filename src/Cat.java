class Cat extends Animal {
    private static int catCount = 0;
    private boolean satiety;

    public Cat(String name) {
        super(name, 200, 0);
        catCount++;
        satiety = false;
    }

    public static int getCatCount() {
        return catCount;
    }

    public void eatFood(Bowl bowl, int portionSize) {
        if (bowl.getFoodAmount() >= portionSize) {
            bowl.decreaseFood(portionSize);
            satiety = true;
        } else {
            satiety = false;
        }
    }

    public boolean isSatiety() {
        return satiety;
    }
}