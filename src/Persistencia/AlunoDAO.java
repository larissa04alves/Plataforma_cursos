package Persistencia;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class AlunoDAO {
    static Connection connection;

    public AlunoDAO() {
        this.connection = postgresConnection.getConnection();
    }

    public void novoAluno(int id_aluno, String nome_aluno, String email_aluno) {
        try {
            PreparedStatement verificaId = connection.prepareStatement("SELECT id_aluno FROM aluno WHERE id_aluno = ?");
            verificaId.setInt(1, id_aluno);
            ResultSet resultSet = verificaId.executeQuery();

            int count = 0;

            if (resultSet.next()) {
                count = resultSet.getInt(1);
                resultSet.close();
            }

            if (count > 0) {
                System.out.println("ID já existente!");
            } else {
                PreparedStatement pst = connection.prepareStatement("INSERT INTO aluno (id_aluno, nome_aluno, email_aluno) values (?, ?, ?)");
                pst.setInt(1, id_aluno);
                pst.setString(2, nome_aluno);
                pst.setString(3, email_aluno);

                pst.executeUpdate();
                System.out.println("Aluno cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao cadastrar aluno!");
        }
    }

    public static boolean verficaId(int id) {
        Connection connection = postgresConnection.getConnection();
        try {
            PreparedStatement verificaId = connection.prepareStatement("SELECT id_aluno FROM aluno WHERE id_aluno = ?");
            verificaId.setInt(1, id);
            ResultSet resultSet = verificaId.executeQuery();

            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao verificar ID!");
            return true;
        }
    }

    public void atualizarAluno(int id_aluno, String novo_nome, String novo_email) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE aluno SET nome_aluno = ?, email_aluno = ? WHERE id_aluno = ?");
            pst.setString(1, novo_nome);
            pst.setString(2, novo_email);
            pst.setInt(3, id_aluno);
            pst.executeUpdate();

            System.out.println("Cadastro atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao atualizar cadastro!");
        }
    }

    public ArrayList<String>listarAlunos(){
        ArrayList<String> alunos = new ArrayList<>();
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM aluno");
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id_aluno");
                String nome = resultSet.getString("nome_aluno");
                String email = resultSet.getString("email_aluno");

                alunos.add("ID: " + id + " | Nome: " + nome + " | Email: " + email);
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao listar alunos!");
        }
        return alunos;
    }
    public boolean deletarAluno(String nome_aluno){
        try{
            PreparedStatement pst = connection.prepareStatement("DELETE FROM aluno WHERE nome_aluno LIKE ?");
            pst.setString(1, "%" + nome_aluno + "%");
            int rowsAffected = pst.executeUpdate();

            if(rowsAffected > 0){
                System.out.println("Aluno deletado com sucesso!");
                return true;
            } else {
                System.out.println("Aluno não encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao deletar aluno!");
            return false;
        }
    }
    public ArrayList<Integer> listarIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT id_aluno FROM aluno");
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id_aluno");
                ids.add(id);
            }
            resultSet.close();
        } catch (SQLException   ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao listar IDs!");
        }
        return ids;
    }

    public String nomeAluno(int idAluno){
        String nomeAluno = null;
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT nome_aluno FROM aluno WHERE id_aluno = ?");
            pst.setInt(1, idAluno);
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()){
                nomeAluno = resultSet.getString("nome_aluno");
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao listar IDs!");
        }
        return nomeAluno;
    }


}

