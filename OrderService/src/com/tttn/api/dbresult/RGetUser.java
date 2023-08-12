package com.tttn.api.dbresult;

public class RGetUser extends DBResult{

	public RGetUser() {
		addField("id", DataType.INTEGER, null);
		addField("fullName", DataType.STRING, "");
		addField("email", DataType.STRING, "");
		addField("phone", DataType.STRING, "");
		addField("address", DataType.STRING, "");
		addField("password", DataType.STRING, "");
		addField("isDeleted", DataType.BOOLEAN, null);
		addField("idRole", DataType.INTEGER, null);
		addField("roleName", DataType.STRING, "");
		addField("loyaltyPoint", DataType.LONG, null);
	}

	public Integer getId() {
		return (Integer) this.getValue("id");
	}

	public String getFullName() {
		return (String) this.getValue("fullName");
	}

	public String getEmail() {
		return (String) this.getValue("email");
	}

	public String getPhone() {
		return (String) this.getValue("phone");
	}

	public String getAddress() {
		return (String) this.getValue("address");
	}

	public String getPassword() {
		return (String) this.getValue("password");
	}

	public Boolean getIsDeleted() {
		return (Boolean) this.getValue("isDeleted");
	}

	public Integer getIdRole() {
		return (Integer) this.getValue("idRole");
	}

	public String getRoleName() {
		return (String) this.getValue("roleName");
	}

	public Long getLoyaltyPoint() {
		return (Long) this.getValue("loyaltyPoint");
	}

}
