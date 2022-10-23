package com.masai.service;

import java.util.Random;

public class RandomString {
	
	
		
		public static String getRandomString() {
				
				Random random = new Random();
				int num = random.nextInt();
				return String.format("06%d", num);
			}
}
