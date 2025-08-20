package persistence;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import domain.Locacao.Locacao;
import domain.dao.ILocacaoDAO;
import domain.dao.LocacaoDTO;

public class LocacaoDAO implements ILocacaoDAO 
{
    public void insert(Locacao locacao) throws SQLException 
    {
        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
            var stmt = conn.prepareStatement("insert into locacoes (id, idCliente, idVeiculo, horaDataLocacao) values (?, ?, ?, ?)")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = locacao.gethoraDataLocacao().format(formatter);
            
            // Define os valores dos parâmetros
            stmt.setString(1, locacao.getId());
            stmt.setString(2, locacao.getCliente().getId());
            stmt.setString(3, locacao.getVeiculo().getId());
            stmt.setString(4, formattedDateTime);
            
            // Executar o comando
            stmt.execute();
        }
    }

    @Override
    public void update(Locacao locacao) throws SQLException 
    {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("update locacoes set id=?, idCliente=?, idVeiculo=?, horaDataLocacao=?")) 
             {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = locacao.gethoraDataLocacao().format(formatter);

            stmt.setString(1, locacao.getId());
            stmt.setString(2, locacao.getCliente().getId());
            stmt.setString(3, locacao.getVeiculo().getId());
            stmt.setString(4, formattedDateTime);
            // Executa o comando
            stmt.execute();
        }
    }
    @Override
    public List<LocacaoDTO> findAll() throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        // Executa o comando que retorna um ResultSet
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("select * from locacoes")) {

            var mapper = new LocacaoMapper();
            var locacoes = new ArrayList<LocacaoDTO>();

            // Para todos os registros vindos do BD, converte os dados
            // do ResultSet em DTO usando o mapper
            while (rs.next()) {
                locacoes.add(mapper.map(rs));
            }

            return locacoes;
        }
    }
}
