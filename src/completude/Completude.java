package completude;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Completude {
	// estrutura para armazenar os dados (estrutura de dados chave - valor, ex: hash)
	private Map<String, Object> campos = new HashMap<>();
	
	// método para adicionar valores aos dados
	public void criaCampo(String chave, Object valor) {
		campos.put(chave, valor);
	}
	
	public void imprimeCampos() {
		imprimeCamposRec(this.campos.entrySet());
	}
	
	public void imprimeCamposRec(Set<Map.Entry<String,Object>> campos) {
		for (Map.Entry<String, Object> entry : campos) {
			Object value = entry.getValue();
			
			if (value == null)
				continue;
			else if(value instanceof String)
				System.out.println(entry.getKey() + ": " + entry.getValue());
			else if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				imprimeCamposRec(subcampo.campos.entrySet());
			}
		}
	}
	
	
    // método para verificar se um campo atômico está completo
	
	// método para verificar se um campo composto está completo (recursivo)
	
	// método principal para cálculo da completude (geral)
}
