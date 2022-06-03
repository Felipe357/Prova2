package viewers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlles.OrçaentoProcess;
import models.Orçamento;

public class OrçamentoForm extends JFrame implements ActionListener{
	
	private JPanel painel;
	private JLabel id, fornecedor, produto, preco;
	private JTextField tfid, tffornecedor, tfproduto, tfpreco;
	private JTextArea vertexto;
	private JButton adicionar, buscar, alterar, excluir;
	//
	private int autoId = OrçaentoProcess.orcamentos.size() + 1;
	private String texto = "";
	
	OrçamentoForm(){
		setTitle("Registro de Orçamentos");
		setBounds(450, 200, 450, 500);
		painel = new JPanel();
		painel.setBackground(new Color(199, 199, 199));
		setContentPane(painel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		id = new JLabel("*Produto");
		id.setBounds(20, 50, 120, 30);
		tfid = new JTextField(String.format("%d", autoId));
		tfid.setBounds(130, 55, 155, 30);
		tfid.setEditable(false);
		
		fornecedor = new JLabel("*Fornecedor:");
		fornecedor.setBounds(20, 90, 220, 30);
		tffornecedor = new JTextField();
		tffornecedor.setBounds(130, 95, 155, 30);
		
		produto = new JLabel("*Produto:");
		produto.setBounds(20, 130, 120, 30);
		tfproduto = new JTextField();
		tfproduto.setBounds(130, 135, 155, 30);
		
		preco = new JLabel("*Preço:");
		preco.setBounds(20, 175, 120, 30);
		tfpreco = new JTextField();
		tfpreco.setBounds(130, 175, 155, 30);
		
		vertexto = new JTextArea();
		vertexto.setBounds(10, 250, 410, 200);
		vertexto.setEnabled(false);
		vertexto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		comprar();
		listarTodos();
		
		adicionar = new JButton("Cadastrar");
		buscar = new JButton("Buscar");
		alterar = new JButton("Alterar");
		excluir = new JButton("Apagar");
		adicionar.setBounds(310, 55, 110, 30);
		buscar.setBounds(310, 95, 110, 30);
		alterar.setBounds(310, 135, 110, 30);
		alterar.setEnabled(false);
		excluir.setBounds(310, 175, 110, 30);
		excluir.setEnabled(false);
		
		buscar.addActionListener(this);
		adicionar.addActionListener(this);
		excluir.addActionListener(this);
		alterar.addActionListener(this);
		
		painel.add(id);
		painel.add(tfid);
		painel.add(fornecedor);
		painel.add(tffornecedor);
		painel.add(produto);
		painel.add(tfproduto);
		painel.add(preco);
		painel.add(tfpreco);
		painel.add(vertexto);
		//
		painel.add(adicionar);
		painel.add(buscar);
		painel.add(alterar);
		painel.add(excluir);
		
	}
	
	private void adicionar() {
		
		if (tffornecedor.getText().length() != 0 && tfproduto.getText().length() !=0 && tfpreco.getText().length() !=0) {
			
			OrçaentoProcess.orcamentos.add(new Orçamento(autoId, tffornecedor.getText().toString(), tfproduto.getText().toString(),
					Double.parseDouble(tfpreco.getText().toString()), false));
			
		} else {
			JOptionPane.showMessageDialog(this, "Favor Preencher todos as informaçõees");
		}
		
		autoId = OrçaentoProcess.orcamentos.size() + 1;
		limpar();
		comprar();
		listarTodos();
		OrçaentoProcess.salvar();
		
	}
	
	private void buscar() {
		String entrada = JOptionPane.showInputDialog(this,"Digite o c�ódigo do produto:");
		
		boolean isNumeric = true;
		if (entrada != null) {
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isDigit(entrada.charAt(i))) {
					isNumeric = false;
				}
			}
		}else {
			isNumeric = false;
		}
		
		if (isNumeric) {
			
			int id = Integer.parseInt(entrada);
			boolean achou = false;
			
			for (Orçamento oc : OrçaentoProcess.orcamentos) {
				if (oc.getId() == id) {
					achou = true;
					int indice = OrçaentoProcess.orcamentos.indexOf(oc);
					tfid.setText(OrçaentoProcess.orcamentos.get(indice).getId("s"));
					tffornecedor.setText(OrçaentoProcess.orcamentos.get(indice).getFornecedor());
					tfproduto.setText(OrçaentoProcess.orcamentos.get(indice).getProduto());
					tfpreco.setText(OrçaentoProcess.orcamentos.get(indice).getPreco("s").replace(",", "."));
					OrçaentoProcess.salvar();
					adicionar.setEnabled(false);
					alterar.setEnabled(true);
					excluir.setEnabled(true);
					break;
				}
			}
			
			if (!achou) {
				JOptionPane.showMessageDialog(this, "Orçamento não encontrado");
			}
		}
	}
	
	private void alterar() {
		
		int id = Integer.parseInt(tfid.getText());
		int indice = -1;
		
		for (Orçamento oc : OrçaentoProcess.orcamentos) {
			if (oc.getId() == id ) {
				indice = OrçaentoProcess.orcamentos.indexOf(oc);
			}
		}
		
		if (tffornecedor.getText().length() != 0 && tfproduto.getText().length() !=0 && tfpreco.getText().length() !=0) {
			OrçaentoProcess.orcamentos.set(indice, new Orçamento(autoId, tffornecedor.getText().toString(), tfproduto.getText().toString(),
					Double.parseDouble(tfpreco.getText().toString()), false));
			listarTodos();
			limpar();
		}else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
		adicionar.setEnabled(true);
		alterar.setEnabled(false);
		excluir.setEnabled(false);
		tfid.setText(String.format("%d", OrçaentoProcess.orcamentos.size() + 1));
		OrçaentoProcess.salvar();
		comprar();
		
	}
	
	private void excluir() {
		
		int id = Integer.parseInt(tfid.getText());
		int indice = -1;
		
		for (Orçamento manu : OrçaentoProcess.orcamentos) {
			if (manu.getId() == id) {
				indice = OrçaentoProcess.orcamentos.indexOf(manu);
			}
			
		}
		
		OrçaentoProcess.orcamentos.remove(indice);
		listarTodos();
		limpar();
		adicionar.setEnabled(true);
		alterar.setEnabled(false);
		excluir.setEnabled(false);
		comprar();
		OrçaentoProcess.salvar();
		tfid.setText(String.format("%d", OrçaentoProcess.orcamentos.size() + 1));
	}
	
	private void listarTodos() {
		texto = "";
		for (Orçamento p : OrçaentoProcess.orcamentos) {
			texto += p.toString()+"\n";
		}
		vertexto.setText(texto);
	}
	
	private void limpar() {
		tffornecedor.setText(null);
		tfproduto.setText(null);
		tfpreco.setText(null);
	}
	
	public void comprar() {
		for (Orçamento orcamento : OrçaentoProcess.orcamentos) {
			OrçaentoProcess.comprarPrdutos(orcamento.getProduto());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adicionar) {
			adicionar();
		}
		if (e.getSource() == buscar) {
			buscar();
		}
		if (e.getSource() == alterar) {
			alterar();
		}
		if (e.getSource() == excluir) {
			excluir();
		}
		
	}
	
	public static void main(String[] args) {

		OrçaentoProcess.abrir();
		OrçamentoForm or = new OrçamentoForm();
		or.setVisible(true);
	}

}
