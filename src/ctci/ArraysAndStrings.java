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

/*

    Key Concepts learnt :

    i) Bit masking: Use bit representation for position based problems. Can be helpful in detecting duplicates.
    ii) Finding a pair of numbers that add upto a given number 
        O(n) solution:
        - Use a hashtable to store the values encountered so far.
        - Check whether for each value a complementary value is available in the hash
        - If so, then you have got the pair
    iii) Sorting a string : 
        - Convert to char array
        - Use Array.sort ( O(nlogn) )
        - Convert back to string
    iv) Characterset matters when you are looking at strings
        - ASCII has only 128 characters
    v) Start from back of a string if you want to do inplace operations
    vi) Check if only a single bit is set :
        - Calculate Number -1
        - Peform AND on number and (number -1)
        - This is useful when trying to compare two values bit vector wise

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
        System.out.println("P2 : " + P2_checkPermutationHashMap("balaji", "iaabal"));
        //System.out.println("P2 Sorting :" + P2_checkPermutationSorting("balaji", "kilaba"));
        //P3
        System.out.println("P3 : "+P3_urlifyBack(new char[]{'a',' ','b',' ','c',' ',' ',' ',' ',' ',' ',' '},6));
        //System.out.println("P3 : "+P3_urlifyBack(new char[]{'a',' ','b',' ',' '},3));
        //P4
        System.out.println("P4 : "+P4_isPalindromePermutation("tactcoa"));
        //P5
        System.out.println("P5 : "+P5_OneWay("pale", "ple"));
        System.out.println("P5 : "+P5_OneWay("ple", "pale"));
        System.out.println("P5 : "+P5_OneWay("pale", "bale"));
        System.out.println("P5 : "+P5_OneWay("pale", "ble"));
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
            } else {
                return false;
            }

        }

        //
        return hm.isEmpty();
    }

    public static boolean P2_checkPermutationSorting(String str1, String str2) {
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
    public static String P3_urlifyBack(char[] input, int length) {

        //Count spaces
        // O(n)
        int space_count = 0;
        for (int i = 0; i < length; i++) {
            if (input[i] == ' ') {
                space_count++;
            }
        }
        // Start from back and insert characters
        // O(n)
        int newlength = input.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            //System.out.println("Input character is :"+input[i]);
            if (input[i] == ' ') {
                input[newlength] = '0';
                input[--newlength] = '2';
                input[--newlength] = '%';
                newlength--;
            } else {
              //  System.out.println("Not space ");
                input[newlength--] = input[i];
            }

        }

        return new String(input);

    }

    /*
    
        Problem 4
    
    */
    
    public static boolean P4_isPalindromePermutation(String input) {
     
        // Count of all characters must be multiple of 2
        // If there is a character with a count of 1, there should only be 1 such character
        input = input.toLowerCase();
        HashMap hm = new HashMap();
        for(int i=0;i<input.length();i++) {
            if(!((input.charAt(i) >=65 && input.charAt(i)<=90) || (input.charAt(i)>=97 && input.charAt(i)<=122)))
                continue;
            if(hm.containsKey(input.charAt(i))) {
                hm.remove(input.charAt(i));
            } else
                hm.put(input.charAt(i),1);
        }
        
        if(hm.isEmpty() || hm.size()==1) {
           return true;
        }
        return false;
    }
    
    /*
    
    Problem 5
    
    */
    
    public static boolean P5_OneWay(String str1,String str2) {
        
        if(str1.length()==str2.length()) {
            // Check for replacement
            boolean replacement_found = false;
            char[] val1 = str1.toCharArray();
            char[] val2 = str2.toCharArray();
            
            for(int i=0;i<val1.length;i++)
            {
             if(val1[i]!=val2[i]){
                 if(replacement_found) {
                     return false;
                 }
                 else 
                     replacement_found = true;
             }   
            }
            
            return true;
        } else if(str1.length()==str2.length()+1) {
            // Check for deletion
            boolean replacement_found = false;
            char[] val1 = str1.toCharArray();
            char[] val2 = str2.toCharArray();
            
            for(int i=0,j=0;i<val1.length;i++)
            {
             if(val1[i]!=val2[j]){
                 if(replacement_found)
                     return false;
                 else
                 replacement_found = true;
                 
             } else
                 j++;
            }
            return true;
            
        } else if(str1.length()==str2.length()-1) {
            //Check for insertion
            boolean replacement_found = false;
            char[] val1 = str2.toCharArray();
            char[] val2 = str1.toCharArray();
            
            for(int i=0,j=0;i<val1.length;i++)
            {
             if(val1[i]!=val2[j]){
                 if(replacement_found)
                     return false;
                 else
                 replacement_found = true;
                 
             } else
                 j++;
            }
            return true;
            

        }       
        
        
        return false;
    }
    
    
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

    
    /*
    
        Problem 7
    
    */


    /*
    
        Problem 8
    */

    public static void P8_ZeroMatrix(int[][] input) {
        
        
        
        
//        for(int i=0;i<input[0].length;i++) {
//            
//            for(int j=0;j<input[i].length;j++) {
//                if()
//            }
//            
//        }
        
        
    }
    
    
    
    /*
    
        Problem 9
    */

    public static boolean P9_isSubstring(String str1,String str2) {
        
        if(!(str1.length()>0 && str1.length()==str2.length())) 
            return false;
        String newString = str1+str1;
        //return sSubstring(newsString,str2);
        return true;
        
    }
    
}


