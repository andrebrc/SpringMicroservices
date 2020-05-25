package com.formiga.clientservice.controller;

import com.formiga.clientservice.model.Cidade;
import com.formiga.clientservice.model.Client;
import com.formiga.clientservice.model.SexoEnum;
import com.formiga.clientservice.repository.ClientRepository;
import com.formiga.clientservice.utils.ClientBuilder;

import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.*;

import org.keycloak.adapters.springboot.KeycloakAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Optional;

@WebMvcTest(ClientController.class)
@ImportAutoConfiguration({
        RibbonAutoConfiguration.class,
        KeycloakAutoConfiguration.class,
        FeignRibbonClientAutoConfiguration.class,
        FeignAutoConfiguration.class})
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CidadeServiceProxy serviceProxy;

    @MockBean
    private ClientRepository mockRepository;

    @Test
    public void it_should_fetch_client_by_id() throws Exception {
        Client jose = new ClientBuilder()
                .setNome("Jose Mock")
                .setDataNascimento(LocalDate.of(1987, 12, 14))
                .setSexo(SexoEnum.MASCULINO)
                .build();

        given(mockRepository.findById(anyLong()))
                .willReturn(Optional.of(jose));
        given(serviceProxy.findById(anyLong()))
                .willReturn(new Cidade("Santa Luzia", "MA"));

        mockMvc.perform(
                get("/clients/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(containsString("Antonio")))
                .andExpect(content().string(containsString("32")))
                .andExpect(content().string(containsString("MASCULINO")))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_fetch_client_by_name() throws Exception {
        Client jose = new ClientBuilder()
                .setNome("Jose Mock")
                .setDataNascimento(LocalDate.of(1985, 12, 14))
                .setSexo(SexoEnum.MASCULINO)
                .build();

        given(mockRepository.findById(anyLong()))
                .willReturn(Optional.of(jose));

        mockMvc.perform(
                get("/clients?name=Antonio")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(containsString("Jose Mock")))
                .andExpect(content().string(containsString("34")))
                .andExpect(content().string(containsString("MASCULINO")))
                .andExpect(status().isOk());
    }
    @Test
    public void is_should_delete_client_by_id() throws Exception {
        Client mockToDelete = new ClientBuilder()
                .setId(1L)
                .setNome("ClientMockToDelete").build();
        given(mockRepository.findById(anyLong())) .willReturn(Optional.of(mockToDelete));
        doNothing().when(mockRepository).deleteById(isNotNull());

        mockMvc.perform(
                delete("/clients/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}

