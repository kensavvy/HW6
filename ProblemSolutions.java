
/******************************************************************
 *
 *   Kendall Savino / COMP 272 002
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

 import java.util.*;
 import java.util.PriorityQueue;
 
 public class ProblemSolutions {
 
     /**
      * Priority Queue (PQ) Game
      *
      * PQ1 Problem Statement:
      * -----------------------
      *
      * You are given an array of integers of boulders where boulders[i] is the
      * weight of the ith boulder.
      *
      * We are playing a game with the boulders. On each turn, we choose the heaviest
      * two boulders and smash them together. Suppose the heaviest two boulders have
      * weights x and y. The result of this smash is:
      *
      *    If x == y, both boulders are destroyed, and
      *    If x != y, the boulder of weight x is destroyed, and the boulder of
      *               weight y has new weight y - x.
      *
      * At the end of the game, there is at most one boulder left.
      *
      * Return the weight of the last remaining boulder. If there are no boulders
      * left, return 0.
      *
      *
      * Example 1:
      *
      * Input: boulders = [2,7,4,1,8,1]
      * Output: 1
      * Explanation:
      * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
      * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
      * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
      * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
      * value of the last stone.
      *
      * Example 2:
      *
      * Input: boulders = [1]
      * Output: 1
      *
      *
      *
      * RECOMMENDED APPROACH
      *
      * Initializing Priority Queue in reverse order, so that it gives
      * max element at the top. Taking top Elements and performing the
      * given operations in the question as long as 2 or more boulders;
      * returning the 0 if queue is empty else return pq.peek().
      */
 
    public static int lastBoulder(int[] boulders) {
 
       
        // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME / SECTION # ABOVE
        
        // Initialize priority queue in reverse since it defaults to min heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

         // Loop through boulders and add them into the heap 
        for (int boulder : boulders) {
            maxHeap.add(boulder);
        }

        // While loop to ensure there's always at least 2 elements to comapre
        while (maxHeap.size() > 1) {

             // Initialize first and second elements of priority queue and remove them
            int heaviest = maxHeap.poll();
            int secondHeaviest = maxHeap.poll();

            // Designate new heaviest boulder as the difference between the two
            if (heaviest != secondHeaviest) {
                maxHeap.add(heaviest - secondHeaviest);
            }
        }
        // If last two elements are equal and destroy each other, return 0
        if (maxHeap.isEmpty()) {
            return 0;
        
        // Return the last element in the queue after loop is exited
        } else {
            return maxHeap.peek();
        }

   } // method lastBoulder
 
 
     /**
      * Method showDuplicates
      *
      * This method identifies duplicate strings in an array list. The list
      * is passed as an ArrayList<String> and the method returns an ArrayList<String>
      * containing only unique strings that appear more than once in the input list.
      * This returned array list is returned in sorted ascending order. Note that
      * this method should consider case (strings are case-sensitive).
      *
      * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
      * the method would return an ArrayList<String> containing: "Dog", "Lion"
      *
      * @param  input an ArrayList<String>
      * @return       an ArrayList<String> containing only unique strings that appear
      *               more than once in the input list. They will be in ascending order.
      */
 
     public static ArrayList<String> showDuplicates(ArrayList<String> input) {
 
        //  YOUR CODE GOES HERE
         
        // Initialize hashMap to count number of appearances
        HashMap<String, Integer> hashMap = new HashMap<>()         ;

        // If string from input exists in map, count it. If not, default to 0
        for (String string : input) {
            hashMap.put(string, hashMap.getOrDefault(string, 0) + 1);
        }

        // Initialize array list to store strings that appear more than once
        ArrayList<String> duplicates = new ArrayList<>();

        // Iterate over the keys of the hashMap
        for (String string : hashMap.keySet()) {

            // If a string has more than one entry, add it into duplicate array
            if (hashMap.get(string) > 1) {
                duplicates.add(string);
            }
        }
        
        // Sort duplicate array alphabetically
        Collections.sort(duplicates);

        return duplicates;  // Make sure result is sorted in ascending order
 
     } // method showDuplicates
 
 
     /**
      * Finds pairs in the input array that add up to k.
      *
      * @param input   Array of integers
      * @param k       The sum to find pairs for
 
      * @return an ArrayList<String> containing a list of strings. The ArrayList
      *        of strings needs to be ordered both within a pair, and
      *        between pairs in ascending order. E.g.,
      *
      *         - Ordering within a pair:
      *            A string is a pair in the format "(a, b)", where a and b are
      *            ordered lowest to highest, e.g., if a pair was the numbers
      *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
      *         - Ordering between pairs:
      *            The ordering of strings of pairs should be sorted in lowest to
      *            highest pairs. E.g., if the following two string pairs within
      *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
      *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
      *
      *         Example output:
      *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
      *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
      *
      *  HINT: Considering using any Java Collection Framework ADT that we have used
      *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
      *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
      *  Java Framework documentation in its use.
      */
 
     public static ArrayList<String> pair(int[] input, int k) {
 
        //  YOUR CODE GOES HERE

        // Initialize empty array list to store results in
        ArrayList<String> result = new ArrayList<>();

        // Initialize empty array list to 
        ArrayList<Integer> processed = new ArrayList<>();
        
        // Loop through each number in input array
        for (int number : input) {
            
            // Initialize integer that adds to k given number
            int targetVal = k - number;

            // Check if target value is already in array list
            if (processed.contains(targetVal)) {

                // Initialize first number in pair by finding smaller of the two
                int firstNumber = Math.min(number, targetVal);
                
                // Initialize second number in pair by finding larger of the two
                int secondNumber = Math.max(number, targetVal);

                // Initialize paired string to contain first and second numbers
                String pairedStrings = "(" + firstNumber + ", " + secondNumber + ")";

                // Avoid duplicate pairs
                if (!result.contains(pairedStrings)) {
                    result.add(pairedStrings);
                }
            }

            // Continue through loop, adding current number to processed numbers list
            processed.add(number);
        }

        // Sort results in ascending order
        Collections.sort(result);

        return result;  // Make sure returned lists is sorted as indicated above

     } // method pair

 }