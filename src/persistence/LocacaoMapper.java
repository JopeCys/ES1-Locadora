package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import domain.dao.LocacaoDTO;

/**
 * Classe responsável por mapear os dados vindos do BD (ResultSet) para o DTO Locacao
 */
public class LocacaoMapper 
{
    public LocacaoDTO map(ResultSet rs) throws SQLException {
    
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        return new LocacaoDTO(
                rs.getString("id"),
                rs.getString("idVeiculo"),
                rs.getString("idCliente"),
                LocalDateTime.parse(rs.getString("horaDataLocacao"), df)
        );
    }
}
