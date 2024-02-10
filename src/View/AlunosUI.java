package View;

import Persistencia.AlunoDAO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

public class AlunosUI extends JFrame {
    public AlunosUI() {
        JPanel p1 = new JPanel(null);
        JLabel lTitulo = new JLabel("Cadastro alunos");
        JLabel lidAluno = new JLabel("ID Aluno");
        JLabel lNome = new JLabel("Nome Aluno");
        JLabel lEmail = new JLabel("Email Aluno");
        JButton bGravar = new JButton("Criar");
        JButton bDeletar = new JButton("Deletar");
        JButton bAlterar = new JButton("Alterar");
        JButton bLimpar = new JButton("Limpar");
        JButton bSair = new JButton("Sair");

        JTextField tidAluno = new JTextField(20);
        JTextField tNome = new JTextField(20);
        JTextField tEmail = new JTextField(20);

        AlunoDAO alunoDAO = new AlunoDAO();
        ArrayList<String> alunos = alunoDAO.listarAlunos();
        DefaultListModel<String> alunosList = new DefaultListModel<>();
        for (String aluno : alunos) {
            alunosList.addElement(aluno);
        }

        JList<String> lista = new JList<>(alunosList);
        JScrollPane scroll = new JScrollPane(lista);

        lTitulo.setFont(new Font("Poppins", Font.BOLD, 20));
        lTitulo.setForeground(new Color(20, 20, 20));

        p1.add(lTitulo);
        p1.add(lidAluno);
        p1.add(tidAluno);
        p1.add(lNome);
        p1.add(tNome);
        p1.add(lEmail);
        p1.add(tEmail);
        p1.add(bGravar);
        p1.add(bDeletar);
        p1.add(bAlterar);
        p1.add(bLimpar);
        p1.add(bSair);
        p1.add(scroll);

        add(p1);
        p1.setBackground(new Color(166, 166, 166));

        lTitulo.setBounds(400, 20, 200, 70);
        lidAluno.setBounds(30, 130, 100, 25);
        tidAluno.setBounds(110, 130, 350, 25);
        lNome.setBounds(30, 180, 100, 25);
        tNome.setBounds(110, 180, 350, 25);
        lEmail.setBounds(30, 230, 100, 25);
        tEmail.setBounds(110, 230, 350, 25);

        bGravar.setBounds(60, 330, 100, 23);
        bDeletar.setBounds(200, 330, 100, 23);
        bAlterar.setBounds(340, 330, 100, 23);
        bLimpar.setBounds(60, 370, 100, 23);
        bSair.setBounds(200, 370, 100, 23);
        scroll.setBounds(540, 90, 420, 300);

        setSize(1010, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - 1010) / 2;
        int centerY = (screenSize.height - 500) / 2;
        setLocation(centerX, centerY);

        bGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tidAluno.getText());
                String nome = tNome.getText();
                String email = tEmail.getText();

                if (alunoDAO.verficaId(id)) {
                    JOptionPane.showMessageDialog(null, "ID já cadastrado!");
                    return;
                } else if (!nome.equals("") && !email.equals("")) {
                    AlunoDAO alunoDAO = new AlunoDAO();
                    alunoDAO.novoAluno(id, nome, email);
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
                    DefaultListModel alunosList = (DefaultListModel) lista.getModel();
                    alunosList.addElement("ID: " + id + " | Nome: " + nome + " | Email: " + email);
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }
            }
        });

        bAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = lista.getSelectedValue();
                String alunoNome = null;

                if (selectedValue != null) {
                    String[] alunoInfo = selectedValue.split(" ");
                    alunoNome = alunoInfo[4];
                }

                int novoid = Integer.parseInt(tidAluno.getText());
                String novoNome = tNome.getText();
                String novoEmail = tEmail.getText();

                int id = Integer.parseInt(tidAluno.getText());
                if (AlunoDAO.verficaId(id)) {
                    if (!novoNome.equals("") && !novoEmail.equals("")) {
                        AlunoDAO alunoDAO = new AlunoDAO();
                        alunoDAO.atualizarAluno(novoid, novoNome, novoEmail);
                        DefaultListModel alunosList = (DefaultListModel) lista.getModel();
                        int selectedIndex = lista.getSelectedIndex();
                        String alunoAtualizado = "ID: " + novoid + " | Nome: " + novoNome + " | Email: " + novoEmail;
                        alunosList.setElementAt(alunoAtualizado, selectedIndex);

                        JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ID não existente!");
                }
            }
        });

        bDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = lista.getSelectedValue();
                String alunoNome = null;

                if (selectedValue != null) {
                    int posInicio = selectedValue.indexOf("Nome:");

                    if (posInicio != -1) {
                        int posFim = selectedValue.indexOf(" |", posInicio + 6);
                        if (posFim != -1) {
                            alunoNome = selectedValue.substring(posInicio + 6, posFim);
                        }
                    }
                }
                AlunoDAO alunoDAO = new AlunoDAO();
                if (alunoDAO.deletarAluno(alunoNome)) {
                    DefaultListModel alunosList = (DefaultListModel) lista.getModel();
                    alunosList.remove(lista.getSelectedIndex());
                    JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao deletar aluno!");
                }
            }
        });

        bLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tidAluno.setText("");
                tNome.setText("");
                tEmail.setText("");
            }
        });
        bSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuUI menuUI = new MenuUI();
                dispose();
            }
        });

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = lista.getSelectedValue();
                }
            }
        });
    }
}
