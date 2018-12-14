package com.sk.transformer.util;

import java.util.ArrayList;
import java.util.List;

import com.sk.transformer.constants.TransformerConstants;
import com.sk.transformer.dto.Transformer;
import com.sk.transformer.dto.TransformerFightResult;
import com.sk.transformer.dto.TransformerResponse;

public class TransformerService {
	List<Transformer> transformerList = new ArrayList<Transformer>();

	public TransformerResponse create(Transformer transformer) {
		TransformerResponse response = new TransformerResponse();
		transformerList.add(transformer);
		response.setMessage(TransformerConstants.SUCCESS_MSG);
		return response;
	}

	public TransformerResponse update(Transformer transformer) {
		TransformerResponse response = new TransformerResponse();
		for (int i = 0; i < transformerList.size(); ++i) {
			if (transformer.getTeamName().equalsIgnoreCase(transformerList.get(i).getTeamName())) {
				transformerList.set(i, transformer);
				response.setMessage(TransformerConstants.SUCCESS_MSG);
			}
		}
		if (response.getMessage() == null) {
			response.setMessage(TransformerConstants.NOT_FOUND_MSG);
		}

		return response;

	}

	public TransformerResponse delete(String teamName) {
		TransformerResponse response = new TransformerResponse();
		for (int i = 0; i < transformerList.size(); ++i) {
			if (teamName.equalsIgnoreCase(transformerList.get(i).getTeamName())) {
				transformerList.remove(transformerList.get(i));
				response.setMessage(TransformerConstants.SUCCESS_MSG);
			}
		}
		if (response.getMessage() == null) {
			response.setMessage(TransformerConstants.NOT_FOUND_MSG);
		}
		return response;
	}

	public List<Transformer> listAll() {
		return transformerList;
	}

	public TransformerFightResult getWinner(List<Transformer> transformersList) {
		TransformerUtil util = new TransformerUtil();
		TransformerFightResult result = util.getResult(transformersList);
		return result;
	}

}
