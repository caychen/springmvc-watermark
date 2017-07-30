package org.com.cay.service;

import java.awt.Color;
import java.awt.Font;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public abstract class WaterMarkService {

	public static final String MARK_TEXT = "木石前盟Cay";
	public static final String FONT_NAME = "微软雅黑";
	public static final int FONT_STYLE = Font.BOLD;
	public static final int FONT_SIZE = 120;
	public static final Color FONT_COLOR = Color.BLACK;

	public static final int X = 10;// 坐标X
	public static final int Y = 10;// 坐标Y

	public static final float ALPHA = 0.3F;// 透明度

	public static final String LOGO = "logo.png";
	
	public abstract String watermark(CommonsMultipartFile file,
			String uploadPath, String realUploadPath);
	
	public int getTextLength(String text) {
		int length = text.length();

		for (int i = 0; i < text.length(); ++i) {
			String ch = String.valueOf(text.charAt(i));
			if (ch.getBytes().length > 1) {
				length++;
			}
		}
		length = length % 2 == 0 ? length / 2 : length / 2 + 1;
		return length;
	}
}
