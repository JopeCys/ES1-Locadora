package usecases.Veiculo;

import java.util.List;
import java.sql.SQLException;

import domain.Veiculo.VeiculoBuilder;
import domain.Veiculo.VeiculoRepository;
import domain.Erro;

public class CadastroVeiculoCtrl 
{
    private final VeiculoRepository repo;

    public CadastroVeiculoCtrl(VeiculoRepository repo)
    {
        this.repo = repo;
    }

    public List<Erro> cadastrarVeiculo(VeiculoRequest request)
    {   
        try 
        {
            // Tenta registrar placa com caixa alta
            var placaCaixaAlta = request.placa().toUpperCase();
            
            // Tenta construir o veiculo através do builder
            var resultado = new VeiculoBuilder()
                                .withPlaca(placaCaixaAlta)
                                .withModelo(request.modelo())
                                .withAnoFabricacao(request.anoFabricao())
                                .withValorDiaria(request.valorDiaria())
                                .withQuilometragem(request.quilometragem())
                                .build();

            if(resultado.sucesso())
            {
                var veiculo = resultado.valor;

                // Verifica se existe placa duplicada, se não salvar veiculo
                var outroVeiculo = repo.findByPlaca(veiculo.getPlaca());
                

                // Caso exista placa duplicada 
                if(outroVeiculo != null)         
                    return List.of(Erro.PLACA_JA_EXISTENTE);
                
                
                repo.add(veiculo);
               
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
