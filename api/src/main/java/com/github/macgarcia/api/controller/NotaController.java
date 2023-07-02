package com.github.macgarcia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.macgarcia.api.dto.NotaDtoEntrada;
import com.github.macgarcia.api.service.NotaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/")
@Tag(name = "Controle de notas")
public class NotaController {
	
	@Autowired
	private NotaService service;
	
	@Operation(summary = "Armazenar dados", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Integração realizada com sucesso.")
	})
	@PostMapping
	public ResponseEntity<?> salvarNota(@RequestBody NotaDtoEntrada request) {
		service.processar(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
