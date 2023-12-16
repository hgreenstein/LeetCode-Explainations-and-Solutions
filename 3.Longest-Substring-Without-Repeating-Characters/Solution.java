class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> chars = new HashSet<>();
        int greatest = 0;
        int left = 0;
        int right = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(!chars.contains(c)){
                chars.add(c);
                greatest = Math.max(greatest, (right - left + 1));
                right++;
            }
            else{
                char leftChar = s.charAt(left);
                chars.remove(leftChar);
                left++; 
            }
        }
        return greatest; 
    }
}
