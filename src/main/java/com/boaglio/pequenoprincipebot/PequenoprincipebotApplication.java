package com.boaglio.pequenoprincipebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
@ComponentScan(basePackages = "com.boaglio")
public class PequenoprincipebotApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(PequenoprincipebotApplication.class,args);

		System.out.println("Iniciando contexto Telegram");
		System.out.println("---------------------------------");

		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			System.out.println("Registrando pequeno principe bot");
			System.out.println("---------------------------------");
			botsApi.registerBot(new PequenoPrincipeBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PequenoprincipebotApplication.class);
	}

}
