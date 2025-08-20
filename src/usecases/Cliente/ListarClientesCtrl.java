package usecases.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Erro;
import domain.Resultado;
import domain.Cliente.Cliente;
import domain.Cliente.ClienteRepository;

/**
 * Classe que implementa a funcionalidade de consulta de clientes
 */
public class ListarClientesCtrl {

	private final ClienteRepository repo;
	
	public ListarClientesCtrl(ClienteRepository repo) {
		super();
		this.repo = repo;
	}

	/**
	 * Recupera todos os clientes do cadastro
	 * 
	 * @return Lista de clientes ou erro de acesso ao BD
	 */
	public Resultado<List<Cliente>> recuperarTodosClientesPorOrdenacao(char tipoOrdenacao) {
		try {
			List<Cliente> clientes = new ArrayList<>();

			if(tipoOrdenacao == 'C')
			{
				clientes = repo.findAllOrderedByCpf();
			}
			else if(tipoOrdenacao == 'N')
			{
				clientes = repo.findAllOrderedByNome();
			}

			return Resultado.ok(clientes);
		} catch (SQLException e) {
			// Se ocorrer alguma exceção no BD, avisa
			return Resultado.erro(List.of(Erro.ERRO_BD));
		}
	}
}
