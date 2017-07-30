package org.com.cay.service.impl;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.com.cay.service.WaterMarkService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 添加单个文字水印的Service类
 * @author Cay
 *
 */
@Service
public class SingleTextMarkServiceImpl extends WaterMarkService {

	@Override
	public String watermark(CommonsMultipartFile file,
			String uploadPath, String realUploadPath) {
		// TODO Auto-generated method stub

		String logoFileName = "logo_" + file.getOriginalFilename();
		OutputStream os = null;

		try {
			// 获取原始图片的信息
			Image formerImage = ImageIO.read(file.getInputStream());
			int width = formerImage.getWidth(null);
			int height = formerImage.getHeight(null);

			// 创建原始图片大小的缓存
			BufferedImage bufferedImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			// 获得画图工具
			Graphics2D g = bufferedImage.createGraphics();

			// 在内存中画同等大小的图片
			g.drawImage(formerImage, 0, 0, width, height, null);

			//设置文字格式
			g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
			//设置文字颜色
			g.setColor(FONT_COLOR);

			int width1 = FONT_SIZE * getTextLength(MARK_TEXT);
			int height1 = FONT_SIZE;

			int widthDiff = width - width1;
			int heightDiff = height - height1;

			int x = X;
			int y = Y;

			if (x > widthDiff)
				x = widthDiff;

			if (y > heightDiff)
				y = heightDiff;

			// 透明度设置
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					ALPHA));

			//写文字
			g.drawString(MARK_TEXT, x, y + FONT_SIZE);//文字左下角
			g.dispose();

			//创建输出流
			os = new FileOutputStream(realUploadPath + File.separator
					+ logoFileName);
			
			//编码
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			en.encode(bufferedImage);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return uploadPath + "/" + logoFileName;
	}
}
