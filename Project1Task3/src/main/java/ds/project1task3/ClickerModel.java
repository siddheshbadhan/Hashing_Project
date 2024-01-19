/*
    Author: sbadhan Siddhesh Badhan
    Last Modified: 2/10/2023
    
    Model file which takes the user answer from the servlet.
    Method addAnswer increments the answer provided by the user and stores it in a hashmap.
 */
package ds.project1task3;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ClickerModel {

    // Map to store the answers
    Map<String, Integer> answer = new TreeMap<>();

    // constructor to initialize the map with the available answers
    public ClickerModel() {
        // putting the key "A" with value 0 in the map
        answer.put("A", 0);
        // putting the key "B" with value 0 in the map
        answer.put("B", 0);
        // putting the key "C" with value 0 in the map
        answer.put("C", 0);
        // putting the key "D" with value 0 in the map
        answer.put("D", 0);
    }

    // method to add an answer to the map
    public Map<String, Integer> addAnswer(String ans) throws UnsupportedEncodingException {
        // getting the count of the answer from the map
        Integer count = answer.get(ans);
        // if the count is null, it means the answer is not present in the map
        if (count == null) {
            // adding the answer to the map with count 1
            answer.put(ans, 1);
        } else {
            // if the answer is already present in the map, incrementing the count by 1
            answer.put(ans, count + 1);
        }
        // returning the updated map
        return answer;
    }
}
