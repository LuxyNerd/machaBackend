package com.turcan.machabackend.response;


public class TestResponse {
	private long id;
	
	private String description;
	
	private Long owner_id;

	public TestResponse(long id, String description, Long owner_id) {
		super();
		this.id = id;
		this.description = description;
		this.owner_id = owner_id;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}
	
	
}
