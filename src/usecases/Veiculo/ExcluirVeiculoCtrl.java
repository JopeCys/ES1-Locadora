package usecases.Veiculo;

import java.sql.SQLException;

import domain.Veiculo.VeiculoRepository;

public class ExcluirVeiculoCtrl {

    private VeiculoRepository repo;

    public ExcluirVeiculoCtrl(VeiculoRepository repo) {
        this.repo = repo;
    }

    public boolean excluirVeiculo(String placa) throws SQLException {
        var veiculoADeletar = repo.findByPlaca(placa);

        if(veiculoADeletar != null)
        {
            repo.remove(veiculoADeletar);
            return true;
        }
        return false;
    }
}
