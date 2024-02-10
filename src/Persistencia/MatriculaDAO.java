package Persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.HashMap;
public class MatriculaDAO {
    static Connection connection;

    public MatriculaDAO() {
        this.connection = postgresConnection.getConnection();
    }

    public void novaMatricula(int id_matricula, int id_aluno, String data_matricula, int id_curso, String nome_curso, String nome_aluno, double valor_curso, String descricao) {
        try {
            PreparedStatement verificaAluno = connection.prepareStatement("SELECT id_aluno FROM aluno WHERE id_aluno = ?");
            verificaAluno.setInt(1, id_aluno);
            ResultSet resultSetAluno = verificaAluno.executeQuery();
            int countAluno = 0;

            if (resultSetAluno.next()) {
                countAluno = resultSetAluno.getInt(1);
                resultSetAluno.close();
            }
            if (countAluno == 0) {
                System.out.println("Aluno não cadastrado!");
                return;
            }
            PreparedStatement verificaCurso = connection.prepareStatement("SELECT id_curso FROM curso WHERE id_curso = ?");
            verificaCurso.setInt(1, id_curso);
            ResultSet resultSetCurso = verificaCurso.executeQuery();
            int countCurso = 0;

            if (resultSetCurso.next()) {
                countCurso = resultSetCurso.getInt(1);
                resultSetCurso.close();
            }
            if (countCurso == 0) {
                System.out.println("Curso não cadastrado!");
                return;
            }

            PreparedStatement verificaMatricula = connection.prepareStatement("SELECT id_matricula FROM matricula WHERE id_matricula = ?");
            verificaMatricula.setInt(1, id_matricula);
            ResultSet resultSetMatricula = verificaMatricula.executeQuery();

            if (resultSetMatricula.next()) {
                System.out.println("ID de Matrícula existente!");
                resultSetMatricula.close();
                return;
            }

            PreparedStatement pst = connection.prepareStatement("INSERT INTO matricula (id_matricula, id_aluno, data_matricula, id_curso, valor_curso, descricao, nome_curso, nome_aluno) values (?, ?, ?, ?, ?, ?, ?,?)");
            pst.setInt(1, id_matricula);
            pst.setInt(2, id_aluno);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = dateFormat.parse(data_matricula);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            pst.setDate(3, sqlDate);
            pst.setInt(4, id_curso);
            pst.setDouble(5, valor_curso);
            pst.setString(6, descricao);
            pst.setString(7, nome_curso);
            pst.setString(8, nome_aluno);

            pst.executeUpdate();
            System.out.println("Matrícula criada com sucesso!");
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao criar matrícula!");
        }
    }
    public static boolean VerificaMatricula (int id){
        Connection connection = postgresConnection.getConnection();
        try{
            PreparedStatement verificaMatricula = connection.prepareStatement("SELECT id_matricula FROM matricula WHERE id_matricula = ?");
            verificaMatricula.setInt(1, id);
            ResultSet resultSet = verificaMatricula.executeQuery();

            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao verificar ID!");
            return true;
        }
    }
    public void atualizarMatricula(int id_matricula, int id_aluno, String data_matricula, int id_curso, String nome_curso, String nome_aluno, double valor_curso, String descricao) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE matricula SET id_aluno = ?, data_matricula = ?, id_curso = ?, valor_curso = ?, descricao = ?, nome_curso = ?, nome_aluno = ? WHERE id_matricula = ?");
            pst.setInt(1, id_aluno);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = dateFormat.parse(data_matricula);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            pst.setDate(2, sqlDate);
            pst.setInt(3, id_curso);
            pst.setDouble(4, valor_curso);
            pst.setString(5, descricao);
            pst.setString(6, nome_curso);
            pst.setInt(8, id_matricula);
            pst.setString(7, nome_aluno);

            pst.executeUpdate();
            System.out.println("Matrícula atualizada com sucesso!");
        } catch (SQLException | ParseException e) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Erro ao atualizar matrícula!");
        }
    }

    public ArrayList<String> pesquisarMatricula(int id_aluno) {
        ArrayList<String> matriculas = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM matricula WHERE id_aluno = ?");
            pst.setInt(1, id_aluno);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String dataMatricula = rs.getDate("data_matricula").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(dataMatricula);
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = newDateFormat.format(parsedDate);
                String matricula = "ID Matrícula: " + rs.getInt("id_matricula") + " | ID Aluno: " + rs.getInt("id_aluno") + " | Nome Aluno: " + rs.getString("nome_aluno") + " | Data Matrícula: " + rs.getDate("data_matricula") + " | ID Curso: " + rs.getInt("id_curso") + " | Nome Curso: " + rs.getString("nome_curso") + " | Valor Curso: " + rs.getDouble("valor_curso") + " | Descrição: " + rs.getString("descricao");
                matriculas.add(matricula);
            }
        } catch (SQLException | ParseException e) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Erro ao pesquisar matrícula!");
        }
        return matriculas;
    }

    public void deletarMatricula(int id_matricula) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM matricula WHERE id_matricula = ?");
            pst.setInt(1, id_matricula);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Matrícula deletada com sucesso para o aluno de ID " + id_matricula);
            } else {
                System.out.println("Nenhuma matrícula encontrada para o aluno de ID " + id_matricula);
            }
        } catch (SQLException e) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Erro ao deletar matrícula!");
        }
    }

    public double calcularValorTotal(){
        double valorTotal = 0.0;
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT SUM(valor_curso) FROM matricula");
            ResultSet resultSet = pst.executeQuery();

            if(resultSet.next()){
                valorTotal = resultSet.getDouble(1);
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao extrair valor do curso!");
        }
        return valorTotal;
    }

    public int contarAlunos(){
        int totalAlunos = 0;
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT COUNT(DISTINCT id_aluno) FROM matricula");
            ResultSet resultSet = pst.executeQuery();

            if(resultSet.next()){
                totalAlunos = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao contar alunos!");
        }
        return totalAlunos;
    }
}