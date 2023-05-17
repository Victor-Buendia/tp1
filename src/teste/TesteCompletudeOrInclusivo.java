package teste;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import completude.Completude;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class TesteCompletudeOrInclusivo {
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ new Object[][] {
					{"PrimeiroNome", "joão"},
					{"NomeMeio", null},
					{"UltimoNome", null}
				}, true },
			{ new Object[][] {
					{"CPF", null},
					{"Matricula", "20/202020"},
					{"Sexo", "Masculino"},
	                {"Email", null}
				}, true },
			{ new Object[][] {
					{"CPF", "111.111.111-10"},
					{"Matricula", "20/202020"},
					{"Sexo", "Masculino"},
	                {"Email", "aluno@aluno.un.br"}
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
	
	@Test
	public void testCompletudeOrInclusivo() {
		Completude c = new Completude(dados);
		assertEquals(resultadoEsperado, c.checarCompletudeOrInclusivo());
	}
}