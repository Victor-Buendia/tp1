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
					{"PrimeiroNome", "Nelso"},
					{"NomeMeio", null},
					{"UltimoNome", null}
				}, true },
			{ new Object[][] {
					{"CPF", null},
					{"Matricula", "23/01234956"},
					{"Sexo", "M"},
	                {"Email", null}
				}, true },
			{ new Object[][] {
					{"CPF", "111.111.111-10"},
					{"Matricula", "23/01234956"},
					{"Sexo", "M"},
	                {"Email", "nelsinho@unb.br"}
				}, true },
			{ new Object[][] {
					{"CPF", null},
					{"Matricula", null},
					{"Sexo", null},
	                {"Email", null}
				}, false },
			{ new Object[][] {
					{"CPF", null},
					{"Matrícula", null},
					{"Sexo", null},
					{"Email", null},
					{"Nome", new Object[][] {
						{"PrimeiroNome", null},
						{"NomeMeio", null},
						{"UltimoNome", null}
					}}
				}, false },
			{ new Object[][] {
					{"CPF", null},
					{"Matrícula", null},
					{"Sexo", "M"},
					{"Email", null},
					{"Nome", new Object[][] {
						{"PrimeiroNome", null},
						{"NomeMeio", null},
						{"UltimoNome", null}
					}}
				}, true },
			{ new Object[][] {
					{"CPF", null},
					{"Matrícula", null},
					{"Sexo", null},
					{"Email", null},
					{"Nome", new Object[][] {
						{"PrimeiroNome", "Ana"},
						{"NomeMeio", null},
						{"UltimoNome", null}
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