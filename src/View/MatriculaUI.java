package View;

import Persistencia.AlunoDAO;
import Persistencia.CursoDAO;
import Persistencia.MatriculaDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class MatriculaUI extends JFrame {

    JComboBox<Integer> idAlunoBox = new JComboBox<>();
    JComboBox<Integer> idCursoBox = new JComboBox<>();

    ArrayList<String> matriculas = new ArrayList<>();
    MatriculaDAO matriculaDAO = new MatriculaDAO();

    public MatriculaUI() {
        matriculaDAO = new MatriculaDAO();

        JPanel p1 = new JPanel(null);
        JLabel lTitulo = new JLabel("Matricular aluno");
        JLabel lTitulo2 = new JLabel("Pesquisas");
        JLabel lidMatric = new JLabel("ID Matricula");
        JLabel lidAluno = new JLabel("ID Aluno");
        JLabel lNomeAluno = new JLabel("Nome Aluno");
        JLabel ldata = new JLabel("Data Matricula");
        JLabel lidCurso = new JLabel("ID Curso");
        JLabel lnomeCurso = new JLabel("Nome Curso");
        JLabel lValor = new JLabel("Valor Curso");
        JLabel lObs = new JLabel("Observações");
        JButton bGravar = new JButton("Matricular");
        JButton bDeletar = new JButton("Deletar");
        JButton bAlterar = new JButton("Alterar");
        JButton bLimpar = new JButton("Limpar");
        JButton bPesquisar = new JButton("Pesquisar");
        JButton bSair = new JButton("Sair");

        JTextField tidMatric = new JTextField(20);
        JTextField tNomeAluno = new JTextField(20);
        JTextField tdata = new JTextField(20);
        JTextField tnomeCurso = new JTextField(20);
        JTextField tValor = new JTextField(20);
        JTextField tObs = new JTextField(20);

        AlunoDAO alunoDAO = new AlunoDAO();
        ArrayList<Integer> idAlunos = alunoDAO.listarIds();
        for(Integer id : idAlunos){
            idAlunoBox.addItem(id);
        }

        CursoDAO cursoDAO = new CursoDAO();
        ArrayList<Integer> idCursos = cursoDAO.listarIds();
        for(Integer id : idCursos){
            idCursoBox.addItem(id);
        }

        DefaultListModel<String> matriculaListModel = new DefaultListModel<>();
        for (String matriculaInfo : matriculas) {
            matriculaListModel.addElement(matriculaInfo);
        }

        JList<String> matriculaList = new JList<>(matriculaListModel);
        matriculaList.setVisibleRowCount(10);

        lTitulo.setFont(new Font("Poppins", Font.BOLD, 20));
        lTitulo.setForeground(new Color(20, 20, 20));

        lTitulo2.setFont(new Font("Poppins", Font.BOLD, 15));
        lTitulo2.setForeground(new Color(20, 20, 20));

        matriculaList.setBounds(460, 90, 480, 320);
        JScrollPane scrollPane = new JScrollPane(matriculaList);
        scrollPane.setBounds(460, 90, 480, 320);

        p1.add(lTitulo);
        p1.add(lTitulo2);
        p1.add(lidMatric);
        p1.add(tidMatric);
        p1.add(lidAluno);
        p1.add(idAlunoBox);
        p1.add(lNomeAluno);
        p1.add(tNomeAluno);
        p1.add(ldata);
        p1.add(tdata);
        p1.add(lidCurso);
        p1.add(idCursoBox);
        p1.add(lnomeCurso);
        p1.add(tnomeCurso);
        p1.add(lValor);
        p1.add(tValor);
        p1.add(lObs);
        p1.add(tObs);
        p1.add(bGravar);
        p1.add(bDeletar);
        p1.add(bAlterar);
        p1.add(bLimpar);
        p1.add(bSair);
        p1.add(bPesquisar);
        p1.add(scrollPane);

        add(p1);
        p1.setBackground(new Color(166, 166, 166));

        lTitulo.setBounds(400, 20, 200, 70);
        lTitulo2.setBounds(665, 40, 200, 70);
        lidMatric.setBounds(50, 100, 200, 30);
        tidMatric.setBounds(150, 100, 250, 30);
        lidAluno.setBounds(50, 140, 200, 30);
        idAlunoBox.setBounds(150, 140, 250, 30);
        lNomeAluno.setBounds(50, 180, 200, 30);
        tNomeAluno.setBounds(150, 180, 250, 30);
        ldata.setBounds(50, 220, 200, 30);
        tdata.setBounds(150, 220, 250, 30);
        lidCurso.setBounds(50, 260, 200, 30);
        idCursoBox.setBounds(150, 260, 250, 30);
        lnomeCurso.setBounds(50, 300, 200, 30);
        tnomeCurso.setBounds(150, 300, 250, 30);
        lValor.setBounds(50, 340, 200, 30);
        tValor.setBounds(150, 340, 250, 30);
        lObs.setBounds(50, 380, 200, 30);
        tObs.setBounds(150, 380, 250, 30);

        bGravar.setBounds(60, 450, 100, 23);
        bDeletar.setBounds(200, 450, 100, 23);
        bAlterar.setBounds(340, 450, 100, 23);
        bLimpar.setBounds(60, 490, 100, 23);
        bSair.setBounds(200, 490, 100, 23);
        bPesquisar.setBounds(340, 490, 100, 23);
        matriculaList.setBounds(460, 90, 480, 320);

        setSize(1010, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - 1010) / 2;
        int centerY = (screenSize.height - 500) / 2;
        setLocation(centerX, centerY);

        bGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idMatric = Integer.parseInt(tidMatric.getText());
                    int idAluno = idAlunoBox.getItemAt(idAlunoBox.getSelectedIndex());
                    String data = tdata.getText();
                    int idCurso = idCursoBox.getItemAt(idCursoBox.getSelectedIndex());
                    String nomeCurso = tnomeCurso.getText();
                    String nomeAluno = tNomeAluno.getText();
                    String valorText = tValor.getText().trim();
                    String obs = tObs.getText();

                    if (data.isEmpty() || nomeCurso.isEmpty() || valorText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                        return;
                    }
                    double valor = Double.parseDouble(valorText);

                    if (MatriculaDAO.VerificaMatricula(idMatric)) {
                        JOptionPane.showMessageDialog(null, "ID de Matrícula já existe!");
                    } else if (!data.equals(" ")) {
                        MatriculaDAO matriculaDAO = new MatriculaDAO();
                        matriculaDAO.novaMatricula(idMatric, idAluno, data, idCurso, nomeCurso, nomeAluno, valor, obs);

                        int idAlunoSelecionado = idAlunoBox.getItemAt(idAlunoBox.getSelectedIndex());
                        int idCursoSelecionado = idCursoBox.getItemAt(idCursoBox.getSelectedIndex());

                        idAlunoBox.removeAllItems();
                        idCursoBox.removeAllItems();

                        ArrayList<Integer> idAlunos = alunoDAO.listarIds();
                        for (Integer id : idAlunos) {
                            idAlunoBox.addItem(id);
                        }
                        idAlunoBox.setSelectedItem(idAlunoSelecionado);

                        ArrayList<Integer> idCursos = cursoDAO.listarIds();
                        for (Integer id : idCursos) {
                            idCursoBox.addItem(id);
                        }
                        idCursoBox.setSelectedItem(idCursoSelecionado);

                        JOptionPane.showMessageDialog(null, "Matrícula criada com sucesso!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar valores!");
                }
            }
        });

        bAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                int idMatric = Integer.parseInt(tidMatric.getText());
                int idAluno = idAlunoBox.getItemAt(idAlunoBox.getSelectedIndex());
                String data = tdata.getText();
                int idCurso = idCursoBox.getItemAt(idCursoBox.getSelectedIndex());
                String nomeCurso = tnomeCurso.getText();
                String nomeAluno = alunoDAO.nomeAluno(idAluno);
                double valor = Double.parseDouble(tValor.getText());
                String obs = tObs.getText();

                if (!MatriculaDAO.VerificaMatricula(idMatric)) {
                    JOptionPane.showMessageDialog(null, "ID de Matrícula não existe!");
                    return;
                }

                MatriculaDAO matriculaDAO = new MatriculaDAO();
                matriculaDAO.atualizarMatricula(idMatric, idAluno, data, idCurso, nomeCurso,nomeAluno, valor, obs);

                idAlunoBox.removeAllItems();
                idCursoBox.removeAllItems();

                ArrayList<Integer> idAlunos = alunoDAO.listarIds();
                for(Integer id : idAlunos){
                    idAlunoBox.addItem(id);
                }

                ArrayList<Integer> idCursos = cursoDAO.listarIds();
                for(Integer id : idCursos){
                    idCursoBox.addItem(id);
                }

                    JOptionPane.showMessageDialog(null, "Matrícula alterada com sucesso!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar valores!");
                }
            }
        });

        bDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idMatricSelecionada = Integer.parseInt(tidMatric.getText());

                    if (MatriculaDAO.VerificaMatricula(idMatricSelecionada)) {
                        MatriculaDAO matriculaDAO = new MatriculaDAO();
                        matriculaDAO.deletarMatricula(idMatricSelecionada);
                    }

                    idAlunoBox.removeAllItems();
                    idCursoBox.removeAllItems();

                    ArrayList<Integer> idAlunos = alunoDAO.listarIds();
                    for (Integer id : idAlunos) {
                        idAlunoBox.addItem(id);
                    }

                    ArrayList<Integer> idCursos = cursoDAO.listarIds();
                    for (Integer id : idCursos) {
                        idCursoBox.addItem(id);
                    }

                    matriculaListModel.clear();
                    ArrayList<String> matriculasAtualizadas = matriculaDAO.pesquisarMatricula(idMatricSelecionada);
                    for (String matricula : matriculasAtualizadas) {
                        matriculaListModel.addElement(matricula);
                    }
                    matriculaList.setModel(matriculaListModel);
                    JOptionPane.showMessageDialog(null, "Matrícula deletada com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar valores!");
                }
            }
        });

        bPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idAlunoSelecionado = idAlunoBox.getItemAt(idAlunoBox.getSelectedIndex());
                    ArrayList<String> matriculas = matriculaDAO.pesquisarMatricula(idAlunoSelecionado);
                    matriculaListModel.clear();
                    for (String matricula : matriculas) {
                        matriculaListModel.addElement(matricula);
                    }
                    matriculaList.setModel(matriculaListModel);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar valores!");
                }
            }
        });

        bLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tidMatric.setText("");
                tNomeAluno.setText("");
                tdata.setText("");
                tnomeCurso.setText("");
                tValor.setText("");
                tObs.setText("");

                matriculaListModel.clear();
                int id_aluno = 0;
                ArrayList<String> matriculas = matriculaDAO.pesquisarMatricula(id_aluno);
                for (String matricula : matriculas) {
                    matriculaListModel.addElement(matricula);
                }
                matriculaList.setModel(matriculaListModel);
            }
        });

        bSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuUI menuUI = new MenuUI();
                dispose();
            }
        });

        idCursoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idAlunoBox.getItemCount() > 0) {
                    int idAluno = idAlunoBox.getItemAt(idAlunoBox.getSelectedIndex());
                    int idCursoSelecionado = idCursoBox.getItemAt(idCursoBox.getSelectedIndex());
                    CursoDAO cursoDAO = new CursoDAO();
                    String nomeCurso = cursoDAO.nomeCurso(idCursoSelecionado);
                    tnomeCurso.setText(nomeCurso);
                }
            }
        });

        idAlunoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idAlunoBox.getItemCount() > 0) {
                    int idAlunoSelecionado = idAlunoBox.getItemAt(idAlunoBox.getSelectedIndex());
                    AlunoDAO alunoDAO = new AlunoDAO();
                    String nomeAluno = alunoDAO.nomeAluno(idAlunoSelecionado);
                    tNomeAluno.setText(nomeAluno);
                }
            }
        });

        matriculaList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                String[] parts = value.toString().split("\\|");
                StringBuilder formattedInfo = new StringBuilder("<html>");
                for (String part : parts) {
                    formattedInfo.append(part.trim()).append("<br>");
                }
                formattedInfo.append("</html>");

                label.setText(formattedInfo.toString());

                return label;
            }
        });

    }
}