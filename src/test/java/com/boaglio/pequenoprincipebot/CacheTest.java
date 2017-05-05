package com.boaglio.pequenoprincipebot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {

	@Test
	public void testa1000acessos() {

		FrasesCache f = new FrasesCache();
		IntStream.range(1,1000).parallel().forEach(i -> f.getFrase());

		assertThat(f.getFrase()).isNotNull();

	}

}
