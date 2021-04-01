package com.example.demo;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvController {
	JSONBean bean = new JSONBean();

	@RequestMapping(value = "/showFullJSON", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showCSVListJSON() {
		return bean.getJSON();
	}

	@RequestMapping(value = "/showExactJSON", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showExactJSON(String column) {
		return bean.getSpecificJSON(column);
	}

	@RequestMapping(value = "/errorFinder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String errorFinder() {
		return bean.errorFinder().toString();
	}

	@RequestMapping(value = "/showSortedJSON", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showSortedJSON(String column) {
		return bean.getSortedJSON(column);
	}

}
