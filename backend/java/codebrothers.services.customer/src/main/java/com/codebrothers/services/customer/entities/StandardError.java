package com.codebrothers.services.customer.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Getter @Setter private Instant timestamp;
	@Getter @Setter private Integer status;
	@Getter @Setter private String error;
	@Getter @Setter private String message;
	@Getter @Setter private String path;

    @Override
    public String toString() {
        return "{\"timestamp\":\"" + timestamp + 
                "\", \"status\":\"" + status + 
                "\", \"error\":\"" + error + 
                "\", \"message\":\""+ message + 
                "\", \"path\":\"" + path + "\"}";
    }
}
