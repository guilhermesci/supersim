package br.com.supersim.backend.controller;

import br.com.supersim.backend.dto.ClientDTO;
import br.com.supersim.backend.exception.ClientNotFoundException;
import br.com.supersim.backend.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.supersim.backend.utils.ClientUtils.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    private static final String CLIENT_API_URL_PATH = "/api/v1/clients";
    private static final long VALID_CLIENT_ID = 1L;
    private static final long INVALID_CLIENT_ID = 9999L;
    private static final String NEW_CLIENT_NAME = "NewName";

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();

        // when
        when(clientService.findById(clientDTO.getId())).thenReturn(clientDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CLIENT_API_URL_PATH + "/" + clientDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is(clientDTO.getCpf())))
                .andExpect(jsonPath("$.name", is(clientDTO.getName())))
                .andExpect(jsonPath("$.email", is(clientDTO.getEmail())))
                .andExpect(jsonPath("$.income", is(clientDTO.getIncome())));
    }

    @Test
    void whenGETIsCalledWithInvalidIdThenNotFoundIsReturned() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();

        // when
        when(clientService.findById(clientDTO.getId())).thenThrow(ClientNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CLIENT_API_URL_PATH + "/" + clientDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETIsCalledWithValidCpfThenOkStatusIsReturned() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();

        // when
        when(clientService.findByCpf(clientDTO.getCpf())).thenReturn(clientDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CLIENT_API_URL_PATH + "/cpf/" + clientDTO.getCpf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is(clientDTO.getCpf())))
                .andExpect(jsonPath("$.name", is(clientDTO.getName())))
                .andExpect(jsonPath("$.email", is(clientDTO.getEmail())))
                .andExpect(jsonPath("$.income", is(clientDTO.getIncome())));
    }

    @Test
    void whenGETIsCalledWithInvalidCpfThenNotFoundIsReturned() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();

        // when
        when(clientService.findByCpf(clientDTO.getCpf())).thenThrow(ClientNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(CLIENT_API_URL_PATH + "/cpf/" + clientDTO.getCpf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPOSTIsCalledThenAClientIsCreated() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();
        // when
        when(clientService.createClient(clientDTO)).thenReturn(clientDTO);
        // then
        mockMvc.perform(post(CLIENT_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf", is(clientDTO.getCpf())))
                .andExpect(jsonPath("$.name", is(clientDTO.getName())))
                .andExpect(jsonPath("$.email", is(clientDTO.getEmail())))
                .andExpect(jsonPath("$.income", is(clientDTO.getIncome())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenBadRequestIsReturned() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();
        clientDTO.setCpf(null);

        // then
        mockMvc.perform(post(CLIENT_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPUTIsCalledWithAValidIdThenOkStatusIsReturned() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();
        clientDTO.setName(NEW_CLIENT_NAME);
        // when
        when(clientService.updateById(VALID_CLIENT_ID, clientDTO)).thenReturn(clientDTO);
        // then
        mockMvc.perform(MockMvcRequestBuilders.put(CLIENT_API_URL_PATH + "/" + VALID_CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(NEW_CLIENT_NAME)));
    }

    @Test
    void whenPUTIsCalledWithAInvalidIdThenNotFoundIsReturned() throws Exception {
        // given
        ClientDTO clientDTO = createFakeClientDTO();
        // when
        when(clientService.updateById(INVALID_CLIENT_ID, clientDTO)).thenThrow(ClientNotFoundException.class);
        // then
        mockMvc.perform(MockMvcRequestBuilders.put(CLIENT_API_URL_PATH + "/" + INVALID_CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // when
        doNothing().when(clientService).deleteById(VALID_CLIENT_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(CLIENT_API_URL_PATH + "/" + VALID_CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // when
        doThrow(ClientNotFoundException.class).when(clientService).deleteById(INVALID_CLIENT_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(CLIENT_API_URL_PATH + "/" + INVALID_CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
//    @Test
//    void whenGETIsCalledThenOkStatusIsReturned() throws Exception {
//        // given
//        ClientDTO clientDTO = createFakeClientDTO();
//
//        //when
//        when(clientService.listAll(Pageable.ofSize(1)))
//                .thenReturn(createFakeClientDTOPage());
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get(CLIENT_API_URL_PATH)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].cpf", is(clientDTO.getCpf())))
//                .andExpect(jsonPath("$[0].name", is(clientDTO.getName())))
//                .andExpect(jsonPath("$[0].email", is(clientDTO.getEmail())))
//                .andExpect(jsonPath("$[0].income", is(clientDTO.getIncome())));
//    }
}
