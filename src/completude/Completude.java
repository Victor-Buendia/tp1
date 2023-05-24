package completude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Completude {
	// estrutura para armazenar os dados (estrutura de dados chave - valor, ex: hash)
	private Map<String, Object> campos = new HashMap<>();
	private Map<String, Object> tipos = new HashMap<>();
	private Map<String, Object> pai = new HashMap<>();
	
	public Completude(Object[][] campos) {
		for(Object[] campo : campos) {
			String chave = (String) campo[0];
			Object valor = campo[1];
			String tipo = (campo.length > 2) ? ((String) campo[2]) : null;
			
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
		return checarCompletude(this);
	}
	
	public boolean checarCompletudeOrInclusivo(Completude camposAninhados) {
		
		for (Map.Entry<String, Object> entry : camposAninhados.campos.entrySet()) {
			Object value = entry.getValue();

			if(value instanceof Completude) {
				Completude subcampo = (Completude) value;
				if(checarCompletudeOrInclusivo(subcampo)) {
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
		return calcularCompletudeMultiCampos(this) * 100;
	}
	public double calcularCompletudeMultiCampos(Completude camposAninhados) {
		double preenchimento = 0.0;
		boolean inclusivo = false, exclusivo = false, orfao = true;
		double camposContados = 0.0;
		double retorno = 0;
		
		for(Map.Entry<String, Object> campo : camposAninhados.campos.entrySet()) {
			Object value = campo.getValue();
			String key = (String) campo.getKey();
			String pai = (String) camposAninhados.pai.get(key);
			String tipo = (String) camposAninhados.tipos.get(key);
			orfao = true;
			
			if(value instanceof Completude && !key.equals("PublisherJournal")) {
				Completude subcampo = (Completude) value;
				System.out.println("PREENCHI ANTES " + preenchimento);
				preenchimento += calcularCompletudeMultiCampos(subcampo);
				System.out.println("RETORNOOOOOOO " + preenchimento);
				if(key.equals("authors")) {
					preenchimento += (preenchimento /= (subcampo.campos.size()));
					camposContados+=1.0;
				}
				
				
			} else if(tipo != null) {
				if(pai == null && tipo.equals("atomico")) {
					camposContados+=1.0;
					System.out.println("oasoddsoaso");
					if(checarCampoAtomico(value)) preenchimento++;
				}
				else if(tipo.equals("inclusivo") && isNumeric(pai) && value != null) {
					inclusivo = true; orfao = false;
					System.out.println("EUEUUEUEUEUEU");
				}
				else if(tipo.equals("exclusivo") && isNumeric(pai)) {
					exclusivo = exclusivo ^ checarCampoAtomico(value); orfao = false;
					System.out.println("DSAKSDKSAKDSKAKD");
				}
			} else if(pai != null && isNumeric(pai)) {
				orfao = false;
			}
		}
		
		if(!orfao) camposContados+=2.0;
		if(inclusivo) {preenchimento+=1.0;}
		if(exclusivo) {preenchimento+=1.0;}
		System.out.println(camposAninhados + "--" + ((camposContados == 0.0) ? 0.0 : ((float)preenchimento/camposContados)));
		for(Map.Entry<String, Object> campo : camposAninhados.campos.entrySet()) {
			if(camposAninhados.tipos.get(campo.getKey()) != null) {
				System.out.println(campo.getKey() + ": " + campo.getValue());
			}
		}
		System.out.println("\n\n");
		System.out.println("preeeeee: " + preenchimento + "  ===== contados: " + camposContados);
		return ((camposContados == 0.0) ? 0.0 : ((double)preenchimento/camposContados*500));
		//(camposContados == 0) ? 0 : (preenchimento/camposContados)
	}
	
	
//	public double calcularCompletudeMultiCampos() {
//		int totalCampos = campos.size();
//		int camposPreenchidos = contarCamposPreenchidos();
//		return ((double) camposPreenchidos / totalCampos) * 100;
//	}
	
	private int contarCamposPreenchidos() {
		int camposPreenchidos = 0;
		for (Object valor : campos.values()) {
			if (valor != null) {
				camposPreenchidos++;
			}
		}
		return camposPreenchidos;
	}

	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}

}
