package com.revature.models;

import java.util.Objects;

public class Reimb {
	private int id;
	private double amount;
	private String submitted;
	private String resolved;
	private String description;
	private String recipt;
	private int authorId;
	private int resolverId;
	private int statusId;
	private int typeId;
	protected String typeName;
	public String resolverName;
	public String statusName;
	
	
	public Reimb() {
		super();
	}


	public Reimb(int id, double amount, String submitted, String resolved, String description, String recipt,
			int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.recipt = recipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	


	public Reimb(int id, int authorId, double amount, int typeId, String description, String submitted,
			String resolved, int resolverId, int statusId) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}


	
	public Reimb(int id, int typeId, double amount, String description, String submitted, int statusId,
			String resolved, int resolverId) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.statusId = statusId;
		this.typeId = typeId;
		this.resolverId = resolverId;
	}
	
	public Reimb(int id, int authorId, double amount, int typeId, String description, String submitted,
			int statusId) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.statusId = statusId;
		this.typeId = typeId;
		this.statusId = statusId;
		}


	public void setTypeName(int i) {
		switch(i)  {
		case 1:
			typeName = "LOGING";
			break;
	  	case 2:
	  		typeName = "TRAVEL";
			break;
	  	case 3:
	  		typeName = "FOOD";
			break;
	  	case 4:
	  		typeName = "OTHERS";
			break;
		}
	}
	public String getTypeName() {
		return typeName;
	}
	public String getStatusName() {
		return statusName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getSubmitted() {
		return submitted;
	}


	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}


	public String getResolved() {
		return resolved;
	}


	public void setResolved(String resolved) {
		this.resolved = resolved;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getRecipt() {
		return recipt;
	}


	public void setRecipt(String recipt) {
		this.recipt = recipt;
	}


	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public int getResolverId() {
		return resolverId;
	}


	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}


	public int getStatusId() {
		return statusId;
	}


	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}


	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, authorId, description, id, recipt, resolved, resolverId, statusId, submitted,
				typeId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimb other = (Reimb) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && authorId == other.authorId
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(recipt, other.recipt) && Objects.equals(resolved, other.resolved)
				&& resolverId == other.resolverId && statusId == other.statusId
				&& Objects.equals(submitted, other.submitted) && typeId == other.typeId;
	}


	@Override
	public String toString() {
		return "Reimb [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", recipt=" + recipt + ", authorId=" + authorId + ", resolverId="
				+ resolverId + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}
	
	


}