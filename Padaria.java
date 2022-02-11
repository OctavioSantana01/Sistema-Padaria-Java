import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class Padaria extends JFrame
        implements ActionListener {

    JTable tabela, tabela2, tabela3, tabela4;
    JScrollPane painel, painel2, painel3, painel4;
    FileInputStream arquivo;
    DataInputStream leitor;
    String[][] dados;
    String[] nomesColunas;
    JButton botaoCliente, botaoProdutos, botaoVendas, botaoQuestionario;

    public Padaria() {
        setLayout(null);
        setTitle("Panificadora Real(Vendas)");
        setBounds(0, 0, 640, 330);
        setResizable(false);
        setBackground(Color.black);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getData("planilha.csv");

        tabela = new JTable(dados, nomesColunas);
        getData("planilha2.csv");
        tabela2 = new JTable(dados, nomesColunas);
        getData("planilha3.csv");
        tabela3 = new JTable(dados, nomesColunas);
        getData("planilha4.csv");
        tabela4 = new JTable(dados, nomesColunas);
        painel = new JScrollPane(tabela);
        painel2 = new JScrollPane(tabela2);
        painel3 = new JScrollPane(tabela3);
        painel4 = new JScrollPane(tabela4);
        botaoCliente = new JButton("Clientes");
        botaoProdutos = new JButton("Produtos");
        botaoVendas = new JButton("Vendas");
        botaoQuestionario = new JButton("Nota Empresa");
        botaoCliente.setFocusable(false);
        botaoProdutos.setFocusable(false);
        botaoCliente.setBounds(15, 210, 150, 40);
        botaoCliente.setBackground(Color.gray);
        botaoCliente.setForeground(Color.white);
        botaoProdutos.setBackground(Color.gray);
        botaoProdutos.setForeground(Color.white);
        botaoProdutos.setBounds(440, 210, 150, 40);
        botaoVendas.setBounds(230, 210, 150, 40);
        botaoVendas.setBackground(Color.gray);
        botaoVendas.setForeground(Color.white);
        botaoCliente.addActionListener(this);
        botaoProdutos.addActionListener(this);
        botaoVendas.addActionListener(this);
        botaoQuestionario.addActionListener(this);

        painel.setBounds(0, 0, 640, 400);
        getContentPane().add(botaoCliente);
        getContentPane().add(botaoProdutos);
        getContentPane().add(painel);

    }

    public void getData(String planilha) {
        int caractere = 0, linha = 0;
        boolean cabecalho = true;
        String buffer = "";

        dados = new String[10][5];//CONTROLE DE LINHAS E COLUNAS NA TELA
        nomesColunas = new String[5];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                dados[i][j] = "";
            }
        }
        for (int i = 0; i < 5; i++) {
            nomesColunas[i] = "";
        }

        try {
            arquivo = new FileInputStream(planilha);
            leitor = new DataInputStream(arquivo);

            while (caractere != -1) {
                caractere = leitor.read();

                if (caractere != 10 && caractere != -1) {
                    buffer += (char) caractere;
                }

                if (caractere == 10) {
                    System.out.println(buffer);
                    if (cabecalho) {
                        nomesColunas = buffer.split(";");
                        cabecalho = false;
                        buffer = "";
                    } else {
                        dados[linha] = buffer.split(";");
                        linha++;
                        buffer = "";
                    }
                }
            }
            arquivo.close();
        } catch (IOException erro) {
            System.out.println("Arquivo nao existe...");
        }
    }

    public static void main(String args[]) {
        JFrame janela = new Padaria();
        janela.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botaoCliente) {
            setTitle("Panificadora Real(Clientes)");
            botaoVendas.setBounds(20, 210, 150, 40);
            botaoVendas.setBackground(Color.gray);
            botaoVendas.setForeground(Color.white);
            botaoVendas.setFocusable(false);
            getContentPane().remove(botaoCliente);
            getContentPane().add(botaoVendas);
            getContentPane().remove(painel);
            getContentPane().remove(painel3);
            getContentPane().remove(painel4);
            getContentPane().add(painel2);
            painel2.setBounds(0, 0, 640, 400);
            getContentPane().repaint();
            getContentPane().add(painel2);

        }

        if (evento.getSource() == botaoVendas) {
            setTitle("Panificadora Real(Vendas)");
            botaoCliente.setBounds(20, 210, 150, 40);
            botaoCliente.setBackground(Color.gray);
            botaoCliente.setForeground(Color.white);
            botaoCliente.setFocusable(false);
            getContentPane().remove(botaoVendas);
            getContentPane().add(botaoCliente);
            getContentPane().remove(painel);
            getContentPane().remove(painel2);
            getContentPane().remove(painel3);
            painel3.setBounds(0, 0, 640, 400);
            getContentPane().repaint();
            getContentPane().add(painel3);

        }
        if (evento.getSource() == botaoProdutos) {
            setTitle("Panificadora Real(Produtos)");
            botaoQuestionario.setBounds(440, 210, 150, 40);
            botaoQuestionario.setBackground(Color.gray);
            botaoQuestionario.setForeground(Color.white);
            botaoQuestionario.setFocusable(false);
            getContentPane().remove(botaoProdutos);
            getContentPane().add(botaoQuestionario);
            getContentPane().remove(painel4);
            getContentPane().remove(painel3);
            getContentPane().remove(painel2);
            getContentPane().add(painel);
            painel.setBounds(0, 0, 640, 400);
            getContentPane().repaint();

        }
        if (evento.getSource() == botaoQuestionario) {
            setTitle("Panificadora Real(Questionario)");
            botaoProdutos.setBounds(440, 210, 150, 40);
            botaoProdutos.setBackground(Color.gray);
            botaoProdutos.setForeground(Color.white);
            botaoProdutos.setFocusable(false);
            getContentPane().remove(botaoQuestionario);
            getContentPane().add(botaoProdutos);
            getContentPane().remove(painel);//TABELA CLIENTES
            getContentPane().remove(painel2);
            getContentPane().remove(painel3);
            getContentPane().add(painel4);
            painel4.setBounds(0, 0, 640, 400);
            getContentPane().repaint();

        }

    }

}

