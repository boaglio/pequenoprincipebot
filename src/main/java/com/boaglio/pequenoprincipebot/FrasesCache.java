package com.boaglio.pequenoprincipebot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FrasesCache {

	private static final String ARQUIVO_DE_FRASES = "frases.txt";

	private int tamanhoDoCache;

	private List<String> frases = new ArrayList<String>();

	private List<String> frasesCache = new ArrayList<String>();

	public synchronized String getFrase() {

		if (frases.size() == 0) {
			init();
		}

		if (frasesCache.size() == 0) {
			frasesCache = new ArrayList<String>(frases);
			tamanhoDoCache = frases.size();
		}

		String frase = null;
		while (frase == null) {
			int randomNumber = getRandomNumber();
			System.out.println("randomNumber: " + randomNumber);
			frase = frasesCache.get(randomNumber);
			frasesCache.remove(randomNumber);
			tamanhoDoCache = frasesCache.size();
			System.out.println("Total de frases no cache: " + frasesCache.size());
		}

		System.out.println("frases.size() = " + frases.size());
		System.out.println("frasesCache.size() = " + frasesCache.size());
		System.out.println("frase = " + frase);

		return frase;
	}

	private void init() {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(ARQUIVO_DE_FRASES);

		try (BufferedReader stream = new BufferedReader(new InputStreamReader(is))) {

			frases = stream.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		tamanhoDoCache = frases.size();
		frasesCache = new ArrayList<String>(frases);

		System.out.println("Total de frases de " + ARQUIVO_DE_FRASES + ": " + tamanhoDoCache);
	}

	private int getRandomNumber() {
		Random r = new Random();
		return r.nextInt(tamanhoDoCache);
	}

}
