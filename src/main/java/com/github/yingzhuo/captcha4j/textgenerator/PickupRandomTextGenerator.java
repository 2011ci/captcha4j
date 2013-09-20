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

import java.util.List;
import java.util.Random;

/**
 * 
 * 
 * @author YING Zhuo
 *
 */
public class PickupRandomTextGenerator implements TextGenerator {

    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/
	
	private static final Random RANDOM = new Random();
	
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

	private List<String> pickupList;
	private boolean     trim			= false;
	
    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
	
	public PickupRandomTextGenerator() {
		super();
	}
	
	public PickupRandomTextGenerator(List<String> pickupList, boolean trim) {
		super();
		this.pickupList = pickupList;
		this.trim = trim;
	}
	
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/
	
	@Override
	public String generate() {
		assert (pickupList != null);
		assert (! pickupList.isEmpty());
		
		int index = RANDOM.nextInt(pickupList.size());
		
		String result = pickupList.get(index);
		if (trim) {
			result = result.trim();
		}
		return result;
	}
	
    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/

	public List<String> getPickupList() {
		return pickupList;
	}

	public void setPickupList(List<String> pickupList) {
		this.pickupList = pickupList;
	}
	
}
