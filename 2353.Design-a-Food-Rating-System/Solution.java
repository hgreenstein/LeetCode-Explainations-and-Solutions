
// ### Optimized approach, store map of Heaps for each cuisine

// ### *Beats 98.87% of LeetCode Submissions*

class Food{
    String food;
    String cuisine;
    int rating;
    public Food(String food,String cuisine,int rating){
        this.food=food;
        this.cuisine=cuisine;
        this.rating=rating;
    }
}
class FoodRatings {
    HashMap<String, Food> foodMap = new HashMap<>();
    HashMap<String, PriorityQueue<Food>> cuisineMap = new HashMap<>();
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for(int i = 0; i < foods.length; i++){
            Food currentFood = new Food(foods[i], cuisines[i], ratings[i]);
            foodMap.put(foods[i], currentFood);
            cuisineMap.computeIfAbsent(cuisines[i], key -> new PriorityQueue<Food>((a, b) -> {
            int rating = b.rating - a.rating;
            if(rating != 0) return rating;
            return a.food.compareTo(b.food);
            })).offer(currentFood);
        }
    }   
    
    public void changeRating(String food, int newRating) {
        Food newRatedFood = new Food(food, foodMap.get(food).cuisine, newRating);
        foodMap.put(food, newRatedFood);
        cuisineMap.get(newRatedFood.cuisine).offer(newRatedFood);
    }
    
    public String highestRated(String cuisine) {
        PriorityQueue<Food> cuisineQueue = cuisineMap.get(cuisine);
        Food highestFood = cuisineQueue.peek();
        while(!(highestFood.rating == foodMap.get(highestFood.food).rating)){
            cuisineQueue.poll(); //This food's rating is not current
            highestFood = cuisineQueue.peek(); 
        }
        return highestFood.food;  
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */

// ### Sort every time `highestedRated` is called ***Too Slow***

class FoodRatings2 {
    HashMap<String, Integer> ratingMap = new HashMap<>();
    HashMap<String, List<String>> cuisineMap = new HashMap<>();
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for(int i = 0; i < foods.length; i++){
            ratingMap.put(foods[i], ratings[i]);
            cuisineMap.computeIfAbsent(cuisines[i], key -> new ArrayList<String>()).add(foods[i]);
        }
    }   
    
    public void changeRating(String food, int newRating) {
        ratingMap.put(food, newRating);
    }
    
    public String highestRated(String cuisine) {
        List<String> cuisineFoods = cuisineMap.get(cuisine);
        Collections.sort(cuisineFoods, (a, b) -> {
            int ratingComparison = Integer.compare(ratingMap.get(a), ratingMap.get(b));
            if(ratingComparison != 0) return ratingComparison;
            return -1 * a.compareTo(b); //Reverse lexographical so the smaller is at the end
        });
        return cuisineFoods.get(cuisineFoods.size() - 1); 
    }
}
