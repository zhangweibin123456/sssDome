package com.controller.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.commons.AppStateStore;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
@Controller
@RequestMapping("/driverControler")
public class RandomCodeControllerImpl {

	@Resource(name = "appStateStore")
	private AppStateStore appStateStore;

	@RequestMapping("/acquirerandomCode")
	public void randomCode(HttpServletResponse response) throws IOException {
		String radomString = "";
		StringBuffer sb = new StringBuffer(radomString);
		// 表示此响应是画图片
		response.setContentType("image/jpeg");
		// 图片对象
		BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		// 画笔对象
		Graphics gra = bi.getGraphics();
		Random ran = new Random();
		// fill the background
		gra.setColor(Color.WHITE);
		gra.fillRect(0, 0, 100, 30);
		// the String of code
		String vliCode = "";
		// set font
		gra.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		// start to draw the letter
		while (vliCode.length() < 4) {
			gra.setColor(new Color(ran.nextInt(255) + 1, ran.nextInt(255) + 1, ran.nextInt(255) + 1));
			String tmp = "";
			// the style of the code
			switch (ran.nextInt(3)) {
			case 0:
				tmp = (char) (ran.nextInt(26) + 65) + "";
				break;
			case 1:
				tmp = (char) (ran.nextInt(26) + 97) + "";
				break;
			default:
				tmp = ran.nextInt(10) + "";
				break;
			}
			// start to draw
			gra.drawString(tmp, 10 + vliCode.length() * 20, 20);
			// remeber to add the chapter
			vliCode += tmp;
			radomString = sb.append(tmp).toString();
		}
		// draw the interference line
		for (int i = 0; i < (ran.nextInt(15) + 5); i++) {
			gra.setColor(new Color(ran.nextInt(255) + 1, ran.nextInt(255) + 1, ran.nextInt(255) + 1));
			gra.drawLine(ran.nextInt(100), ran.nextInt(30), ran.nextInt(100), ran.nextInt(30));
		}
		// 获取输出流对象
		// 获取输出流对象
		ServletOutputStream out = response.getOutputStream();
		// 创建一个和指定输出流关联的JPEGImageEncoder对象
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(bi);
		// 给浏览器绘制图片
		out.flush();
		// 把验证码字符串放进会话中
		appStateStore.setAttribute("randomCode", radomString);
	}

}
