package domain.Locacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import domain.Erro;
import domain.Persistent;
import domain.Resultado;
import domain.Cliente.Cliente;
import domain.Veiculo.Veiculo;

public class Locacao extends Persistent {
    private Veiculo veiculo;
    private Cliente cliente;
    private LocalDateTime horaDataLocacao;

    public Locacao(Veiculo veiculo, Cliente cliente, LocalDateTime horaDataLocacao) {
        super();
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.horaDataLocacao = horaDataLocacao;
    }

    public static Resultado<Locacao> create(Veiculo veiculo, Cliente cliente, LocalDateTime horaDataLocacao) {
        List<Erro> erros = new ArrayList<>();

        if (veiculo == null) {
            erros.add(Erro.VEICULO_NAO_ENCONTRADO);
        }

        if (cliente == null) {
            erros.add(Erro.CLIENTE_NAO_ENCONTRADO);
        }

        return erros.isEmpty() ?
                Resultado.ok(new Locacao(veiculo, cliente, horaDataLocacao)) :
                Resultado.erro(erros);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime gethoraDataLocacao() {
        return horaDataLocacao;
    }

    public void sethoraDataLocacao(LocalDateTime horaDataLocacao) {
        this.horaDataLocacao = horaDataLocacao;
    }
}