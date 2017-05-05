package com.boaglio.pequenoprincipebot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.vdurmont.emoji.EmojiParser;

public class PequenoPrincipeBot extends TelegramLongPollingBot {

	private static final String BOT_TOKEN = "xxxxxxxxxxx";
	private static final String BOT_NAME = "xxxxxxxxx";

	private FrasesCache frasesCache = new FrasesCache();
	private static final String AJUDA = EmojiParser.parseToUnicode("Use /frase para ler minhas sábias palavras :smile: ");

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {

			System.out.println("Remente: ");

			if (update.getMessage().getFrom() != null) {
				System.out.println("         Id = " + update.getMessage().getFrom().getId());
				System.out.println(" First Name = " + update.getMessage().getFrom().getFirstName());
				System.out.println("  Last Name = " + update.getMessage().getFrom().getLastName());
			}

			String msg = update.getMessage().getText();
			String resposta = "";
			if (update.getMessage().isCommand()) {

				if (msg.toLowerCase().contains(Commands.start.name()) || msg.toLowerCase().contains(Commands.frase.name())) {
					resposta = EmojiParser.parseToUnicode(":rose: " + frasesCache.getFrase() + " :rose:");
				} else {
					resposta = AJUDA;
				}

			} else {
				resposta = AJUDA;
			}

			SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId()).setText(resposta);

			try {
				sendMessage(message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}
}
