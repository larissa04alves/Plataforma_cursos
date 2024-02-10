package Negocios;

import Persistencia.MatriculaDAO;
import Persistencia.CursoDAO;

import java.util.ArrayList;

public class ConsultasGerais {

    public static double valorTotalCursos(){
       MatriculaDAO matriculaDAO = new MatriculaDAO();
       double valorTotal = matriculaDAO.calcularValorTotal();

       return valorTotal;
    }

    public static int totalAlunos(){
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        int totalAlunos = matriculaDAO.contarAlunos();

        return totalAlunos;
    }

    public static int totalCursos(){
        CursoDAO cursoDAO = new CursoDAO();
        ArrayList<Integer> idsCursos = cursoDAO.listarIds();
        int totalCursos = idsCursos.size();

        return totalCursos;
    }

    public static void main(String[] args) {
        System.out.println(valorTotalCursos());
        System.out.println(totalAlunos());
    }

}
