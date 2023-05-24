package teste;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import completude.Completude;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class TesteCompletudeOrInclusivo {
	
	Completude c;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ new Object[][] {
					{"PrimeiroNome", "Nelso", "inclusivo"},
					{"NomeMeio", null, "inclusivo"},
					{"UltimoNome", null, "inclusivo"}
				}, true },
			{ new Object[][] {
					{"CPF", null, "inclusivo"},
					{"Matricula", "23/01234956", "inclusivo"},
					{"Sexo", "M", "inclusivo"},
	                {"Email", null, "inclusivo"}
				}, true },
			{ new Object[][] {
					{"CPF", "111.111.111-10", "inclusivo"},
					{"Matricula", "23/01234956", "inclusivo"},
					{"Sexo", "M", "inclusivo"},
	                {"Email", "nelsinho@unb.br", "inclusivo"}
				}, true },
			{ new Object[][] {
					{"CPF", null, "inclusivo"},
					{"Matricula", null, "inclusivo"},
					{"Sexo", null, "inclusivo"},
	                {"Email", null, "inclusivo"}
				}, false },
			{ new Object[][] {
					{"CPF", null, "inclusivo"},
					{"Matrícula", null, "inclusivo"},
					{"Sexo", null, "inclusivo"},
					{"Email", null, "inclusivo"},
					{"Nome", new Object[][] {
						{"PrimeiroNome", null, "inclusivo"},
						{"NomeMeio", null, "inclusivo"},
						{"UltimoNome", null, "inclusivo"}
					}}
				}, false },
			{ new Object[][] {
					{"CPF", null, "inclusivo"},
					{"Matrícula", null, "inclusivo"},
					{"Sexo", "M", "inclusivo"},
					{"Email", null, "inclusivo"},
					{"Nome", new Object[][] {
						{"PrimeiroNome", null, "inclusivo"},
						{"NomeMeio", null, "inclusivo"},
						{"UltimoNome", null, "inclusivo"}
					}}
				}, true },
			{ new Object[][] {
					{"CPF", null, "inclusivo"},
					{"Matrícula", null, "inclusivo"},
					{"Sexo", null, "inclusivo"},
					{"Email", null, "inclusivo"},
					{"Nome", new Object[][] {
						{"PrimeiroNome", "Ana", "inclusivo"},
						{"NomeMeio", null, "inclusivo"},
						{"UltimoNome", null, "inclusivo"}
					}}
				}, true }

		});
	}
	
	private Object[][] dados;
	private boolean resultadoEsperado;
	
	public TesteCompletudeOrInclusivo(Object[][] dados, boolean resultadoEsperado) {
		this.dados = dados;
		this.resultadoEsperado = resultadoEsperado;
	}
	
	@Before
	public void setup() {
		c = new Completude(dados);
	}

	
	@Test
	public void testCompletudeOrInclusivo() {
		assertEquals(resultadoEsperado, c.checarCompletudeOrInclusivo());
	}
}