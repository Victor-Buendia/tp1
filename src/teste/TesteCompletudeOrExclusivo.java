package teste;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import completude.Completude;

// Calculo da completude de campos OR EXCLUSIVO
// Fazer a falsificação, duplicação e triangulação
// Finalizar parametrizando os testes

@RunWith(Parameterized.class)
public class TesteCompletudeOrExclusivo {
	
	private Completude completude;
	private Object[][] camposAninhados;
	private boolean resultado;
	
	public TesteCompletudeOrExclusivo(Object[][] camposAninhados, boolean resultado) {
		this.camposAninhados = camposAninhados;
		this.resultado = resultado;
	}
	
	@Parameters
	public static Collection<Object[]> getParameters(){
		Object[][] parameters = new Object[][] {
			
			// testOrExclusivoVerdadeiro
			{new Object[][] {
				{"Rua", null},
				{"Cidade", null},
				{"Estado", "Distrito Federal"},
				{"CEP", null}
			}, true},
			
			// testOrExclusivoFalso
			{new Object[][] {
				{"Rua", null},
				{"Cidade", "Brasília"},
				{"Estado", "Distrito Federal"},
				{"CEP", null}
			}, false},
			
			//testOrExclusivoAninhadoVerdadeiro
			{new Object[][] {
				{"CPF", null},
				{"Matrícula", null},
				{"Sexo", "M"},
				{"Email", null},
				{"Nome", new Object[][] {
					{"PrimeiroNome", null},
					{"NomeMeio", null},
					{"UltimoNome", null}
				}}
			}, true},
			
			//testOrExclusivoAninhadoFalso
			{new Object[][] {
				{"CPF", null},
				{"Matrícula", null},
				{"Sexo", "M"},
				{"Email", null},
				{"Nome", new Object[][] {
					{"PrimeiroNome", "Victor"},
					{"NomeMeio", null},
					{"UltimoNome", null}
				}}
			}, false},
		};
		return Arrays.asList(parameters);
	}
	
	@Before
	public void setup() {
		completude = new Completude(camposAninhados);
	}

	@Test
	public void testeOrExclusivo() {
		assertEquals(completude.checarCompletude(), resultado);
	}
}
