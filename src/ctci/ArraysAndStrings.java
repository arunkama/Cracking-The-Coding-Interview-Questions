/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctci;

import java.util.Arrays;
import java.util.HashMap;
import javafx.scene.input.ClipboardContent;

/**
 *
 * @author Arun
 */
public class ArraysAndStrings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //P1
        System.out.println("P1 : " + P1_isUniqueBit("aba"));

        //P2
        
        System.out.println("P2 : "+P2_checkPermutationHashMap("balaji","iaabal"));
        System.out.println("P2 Sorting :"+P2_checkPermutationSorting("balaji", "kilaba"));  
        //P6
    }
     /*
    
    Problem 1
    
    
    */
    // With Hashtable
    public boolean P1_isUnique(String input) {

        HashMap hm = new HashMap();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (hm.containsKey(c)) {
                return false;
            }
            hm.put(c, 1);

        }

        return true;

    }
    // Using Bitwise
    public static boolean P1_isUniqueBit(String input) {
        int checker = 0;
        for (int i = 0; i < input.length(); i++) {
            int val = input.charAt(i) - 'a';

            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= 1 << val;

        }
        return true;
    }
    /*
    
    * Problem 2
    
    */
                                                                                                                                                                                                                    
    public static boolean P2_checkPermutationHashMap(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        //HashMap Method
        HashMap hm = new HashMap();

        for (int i = 0; i < str1.length(); i++) {
            int count = 1;
            if (hm.containsKey(str1.charAt(i))) {
                count = (Integer) hm.get(str1.charAt(i)) + 1;
            }
            hm.put(str1.charAt(i), count);
        }

        for (int i = 0; i < str2.length(); i++) {
            int count;
            if (hm.containsKey(str2.charAt(i))) {
                count = (Integer) hm.get(str2.charAt(i)) - 1;
                if (count == 0) {
                    hm.remove(str2.charAt(i));
                } else {
                    hm.put(str2.charAt(i), count);
                }
            } else
                return false;
            
        }

        //
        return hm.isEmpty();
    }
    
    public static boolean P2_checkPermutationSorting(String str1,String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
              
        return sortString(str1).equals(sortString(str2));
    }
           
    public static String sortString(String input) {
        
        char[] in = input.toCharArray();
        Arrays.sort(in);
        return new String(in);
        
        
    }
    
    /*
    
    Problem 3
    
    */
    
    
    /*
       Problem 6
    
    */
    
    public static String P6_compressString(String input) {

        StringBuilder output = new StringBuilder();
        char c;
        char present;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {

            count++;
            c = input.charAt(i);
            if (i + 1 == input.length() || input.charAt(i + 1) != c) {
                output.append(c).append(count);
                count = 0;
            }
        }

        if (output.length() == input.length()) {
            return input;
        } else {
            return output.toString();
        }

    }

    
   
}
