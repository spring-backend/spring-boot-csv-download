package com.suresh.spring.csvdownload.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.suresh.spring.csvdownload.service.CsvDownloadService;
import com.suresh.spring.csvdownload.util.FileDownloadUtils;

@Service
public class CsvDownloadServiceImpl implements CsvDownloadService {

	@Override
	public void downloadCsvFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		doPreValidationForFileDownload(request, response);
		
		performDownloadFile(request, response);
		
		doPostValidationForFileDownload(request, response);
		
	}

	private void doPostValidationForFileDownload(HttpServletRequest request, HttpServletResponse response) {
		//Do post download checks
		/**
		 * 1. Capture the number of downloads.
		 * 2. Use it for analytics 
		 */
	}

	/**
	 * Generate content to download a file
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void performDownloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String fileNameForDownload = FileDownloadUtils.getFileNameForDownload();
		
		FileDownloadUtils.addFileNameAndUpdateResponseParams(response, fileNameForDownload);
		
		generateDownloadFile(request, response);
	}

	private void generateDownloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OutputStream outputStream = null;
		Scanner scanner = null;
		InputStream is = null;

		String headerForDownload = generateHeaderForDownload();
		String contentForDownload = generateDataForDownload();
		
		try {
			outputStream = response.getOutputStream();
			is = new ByteArrayInputStream(headerForDownload.getBytes());
			scanner = new Scanner(is,"UTF-8");
			while (scanner.hasNext()) {
				String nextLine = scanner.nextLine();
				outputStream.write(nextLine.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
			if (scanner != null) {
				scanner.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	private void doPreValidationForFileDownload(HttpServletRequest request, HttpServletResponse response) {
		//Do any validations before you generate contents for download.
		/**
		 * 1. Check if the user is valid and has permission to download.
		 * 2. Check rate limiter (EXample: Number of downloads in the last 'N' minutes/seconds/hours 
		 */
	}
	
	private String generateDataForDownload() {
		String contents = null;
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("Suresh");
		contentBuilder.append("|");
		contentBuilder.append("Download Demo");
		contents = contentBuilder.toString();
		return contents;
	}
	
	private String generateHeaderForDownload() {
		String contentHeader = null;
		StringBuilder headerBuilder = new StringBuilder();
		headerBuilder.append("Name");
		headerBuilder.append("|");
		headerBuilder.append("Purpose");
		contentHeader = headerBuilder.toString();
		return contentHeader;
	}
}
