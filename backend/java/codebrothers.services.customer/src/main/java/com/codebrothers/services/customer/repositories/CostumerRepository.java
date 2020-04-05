package com.codebrothers.services.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebrothers.services.customer.entities.Costumer;
/*
 *  Essa JpaRepository faz o trabalho sujo pra nós, apos isso não já é 
 *  possivel fazer os CRUDs  do Customer sem sofrimento
 *  ref:  explicação https://blog.algaworks.com/spring-data-jpa/
 *  anotação abaixo pode ser omitida, acho legal pra facilitar leitura, 
 *  mas no mundo dos javeiros parece q não usam
 */
@Repository
public interface CostumerRepository  extends JpaRepository<Costumer, Long> {

}