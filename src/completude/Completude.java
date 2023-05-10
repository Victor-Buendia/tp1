package completude;

import java.util.HashMap;
import java.util.Map;

public class Completude {
	// estrutura para armazenar os dados (estrutura de dados chave - valor, ex: hash)
	private Map<String, Object> campos = new HashMap<>();
	
	// método para adicionar valores aos dados
	public void criaCampo(String chave, Object valor) {
		campos.put(chave, valor);
	}
	
	public void imprimeCampos() {
		for (Map.Entry<String, Object> entry : campos.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	
    // método para verificar se um campo atômico está completo
	
	// método para verificar se um campo composto está completo (recursivo)
	
	// método principal para cálculo da completude (geral)
}
