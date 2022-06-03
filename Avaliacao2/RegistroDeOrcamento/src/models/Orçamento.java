package models;

import java.util.Objects;

public class Orçamento {
	
	private int id;
	private String fornecedor;
	private String produto;
	private double preco;
	private boolean maisBarato;
	
	public Orçamento(int id, String fornecedor, String produto, double preco, boolean maisBarato) {
		super();
		this.id = id;
		this.fornecedor = fornecedor;
		this.produto = produto;
		this.preco = preco;
		this.maisBarato = maisBarato;
	}


	public Orçamento(String linha) {
		this.id = Integer.parseInt(linha.split(";")[0]);
		this.fornecedor = linha.split(";")[1];
		this.produto = linha.split(";")[2];
		this.preco = Double.parseDouble(linha.split(";")[3]);
		this.maisBarato = false;
	}


	public String getId(String s) {
		return String.format("%d", id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getProduto() {
		return produto;
	}
	
	public String getPreco(String s) {
		return String.format("%.2f", preco);
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isMaisBarato() {
		return maisBarato;
	}

	public void setMaisBarato(boolean maisBarato) {
		this.maisBarato = maisBarato;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(fornecedor, id, maisBarato, preco, produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orçamento other = (Orçamento) obj;
		return Objects.equals(fornecedor, other.fornecedor) && id == other.id && maisBarato == other.maisBarato
				&& Double.doubleToLongBits(preco) == Double.doubleToLongBits(other.preco)
				&& Objects.equals(produto, other.produto);
	}

	@Override
	public String toString() {
		return  id + "\t" + fornecedor + "\t" + produto + "\t" + String.format("%.2f",preco) + "\t" + maisBarato;
	}
	
	
	public String tocsv() {
		return  id + ";" + fornecedor + ";" + produto + ";" + preco + ";" + maisBarato;
	}


}
