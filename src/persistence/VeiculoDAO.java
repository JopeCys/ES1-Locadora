package persistence;

import domain.Veiculo.Veiculo;
import domain.dao.IVeiculoDAO;
import domain.dao.VeiculoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements IVeiculoDAO {
    /**
     * Insere um veículo no BD
     *
     * @param veiculo Veículo a ser inserido
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public void insert(Veiculo veiculo) throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
            var stmt = conn.prepareStatement("insert into veiculos (id, placa, modelo, anoFabricacao, valorDiaria, quilometragem) values (?, ?, ?, ?, ?, ?)")) {

            // Define os valores dos parâmetros
            stmt.setString(1, veiculo.getId());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAnoFabricacao().getValue());
            stmt.setDouble(5, veiculo.getValorDiaria());
            stmt.setInt(6, veiculo.getQuilometragem());

            // Executar o comando
            stmt.execute();
        }
    }
    
    /**
     * Atualiza um veículo no BD
     *
     * @param veiculo Veículo a ser atualizado
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public void update(Veiculo veiculo) throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("update veiculos set modelo=?, anoFabricacao=?, valorDiaria=?, quilometragem=? where placa=?")) {

            // Define os valores dos parâmetros
            var df = DateTimeFormatter.ofPattern("yyyy");

            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, df.format(veiculo.getAnoFabricacao()));
            stmt.setDouble(3, veiculo.getValorDiaria());
            stmt.setInt(4, veiculo.getQuilometragem());
            stmt.setString(5, veiculo.getPlaca());

            // Executa o comando
            stmt.execute();
        }
    }

    /**
     * Deleta um veículo do BD
     *
     * @param veiculo Veículo a ser deletado
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public void delete(Veiculo veiculo) throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("delete from veiculos where placa=?")) {

            // Define a placa do comando
            stmt.setString(1, veiculo.getPlaca());

            // Executa o comando
            stmt.execute();
        }
    }

    /**
     * Retorna todos os veículos do BD
     *
     * @return Lista de DTOs com os dados dos veículos
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public List<VeiculoDTO> findAll() throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        // Executa o comando que retorna um ResultSet
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("select * from veiculos")) {

            var mapper = new VeiculoMapper();
            var veiculos = new ArrayList<VeiculoDTO>();

            // Para todos os registros vindos do BD, converte os dados
            // do ResultSet em DTO usando o mapper
            while (rs.next())
                veiculos.add(mapper.map(rs));

            return veiculos;
        }
    }

    public List<VeiculoDTO> findAllOrderedByPlaca() throws SQLException {
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("select * from veiculos order by placa")) {

            var mapper = new VeiculoMapper();
            var veiculos = new ArrayList<VeiculoDTO>();
            while (rs.next()) {
                veiculos.add(mapper.map(rs));
            }
            return veiculos;
        }
    }

    public List<VeiculoDTO> findAllOrderedByModelo() throws SQLException {
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("select * from veiculos order by modelo")) {

            var mapper = new VeiculoMapper();
            var veiculos = new ArrayList<VeiculoDTO>();
            while (rs.next()) {
                veiculos.add(mapper.map(rs));
            }
            return veiculos;
        }
    }

    /**
     * Retorna um veículo baseado na placa
     *
     * @param placa Placa do veículo
     * @return DTO com os dados do veículo
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    @Override
    public VeiculoDTO findByPlaca(String placa) throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("select * from veiculos where placa = ?")) {

            // Define a placa do comando
            stmt.setString(1, placa);

            // Executa o comando que retorna um ResultSet
            try (var rs = stmt.executeQuery()) {

                var mapper = new VeiculoMapper();

                // Se existe um registro, converte os dados
                // do ResultSet em DTO usando o mapper
                if (rs.next())
                    return mapper.map(rs);

                // Se não existe, retorna nulo
                return null;
            }
        }
    }

    @Override
    public VeiculoDTO findById(String id) throws SQLException {
        // Query SQL para buscar veículo por ID
        String sql = "SELECT * FROM veiculos WHERE id = ?";

        // Conectar ao banco de dados e executar a consulta
        try (Connection conn = DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Definir o parâmetro da query (ID)
            stmt.setString(1, id);

            // Executar a query e obter o resultado
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Usar o mapper para converter ResultSet em VeiculoDTO
                    VeiculoMapper mapper = new VeiculoMapper();
                    return mapper.map(rs);
                }
            }
        }

        // Se não encontrou o veículo, retorna null
        return null;
    }
}
