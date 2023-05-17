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

    @Test
	public void testCompletudeOrInclusivoVerdadeiroComposto() {
		Object[][] subdados = {
				{"PrimeiroNome", "joão"},
				{"NomeMeio", null},
				{"UltimoNome", null}
			};
		Object[][] dados = {
				{"CPF", null},
				{"Matricula", "20/202020"},
				{"Sexo", "Masculino"},
                {"Email", null},
                {"Nome", subdados}
			};
		Completude c = new Completude(dados);
		assertTrue(c.checarCompletudeOrInclusivo());
	}

    @Test
	public void testCompletudeOrInclusivoVerdadeiroCompostoFalso() {
		Object[][] subdados = {
				{"PrimeiroNome", null},
				{"NomeMeio", null},
				{"UltimoNome", null}
			};
		Object[][] dados = {
				{"CPF", null},
				{"Matricula", null},
				{"Sexo", null},
                {"Email", null},
                {"Nome", subdados}
			};
		Completude c = new Completude(dados);
		assertFalse(c.checarCompletudeOrInclusivo());
	}

    
}