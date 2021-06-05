package com.markusdel.peopleapi.service;

import com.markusdel.peopleapi.dto.response.MessageResponseDTO;
import com.markusdel.peopleapi.dto.resquest.PersonDTO;
import com.markusdel.peopleapi.entity.Person;
import com.markusdel.peopleapi.repository.PersonRepository;
import static com.markusdel.peopleapi.utils.PersonUtils.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO fakeDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectSucessMessage = createExpectSuccessMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.createPerson(fakeDTO);

        assertEquals(successMessage, expectSucessMessage);
    }

    private MessageResponseDTO createExpectSuccessMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
