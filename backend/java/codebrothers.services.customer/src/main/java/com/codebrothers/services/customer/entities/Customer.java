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
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Entity
@Table(name = "tb_customer")
@ApiModel(value="Customer", description = "Customer model")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter 
@EqualsAndHashCode
public class Customer implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Id of Customer", dataType = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Name of Customer", dataType = "String", required = true)
    @Size(max = 30, message = "Quantidade de caracteres do Nome acima do permitido!")
    @NotBlank(message = "nome não pode ser em branco!")
    @Column(name = "nome", length = 30)
    @NotNull
    private String nome;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Surname of Customer", dataType = "String", required = false)
    @Size(max = 50, message = "Quantidade de caracteres do Sobrenome acima do permitido!")
    @Column(name = "sobrenome", length = 50)
    @Getter @Setter private String sobrenome;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "RG of Customer", dataType = "String", required = false)
    @Size(max = 12, message = "Quantidade de digitos do RG acima do permitido!")
    @Column(name = "rg", length = 12)
    private String rg;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "CPF of Customer", dataType = "String", required = true)
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
    @Size(min = 11, max = 14, message = "Quantidade de digitos do CPF inválido!")
    @Column(name = "cpf", length = 14)
    @NotBlank
    @NotNull
    private String cpf;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Status of Customer", dataType = "Boolean", required = true)
    @Column(name = "ativo", columnDefinition = "boolean default true")
    @NotNull
    private Boolean ativo;

    @EqualsAndHashCode.Exclude
    //@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\-](0?[1-9]|1[012])[\\-][0-9]{4}$")
    @ApiModelProperty(notes = "Birthdate of Customer", dataType = "LocalDate", required = false)
    @Column(name = "datanascimento")
     private LocalDate dataNascimento;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Customer Created On", dataType = "LocalDateTime", required = true)
    @Column(name = "criadoem", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @NotNull
    private LocalDateTime criadoEm;
    
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Customer Modified On", dataType = "LocalDateTime", required = false)
    @Column(name = "atualizadoem")
    private LocalDateTime atualizadoEm;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Email of Customer", dataType = "String", required = false)
    @Email
    @Size(max = 50, message = "Quantidade de caracteres do Email acima do permitido!")
    @Column(name = "email", length = 50)
    private String email;
}