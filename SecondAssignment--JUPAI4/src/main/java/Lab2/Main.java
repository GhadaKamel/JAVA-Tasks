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
public class Main {
  public static void main(String[] args) {
      
      
        String s1 = "Longer";
        String s2 = "Shorter";
        
        String longer_str = StringUtils.betterString(s1, s2, (st1,st2) -> st1.length() > st2.length());
        System.out.println(longer_str);
        
        String result2 = StringUtils.betterString(s1, s2, (st1,st2) -> (int)st1.charAt(0) > (int)st2.charAt(0));
        System.out.println(result2);
       
        
        String first = StringUtils.betterString("first", "second", (st1, st2) -> true);
        System.out.println(first);
        
        
        
        System.out.println(Letter.isLetter(s1));
        System.out.println(Letter.isLetter2(s2));
        
        String ss1= "123";
        String ss2= "ghada 12";
        System.out.println(Letter.isLetter(ss1));
        System.out.println(Letter.isLetter2(ss2));
    

   }  
}
