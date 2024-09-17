package com.example.wave.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotBlank(message = "userId는 비어 있을 수 없습니다.")
	private String userId;

	@NotBlank(message = "username은 비어 있을 수 없습니다.")
	private String username;

	private String globalName;
	private String locale;
	private Integer koreanlistHeartCount;

}
