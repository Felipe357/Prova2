package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrçamentoDAO {

	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = ".\\Dados\\Orçamentos.csv";

	public ArrayList<Orçamento> ler() {
		ArrayList<Orçamento> linhas = new ArrayList<>();
		Orçamento or;
		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			while(linha != null) {
				or = new Orçamento(linha);
				linhas.add(or);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return linhas;
	}
	
	public void escrever(ArrayList<Orçamento> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(arquivo));
			for (Orçamento p : linhas) {
				bw.write(p.tocsv()+"\r\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
}
