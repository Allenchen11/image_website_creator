package com.iwc.restful;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.iwc.bean.param.DogBean;
import com.iwc.bean.param.GenImageWebsiteParamBean;
import com.iwc.bean.vo.WebDownloadRecordVO;
import com.iwc.dao.WebDownloadRecordDAO;
import com.iwc.utils.FileUtil;
import com.iwc.utils.IOUtil;
import com.iwc.utils.ImageUtil;
import com.iwc.utils.ZipUtil;

@RestController
@RequestMapping("GenImageWebsite")
public class GenImageWebsite extends BaseRestful{

	private static final Logger logger = LogManager.getLogger(GenImageWebsite.class);

	@Autowired
	private WebDownloadRecordDAO webDownloadRecordDAO;

	@Transactional
	@RequestMapping(value = "/downloadZip", method = RequestMethod.POST)
	public void downloadZip(@ModelAttribute GenImageWebsiteParamBean paramBean) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

		File mainPicDestFile = null;
		File portfolioImg1DestFile = null;
		File portfolioImg2DestFile = null;
		File portfolioImg3DestFile = null;

		try {

			MultipartFile mainPic = paramBean.getMainPic();
			String mainPicFileName = mainPic.getOriginalFilename();
			MultipartFile portfolioImg1 = paramBean.getPortfolioImg1();
			String portfolioImg1FileName = portfolioImg1.getOriginalFilename();
			MultipartFile portfolioImg2 = paramBean.getPortfolioImg2();
			String portfolioImg2FileName = portfolioImg2.getOriginalFilename();
			MultipartFile portfolioImg3 = paramBean.getPortfolioImg3();
			String portfolioImg3FileName = portfolioImg3.getOriginalFilename();

			String srcFolderPath = resourcesStaticPath + File.separator + "srcDir";
			String destFolderPath = resourcesStaticPath + File.separator + "destDir";

			IOUtil.copyAllFolder(srcFolderPath, destFolderPath);

			String srcFilePath = srcFolderPath + File.separator + "index.html";
			String template = IOUtil.readFileContextToString(srcFilePath);
			template = template.replace("@@title@@", paramBean.getTitle());
			template = template.replace("@@navbarBrand@@", paramBean.getNavbarBrand());
			template = template.replace("@@mastheadHeading@@", paramBean.getMastheadHeading());
			template = template.replace("@@mastheadSubheading@@", paramBean.getMastheadSubheading());
			template = template.replace("@@location@@", paramBean.getLocation());
			template = template.replace("@@aboutLeft@@", paramBean.getAboutLeft());
			template = template.replace("@@aboutRight@@", paramBean.getAboutRight());
			template = template.replace("@@copyright@@", paramBean.getCopyright());
			template = template.replace("@@aboutFooter@@", paramBean.getAboutFooter());
			template = template.replace("@@mainPic@@", mainPicFileName);
			template = template.replace("@@portfolioImg1@@", portfolioImg1FileName);
			template = template.replace("@@portfolioImg2@@", portfolioImg2FileName);
			template = template.replace("@@portfolioImg3@@", portfolioImg3FileName);

			WebDownloadRecordVO webDownloadRecordVO = new WebDownloadRecordVO();
			webDownloadRecordVO.setAboutFooter(paramBean.getAboutFooter());
			webDownloadRecordVO.setAboutLeft(paramBean.getAboutLeft());
			webDownloadRecordVO.setAboutRight(paramBean.getAboutRight());
			webDownloadRecordVO.setCopyright(paramBean.getCopyright());
			webDownloadRecordVO.setLocation(paramBean.getLocation());
			webDownloadRecordVO.setMainPic(mainPicFileName);
			byte[] mainPicByte = null;
			mainPicByte = ImageUtil.compressUnderSize(mainPic.getBytes(), 65535);
			if (mainPic.getSize() > 65535) {
				mainPicByte = ImageUtil.compressUnderSize(mainPic.getBytes(), 65535);
			}
			webDownloadRecordVO.setMainPicFile(new SerialBlob(mainPicByte));
			webDownloadRecordVO.setMainPicColor(paramBean.getMainPicColor());
			webDownloadRecordVO.setMastheadHeading(paramBean.getMastheadHeading());
			webDownloadRecordVO.setMastheadSubheading(paramBean.getMastheadSubheading());
			webDownloadRecordVO.setNavbarBrand(paramBean.getNavbarBrand());
			webDownloadRecordVO.setPortfolioImg1(portfolioImg1FileName);
			webDownloadRecordVO.setPortfolioImg1File(new SerialBlob(portfolioImg1.getBytes()));
			webDownloadRecordVO.setPortfolioImg2(portfolioImg2FileName);
			webDownloadRecordVO.setPortfolioImg2File(new SerialBlob(portfolioImg2.getBytes()));
			webDownloadRecordVO.setPortfolioImg3(portfolioImg3FileName);
			webDownloadRecordVO.setPortfolioImg3File(new SerialBlob(portfolioImg3.getBytes()));
			webDownloadRecordVO.setTitle(paramBean.getTitle());
			webDownloadRecordDAO.save(webDownloadRecordVO);

			IOUtil.stringToHtmlFile(destFolderPath + "\\index.html", template);

			mainPicDestFile = new File(destFolderPath + "\\assets\\img\\" + mainPicFileName);
			mainPic.transferTo(mainPicDestFile);
			// change mainPicColor to monochrome
			String mainPicColor = paramBean.getMainPicColor();
			if ("monochrome".equals(mainPicColor)) {
				ImageUtil.changeImageToMono(mainPicDestFile, mainPicDestFile);
			}

			portfolioImg1DestFile = new File(destFolderPath + "\\assets\\img\\portfolio\\" + portfolioImg1FileName);
			portfolioImg1.transferTo(portfolioImg1DestFile);
			portfolioImg2DestFile = new File(destFolderPath + "\\assets\\img\\portfolio\\" + portfolioImg2FileName);
			portfolioImg2.transferTo(portfolioImg2DestFile);
			portfolioImg3DestFile = new File(destFolderPath + "\\assets\\img\\portfolio\\" + portfolioImg3FileName);
			portfolioImg3.transferTo(portfolioImg3DestFile);

			String zipSavePath = resourcesStaticPath + File.separator + "zipRepository" + "\\result.zip";
			File zipSaveFile = new File(zipSavePath);
			if (!zipSaveFile.exists() || zipSaveFile.delete()) {
				ZipUtil zipUtil = new ZipUtil();
				zipUtil.pack(destFolderPath, zipSavePath);
			}

			// download to front-end
			InputStream is = new FileInputStream(zipSavePath);
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();

		} catch (Exception exception) {
			logger.error(exception);
		} finally {
			mainPicDestFile.delete();
			portfolioImg1DestFile.delete();
			portfolioImg2DestFile.delete();
			portfolioImg3DestFile.delete();
		}
	}
	
	
	@RequestMapping(value = "/queryLatestTenWeb", method = RequestMethod.GET)
	public Map<String,String> queryLatestTenWeb() {
		Map<String, String> map = new HashMap<String, String>();
		
		List<WebDownloadRecordVO> list =  webDownloadRecordDAO.queryLatestTenWeb();
		int index = 1;
		for(WebDownloadRecordVO aWebDownloadRecordVO:list) {
			map.put(String.valueOf(index), aWebDownloadRecordVO.getTitle());
			index++;
		}
		
		return map;	
	}
	
	
	@RequestMapping(value = "/testPost", method = RequestMethod.POST)
	public Map<String,String> testPost(@RequestBody DogBean dog) {
		Map map = new HashMap();
		
		map.put(dog.getName(),dog.getAge() );
		
		return map;	
	}


}
