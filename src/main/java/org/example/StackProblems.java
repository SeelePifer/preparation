package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if(minStack.isEmpty() || val <= minStack.peek()){
                minStack.push(val);
            }
        }

        public void pop() {
            int removed = stack.pop();
            if(removed == minStack.peek()){
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public int evalRPN(String[] tokens){
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens){
            switch (token){
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                        stack.push(-stack.pop() + stack.pop());
                        break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }
    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        generateParenthesis(n,n,"",result);
        return result;
    }
    private void generateParenthesis(int open, int close, String string, List<String> result){
        if (open == 0 && close == 0){
            result.add(string);
            return;
        }
        if( open > 0){
            generateParenthesis(open -1 , close, string + "(", result);
        }
        if(close > open){
            generateParenthesis(open, close - 1, string + ")", result);
        }
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }
    class Car{
        int position;
        double time;
        public Car(int position, double time){
            this.position = position;
            this.time = time;
        }
    }
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        }
        Arrays.sort(cars, (a,b) -> b.position - a.position);
        int response = 0;
        double cur = 0;
        for (Car car: cars){
            if(car.time > cur){
                response++;
                cur = car.time;
            }
        }
        return response;
    }

}
