package br.com.lucas.projeto.arquitetura.sso.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.lucas.projeto.arquitetura.sso.api.entities.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(Include.NON_NULL)
public class LoginResponseDTO {

	@JsonIgnore
	private String jwt;
	private List<Application> aplicacoes;
}
