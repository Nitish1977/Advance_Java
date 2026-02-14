package com.tyss;

public class Calculator {
  public int add(int a, int b){
      return a+b;
  }
  public int subtract(int a, int b){
      return a-b;
  }
  public boolean isEven(int a) {
      return a % 2 == 0;
  }
  public int divide(int a, int b) {
      return a / b;
  }
  public  int reminder(int a, int b){
      return a%b;
  }
  public int factorial(int input){
      int result =1;
      for(int i=1; i<=input; i++){
          result *= i;
      }
      return  result;
  }

}
