package completude;

import java.util.HashMap;
import java.util.Map;

public class Completude {
	// estrutura para armazenar os dados (estrutura de dados chave - valor, ex: hash)
	private Map<String, Object> campos = new HashMap<>();
	private Map<String, Object> tipos = new HashMap<>();
	private Map<String, Object> pai = new HashMap<>();
	
	public Completude(Object[][] campos) throws IllegalArgumentException {
		for(Object[] campo : campos) {
			String chave = (String) campo[0];
			Object valor = campo[1];
			String tipo = (campo.length > 2) ? ((String) campo[2]) : null;
			
			if(chave == null) throw new IllegalArgumentException();
			
			if(valor instanceof Object[][]) {
				Object[][] subcampo = (Object[][]) valor;
				Completude campoAninhado = new Completude(subcampo);
				this.campos.put(chave, campoAninhado);
				for(Map.Entry<String, Object> filho : campoAninhado.campos.entrySet()) {
					campoAninhado.pai.put(filho.getKey(), chave);
				}
			}
			else {
				this.tipos.put(chave, tipo);
				this.campos.put(chave, valor);
			}
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
			else {
				System.out.println(entry.getKey() + ": " + entry.getValue() + " ---->>> " + camposAninhados.tipos.get(entry.getKey()) + " \\\\ meu pai: " + camposAninhados.pai.get(entry.getKey()));
			}
		}
	}
	
    // método para verificar se um campo atômico está completo
	public boolean checarCampoAtomico(Object campo) {
		return (campo == null)?false:true;
	}
	
	// método para verificar se um campo composto está completo (recursivo)
	public boolean checarCompletudeOrExclusivo() {
		return checarCompletudeOrExclusivo(this);
	}
	
	public boolean checarCompletudeOrExclusivo(Completude camposAninhados) {
		boolean resultado = false;
		
		for (Map.Entry<String, Object> entry : camposAninhados.campos.entrySet()) {
			Object value = entry.getValue();
			
			if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				resultado = resultado ^ checarCompletudeOrExclusivo(subcampo);
			}
			else 
				resultado = resultado ^ checarCampoAtomico(value);
		}
		
		return resultado;
	}

	// método para verificar se um campo é orinclusivo (recursivo)
	public boolean checarCompletudeOrInclusivo() {
		return checarCompletudeOrInclusivo(this);
	}
	
	public boolean checarCompletudeOrInclusivo(Completude camposAninhados) {
		boolean resultado = false;
		
		for (Map.Entry<String, Object> entry : camposAninhados.campos.entrySet()) {
			Object value = entry.getValue();
			
			if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				resultado = resultado || checarCompletudeOrInclusivo(subcampo);
			}
			else if(checarCampoAtomico(value)) {
				resultado = true;
			}
		}
		
		return resultado;
	}
	
	// método para cálculo de registro multi-campos
	public double calcularCompletudeMultiCampos() {
		return calcularCompletudeMultiCampos(this) * 100;
	}
	public double calcularCompletudeMultiCampos(Completude camposAninhados) {
		double preenchimento = 0.0;
		boolean inclusivo = false, exclusivo = false, orfao = true;
		int camposContados = 0;
		double retorno = 0;
		
		for(Map.Entry<String, Object> campo : camposAninhados.campos.entrySet()) {
			Object value = campo.getValue();
			String key = (String) campo.getKey();
			String pai = (String) camposAninhados.pai.get(key);
			String tipo = (String) camposAninhados.tipos.get(key);
			orfao = true;
			
			if(value instanceof Completude && !key.equals("PublisherJournal")) {
				Completude subcampo = (Completude) value;
				
				retorno = calcularCompletudeMultiCampos(subcampo);
				
				if(key.equals("authors")) {
					preenchimento += (retorno /= (subcampo.campos.size()));
					camposContados++;
				}
				else preenchimento += retorno;
				
				
			} else if(tipo != null) {
				if(pai == null && tipo.equals("atomico")) {
					camposContados++;
					if(checarCampoAtomico(value)) preenchimento++;
				}
				else if(tipo.equals("inclusivo") && isNumeric(pai) && value != null) {
					inclusivo = true; orfao = false;
				}
				else if(tipo.equals("exclusivo") && isNumeric(pai)) {
					exclusivo = exclusivo ^ checarCampoAtomico(value); orfao = false;
				}
			} else if(pai != null && isNumeric(pai)) {
				orfao = false;
			}
		}
		
		if(!orfao) camposContados+=2;
		if(inclusivo) {preenchimento++;}
		if(exclusivo) {preenchimento++;}
		
		return ((camposContados == 0) ? preenchimento : ((double)preenchimento/camposContados));
	}
	
	
//	public double calcularCompletudeMultiCampos() {
//		int totalCampos = campos.size();
//		int camposPreenchidos = contarCamposPreenchidos();
//		return ((double) camposPreenchidos / totalCampos) * 100;
//	}
	
//	private int contarCamposPreenchidos() {
//		int camposPreenchidos = 0;
//		for (Object valor : campos.values()) {
//			if (valor != null) {
//				camposPreenchidos++;
//			}
//		}
//		return camposPreenchidos;
//	}

	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}

}
