package com.dh.controller;

import com.dh.dto.OdontologoDto;
import com.dh.exceptions.BadRequestException;
import com.dh.service.impl.OdontologoService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OdontologoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private OdontologoService odontologoService;

    public void cargarDataSet() throws BadRequestException {
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.nombre = "Laura";
        odontologoDto.apellido = "Perez";
        odontologoDto.numeroMatricula = 123654;
        OdontologoDto result = odontologoService.crearOdontologo(odontologoDto);
    }

    @Test
    public void registrarOdontologo() throws Exception {
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.nombre = "Laura";
        odontologoDto.apellido = "Perez";
        odontologoDto.numeroMatricula = 123654;

        OdontologoDto result = odontologoService.crearOdontologo(odontologoDto);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/odontologos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(result)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void eliminarOdontologo() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/odontologos/eliminar/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void listarOdontologos() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/odontologos/lista")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void buscarOdontologoPorId() throws Exception {
        this.cargarDataSet();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/buscar-por-id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }
}