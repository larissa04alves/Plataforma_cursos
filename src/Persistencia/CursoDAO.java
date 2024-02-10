package Persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class CursoDAO {
    static Connection connection;
    public CursoDAO() {
        this.connection = postgresConnection.getConnection();
    }

    public void criarCurso(int id_curso, String nome_curso, String data_inicial, String data_final){
        try{
            PreparedStatement verificaId = connection.prepareStatement("SELECT id_curso FROM curso WHERE id_curso = ?");
            verificaId.setInt(1, id_curso);
            ResultSet resultSet = verificaId.executeQuery();

            int count = 0;

            if(resultSet.next()){
                count = resultSet.getInt(1);
                resultSet.close();
            }

            if (count > 0){
                System.out.println("ID já existente!");
            } else {
                PreparedStatement pst = connection.prepareStatement("INSERT INTO curso (id_curso, nome_curso, data_inicial, data_final) values (?, ?, ?, ?)");
                pst.setInt(1, id_curso);
                pst.setString(2, nome_curso);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsedDate = dateFormat.parse(data_inicial);
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                pst.setDate(3, sqlDate);

                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsedDate2 = dateFormat2.parse(data_final);
                java.sql.Date sqlDate2 = new java.sql.Date(parsedDate2.getTime());
                pst.setDate(4, sqlDate2);

                pst.executeUpdate();
                System.out.println("Curso criado com sucesso!");
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao criar curso!");
        }
    }
    public static boolean verficaId (int id){
        Connection connection = postgresConnection.getConnection();
        try{
            PreparedStatement verificaId = connection.prepareStatement("SELECT id_curso FROM curso WHERE id_curso = ?");
            verificaId.setInt(1, id);
            ResultSet resultSet = verificaId.executeQuery();

            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao verificar ID!");
            return true;
        }
    }

    public void atualizarCurso(int id_curso, String novo_nome, String nova_data, String nova_dataFinal){
        try{
            PreparedStatement pst = connection.prepareStatement("UPDATE curso SET nome_curso = ?, data_inicial = ?, data_final = ? WHERE id_curso = ?");
            pst.setString(1, novo_nome);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = dateFormat.parse(nova_data);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            pst.setDate(2, sqlDate);

            java.util.Date parsedDate2 = dateFormat.parse(nova_dataFinal);
            java.sql.Date sqlDate2 = new java.sql.Date(parsedDate2.getTime());
            pst.setDate(3, sqlDate2);

            pst.setInt(4, id_curso);

            pst.executeUpdate();

            System.out.println("Curso atualizado com sucesso!");
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(postgresConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao atualizar curso!");
        }
    }

    public ArrayList<String> listarCursos(){
        ArrayList<String> cursos = new ArrayList<>();
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM curso");
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_curso");
                String nome = resultSet.getString("nome_curso");
                String dataInicial = resultSet.getString("data_inicial");
                String dataFinal = resultSet.getString("data_final");

                String curso = "ID: " + id + " | Nome: " + nome + " | Data Inicial: " + dataInicial + " | Data Final: " + dataFinal;
                cursos.add(curso);
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(postgresConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao listar cursos!");
        }
        return cursos;
    }
    public boolean deletarCurso(String nome_curso){
        try{
            PreparedStatement pst = connection.prepareStatement("DELETE FROM curso WHERE nome_curso LIKE ?");
            pst.setString(1, "%" + nome_curso + "%");
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Curso deletado com sucesso!");
                return true;
            } else {
                System.out.println("Curso não encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(postgresConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao deletar curso!");
            return false;
        }
    }

    public ArrayList<Integer> listarIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT id_curso FROM curso");
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_curso");
                ids.add(id);
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(postgresConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao listar IDs!");
        }
        return ids;
    }

    public String nomeCurso(int idCurso){
        String nomeCurso = null;
        try{
            PreparedStatement pst = connection.prepareStatement("SELECT nome_curso FROM curso WHERE id_curso = ?");
            pst.setInt(1, idCurso);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                nomeCurso = resultSet.getString("nome_curso");
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(postgresConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao listar IDs!");
        }
        return nomeCurso;
    }
}

