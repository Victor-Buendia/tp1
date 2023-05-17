package teste;

import static org.junit.Assert.*;

import org.junit.Test;


import completude.Completude;

public class TesteCompletudeOrInclusivo {

    @Test
	public void testCompletudeOrInclusivoFalso() {
		Object[][] dados = {
				{"CPF", null},
				{"Matricula", null},
				{"Sexo", null},
                {"Email", null}
			};
		Completude c = new Completude(dados);
		assertFalse(c.checarCompletudeOrInclusivo());
	}

    @Test
	public void testCompletudeOrInclusivoVerdadeiroAtomico() {
		Object[][] dados = {
				{"CPF", null},
				{"Matricula", "20/202020"},
				{"Sexo", "Masculino"},
                {"Email", null}
			};
		Completude c = new Completude(dados);
		assertTrue(c.checarCompletudeOrInclusivo());
	}
}