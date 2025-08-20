package usecases.Locacao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import domain.Erro;
import domain.Cliente.Cliente;
import domain.Cliente.ClienteRepository;
import domain.Locacao.LocacaoBuilder;
import domain.Locacao.LocacaoRepository;
import domain.Veiculo.Veiculo;
import domain.Veiculo.VeiculoRepository;

public class CadastroLocacaoCtrl 
{
    private final VeiculoRepository repoVeiculo;
    private final ClienteRepository repoCliente;
    private final LocacaoRepository repoLocacao;

    public CadastroLocacaoCtrl(VeiculoRepository repoVeiculo, ClienteRepository repoCliente, LocacaoRepository repoLocacao)
    {
        this.repoVeiculo = repoVeiculo;
        this.repoCliente = repoCliente;
        this.repoLocacao = repoLocacao;
    }

    public List<Erro> cadastrarLocacao(LocacaoRequest request)
    {
        try
        {
            LocalDateTime horaDataLocacao;
            Cliente cliente;
            Veiculo veiculo;

            //Verifica se existem placa e cpf
            if(request.cpf() != null)
                cliente = repoCliente.findByCPF(request.cpf());
            else
                cliente = null;
            
            veiculo = repoVeiculo.findByPlaca(request.placa());

            // Pegar hora da locação
            horaDataLocacao = LocalDateTime.now();

            var resultado = new LocacaoBuilder()
                            .withCliente(cliente)
                            .withVeiculo(veiculo)
                            .withhoraDataLocacao(horaDataLocacao)
                            .build();

            if(resultado.sucesso())
            {
                var locacao = resultado.valor;
                
                repoLocacao.add(locacao);
                
                return null;
            }
            else 
            {
                return resultado.erros;
            }

        }   
        catch(SQLException e)
        {
            return List.of(Erro.ERRO_BD);
        }
    }
}
