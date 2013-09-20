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
package com.github.yingzhuo.captcha4j.filter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.yingzhuo.captcha4j.image.CaptchaService;
import com.github.yingzhuo.captcha4j.image.DefaultCaptchaService;

/**
 * The filter to create the image of captcha.
 * 
 * @see org.springframework.web.filter.DelegatingFilterProxy
 * @author YING Zhuo
 *
 */
public class CaptchaFilter implements Filter {
	
    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/
	public static final String CAPTCHA_SESSION_ATTR_NAME = CaptchaFilter.class.getName() + "#CAPTCHA_SESSION_ATTR_NAME";

	/*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
	private CaptchaService captchaService = new DefaultCaptchaService();
	
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

	/*--------------------------------------------
    |         C L A S S - M E T H O D S         |
    ============================================*/
	
	public static boolean validate(String captcha, HttpSession session, boolean ignoreCase) {
		String c = (String) session.getAttribute(CAPTCHA_SESSION_ATTR_NAME);
		if (c == null) return false;
		if (ignoreCase) {
			return c.equalsIgnoreCase(captcha);
		} else {
			return c.equals(captcha);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing.
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		int width  = captchaService.getWidth();
		int height = captchaService.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandomColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(captchaService.getFont());

		g.setColor(getRandomColor(160, 200));

		// 干扰线
		if (captchaService.isConfounded()) {
			for (int i = 0; i < 155; i++) {
				int x = random.nextInt(width - 1);
				int y = random.nextInt(height - 1);
				int xl = random.nextInt(6) + 1;
				int yl = random.nextInt(12) + 1;
				g.drawLine(x, y, x + xl, y + yl);
			}
	
			for (int i = 0; i < 70; i++) {
				int x = random.nextInt(width - 1);
				int y = random.nextInt(height - 1);
				int xl = random.nextInt(12) + 1;
				int yl = random.nextInt(6) + 1;
				g.drawLine(x, y, x - xl, y - yl);
			}
		}

		String captcha = captchaService.getTextGenerator().generate();
		for (int i = 0; i < captcha.length(); i++) {
			char ctmp = (char) captcha.charAt(i);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(String.valueOf(ctmp), 15 * i + 10, 16);
		}

		session.setAttribute(CAPTCHA_SESSION_ATTR_NAME, captcha);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
	private Color getRandomColor(int fc, int bc) {  
        Random random = new Random();  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }

	@Override
	public void destroy() {
		// do nothing.
	}
	
    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/

	public CaptchaService getCaptchaService() {
		return captchaService;
	}

	public void setCaptchaService(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

}
