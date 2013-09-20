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
package com.github.yingzhuo.captcha4j.image;

import java.awt.Font;

import com.github.yingzhuo.captcha4j.textgenerator.RandomTextGenerator;
import com.github.yingzhuo.captcha4j.textgenerator.TextGenerator;

public class DefaultCaptchaService implements CaptchaService {

	@Override
	public TextGenerator getTextGenerator() {
		return new RandomTextGenerator();
	}

	@Override
	public Font getFont() {
		return new Font("Times New Roman", Font.PLAIN, 17);
	}

	@Override
	public int getWidth() {
		return 100;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public boolean isConfounded() {
		return true;
	}

}