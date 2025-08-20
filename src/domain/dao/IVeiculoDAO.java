package domain.dao;

import java.sql.SQLException;
import java.util.List;

import domain.Veiculo.Veiculo;

public interface IVeiculoDAO {
    void insert(Veiculo veiculo) throws SQLException;

    void update(Veiculo veiculo) throws SQLException;

    void delete(Veiculo veiculo) throws SQLException;

    List<VeiculoDTO> findAll() throws SQLException;

    List<VeiculoDTO> findAllOrderedByModelo() throws SQLException;

    List<VeiculoDTO> findAllOrderedByPlaca() throws SQLException;

    VeiculoDTO findByPlaca(String placa) throws SQLException;

    VeiculoDTO findById(String id) throws SQLException;
}
