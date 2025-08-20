package usecases.Locacao;

import domain.Erro;
import domain.Locacao.Locacao;
import domain.Locacao.LocacaoRepository;
import domain.Resultado;

import java.sql.SQLException;
import java.util.List;

public class ListarLocacoesCtrl {
    private final LocacaoRepository repo;


    public ListarLocacoesCtrl(LocacaoRepository repo) {
        super();
        this.repo = repo;
    }
/**
 * Recupera todos as locações do cadastro
 *
 * @return Lista de locações ou erro de acesso ao BD
 */
public Resultado<List<Locacao>> recuperarTodasLocacoes() {
    try {
        // Recupera todas as locações usando o método findAll() do repositório.
        List<Locacao> locacoes = repo.findAll();
        return Resultado.ok(locacoes);
    } catch (SQLException e) {
        // Se ocorrer alguma exceção no BD, avisa.
        return Resultado.erro(List.of(Erro.ERRO_BD));
    }
}
}
