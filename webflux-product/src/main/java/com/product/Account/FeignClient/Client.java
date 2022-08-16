package com.product.Account.FeignClient;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

	private String dni;
	private String names;
	@Email(message = "Email.client.email")
	private String email;
	private String typeAccount;
	@JsonFormat(pattern = "dd:MM:yyyy KK:mm a")
	private LocalDateTime dateRegister;

}
