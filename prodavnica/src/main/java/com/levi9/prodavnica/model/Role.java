package com.levi9.prodavnica.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {
	
	@Id
	private Long roleId;
	
	private String name;
	
	@OneToMany(mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<User> users;
	
}
