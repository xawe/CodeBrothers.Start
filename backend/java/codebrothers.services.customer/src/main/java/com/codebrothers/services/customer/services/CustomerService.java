package com.codebrothers.services.customer.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.codebrothers.services.customer.entities.Customer;
import com.codebrothers.services.customer.exceptions.DataBaseException;
import com.codebrothers.services.customer.exceptions.ResourceNotFoundException;
import com.codebrothers.services.customer.repositories.CustomerRepository;

/*
 * Anotação para o framework identificar a classe como um serviço
 */
@Service
public class CustomerService {
    // Injeção da interface que faz parte do trabalho sujo com o banco
    @Autowired
    private CustomerRepository repository;
    
    public List<Customer> findAll() {
        return repository.findAll();
    }
    
    public Customer findById(Long id) {      
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Customer insert(Customer obj) {
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
    
    public Customer update(Long id, Customer obj) {
        try {
            Customer entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }   
    }

    private void updateData(Customer entity, Customer obj) {
        entity.setNome(obj.getNome());
        entity.setSobrenome(obj.getSobrenome());
        entity.setRg(obj.getRg());
        entity.setCpf(obj.getCpf());
        entity.setDataNascimento(obj.getDataNascimento());
        entity.setAtivo(obj.getAtivo());
        entity.setAtualizadoEm(LocalDateTime.now());
    }
}
