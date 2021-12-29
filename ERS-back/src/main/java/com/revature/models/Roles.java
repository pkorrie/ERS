package com.revature.models;

import java.util.Objects;

public class Roles {
	private int id;
	private String role;
	
	
	public Roles() {
		super();
	}
	
	public Roles(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, role);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roles other = (Roles) obj;
		return id == other.id && Objects.equals(role, other.role);
	}
	@Override
	public String toString() {
		return "Roles [id=" + id + ", role=" + role + "]";
	}
	
	
}
