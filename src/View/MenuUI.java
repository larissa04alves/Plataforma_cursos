package View;

import Persistencia.postgresConnection;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
public class MenuUI extends JFrame{
    public MenuUI(){
        JPanel p1 = new JPanel(null);
        JLabel lTitulo = new JLabel("Plataforma de cursos");
        JButton bAlunos = new JButton("Cadastrar aluno");
        JButton bCursos = new JButton("Cadastrar curso");
        JButton bMatriculas = new JButton("Matriculas");
        JButton bConsulta = new JButton("Consultas sistema");
        JButton bSair = new JButton("Sair");

        lTitulo.setFont(new Font("Poppins", Font.BOLD, 20));
        lTitulo.setForeground(new Color(20 , 20, 20));

        p1.add(lTitulo);
        p1.add(bAlunos);
        p1.add(bCursos);
        p1.add(bMatriculas);
        p1.add(bConsulta);
        p1.add(bSair);

        add(p1);
        p1.setBackground(new Color(166, 166, 166));

        lTitulo.setBounds(250, 20, 300, 70);
        bAlunos.setBounds(250, 100, 200, 30);
        bCursos.setBounds(250, 150, 200, 30);
        bMatriculas.setBounds(250, 200, 200, 30);
        bConsulta.setBounds(250, 250, 200, 30);
        bSair.setBounds(250, 300, 200, 30);


        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - 700) / 2;
        int centerY = (screenSize.height - 400) / 2;
        setLocation(centerX, centerY);


        bAlunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunosUI alunosUI = new AlunosUI();
                alunosUI.setVisible(true);
                dispose();
            }
        });

        bCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursosUI cursosUI = new CursosUI();
                cursosUI.setVisible(true);
                dispose();
            }
        });

        bMatriculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MatriculaUI matriculaUI = new MatriculaUI();
                matriculaUI.setVisible(true);
                dispose();
            }
        });

        bConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultasUI consultasUI = new ConsultasUI();
                consultasUI.setVisible(true);
                dispose();
            }
        });

        bSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                postgresConnection conexao = new postgresConnection();
                conexao.desconectar();
            }
        });
    }

}
