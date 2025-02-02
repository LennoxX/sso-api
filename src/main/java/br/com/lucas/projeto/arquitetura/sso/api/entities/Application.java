package br.com.lucas.projeto.arquitetura.sso.api.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "applications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String name;
	
	@Column(length = 50)
	private String appPath;
	
	@Column(length = 50)
	private String apiPath;
	
	@Column(nullable = true)
	@Builder.Default
	private Boolean selectable = true;
	
	@Column()
	private String icon;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Builder.Default
	@Column(name = "created_at", updatable = false)
	@JsonIgnore
	private LocalDateTime createdAt = LocalDateTime.now();

	@ManyToMany(mappedBy = "applications")
	@JsonIgnore
	private Set<User> users;
	
	public Application(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Application(Long id, String name, String description, String apiPath, String appPath, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.apiPath = apiPath;
		this.appPath = appPath;
		this.icon = icon;
	}
	
	
}
