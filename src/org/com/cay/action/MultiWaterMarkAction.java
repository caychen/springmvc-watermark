package org.com.cay.action;

import java.util.ArrayList;
import java.util.List;

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
public class MultiWaterMarkAction {

	@Resource(name="uploadService")
	private UploadService uploadService;
	@Resource(name="multiImageMarkService")
	private WaterMarkService waterMarkService;

	/**
	 * 
	 */

	@RequestMapping(value = "/watermark_multi.action", method = RequestMethod.POST)
	public String watermark(
			@RequestParam("image") CommonsMultipartFile[] files,
			HttpSession session, Model model) throws Exception {
		// 上传的真实路径
		String uploadPath = "/images";
		String realUploadPath = session.getServletContext().getRealPath(
				uploadPath);
		List<PicInfo> picInfos = new ArrayList<PicInfo>();

		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; ++i) {
				if (files[i] != null && files[i].getSize() > 0) {
					PicInfo picInfo = new PicInfo();

					// 返回上传图片的相对路径
					picInfo.setImageURL(uploadService.uploadImage(files[i],
							uploadPath, realUploadPath));

					picInfo.setLogoImageURL(waterMarkService.watermark(
							files[i], uploadPath, realUploadPath));

					picInfos.add(picInfo);
					model.addAttribute("picInfos", picInfos);
				}
			}
		}

		return "multiwatermark";
	}
}
