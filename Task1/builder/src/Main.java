public class Main {
    public static void main(String[] args) {
        Meal vegMeal = new Meal.MealBuilder()
                .addItem("Burger", "Veggie Burger")
                .addItem("Drink", "Coke")
                .addItem("Fries", "Regular Fries")
                .build();
        vegMeal.showMeal();

        Meal kidsMeal = new Meal.MealBuilder()
                .addItem("Burger", "Mini Burger")
                .addItem("Drink", "Juice Box")
                .addItem("Fries", "Small Fries")
                .addItem("Dessert", "Ice Cream")
                .build();
        kidsMeal.showMeal();

        Meal customMeal = new Meal.MealBuilder()
                .addItem("Burger", "Cheese Burger")
                .addItem("Drink", "Pepsi")
                .build();
        customMeal.showMeal();
    }
}
