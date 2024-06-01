package org.example;

import java.util.Stack;

public class StackProblems {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char keys : s.toCharArray()){
            if(keys == '('){
                stack.push(')');
            }
            else if (keys == '{'){
                stack.push('}');
            }
            else if( keys == '['){
                stack.push(']');
            }
            else if( stack.isEmpty() || stack.pop() != keys){
                return false;
            }
        }
        return true;
    }

}
