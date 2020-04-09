package com.codebrothers.services.customer.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;;

@Entity
@Table(name = "tb_customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30, message = "Quantidade de caracteres do Nome acima do permitido!")
    @NotBlank(message = "nome não pode ser em branco!")
    @Column(name = "nome", length = 30)
    @NotNull
    private String nome;

    @Size(max = 50, message = "Quantidade de caracteres do Sobrenome acima do permitido!")
    @Column(name = "sobrenome", length = 50)
    private String sobrenome;

    @Size(max = 12, message = "Quantidade de digitos do RG acima do permitido!")
    @Column(name = "rg", length = 12)
    private String rg;

    @Pattern(regexp = "([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})")
    @Pattern(regexp = "^(?:(?!000\\.?000\\.?000-?00).)*$")
    @Pattern(regexp = "^(?:(?!111\\.?111\\.?111-?11).)*$")
    @Pattern(regexp = "^(?:(?!222\\.?222\\.?222-?22).)*$")
    @Pattern(regexp = "^(?:(?!333\\.?333\\.?333-?33).)*$")
    @Pattern(regexp = "^(?:(?!444\\.?444\\.?444-?44).)*$")
    @Pattern(regexp = "^(?:(?!555\\.?555\\.?555-?55).)*$")
    @Pattern(regexp = "^(?:(?!666\\.?666\\.?666-?66).)*$")
    @Pattern(regexp = "^(?:(?!777\\.?777\\.?777-?77).)*$")
    @Pattern(regexp = "^(?:(?!888\\.?888\\.?888-?88).)*$")
    @Pattern(regexp = "^(?:(?!999\\.?999\\.?999-?99).)*$")
    @NotBlank
    @Size(min = 11, max = 14, message = "Quantidade de digitos do CPF inválido!")
    @Column(name = "cpf", length = 14)
    @NotNull
    private String cpf;

    @Column(name = "ativo", columnDefinition = "boolean default true")
    @NotNull
    private Boolean ativo;

    //@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\-](0?[1-9]|1[012])[\\-][0-9]{4}$")
    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @Column(name = "criadoem", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @NotNull
    private LocalDateTime criadoEm;

    @Column(name = "atualizadoem")
    private LocalDateTime atualizadoEm;

    @Email
    @Size(max = 50, message = "Quantidade de caracteres do Email acima do permitido!")
    @Column(name = "email", length = 50)
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer() {

    }

    public Customer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}