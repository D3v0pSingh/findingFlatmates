package com.example.findingFlatmates.swaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
			info = @Info(
					title = "findingFlatmates Application",
					description = "Here you can find a flat for rent for free and also if you can also find a flatmate to share a flat with.<br> No Broker ki maa chodne ke liye bna rhe hai. Flats searching should be free forever.<br> A gift for fresher working in service based company.",
					termsOfService = "No Terms and Condition applied. Made in bakchodi to help people and build good resumes for us.",
					contact = @Contact(
							name = "Gagandeep Singh(Backend Developer), Rishabh Somvanshi(Frontend Developer)",
							email = "imgagan2012@gmail.com"
							),
					license = @License(
							name = "Hard-Developer.Ltd"
							),
					version = "v1.0"
					
				),
			servers = {
					@Server(
							description = "Local",
							url = "http://localhost:8089"
					),
					@Server(
							description = "Prod",
							url = "http://findingflatmates-production.up.railway.app"
					)
			}
		)
@SecurityScheme(
			name = "auth",
			in = SecuritySchemeIn.HEADER,
			type = SecuritySchemeType.HTTP,
			bearerFormat = "JWT",
			scheme="bearer",
			description = "This is for authentication purposes."
		)
public class configSwagger {

}
