package usecases.Veiculo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Erro;
import domain.Resultado;
import domain.Veiculo.Veiculo;
import domain.Veiculo.VeiculoRepository;

/**
 * Classe que implementa a funcionalidade de consulta de veículos
 */
public class ListarVeiculosCtrl 
{

    private final VeiculoRepository repo;

    public ListarVeiculosCtrl(VeiculoRepository repo) 
    {
        super();
        this.repo = repo;
    }

    /**
     * Recupera todos os veículos do cadastro
     *
     * @return Lista de veículos ou erro de acesso ao BD
     */
    public Resultado<List<Veiculo>> recuperarTodosVeiculosPorOrdenacao(char tipoOrdenacao) 
    {
        try {
            List<Veiculo> veiculos = new ArrayList<>();

            if(tipoOrdenacao == 'P')
            {
                veiculos = repo.findAllOrderedByPlaca();
            }
            else if(tipoOrdenacao == 'M')
            {
                veiculos = repo.findAllOrderedByModelo();
            }

            return Resultado.ok(veiculos);
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }
}

