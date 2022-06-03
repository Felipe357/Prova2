package controlles;

import java.util.ArrayList;

import models.Orçamento;
import models.OrçamentoDAO;

public class OrçaentoProcess {

	public static ArrayList<Orçamento> orcamentos = new ArrayList<>();
	private static OrçamentoDAO od = new OrçamentoDAO();
	
	public static void comprarPrdutos(String produto) {
		int index = 0;
		double maisBarato = 999999999;
		for (Orçamento orcamento : orcamentos) {
			if (orcamento.getProduto().equals(produto) && orcamento.getPreco() < maisBarato) {
				index = orcamentos.indexOf(orcamento);
				maisBarato = orcamento.getPreco();
			}
		}
		
		for (Orçamento orcamento : orcamentos) {
			if (orcamentos.indexOf(orcamento) == index) {
				orcamento.setMaisBarato(true);
			} else if(orcamento.getProduto() == produto){
				orcamento.setMaisBarato(false);
			}
		}
	}
	
	public static void abrir() {
		orcamentos = od.ler();
	}
	
	public static void salvar() {
		od.escrever(orcamentos);
	
	}
	
	
}
