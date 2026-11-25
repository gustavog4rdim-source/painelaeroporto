package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entities.enums.StatusVoo;
import exception.BusinessException;

public class Voo {

	private String numeroVoo;
	private String companhiaAerea;
	private LocalDateTime horarioEmbarque;
	private LocalDateTime horarioSaida;
	private String destino;
	private StatusVoo status;

	public static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public Voo(String numeroVoo, String companhiaAerea, LocalDateTime horarioEmbarque, LocalDateTime horarioSaida,
			String destino) {
		verificarDados(numeroVoo, "Número do Voo");
		verificarDados(companhiaAerea, "Companhia Aérea");
		verificarDados(destino, "Destino");
		verificarHorario(horarioEmbarque, horarioSaida);
		this.numeroVoo = padronizarTexto(numeroVoo);
		this.companhiaAerea = padronizarTexto(companhiaAerea);
		this.horarioEmbarque = horarioEmbarque;
		this.horarioSaida = horarioSaida;
		this.destino = padronizarTexto(destino);
		this.status = StatusVoo.PREVISTO;
	}

	public String getNumeroVoo() {
		return numeroVoo;
	}

	public String getCompanhiaAerea() {
		return companhiaAerea;
	}

	public void alterarCompanhiaAerea(String companhiaAerea) {
		verificarDecolagem();
		verificarDados(companhiaAerea, "Companhia Aérea");
		this.companhiaAerea = padronizarTexto(companhiaAerea);
	}

	public LocalDateTime getHorarioEmbarque() {
		return horarioEmbarque;
	}

	public void alterarHorarios(LocalDateTime horarioEmbarque, LocalDateTime horarioSaida) {
		verificarDecolagem();
		verificarHorario(horarioEmbarque, horarioSaida);
		this.horarioEmbarque = horarioEmbarque;
		this.horarioSaida = horarioSaida;
	}

	public LocalDateTime getHorarioSaida() {
		return horarioSaida;
	}

	public String getDestino() {
		return destino;
	}

	public void alterarDestino(String destino) {
		verificarDecolagem();
		verificarDados(destino, "Destino");
		this.destino = padronizarTexto(destino);
	}

	// "DECOLOU" é o último status possível
	public StatusVoo avancarStatus() {
		verificarDecolagem();
		StatusVoo[] lista = StatusVoo.values();
		this.status = lista[status.ordinal() + 1];
		return status;
	}

	private void verificarHorario(LocalDateTime horarioEmbarque, LocalDateTime horarioSaida) {
		if (horarioEmbarque == null || horarioSaida == null) {
			throw new BusinessException("Horário ausente, insira um novo horário onde a saída é depois do Embarque");
		}
		if(horarioSaida.equals(horarioEmbarque)) {
			throw new BusinessException("Horários iguais são inválidos, o embarque deve ser antes da saída");
		}
		if (!horarioEmbarque.isBefore(horarioSaida)) {
			throw new BusinessException("horário de saída inserido antes do embarque, insira um horário de saída após o embarque");
		}
	}

	private void verificarDecolagem() {
		if (status == StatusVoo.DECOLOU) {
			throw new BusinessException("O Voo já decolou, alterações impossibilitadas");
		}
	}

	private void verificarDados(String entrada, String campo) {
		if (entrada == null || entrada.isBlank()) {
			throw new BusinessException("Erro ao inserir uma informação inválida em " + campo);
		}
	}

	public static String padronizarTexto(String entrada) {
		entrada = entrada.trim();
		entrada = entrada.toUpperCase();

		return entrada;
	}

	@Override
	public String toString() {
		return numeroVoo + ", " + companhiaAerea + ", " + FMT.format(horarioEmbarque) + ", " + FMT.format(horarioSaida)
				+ ", " + destino + ", " + status;
	}

}
