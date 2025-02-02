package br.com.lucas.projeto.arquitetura.sso.api.dto;

import java.util.Set;

import br.com.lucas.projeto.arquitetura.sso.api.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;

	@NotBlank(message = "O username não pode ser vazio ou nulo.")
	@Size(min = 3, max = 50, message = "O username deve ter entre 3 e 50 caracteres.")
	private String username;

	@NotBlank(message = "A senha não pode ser vazia ou nula.")
	@Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
	private String password;

	@NotEmpty(message = "Ao menos uma role deve ser informada.")
	private Set<Role> roles;

	@NotBlank(message = "O e-mail não pode ser vazio ou nulo.")
	@Email(message = "O e-mail informado não é válido.")
	private String email;
	
}
