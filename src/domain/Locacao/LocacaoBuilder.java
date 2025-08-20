package domain.Locacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import domain.Erro;
import domain.Resultado;
import domain.Cliente.Cliente;
import domain.Veiculo.Veiculo;

public class LocacaoBuilder 
{
    private Veiculo veiculo;
    private Cliente cliente;
    private LocalDateTime horaDataLocacao;

    public LocacaoBuilder() {
    }

    public LocacaoBuilder withVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
        return this;
    }

    public LocacaoBuilder withCliente(Cliente cliente){
        this.cliente = cliente;
        return this;
    }

    public LocacaoBuilder withhoraDataLocacao(LocalDateTime horaDataLocacao) {
        this.horaDataLocacao = horaDataLocacao;
        return this;
    }

    public Resultado<Locacao> build() {
        List<Erro> erros = new ArrayList<>();

        // Tenta criar Locacao
        var resultLocacao = Locacao.create(veiculo, cliente, horaDataLocacao);

        // Se houver erros, adiciona à lista
        if(resultLocacao.falha())
            erros.addAll(resultLocacao.erros);
        
        // Retorna lista de erros se houver
        return erros.isEmpty() ? 
            Resultado.ok(resultLocacao.valor) : 
            Resultado.erro(erros);
    }
}
