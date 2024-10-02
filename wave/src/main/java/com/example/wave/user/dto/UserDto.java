package com.example.wave.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NotBlank(message = "id는 비어 있을 수 없습니다.")
	private String discordId;

	@NotBlank(message = "username은 비어 있을 수 없습니다.")
	private String userName;

	private String globalName;

}
