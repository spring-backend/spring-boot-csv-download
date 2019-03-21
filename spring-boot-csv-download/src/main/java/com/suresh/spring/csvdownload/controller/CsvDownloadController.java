package com.suresh.spring.csvdownload.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.spring.csvdownload.service.CsvDownloadService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/file-download")
@CrossOrigin(origins = "*")
@Api(tags={"File Download Controller"})
public class CsvDownloadController {

	@Autowired
	private CsvDownloadService csvDownloadService;
	
	@GetMapping
	public void downloadCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		csvDownloadService.downloadCsvFile(request, response);
	}
}
