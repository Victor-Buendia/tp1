package completude;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestCompletude {
	
	Completude pessoaFisica;
	Completude nome;
	Completude endereco;

	@Before
	public void setUp() throws Exception {
		pessoaFisica = new Completude();
		nome = new Completude();
		endereco = new Completude();
		return;
	}
	
	@Test
	public void testCriacaoCampos() {
		nome.criaCampo("PrimeiroNome", "Caio");
		nome.criaCampo("NomeMeio","Cesar");
		nome.criaCampo("UltimoNome","Oliveira");

		pessoaFisica.criaCampo("CPF",null);
		pessoaFisica.criaCampo("Matrícula", "2032344532");
		pessoaFisica.criaCampo("Sexo", "M");
		pessoaFisica.criaCampo("Email", "Teste@gmail.com");
		pessoaFisica.criaCampo("Nome", nome);
		
		pessoaFisica.imprimeCampos();
	}

	// Calculo da completude de campos OR EXCLUSIVO
	// Fazer a falsificação, duplicação e triangulação
	// Finalizar parametrizando os testes
	@Test
	public void testOrExclusivoVerdadeiro() {
		endereco.criaCampo("Rua", null);
		endereco.criaCampo("Cidade", null);
		endereco.criaCampo("Estado", "Distrito Federal");
		endereco.criaCampo("CEP", null);
		
		assertTrue(endereco.checarCompletude(endereco));
	}

	@Test
	public void testOrExclusivoFalso() {
		endereco.criaCampo("Rua", null);
		endereco.criaCampo("Cidade", "Brasília");
		endereco.criaCampo("Estado", "Distrito Federal");
		endereco.criaCampo("CEP", null);
		
		assertFalse(endereco.checarCompletude(endereco));
	}
	
	// Calculo da completude de campos OR INCLUSIVO
	// Fazer a falsificação, duplicação e triangulação
	// Finalizar parametrizando os testes
	@Test
	public void test2() {
		fail("Calculo da completude de campos OR INCLUSIVO");
	}

	// Cálculo da completude de registros multi-campos
	// Fazer a falsificação, duplicação e triangulação
	// Finalizar parametrizando os testes
	@Test
	public void test3() {
		fail("Cálculo da completude de registros multi-campos");
	}

	// Testes de exceção
	// Não são parametrizados
	@Test
	public void test4() {
		fail("Exceção");
	}


}
