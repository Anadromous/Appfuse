package com.chapman.util;

import java.math.BigDecimal;

public class TestUtil {

	public static void main(String[] args) {
		CurrencyConverter c = new CurrencyConverter();
		 System.out.println(c.convert(BigDecimal.class, "234.34"));
		//testCurrencyConverter();
	}
	
/*	private static void testCurrencyConverter(){
		 CurrencyConverter c = new CurrencyConverter();
		 System.out.println(c.convert(BigDecimal.class, "234.34"));
	}*/

}
