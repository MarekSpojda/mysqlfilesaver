package pl.marekspojda.MySqlFileSaver.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import pl.marekspojda.MySqlFileSaver.config.SecurityConfiguration;
import pl.marekspojda.MySqlFileSaver.dto.UserDTO;
import pl.marekspojda.MySqlFileSaver.repository.RoleRepository;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String surname;
	private String email;
	private String password;
	private int active = 1;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private List<Role> roles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_file", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "fileId"))
	private List<FileRepresentation> files;

	// Needed for CustomUserDetails
	public User() {
	}

	// Needed for CustomUserDetails
	public User(User user) {
		this.active = user.getActive();
		this.email = user.getEmail();
		this.roles = user.getRoles();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.userId = user.getUserId();
		this.password = user.getPassword();
		this.files = user.getFiles();
	}

	public User(UserDTO userDTO, RoleRepository roleRepository) {
		this.setName(userDTO.getName());
		this.setSurname(userDTO.getSurname());
		this.setEmail(userDTO.getEmail());
		this.setPassword(new SecurityConfiguration().passwordEncoder().encode(userDTO.getPassword()));

		// Adding role 'USER' to new user if that role is present in table under index 2
		List<Role> roles = new ArrayList<>();
		if (roleRepository.findById(2L).isPresent()) {
			roles.add(roleRepository.findById(2L).get());
		}
		this.setRoles(roles);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<FileRepresentation> getFiles() {
		return files;
	}

	public void setFiles(List<FileRepresentation> files) {
		this.files = files;
	}
}