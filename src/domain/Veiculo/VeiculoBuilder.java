package domain.Veiculo;

import java.time.Year;
import java.util.List;
import java.util.ArrayList;

import domain.Erro;
import domain.Resultado;

public class VeiculoBuilder {
    private String placa;
    private String modelo;
    private Year anoFabricacao;
    private Double valorDiaria;
    private Integer quilometragem;

    public VeiculoBuilder() {
    }

    public VeiculoBuilder withPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public VeiculoBuilder withModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public VeiculoBuilder withAnoFabricacao(Year anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
        return this;
    }

    public VeiculoBuilder withValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
        return this;
    }

    public VeiculoBuilder withQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
        return this;
    }

    public Resultado<Veiculo> build()
    {
        List<Erro> erros = new ArrayList<>();

        // Tenta criar Veiculo
        var resultVeiculo = Veiculo.create(placa, modelo, anoFabricacao, valorDiaria, quilometragem);

        // Se houver erros adiciona a lista
        if(resultVeiculo.falha())
            erros.addAll(resultVeiculo.erros);
        
        // Retorna lista de erros se houver
        return erros.isEmpty() ? 
            Resultado.ok(resultVeiculo.valor) : 
            Resultado.erro(erros);
    }
}
