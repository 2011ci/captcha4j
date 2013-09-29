(一) 这是什么？
本项目提供一个可配置的servlet生成图形化的人机验证码


(二) 基础配置
WEB-INF/web.xml

<web-app>
	<servlet>
		<servlet-name>test</servlet-name>
		<servlet-class>com.github.yingzhuo.captcha4j.servlet.Captcha4jServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>test</servlet-name>
		<url-pattern>/images/captcha.jpeg</url-pattern>
	</servlet-mapping>
</web-app>

(三) 更多配置

如：
<web-app>
	<servlet>
		<servlet-name>test</servlet-name>
		<servlet-class>com.github.yingzhuo.captcha4j.servlet.Captcha4jServlet</servlet-class>
		<init-param>
			<param-name>captcha4j.session.key</param-name>
			<param-value>CAPTCHA_KEY</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>test</servlet-name>
		<url-pattern>/hello.jpeg</url-pattern>
	</servlet-mapping>
</web-app>

参考 com.github.yingzhuo.captcha4j.Captcha4jConstants