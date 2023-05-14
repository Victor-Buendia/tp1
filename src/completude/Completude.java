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
		imprimeCampos(this);
	}
	
	public void imprimeCampos(Completude camposAninhados) {
		for (Map.Entry<String, Object> entry : camposAninhados.campos.entrySet()) {
			Object value = entry.getValue();
			
			if (value == null) continue;
			else if(value instanceof String) System.out.println(entry.getKey() + ": " + entry.getValue());
			else if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				imprimeCampos(subcampo);
			}
		}
	}
	
    // método para verificar se um campo atômico está completo
	public boolean checarCampoAtomico(Object campo) {
		return (campo == null)?false:true;
	}
	
	// método para verificar se um campo composto está completo (recursivo)
	public boolean checarCompletude() {
		return checarCompletude(this);
	}
	
	public boolean checarCompletude(Completude camposAninhados) {
		boolean resultado = false;
		
		for (Map.Entry<String, Object> entry : camposAninhados.campos.entrySet()) {
			Object value = entry.getValue();
			
			if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				resultado = resultado ^ checarCompletude(subcampo);
			}
			else 
				resultado = resultado ^ checarCampoAtomico(value);
		}
		
		return resultado;
	}
	
	// método principal para cálculo da completude (geral)
}
