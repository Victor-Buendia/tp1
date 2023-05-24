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
				{"Rua", null, "exclusivo"},
				{"Cidade", null, "exclusivo"},
				{"Estado", "Distrito Federal", "exclusivo"},
				{"CEP", null, "exclusivo"}
			}, true},
			
			// testOrExclusivoFalso
			{new Object[][] {
				{"Rua", null, "exclusivo"},
				{"Cidade", "Brasília", "exclusivo"},
				{"Estado", "Distrito Federal", "exclusivo"},
				{"CEP", null}
			}, false},
			
			//testOrExclusivoAninhadoVerdadeiro
			{new Object[][] {
				{"CPF", null, "exclusivo"},
				{"Matrícula", null, "exclusivo"},
				{"Sexo", "M", "exclusivo"},
				{"Email", null, "exclusivo"},
				{"Nome", new Object[][] {
					{"PrimeiroNome", null, "exclusivo"},
					{"NomeMeio", null, "exclusivo"},
					{"UltimoNome", null, "exclusivo"}
				}}
			}, true},
			
			//testOrExclusivoAninhadoFalso
			{new Object[][] {
				{"CPF", null, "exclusivo"},
				{"Matrícula", null, "exclusivo"},
				{"Sexo", "M", "exclusivo"},
				{"Email", null, "exclusivo"},
				{"Nome", new Object[][] {
					{"PrimeiroNome", "Victor", "exclusivo"},
					{"NomeMeio", null, "exclusivo"},
					{"UltimoNome", null, "exclusivo"}
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
		assertEquals(completude.checarCompletudeOrExclusivo(), resultado);
	}
}
