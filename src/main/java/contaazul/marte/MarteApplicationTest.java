package contaazul.marte;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MarteApplicationTest {

	@Test
	public String testConsultar() {
		String codigo = "MMMR";
		if((!codigo.contains("L") && !codigo.contains("R") && !codigo.contains("M"))) {
			return "erroCodigo";
		}
		
		if(codigo.length() > 1) {
			String[] comandoAux = codigo.split("(?<=[RL])");
			String[] posicao = {"N", "E", "S", "W"};
			int contadorPosicao = 0;
		
			System.out.println("inicio");
			for(String x : comandoAux) {
				System.out.println("nati: " + x);
			}
			System.out.println("fim");
			System.out.println("tam: " + comandoAux.length);
			
			int posicaoX = 0;
			int posicaoY = 0;
			int posicaoMaxima = 5;
			List<String> list = null;
			
			for(String y : comandoAux) {
				int contadorDuplicadoM = 0;
				
 				for (int z = 0; z < y.length(); z++) {
				    if (y.charAt(z) == 'M') {
				    	contadorDuplicadoM++;
				    }
				}
				if(contadorDuplicadoM == codigo.length()) {
					return "erroDuplicado";
				}
				
				if(y.charAt(y.length()-1) == 'R') {
			    	posicaoX = posicaoX + contadorDuplicadoM;
			    	if(posicaoY <= posicaoMaxima) {
			    	list = Arrays.asList(posicao);
			        Collections.rotate(list, -1);
			        System.out.println("Rotated List: " + list);
			        System.out.println(posicaoX + ", " + posicaoY + ", " + list.get(contadorPosicao));
			    	}
			    	else {
			    		return "erroPosicao";
			    	}
			        
			    }
			    else if(y.charAt(y.length()-1) == 'L') {
			    	posicaoY = posicaoY + contadorDuplicadoM;
			    	if(posicaoY <= posicaoMaxima) {
				    	list = Arrays.asList(posicao);
				        Collections.rotate(list, 1);
				        System.out.println("Rotated List: " + list);
				        System.out.println(posicaoX + ", " + posicaoY + ", " + list.get(contadorPosicao));
				    }
			    	else {
			    		return "erroPosicao";
			    	}
			        
			    }
			}
			return "(" + posicaoX + ", " + posicaoY + ", " + list.get(contadorPosicao) + ")";
		}
		
		return "";
	}
}
