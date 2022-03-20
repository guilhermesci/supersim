package br.com.supersim.backend.utils;

import br.com.supersim.backend.dto.ClientDTO;
import br.com.supersim.backend.entity.Client;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ClientUtils {

    public static ClientDTO createFakeClientDTO() {
        return ClientDTO.builder()
                .id(1L)
                .name("ClientFake")
                .cpf("12345678901")
                .email("client.fake@gmail.com")
                .income(1234D)
                .build();
    }

    public static Client createFakeClient() {
        return Client.builder()
                .id(1L)
                .name("ClientFake")
                .cpf("12345678901")
                .email("client.fake@gmail.com")
                .income(1234D)
                .build();
    }

//    public static ClientDTO createInvalidFakeClientDTO() {
//        return ClientDTO.builder()
//                .id(1L)
//                .name(null)
//                .cpf("12345678901")
//                .email("client.dto.fake@gmail.com")
//                .income(1234D)
//                .build();
//    }
//
//    public static Page<ClientDTO> createFakeClientDTOPage(){
//        return (Page<ClientDTO>) Collections.singletonList(createFakeClientDTO());
//    }

    public static String asJsonString(ClientDTO clientDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(clientDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
