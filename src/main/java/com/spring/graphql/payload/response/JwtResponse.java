package com.spring.graphql.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
public class JwtResponse {

	@NonNull
	String token;

	String type = "Bearer";
}