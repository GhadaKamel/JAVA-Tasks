/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

/**
 *
 * @author GHADA KAMEL
 */
public class Letter {
  
    public static  boolean isLetter(String str) {
    char[] chars = str.toCharArray();

    for (char c : chars) {
        if(!Character.isLetter(c)) {
            return false;
        }
    }

    return true;
}
   
   static boolean isLetter2(String str1) {
        
        return str1.chars().allMatch((a) -> Character.isLetter(a));
        
    }
}
