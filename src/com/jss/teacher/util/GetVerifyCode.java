package com.jss.teacher.util;

import java.util.Random;

public class GetVerifyCode {
    public String verifyCode(){
    	Random a = new Random();
    	String code = "";
    	for(int i=0;i<4;i++){
        	int n = a.nextInt(26)+65;
    		code = code + (char)n;
    	}
    	return code;
    }
}
