package com.tool.as;

public class TestPojo {
	private String id;
	private String name;
	private String privilege;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public TestPojo(String id, String name, String privilege) {
		super();
		this.id = id;
		this.name = name;
		this.privilege = privilege;
	}

	public TestPojo() {
		super();
	}
}
