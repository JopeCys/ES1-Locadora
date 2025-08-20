package domain.Veiculo;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import domain.Persistent;
import domain.Resultado;
import domain.Erro;

/*
 * Class que representa veiculo
 */
public class Veiculo extends Persistent{

    private String placa;
    private String modelo;
    private Year anoFabricacao;
    private Double valorDiaria;
    private Integer quilometragem;

    //Construtor
    public Veiculo(String placa, String modelo, Year anoFabricacao, Double valorDiaria, Integer quilometragem) {
        super();
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.valorDiaria = valorDiaria;
        this.quilometragem = quilometragem;
    }

    public static Resultado<Veiculo> create(String placa, String modelo, Year anoFabricacao, Double valorDiaria, Integer quilometragem){
        List<Erro> erros = new ArrayList<>();
        
        // Tenta criar placa
        if(placa == null)
        {
            erros.add(Erro.PLACA_INVALIDA);
        }
        else
        {
            Placa placaNova = Placa.create(placa);

            if(placaNova == null)
            {
                erros.add(Erro.PLACA_INVALIDA);
            }
        }

        // Tenta criar o modelo
        if (modelo == null || modelo.length() < 3 || modelo.length() > 30) {
            erros.add(Erro.MODELO_INVALIDO);
        }

        // Tenta criar o ano de fabricação
        Year anoAtual = Year.now();
        if (anoFabricacao == null || anoFabricacao.isBefore(Year.of(2000)) || anoFabricacao.isAfter(anoAtual)) {
            erros.add(Erro.ANO_FABRICACAO_INVALIDO);
        }

        // Tenta criar o valor da diária
        if (valorDiaria == null || valorDiaria <= 0) {
            erros.add(Erro.VALOR_DIARIA_INVALIDO);
        }

        // Tenta criar a quilometragem
        if (quilometragem == null || quilometragem <= 0) {
            erros.add(Erro.QUILOMETRAGEM_INVALIDA);
        }

        // Retorna o veículo ou a lista de códigos de erro
        return erros.isEmpty() ?
                Resultado.ok(new Veiculo(placa, modelo, anoFabricacao, valorDiaria, quilometragem)) :
                Resultado.erro(erros);
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public Year getAnoFabricacao() {
        return anoFabricacao;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }
}
