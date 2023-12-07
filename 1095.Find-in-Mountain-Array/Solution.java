/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int length = mountainArr.length(); 
        int left = 1;
        int right = length - 2;
        int middle = 0; 
        while(left <= right){
            middle = left + (right - left)/2;
            int leftElement = mountainArr.get(middle - 1);
            int middleElement = mountainArr.get(middle);
            int rightElement = mountainArr.get(middle + 1);
            if(leftElement < middleElement && middleElement < rightElement){
                left = middle + 1; 
            }
            else if(leftElement > middleElement && middleElement > rightElement){
                right = middle - 1;
            }
            else{
                break;
            }
        }
        int peak = middle;
        left = 0;
        right = peak;
        int middleElement = Integer.MAX_VALUE;
        while(left <= right){
            middle = left + (right - left)/2;
            middleElement = mountainArr.get(middle);
            if(target < middleElement){
                right = middle - 1;
            }
            else if(target > middleElement){
                left = middle + 1;
            }
            else{
                break;
            }
        }
        if(middleElement == target){
            return middle;
        }
        left = peak;
        right = length - 1;
        while(left <= right){
            middle = left + (right - left)/2;
            middleElement = mountainArr.get(middle);
            if(target > middleElement){
                right = middle - 1;
            }
            else if(target < middleElement){
                left = middle + 1;
            }
            else{
                break;
            }
        }
        if(middleElement == target){
            return middle;
        }
        return -1; 
    }
}
