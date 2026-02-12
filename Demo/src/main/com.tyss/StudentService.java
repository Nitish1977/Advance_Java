package com.tyss;

public class StudentService {
    public boolean eligibity(int age){
        if(age > 18){
            return true;
        }else{
            return false;
        }
    }
}
