import Persistencia.postgresConnection;
import View.MenuUI;

public class main {
    public static void main(String[] args) {
        postgresConnection conexao = new postgresConnection();
        conexao.conectar();
        MenuUI menuUI = new MenuUI();
    }
}
