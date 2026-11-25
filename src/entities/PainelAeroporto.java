package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exception.BusinessException;

public class PainelAeroporto {

	private List<Voo> voos = new ArrayList<>();

	public boolean adicionarVoo(String numeroVoo, String companhiaAerea, LocalDateTime horarioEmbarque,
			LocalDateTime horarioSaida, String destino) {
		Voo voo = (new Voo(numeroVoo, companhiaAerea, horarioEmbarque, horarioSaida, destino));
		if (existeVoo(numeroVoo) == true) {
			throw new BusinessException("Voo já cadastrado");
		} else {
			return voos.add(voo);
		}
	}

	// Resolver posteriormente
	public void removerVoo(String numeroVoo) {

	}

	public Voo buscarVoo(String numeroVoo) {
		numeroVoo = Voo.padronizarTexto(numeroVoo);
		if (buscaPrivada(numeroVoo) == null) {
			throw new BusinessException("Voo inexistente");
		} else {
			return buscaPrivada(numeroVoo);
		}
	}

	private boolean existeVoo(String numeroVoo) {
		numeroVoo = Voo.padronizarTexto(numeroVoo);
		if (buscaPrivada(numeroVoo) == null) {
			return false;
		} else {
			return true;
		}

	}

	private Voo buscaPrivada(String numeroVoo) {
		numeroVoo = Voo.padronizarTexto(numeroVoo);
		for (Voo v : voos) {
			if (v.getNumeroVoo().equals(numeroVoo)) {
				return v;
			}
		}
		return null;
	}

	public String toString() {
		return Voo.class.toString();
	}

}
