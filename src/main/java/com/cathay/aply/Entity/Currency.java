package com.cathay.aply.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	    
	@Column(unique = true)
	private String code;
	    
	@Column(unique = true)
	private String name;
	
	//如果@Data註解沒作用自建getter/setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
