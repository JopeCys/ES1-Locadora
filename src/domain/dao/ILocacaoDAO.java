package domain.dao;

import java.sql.SQLException;
import java.util.List;

import domain.Locacao.Locacao;

public interface ILocacaoDAO {

    void insert(Locacao locacao) throws SQLException;

    void update(Locacao locacao) throws SQLException;

    List<LocacaoDTO> findAll() throws SQLException;
}
