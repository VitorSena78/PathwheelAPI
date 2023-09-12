package com.pathwheel.model;

//import org.codehaus.jackson.map.annotate.JsonSerialize;//esse import permite que você faça o uso da biblioteca Jackson para serializar objetos Java em formato JSON de forma personalizada.

import com.fasterxml.jackson.databind.annotation.JsonSerialize;//Essa biblioteca oferece recursos mais avançados para a serialização e desserialização de objetos Java em JSON.

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SpotType {
	public static final Integer ALERT = 1;
	public static final Integer DANGER = 2;
	public static final Integer BARRIER = 3;
	
	private Integer id;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SpotType [id=" + id + ", description=" + description + "]";
	}
	
}
