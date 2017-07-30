package org.com.cay.action;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.com.cay.service.WaterMarkService;
import org.com.cay.service.impl.UploadService;
import org.com.cay.watermark.PicInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@Scope("prototype")
public class SingleWaterMarkAction{	
	@Resource(name="uploadService")
	private UploadService uploadService;
	@Resource(name="singleImageMarkService")
	private WaterMarkService waterMarkService;

	/**
	 * 
	 */
	@RequestMapping(value="/watermark_single.action", method=RequestMethod.POST)
	public String watermark(@RequestParam("image")CommonsMultipartFile file,HttpSession session, Model model) throws Exception {
		// 上传的真实路径
		String uploadPath = "/images";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);

		// 图片上传Service
		PicInfo picInfo = new PicInfo();
		//返回上传图片的相对路径
		picInfo.setImageURL(uploadService.uploadImage(file,
				uploadPath, realUploadPath));
		picInfo.setLogoImageURL(waterMarkService.watermark(file,
				uploadPath, realUploadPath));
		
//		model.addAttribute("imageurl", picInfo.getImageURL());
//		model.addAttribute("logoimageurl", picInfo.getLogoImageURL());
		model.addAttribute("picInfo", picInfo);
		return "singlewatermark";
	}

	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public WaterMarkService getWaterMarkService() {
		return waterMarkService;
	}

	public void setWaterMarkService(WaterMarkService waterMarkService) {
		this.waterMarkService = waterMarkService;
	}
}
