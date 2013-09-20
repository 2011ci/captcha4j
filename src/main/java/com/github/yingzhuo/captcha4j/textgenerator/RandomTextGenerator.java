/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.yingzhuo.captcha4j.textgenerator;



/**
 * 
 * 
 * @author YING Zhuo
 *
 */
public class RandomTextGenerator implements TextGenerator {
	
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

	private int      length = 6;
	private boolean upperCase  = true;
	private TextStyle textStyle = TextStyle.ALPHANUMERIC;
	
    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
	
	public RandomTextGenerator() {
		super();
	}
	
	public RandomTextGenerator(int length, boolean upperCase, TextStyle textStyle) {
		super();
		this.length = length;
		this.upperCase = upperCase;
		this.textStyle = textStyle;
	}
	
    /*--------------------------------------------
    |               O B J E C T                 |
    ============================================*/
	
	@Override
	public String toString() {
		return super.toString();
	}
	
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

	@Override
	public String generate() {
		String result = null;
		
		switch (textStyle) {
		case ALPHABETIC:
			result = RandomStringUtils.randomAlphabetic(length);
			break;
			
		case ALPHANUMERIC:
			result = RandomStringUtils.randomAlphanumeric(length);
			break;
		
		case NUMERIC:
			result = RandomStringUtils.randomNumeric(length);
			break;
		}

		return this.upperCase ? result.toUpperCase() : result.toLowerCase();
	}
	
    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isUpperCase() {
		return upperCase;
	}

	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

}
