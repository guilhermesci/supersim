package br.com.supersim.backend.service;

import br.com.supersim.backend.dto.ClientDTO;
import br.com.supersim.backend.entity.Client;
import br.com.supersim.backend.exception.ClientAlreadyCreatedException;
import br.com.supersim.backend.exception.ClientNotFoundException;
import br.com.supersim.backend.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.supersim.backend.utils.ClientUtils.*;
import static br.com.supersim.backend.utils.ClientUtils.createFakeClientDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    private static final long INVALID_CLIENT_ID = 9999L;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void whenClientInformedThenItShouldBeCreated() throws ClientAlreadyCreatedException {
        // given
        ClientDTO expectedClientDTO = createFakeClientDTO();
        Client expectedSavedClient = createFakeClient();

        // when
        when(clientRepository.findByCpf(expectedClientDTO.getCpf())).thenReturn(Optional.empty());
        when(clientRepository.save(expectedSavedClient)).thenReturn(expectedSavedClient);

        //then
        ClientDTO createdClientDTO = clientService.createClient(expectedClientDTO);

        assertThat(createdClientDTO.getId(), is(equalTo(expectedClientDTO.getId())));
        assertThat(createdClientDTO.getCpf(), is(equalTo(expectedClientDTO.getCpf())));
        assertThat(createdClientDTO.getName(), is(equalTo(expectedClientDTO.getName())));
        assertThat(createdClientDTO.getEmail(), is(equalTo(expectedClientDTO.getEmail())));
        assertThat(createdClientDTO.getIncome(), is(equalTo(expectedClientDTO.getIncome())));
    }

    @Test
    void whenAlreadyCreatedClientInformedThenAnExceptionShouldBeThrown() {
        // given
        ClientDTO expectedClientDTO = createFakeClientDTO();
        Client duplicatedClient = createFakeClient();

        // when
        when(clientRepository.findByCpf(expectedClientDTO.getCpf())).thenReturn(Optional.of(duplicatedClient));

        // then
        assertThrows(ClientAlreadyCreatedException.class, () -> clientService.createClient(expectedClientDTO));
    }

    @Test
    void whenValidClientCpfIsGivenThenReturnAClient() throws ClientNotFoundException {
        // given
        ClientDTO expectedFoundClientDTO = createFakeClientDTO();
        Client expectedFoundClient = createFakeClient();

        // when
        when(clientRepository.findByCpf(expectedFoundClient.getCpf())).thenReturn(Optional.of(expectedFoundClient));

        // then
        ClientDTO foundClientDTO = clientService.findByCpf(expectedFoundClientDTO.getCpf());

        assertThat(foundClientDTO, is(equalTo(expectedFoundClientDTO)));
    }

    @Test
    void whenNotCreatedClientCpfIsGivenThenThrowAnException() {
        // given
        ClientDTO expectedFoundClientDTO = createFakeClientDTO();

        // when
        when(clientRepository.findByCpf(expectedFoundClientDTO.getCpf())).thenReturn(Optional.empty());

        // then
        assertThrows(ClientNotFoundException.class, () -> clientService.findByCpf(expectedFoundClientDTO.getCpf()));
    }

    @Test
    void whenInvalidIdIsGivenThenThrowAnException() {
        // when
        when(clientRepository.findById(INVALID_CLIENT_ID)).thenReturn(Optional.empty());

        // then
        assertThrows(ClientNotFoundException.class, () -> clientService.findById(INVALID_CLIENT_ID));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAClientShouldBeDeleted() throws ClientNotFoundException{
        // given
        ClientDTO expectedDeletedClientDTO = createFakeClientDTO();
        Client expectedDeletedClient = createFakeClient();

        // when
        when(clientRepository.findById(expectedDeletedClientDTO.getId())).thenReturn(Optional.of(expectedDeletedClient));
        doNothing().when(clientRepository).deleteById(expectedDeletedClientDTO.getId());

        // then
        clientService.deleteById(expectedDeletedClientDTO.getId());

        verify(clientRepository, times(1)).findById(expectedDeletedClientDTO.getId());
        verify(clientRepository, times(1)).deleteById(expectedDeletedClientDTO.getId());
    }
    
//    @Test
//    void whenListClientIsCalledThenReturnAListOfClients() {
//        // given
//        ClientDTO expectedFoundClientDTO = createFakeClientDTO();
//        Client expectedFoundClient = createFakeClient();
//
//        //when
//        when(clientRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundClient));
//
//        //then
//        Page<ClientDTO> foundPageClientDTO = clientService.listAll(Pageable.ofSize(1));
//
//        assertThat(foundPageClientDTO, is(not(empty())));
//        assertThat(foundPageClientDTO.get().collect(Collectors.toList()).get(0), is(equalTo(expectedFoundClientDTO)));
//    }
}
