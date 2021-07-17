package com.simregister.uploadcsv.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.simregister.model.Sim;
import com.simregister.service.SimRegisterService;

@Controller
public class UploadController {
	
	@Autowired
	private SimRegisterService simRegisterService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/upload-csv-file")
	public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {

			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

				CsvToBean<Sim> csvToBean = new CsvToBeanBuilder(reader).withType(Sim.class)
						.withIgnoreLeadingWhiteSpace(true).build();

				List<Sim> listOfSim = csvToBean.parse();
				simRegisterService.simRegistraion(listOfSim);

				//model.addAttribute("users", listOfSim);
			//	model.addAttribute("status", true);

			} catch (Exception ex) {
				model.addAttribute("message", "An error occurred while processing the CSV file.");
				model.addAttribute("status", false);
			}
		}

		return "file-upload-status";
	}
}
