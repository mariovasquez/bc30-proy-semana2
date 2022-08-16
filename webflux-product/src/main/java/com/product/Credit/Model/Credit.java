package com.product.Credit.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="credits")
public class Credit {

	@Id
	private String dniCreador;
	private float cash;
	private String typeAccount;
	@JsonFormat(pattern = "dd:MM:yyyy KK:mm a")
	private LocalDateTime dateRegister;

}
