package com.codebrothers.services.customer.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.codebrothers.services.customer.entities.Costumer;
import com.codebrothers.services.customer.repositories.CostumerRepository;
import com.codebrothers.services.customer.service.exceptions.DataBaseException;
import com.codebrothers.services.customer.service.exceptions.ResourceNotFoundException;

/*
 * Anotação para o framework identificar a classe como um serviço
 */
@Service
public class CostumerService {
    // Injeção da interface que faz parte do trabalho sujo com o banco
    @Autowired
    private CostumerRepository repository;
    
    public List<Costumer> findAll() {
        return repository.findAll();
    }
    
    public Costumer findById(Long id) {      
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Costumer insert(Costumer obj) {
        return repository.save(obj);
    }
    
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
    
    public Costumer update(Long id, Costumer obj) {
        try {
            Costumer entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }   
    }

    private void updateData(Costumer entity, Costumer obj) {
        entity.setNome(obj.getNome());
        entity.setSobrenome(obj.getSobrenome());
        entity.setRg(obj.getRg());
    }
}
