package View;
import Persistencia.CursoDAO;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class CursosUI extends JFrame{
    public CursosUI() {
        JPanel p1 = new JPanel(null); // Cria um painel
        JLabel lTitulo = new JLabel("Adicionar cursos"); // Cria um rótulo
        JLabel lidCurso = new JLabel("ID Curso");
        JLabel lNome = new JLabel("Nome Curso");
        JLabel lData = new JLabel("Data Inicial");
        JLabel lDataFim = new JLabel("Data Final");
        JButton bGravar = new JButton("Criar"); // Cria um botão
        JButton bDeletar = new JButton("Deletar");
        JButton bAlterar = new JButton("Alterar");
        JButton bLimpar = new JButton("Limpar");
        JButton bSair = new JButton("Sair");


        JTextField tidCurso = new JTextField(20);
        JTextField tNome = new JTextField(20);
        JTextField tData = new JTextField(20);
        JTextField tDataFim = new JTextField(20);

        CursoDAO cursoDAO = new CursoDAO();
        ArrayList<String> cursos = cursoDAO.listarCursos();
        DefaultListModel<String> cursosList = new DefaultListModel<>();
        for (String curso : cursos) {
            cursosList.addElement(curso);
        }

        JList<String> lista = new JList<>(cursosList);
        JScrollPane scroll = new JScrollPane(lista);

        lTitulo.setFont(new Font("Poppins", Font.BOLD, 20)); // Define a fonte do título
        lTitulo.setForeground(new Color(20 , 20, 20));

        p1.add(lTitulo);
        p1.add(lidCurso);
        p1.add(tidCurso);
        p1.add(lNome);
        p1.add(tNome);
        p1.add(lData);
        p1.add(tData);
        p1.add(lDataFim);
        p1.add(tDataFim);
        p1.add(bGravar);
        p1.add(bDeletar);
        p1.add(bAlterar);
        p1.add(bLimpar);
        p1.add(bSair);
        p1.add(scroll);

        add(p1);
        p1.setBackground(new Color(166, 166, 166));

        lTitulo.setBounds(400, 20, 200, 70);
        lidCurso.setBounds(30, 130, 100, 25);
        tidCurso.setBounds(110, 130, 350, 25);
        lNome.setBounds(30, 180, 100, 25);
        tNome.setBounds(110, 180, 350, 25);
        lData.setBounds(30, 230, 100, 25);
        tData.setBounds(110, 230, 350, 25);
        lDataFim.setBounds(30, 280, 100, 25);
        tDataFim.setBounds(110, 280, 350, 25);

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
                int id = Integer.parseInt(tidCurso.getText());
                String nome = tNome.getText();
                String data = tData.getText();
                String dataFim = tDataFim.getText();

                if(CursoDAO.verficaId(id)){
                    JOptionPane.showMessageDialog(null, "ID já existente!");
                } else {
                    if (!nome.isEmpty() && !data.isEmpty() && !dataFim.isEmpty()) {
                        try {
                            // Validando o formato das datas antes de tentar converter
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            dateFormat.setLenient(false);
                            dateFormat.parse(data);
                            dateFormat.parse(dataFim);

                            CursoDAO cursoDAO = new CursoDAO();
                            cursoDAO.criarCurso(id, nome, data, dataFim);
                            JOptionPane.showMessageDialog(null, "Curso criado com sucesso!");
                            DefaultListModel<String> cursosListModel = (DefaultListModel<String>) lista.getModel();
                            cursosListModel.addElement("ID: " + id + " | Nome: " + nome + " | Data Inicial: " + data + " | Data Final: " + dataFim);
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Formato de data inválido!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    }
                }
            }
        });

        bAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = lista.getSelectedValue();
                String cursoNome = null;

                if (selectedValue != null) {
                    String[] cursoInfo = selectedValue.split(" ");
                    cursoNome = cursoInfo[4];
                }

                int novoId = Integer.parseInt(tidCurso.getText());
                String novoNome = tNome.getText();
                String novaData = tData.getText();
                String novaDataFim = tDataFim.getText();

                int id = Integer.parseInt(tidCurso.getText());
                if (CursoDAO.verficaId(id)) {
                    if (!novoNome.isEmpty() && !novaData.isEmpty() && !novaDataFim.isEmpty()) {
                        try {
                            // Validação do formato das datas antes de tentar converter
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            dateFormat.setLenient(false);
                            dateFormat.parse(novaData);
                            dateFormat.parse(novaDataFim);

                            CursoDAO cursoDAO = new CursoDAO();
                            cursoDAO.atualizarCurso(novoId, novoNome, novaData, novaDataFim);
                            int selectedIndex = lista.getSelectedIndex();
                            JOptionPane.showMessageDialog(null, "Curso atualizado com sucesso!");
                            String cursoAtualizado = "ID: " + novoId + " | Nome: " + novoNome + " | Data Inicial: " + novaData + " | Data Final: " + novaDataFim;
                            DefaultListModel<String> cursosListModel = (DefaultListModel<String>) lista.getModel();
                            cursosListModel.setElementAt(cursoAtualizado, selectedIndex);
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Formato de data inválido!");
                        }
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
                String cursoNome = null;

                if (selectedValue != null) {
                   int posInicio = selectedValue.indexOf("Nome:");

                    if(posInicio != -1){
                        int posFim = selectedValue.indexOf(" |", posInicio + 6);
                        if (posFim != -1) {
                            cursoNome = selectedValue.substring(posInicio + 6, posFim);
                        }
                    }
                }
                CursoDAO cursoDAO = new CursoDAO();
                if (cursoDAO.deletarCurso(cursoNome)) {
                    DefaultListModel cursosList = (DefaultListModel) lista.getModel();
                    cursosList.remove(lista.getSelectedIndex());
                    JOptionPane.showMessageDialog(null, "Curso deletado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao deletar curso!");
                }
            }
        });

        bLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tidCurso.setText("");
                tNome.setText("");
                tData.setText("");
                tDataFim.setText("");
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
                if(!e.getValueIsAdjusting()){
                    String selectedValue = lista.getSelectedValue();
                }
            }
        });
    }
}
