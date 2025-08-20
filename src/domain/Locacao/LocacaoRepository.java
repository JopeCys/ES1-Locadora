package domain.Locacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.Cliente.Cliente;
import domain.Cliente.ClienteRepository;
import domain.Veiculo.Veiculo;
import domain.Veiculo.VeiculoRepository;
import domain.Repository;
import domain.dao.ILocacaoDAO;
import domain.dao.LocacaoDTO;

public class LocacaoRepository implements Repository 
{
    private final ILocacaoDAO dao;
    private final ClienteRepository clienteRepo;
    private final VeiculoRepository veiculoRepo;


    public LocacaoRepository (ILocacaoDAO dao, ClienteRepository clienteRepo, VeiculoRepository veiculoRepo)
    {
        this.dao = dao;
        this.clienteRepo = clienteRepo;
        this.veiculoRepo = veiculoRepo;
    }

    /**
     * Retorna todas as locações
     *
     * @return Lista de locações
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public List<Locacao> findAll() throws SQLException {
        // Busca todas as locações do repositório
        var dtos = dao.findAll();

        // Converte os DTOs vindo do repositório em locações
        var locacoes = new ArrayList<Locacao>();

        for (var dto : dtos) {
            locacoes.add(create(dto));
        }

        return locacoes;
    }

    public void add(Locacao locacao) throws SQLException
    {
        // Se o veículo NÃO tem ID, então NÃO veio do BD
        if (locacao.getId() == null) {
            // Cria um ID artificial baseado no UUID
            locacao.setId(UUID.randomUUID().toString());
            // Insere o veículo no BD
            dao.insert(locacao);
        }
        else
            // Veículo já existe: atualiza no BD
            dao.update(locacao);
    }

    private Locacao create(LocacaoDTO dto) 
    {
        Cliente cliente = null;
        Veiculo veiculo = null;
        
        try 
        {
            cliente = clienteRepo.findById(dto.idCliente());
            veiculo = veiculoRepo.findById(dto.idVeiculo());
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao recuperar cliente e veiculo no banco dados");
        }

        // Usa o builder para construir a locação com os dados vindos do BD
        var resultado = new LocacaoBuilder()
                .withCliente(cliente)
                .withVeiculo(veiculo)
                .withhoraDataLocacao(dto.horaDataLocacao())
                .build();

        // Assume que a criação foi bem-sucedida, pois os dados do BD devem estar consistentes
        var locacao = resultado.valor;

        // Seta o ID do objeto, pois ele veio do BD
        locacao.setId(dto.id());

        return locacao;

    }
}
