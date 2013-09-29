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
package com.github.yingzhuo.captcha4j.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.github.yingzhuo.captcha4j.WordRenderer;
import com.github.yingzhuo.captcha4j.config.Configurable;

/**
 * The default implementation of {@link WordRenderer}, creates an image with a
 * word rendered on it.
 */
public class DefaultWordRenderer extends Configurable implements WordRenderer
{
	/**
	 * Renders a word to an image.
	 * 
	 * @param word
	 *            The word to be rendered.
	 * @param width
	 *            The width of the image to be created.
	 * @param height
	 *            The height of the image to be created.
	 * @return The BufferedImage created from the word.
	 */
	public BufferedImage renderWord(String word, int width, int height)
	{
		int fontSize = getConfig().getTextProducerFontSize();
		Font[] fonts = getConfig().getTextProducerFonts(fontSize);
		Color color = getConfig().getTextProducerFontColor();
		int charSpace = getConfig().getTextProducerCharSpace();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(color);

		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2D.setRenderingHints(hints);

		FontRenderContext frc = g2D.getFontRenderContext();
		Random random = new Random();

		int startPosY = (height - fontSize) / 5 + fontSize;

		char[] wordChars = word.toCharArray();
		Font[] chosenFonts = new Font[wordChars.length];
		int [] charWidths = new int[wordChars.length];
		int widthNeeded = 0;
		for (int i = 0; i < wordChars.length; i++)
		{
			chosenFonts[i] = fonts[random.nextInt(fonts.length)];

			char[] charToDraw = new char[]{
				wordChars[i]
			};
			GlyphVector gv = chosenFonts[i].createGlyphVector(frc, charToDraw);
			charWidths[i] = (int)gv.getVisualBounds().getWidth();
			if (i > 0)
			{
				widthNeeded = widthNeeded + 2;
			}
			widthNeeded = widthNeeded + charWidths[i];
		}
		
		int startPosX = (width - widthNeeded) / 2;
		for (int i = 0; i < wordChars.length; i++)
		{
			g2D.setFont(chosenFonts[i]);
			char[] charToDraw = new char[] {
				wordChars[i]
			};
			g2D.drawChars(charToDraw, 0, charToDraw.length, startPosX, startPosY);
			startPosX = startPosX + (int) charWidths[i] + charSpace;
		}

		return image;
	}
}
