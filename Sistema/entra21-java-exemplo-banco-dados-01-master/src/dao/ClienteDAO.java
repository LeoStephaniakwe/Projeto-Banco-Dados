package dao;

import bean.ClienteBean;
import conexao.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo
 */
public class ClienteDAO {

    public int inserir(ClienteBean cliente) {
        Connection conexao = ConexaoFactory.obterConexao();
        if (conexao != null) {
            String sql = "INSERT INTO clientes"
                    + "\n(nome, data_nascimento, cpf)"
                    + "\nVALUES(?,?,?)";
            try {
                PreparedStatement preparedStatement = conexao.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getData());
                preparedStatement.setString(3, cliente.getCpf());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConexaoFactory.fecharConexao();

            }

        }

        return 0;
    }

    public boolean alterar(ClienteBean cliente) {
        return false;
    }

    public boolean apagar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        Connection conexao = ConexaoFactory.obterConexao();
        if(conexao != null){
            try{
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1,id);
                return ps.executeUpdate() == 1;
            }catch (SQLException e ){
                e.printStackTrace();
            }finally{
                ConexaoFactory.fecharConexao();
            }
        }
        return false;
    }


    public List<ClienteBean> obterClientes() {
           List<ClienteBean> clientes = new ArrayList<>();

        Connection conexao = ConexaoFactory.obterConexao();
        if (conexao != null) {
            String sql = "SELECT id, nome, cpf, data_nascimento, ativo FROM clientes;";
            try {
                Statement statement = conexao.createStatement();
                statement.execute(sql);
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    ClienteBean cliente = new ClienteBean();
                    cliente.setId(resultSet.getInt("Id"));
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setData((resultSet.getString("data_nascimento")));
                    cliente.setCpf((resultSet.getString("cpf")));
                    cliente.setAtivo(resultSet.getBoolean("ativo"));
                    clientes.add(cliente);
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }finally{
                ConexaoFactory.fecharConexao();
            }
        }

        return clientes;
    }

}
