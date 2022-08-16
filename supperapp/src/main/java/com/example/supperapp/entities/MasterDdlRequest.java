package com.example.supperapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterDdlRequest {

	@JsonProperty("Type")
	private String Type;
	@JsonProperty("SelectedGuId")
	private String SelectedGuId;
}
