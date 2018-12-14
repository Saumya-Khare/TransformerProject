package com.sk.transformer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sk.transformer.dto.Transformer;
import com.sk.transformer.dto.TransformerFightResult;
import com.sk.transformer.dto.TransformerResponse;
import com.sk.transformer.util.TransformerService;

@RestController
@RequestMapping(TransformerController.TRANSFORMER_BASE_URI)
public class TransformerController {
	
	public static final String TRANSFORMER_BASE_URI = "svc/v1/transformers";
	
	@Autowired
	TransformerService service;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public TransformerResponse create(@RequestBody Transformer transformer) {
		return service.create(transformer);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TransformerResponse update(@RequestBody Transformer transformer) {
		return service.update(transformer);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public TransformerResponse delete(@PathVariable("id") String teamName) {
		return service.delete(teamName);
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Transformer> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value = "/getFightResult", method = RequestMethod.POST)
	@ResponseBody
	public TransformerFightResult getWinner(@RequestBody List<Transformer> transformersList) {
		return service.getWinner(transformersList);
		
	}

}
