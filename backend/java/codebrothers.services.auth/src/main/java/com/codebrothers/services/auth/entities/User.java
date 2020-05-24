package com.codebrothers.services.auth.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tb_user")
@ApiModel(value="User", description = "User model")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

	@ApiModelProperty(notes = "Id of user", dataType = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Name of user", dataType = "String", required = true)
    @Size(max = 30, message = "Quantidade de caracteres do Nome acima do permitido!")
    @NotBlank(message = "nome não pode ser em branco!")
    @Column(name = "nome", length = 30)
    @NotNull
    private String name;
	
	@EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Email of User", dataType = "String", required = false)
    @Email
    @Size(max = 50, message = "Quantidade de caracteres do Email acima do permitido!")
    @Column(name = "email", length = 50)
    private String email;
	
	@EqualsAndHashCode.Exclude
    @ApiModelProperty(notes = "Password of User", dataType = "String", required = false)    
    @Size(max = 60, message = "Quantidade de caracteres do Password acima do permitido!")
    @Column(name = "password", length = 250)
    private String password;
}
