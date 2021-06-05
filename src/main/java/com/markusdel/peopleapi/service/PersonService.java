package com.markusdel.peopleapi.service;

import com.markusdel.peopleapi.dto.response.MessageResponseDTO;
import com.markusdel.peopleapi.dto.resquest.PersonDTO;
import com.markusdel.peopleapi.entity.Person;
import com.markusdel.peopleapi.mapper.PersonMapper;
import com.markusdel.peopleapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person savedPerson = personRepository.save(personMapper.toModel(personDTO));

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
