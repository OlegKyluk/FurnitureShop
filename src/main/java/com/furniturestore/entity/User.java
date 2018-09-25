package com.furniturestore.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1654713090293593231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

	private String name;

	private String lastName;

	private int active;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@ManyToOne(fetch = FetchType.LAZY)
	private ShoppingCart shoppingCart;

	public User() {
	}

	public User(String email, String password, String name, String lastName, int active, Set<Role> roles,
			ShoppingCart shoppingCart) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roles = roles;
		this.shoppingCart = shoppingCart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;

		User user = (User) o;

		if (getId() != user.getId())
			return false;
		if (getActive() != user.getActive())
			return false;
		if (!getEmail().equals(user.getEmail()))
			return false;
		if (!getPassword().equals(user.getPassword()))
			return false;
		if (!getName().equals(user.getName()))
			return false;
		if (!getLastName().equals(user.getLastName()))
			return false;
		if (!getRoles().equals(user.getRoles()))
			return false;
		return getShoppingCart().equals(user.getShoppingCart());
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + getEmail().hashCode();
		result = 31 * result + getPassword().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getLastName().hashCode();
		result = 31 * result + getActive();
		result = 31 * result + getRoles().hashCode();
		result = 31 * result + getShoppingCart().hashCode();
		return result;
	}
}
