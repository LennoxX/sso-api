package br.com.lucas.projeto.arquitetura.sso.api.dto;

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
public class LoginRequestDTO {

	private String username;
	private String password;
	private Long applicationId;

}
