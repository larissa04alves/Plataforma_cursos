package View;

import Negocios.ConsultasGerais;
import Persistencia.CursoDAO;
import Persistencia.MatriculaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConsultasUI extends JFrame {
    MatriculaDAO matriculaDAO = new MatriculaDAO();
    ConsultasGerais consultasGerais = new ConsultasGerais();
    private JLabel tTotAlunos;

    private JLabel tValorTotal;
    public ConsultasUI() {
        JPanel p1 = new JPanel(null);
        JLabel lTitulo = new JLabel("Consultas Gerais");
        JLabel lValorTotal = new JLabel("Valor total dos cursos");
        JLabel lTotAlunos = new JLabel("Total de alunos");
        JLabel lTotalCursos = new JLabel("Total de cursos");
        JButton bSair = new JButton("Sair");

        double valorTotal = consultasGerais.valorTotalCursos();

        tValorTotal = new JLabel(String.valueOf("R$ " + valorTotal));
        JLabel tTotAlunos = new JLabel(String.valueOf(new ConsultasGerais().totalAlunos() + " alunos cadastrados"));
        JLabel tTotalCursos = new JLabel(String.valueOf(new ConsultasGerais().totalCursos() + " cursos cadastrados"));


        lTitulo.setFont(new Font("Poppins", Font.BOLD, 20));
        lTitulo.setForeground(new Color(20, 20, 20));
        tValorTotal.setFont(new Font("Poppins", Font.BOLD, 14));
        tTotAlunos.setFont(new Font("Poppins", Font.BOLD, 14));
        tTotalCursos.setFont(new Font("Poppins", Font.BOLD, 14));


        p1.add(lTitulo);
        p1.add(lValorTotal);
        p1.add(tValorTotal);
        p1.add(lTotalCursos);
        p1.add(tTotalCursos);
        p1.add(lTotAlunos);
        p1.add(tTotAlunos);
        p1.add(bSair);

        add(p1);
        p1.setBackground(new Color(166, 166, 166));

        lTitulo.setBounds(270, 20, 300, 70);
        lValorTotal.setBounds(25, 100, 150, 25);
        tValorTotal.setBounds(210, 100, 210, 25);
        lTotalCursos.setBounds(25, 160, 150, 25);
        tTotalCursos.setBounds(210, 160, 210, 25);
        lTotAlunos.setBounds(25, 220, 150, 25);
        tTotAlunos.setBounds(210, 220, 210, 25);

        bSair.setBounds(290, 320, 150, 25);

        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - 700) / 2;
        int centerY = (screenSize.height - 400) / 2;
        setLocation(centerX, centerY);

        int totalAlunos = new ConsultasGerais().totalAlunos();
        tTotAlunos.setText(totalAlunos + " alunos cadastrados");

        bSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MenuUI menuUI = new MenuUI();
                dispose();
            }
        });
    }
}
