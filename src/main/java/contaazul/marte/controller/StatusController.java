package contaazul.marte.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatusController {
	
	@Autowired

	@RequestMapping("/status")
	public String check() {
		return "Online";
	}
	
	@PostMapping(path = "/rest/mars/{codigo}")
	public String consultar(@PathVariable("codigo") String codigo) {
		
		if((!codigo.contains("L") && !codigo.contains("R") && !codigo.contains("M"))) {
			return "400 Bad Request";
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
					return "400 Bad Request";
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
			    		return "400 Bad Request";
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
			    		return "400 Bad Request";
			    	}
			        
			    }
			}
			return "(" + posicaoX + ", " + posicaoY + ", " + list.get(contadorPosicao) + ")";
		}
		
		return "400 Bad Request";
	}
}
