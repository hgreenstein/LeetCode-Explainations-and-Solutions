# [2353. Design a Food Rating System](https://leetcode.com/problems/design-a-food-rating-system/)

---
tags:
  - ObjectCreation
  - customComparator
  - HashMap
  - Heap
  - O_nlogk
  - O_nspace
  - Medium
---
## Problem Statement: 

Modify the rating of a food item listed in the system.
Return the highest-rated food item for a type of cuisine in the system.
Implement the `FoodRatings` class:

`FoodRatings(String[] foods, String[] cuisines, int[] ratings)` Initializes the system. The food items are described by `foods`, `cuisines` and `ratings`, all of which have a length of `n`.
- `foods[i]` is the name of the ith food,
- `cuisines[i]` is the type of cuisine of the ith food, and
- `ratings[i]` is the initial rating of the ith food.

`void changeRating(String food, int newRating)` Changes the rating of the food item with the name `food`.
`String highestRated(String cuisine)` Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
Note that a string `x` is lexicographically smaller than string `y` if `x` comes before `y` in dictionary order, that is, either `x` is a prefix of `y`, or if `i` is the first position such that `x[i]` != `y[i]`, then `x[i]` comes before `y[i]` in alphabetic order.

Example 1:

Input
`["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]`
`[[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]`
Output
`[null, "kimchi", "ramen", null, "sushi", null, "ramen"]`

Explanation
`FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);`
`foodRatings.highestRated("korean"); // return "kimchi"`
                                    `// "kimchi" is the highest rated korean food with a rating of 9.`
`foodRatings.highestRated("japanese"); // return "ramen"`
                                      `// "ramen" is the highest rated japanese food with a rating of 14.`
`foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.`
`foodRatings.highestRated("japanese"); // return "sushi"`
                                      `// "sushi" is the highest rated japanese food with a rating of 16.`
`foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.`
`foodRatings.highestRated("japanese"); // return "ramen"`
                                      `// Both "sushi" and "ramen" have a rating of 16.`
                                      `// However, "ramen" is lexicographically smaller than "sushi".`


## Logic & Algorithms:

### Object Creation and Food Map

The first thing to note in this problem, is that each food has 3 attributes, which is a good sign we might want to create a specific `food` object. This will help such that if we are only given the food name, like in the `changeRating` function, we can easily get it's previous rating and cuisine type. 

Our custom Food object will be a simple constructor that takes in the food name, food cuisine, and rating and stores them in 3 public variables of the same name.

Next, we understand that we are going to have to get the equivalent food object we just specified from only the string of the food's name. This is easily accomplishable by a `foodMap` that will store pairs of `<String, Food>` or in otherwards, sets of food by string and food objects.

### Custom Comparator and Cuisine Map / Heap Implementation

A huge part of this problem is sorting the foods correctly by rating within each cuisine. Luckily, the cuisine will never changed from when it is initialized so that makes it easier for us. We sort based on two important rules, first the rating of the two foods, and then if they are the same then we sort them lexicographically by the lower ranked string.

Now, we could simply store all the foods in a list of strings in a map with key of each cuisine, then sort on demand whenever we need the highest rated with whatever the current rating is. This works, and can be seen in the second coding approach below. However, often times only one or two foods or maybe even no foods might change between calls to `highestRated`. Thus, sorting each time is not very efficient in this use case.

Instead, we store a list of Heaps at each cuisine that each have a custom comparator based on the question conditions. In order to make sure the highest rated food is at the top of the heap, we sort in an order that has the highest rated and lowest lexicographical string at the top of the heap. Instead of removing old ratings from the heap, we simply add a new food with the updated rating and also update the food map with the new food. 

When we go to get the highest rated food in the cuisine, we could have an out of date food rating on top of the heap. Thus, we keep removing from the heap until the food on top has a rating that is up to date, which we can check by making sure the rating of the heap food is the same as the rating of the most up to date food object in the food map. 

## Pseudo Code:

### Class Food()

1. Initialize public variables of `food`, `cuisine`, and `rating`
#### Public Food()
1. Have a basic constructor that accepts the three arguments and sets `this`.`arg` to the `arg`

### Class FoodRatings():
#### FoodRatings()

1. For every food in the list
	1. Make a new food object with the current food's name, cuisine, and rating
	2. Add the food object to the `foodMap` with key of the food's name as a string
	3. Add the food object to the `cuisineMap` at the key of the food's cuisine
		1. If the key is not already in the map, create a new PriorityQueue/Heap with a custom comparator
			1. Take in two food objects `a` and `b`
			2. Rank higher ratings in lower indexes by subtracting `b`'s rating from `a` 
			3. If the ratings are not the same return that diference
			4. Return comparing the string of `a`'s food name to b thus sorting lower lexicographical strings at lower indexes in the list
#### changeRating()

1. Make a new food object with the same food name and cuisine (from the `foodMap`) but with the new rating
2. Update the `foodMap` with the new food item, replacing the value of the food object currently stored at the food name
3. Get the Heap of the cuisine of the updated food and add the new food object to it

#### highestRated()

1. Get the Heap of the cuisine stored in the `cuisineMap`
2. Peek the highested rated food in the heap and store it in `highestFood`
3. While the rating of the highest food in the heap does not match the rating of the food's most up to date object in the `foodMap`
	1. Remove that food object from the heap
4. Return the `highestFood` which is both the highest sorted and has a currently accurate rating
## Time Complexity O($n*log(k)$) - 
Bounded by `FoodRatings()` which does a $log(k)$ sorting operation of adding to the cuisine heap of length `k` for every food in the food array of length `n` 
## Space Complexity O(n) - 
Although many HashMaps and heaps are used, the combined storage of every element will be a constant times the number of food times. For example, the sum of all foods in all heaps in the `cuisineMap` is `n` since every food is only in one heap, and the `foodMap` only has one entry per food of length `n`. 
## Java Code:

### Optimized approach, store map of Heaps for each cuisine

### *Beats 98.87% of LeetCode Submissions*

```java
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
```

### Sort every time `highestedRated` is called ***Too Slow***

```java
class FoodRatings {
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
```
