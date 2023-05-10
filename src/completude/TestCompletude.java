package completude;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestCompletude {
	
	Completude dados;

	@Before
	public void setUp() throws Exception {
		dados = new Completude();
		return;
	}
	
	@Test
	public void test() {
		dados.criaCampo("teste", true);
		dados.imprimeCampos();
	}

	// Calculo da completude de campos OR EXCLUSIVO
	// Fazer a falsificação, duplicação e triangulação
	// Finalizar parametrizando os testes
	@Test
	public void test1() {
		fail("Calculo da completude de campos OR EXCLUSIVO");
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
