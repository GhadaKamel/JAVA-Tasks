/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

import java.util.function.BiPredicate;

/**
 *
 * @author GHADA KAMEL
 */
public class StringUtils {
    
    static String betterString(String str1, String str2, BiPredicate<String, String> f) {

        return f.test(str1, str2) ? str1 : str2;

    }
    
    
}
