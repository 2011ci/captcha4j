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
package com.github.yingzhuo.captcha4j.config;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;

import com.github.yingzhuo.captcha4j.BackgroundProducer;
import com.github.yingzhuo.captcha4j.Captcha4jConstants;
import com.github.yingzhuo.captcha4j.GimpyEngine;
import com.github.yingzhuo.captcha4j.NoiseProducer;
import com.github.yingzhuo.captcha4j.Producer;
import com.github.yingzhuo.captcha4j.TextProducer;
import com.github.yingzhuo.captcha4j.WordRenderer;
import com.github.yingzhuo.captcha4j.component.DefaultBackground;
import com.github.yingzhuo.captcha4j.component.DefaultNoise;
import com.github.yingzhuo.captcha4j.component.DefaultProducer;
import com.github.yingzhuo.captcha4j.component.DefaultTextProducer;
import com.github.yingzhuo.captcha4j.component.DefaultWordRenderer;
import com.github.yingzhuo.captcha4j.component.WaterRipple;

/**
 * {@link Config} retrieves configuration values from properties file and
 * specifies default values when no value is specified.
 */
public class Config {

	private Properties properties;

	private ConfigHelper helper;

	public Config(Properties properties) {
		this.properties = properties;
		this.helper = new ConfigHelper();
	}

	public boolean isBorderDrawn() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_BORDER;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getBoolean(paramName, paramValue, true);
	}

	public Color getBorderColor() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_BORDER_COLOR;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getColor(paramName, paramValue, Color.BLACK);
	}

	public int getBorderThickness() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_BORDER_THICKNESS;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getPositiveInt(paramName, paramValue, 1);
	}

	public Producer getProducerImpl() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_PRODUCER_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		Producer producer = (Producer) this.helper.getClassInstance(paramName, paramValue, new DefaultProducer(), this);
		return producer;
	}

	public TextProducer getTextProducerImpl() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_TEXTPRODUCER_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		TextProducer textProducer = (TextProducer) this.helper.getClassInstance(paramName, paramValue, new DefaultTextProducer(), this);
		return textProducer;
	}

	public char[] getTextProducerCharString() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_TEXTPRODUCER_CHAR_STRING;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getChars(paramName, paramValue, "abcde2345678gfynmnpwx".toCharArray());
	}

	public int getTextProducerCharLength() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_TEXTPRODUCER_CHAR_LENGTH;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getPositiveInt(paramName, paramValue, 5);
	}

	public Font[] getTextProducerFonts(int fontSize) {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_TEXTPRODUCER_FONT_NAMES;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getFonts(paramName, paramValue, fontSize, new Font[] { new Font("Arial", Font.BOLD, fontSize), new Font("Courier", Font.BOLD, fontSize) });
	}

	public int getTextProducerFontSize() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_TEXTPRODUCER_FONT_SIZE;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getPositiveInt(paramName, paramValue, 40);
	}

	public Color getTextProducerFontColor() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_TEXTPRODUCER_FONT_COLOR;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getColor(paramName, paramValue, Color.BLACK);
	}

	public int getTextProducerCharSpace() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_TEXTPRODUCER_CHAR_SPACE;
		String paramValue = properties.getProperty(paramName);
		return this.helper.getPositiveInt(paramName, paramValue, 2);
	}

	public NoiseProducer getNoiseImpl() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_NOISE_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		NoiseProducer noiseProducer = (NoiseProducer) this.helper.getClassInstance(paramName, paramValue, new DefaultNoise(), this);
		return noiseProducer;
	}

	public Color getNoiseColor() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_NOISE_COLOR;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getColor(paramName, paramValue, Color.BLACK);
	}

	public GimpyEngine getObscurificatorImpl() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_OBSCURIFICATOR_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		GimpyEngine gimpyEngine = (GimpyEngine) this.helper.getClassInstance(paramName, paramValue, new WaterRipple(), this);
		return gimpyEngine;
	}

	public WordRenderer getWordRendererImpl() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_WORDRENDERER_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		WordRenderer wordRenderer = (WordRenderer) this.helper.getClassInstance(paramName, paramValue, new DefaultWordRenderer(), this);
		return wordRenderer;
	}

	public BackgroundProducer getBackgroundImpl() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_BACKGROUND_IMPL;
		String paramValue = this.properties.getProperty(paramName);
		BackgroundProducer backgroundProducer = (BackgroundProducer) this.helper.getClassInstance(paramName, paramValue, new DefaultBackground(), this);
		return backgroundProducer;
	}

	public Color getBackgroundColorFrom() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_BACKGROUND_CLR_FROM;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getColor(paramName, paramValue, Color.LIGHT_GRAY);
	}

	public Color getBackgroundColorTo() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_BACKGROUND_CLR_TO;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getColor(paramName, paramValue, Color.WHITE);
	}

	public int getWidth() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_IMAGE_WIDTH;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getPositiveInt(paramName, paramValue, 200);
	}

	public int getHeight() {
		String paramName = Captcha4jConstants.CAPTCHA_4_J_IMAGE_HEIGHT;
		String paramValue = this.properties.getProperty(paramName);
		return this.helper.getPositiveInt(paramName, paramValue, 50);
	}

	public String getSessionKey() {
		return this.properties.getProperty(
				Captcha4jConstants.CAPTCHA_4_J_SESSION_CONFIG_KEY,
				Captcha4jConstants.CAPTCHA_4_J_SESSION_KEY);
	}

	public String getSessionDate() {
		return this.properties.getProperty(
				Captcha4jConstants.CAPTCHA_4_J_SESSION_CONFIG_DATE,
				Captcha4jConstants.CAPTCHA_4_J_SESSION_DATE);
	}

	public Properties getProperties() {
		return this.properties;
	}

}
