package com.suresh.spring.csvdownload.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class FileDownloadUtils {

	public static String[] VALID_FILE_EXTENSIONS = {"csv", "CSV"};
	
	public static final String DATE_PATTERN_FOR_DOWNLOAD = "yyyy-MM-dd HH-mm-ss";
	
	public static void addFileNameAndUpdateResponseParams(HttpServletResponse response, String fileName) {
		
		if (response == null) {
			return ;
		}
		
		if (StringUtils.isEmpty(fileName)) {
			fileName = getFileNameForDownload();
		}
		
		response.setContentType("application/csv; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
	}
	
	public static String getFileNameForDownload() {

		String dateFormat = generateDateStringWithPattern();
		
		StringBuilder sb = new StringBuilder();
		
		return sb.append("\"").append(dateFormat).append(".csv").append("\"").toString();
	}
	
	public static String generateDateStringWithPattern() {
		
		String patternValue = DATE_PATTERN_FOR_DOWNLOAD;
		
		SimpleDateFormat sdf = new SimpleDateFormat(patternValue);
		Date currentDate = new Date(System.currentTimeMillis());
		String dateFormat = sdf.format(currentDate);
		
		return dateFormat;
	}
}
