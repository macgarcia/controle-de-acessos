package com.github.macgarcia.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.github.macgarcia.api.dto.IntegracaoResponse;
import com.github.macgarcia.api.dto.NotaDtoEntrada;
import com.github.macgarcia.api.service.NotaService;

@MockitoSettings
@ExtendWith(MockitoExtension.class)
public class NotaControllerTest {

	@InjectMocks
	NotaController controller;

	@Mock
	private NotaService service;

	MockMvc mockMvc;

	private NotaDtoEntrada dto;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
		dto = new NotaDtoEntrada(null, null, null, null, null, null, null, null, null);
	}

	@Test
	void testeIntegracaoDeDados() throws Exception {

		doNothing().when(service).processar(dto);

		ResponseEntity<IntegracaoResponse> response = controller.integrarDadosDeNota(dto);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Integração de dados", response.getBody().metodo());
		assertEquals("Não houve erros", response.getBody().erro());

		verify(service, times(1)).processar(dto);

	}

	@Test
	void testeDeErroIntegracaoDeDados() throws Exception {

		Exception e = new Exception("Erro de integração");

		doThrow(e).when(service).processar(dto);

		ResponseEntity<IntegracaoResponse> response = controller.integrarDadosDeNota(dto);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Integração de dados", response.getBody().metodo());
		assertEquals("Erro de integração", response.getBody().erro());

		verify(service, times(1)).processar(dto);

	}

}
