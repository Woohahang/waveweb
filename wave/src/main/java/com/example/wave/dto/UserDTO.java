package com.example.wave.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotBlank(message = "id는 비어 있을 수 없습니다.")
	private String id;

	@NotBlank(message = "username은 비어 있을 수 없습니다.")
	private String username;

	private String global_name;
	private String locale;
	private Integer koreanlistHeartCount;

}
