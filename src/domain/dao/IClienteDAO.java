package domain.dao;

import java.sql.SQLException;
import java.util.List;

import domain.Cliente.Cliente;

/**
 * Interface que representa as possíveis operações no BD
 */
public interface IClienteDAO {

	void insert(Cliente cliente) throws SQLException;

	void update(Cliente cliente) throws SQLException;

	void delete(Cliente cliente) throws SQLException;

	List<ClienteDTO> findAll() throws SQLException;

	List<ClienteDTO> findAllOrderedByCpf() throws SQLException;

    List<ClienteDTO> findAllOrderedByNome() throws SQLException;

	ClienteDTO findByCPF(Long cpf) throws SQLException;

	ClienteDTO findById(String id) throws SQLException;
}