package completude;

import java.util.HashMap;
import java.util.Map;

public class Completude {
	// estrutura para armazenar os dados (estrutura de dados chave - valor, ex: hash)
	private Map<String, Object> campos = new HashMap<>();
	
	public Completude(Object[][] campos) {
		for(Object[] campo : campos) {
			String chave = (String) campo[0];
			Object valor = campo[1];
			
			if(valor instanceof Object[][]) {
				Object[][] subcampo = (Object[][]) valor;
				Completude campoAninhado = new Completude(subcampo);
				this.campos.put(chave, campoAninhado);
			}
			else this.campos.put(chave, valor);
		}
	}
	
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
			
			if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				System.out.println(entry.getKey() + "--------------");
				imprimeCampos(subcampo);
				System.out.println("--------------");
			}
			else System.out.println(entry.getKey() + ": " + entry.getValue());
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

	// método para verificar se um campo é orinclusivo (recursivo)
	public boolean checarCompletudeOrInclusivo() {
		
		for (Map.Entry<String, Object> entry : campos.entrySet()) {
			Object value = entry.getValue();

			if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				if(subcampo.checarCompletudeOrInclusivo()) {
					return true;
				}
			}
			else if(checarCampoAtomico(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	// método para cálculo de registro multi-campos
	public double calcularCompletudeMultiCampos() {
		int totalCampos = campos.size();
		int camposPreenchidos = contarCamposPreenchidos();
		return ((double) camposPreenchidos / totalCampos) * 100;
	}
	
	private int contarCamposPreenchidos() {
		int camposPreenchidos = 0;
		for (Object valor : campos.values()) {
			if (valor != null) {
				camposPreenchidos++;
			}
		}
		return camposPreenchidos;
	}
}
