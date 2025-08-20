package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import domain.dao.VeiculoDTO;

/**
 * Classe responsável por mapear os dados vindos do BD (ResultSet) para o DTO Veiculo
 */
public class VeiculoMapper {

    /**
     * Mapeia os dados do ResultSet no DTO
     *
     * @param rs ResultSet com os dados
     * @return VeiculoDTO
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public VeiculoDTO map(ResultSet rs) throws SQLException {
        return new VeiculoDTO(
                rs.getString("id"),
                rs.getString("placa"),
                rs.getString("modelo"),
                Year.parse(rs.getString("anoFabricacao")),  // Converte a string diretamente para Year
                rs.getDouble("valorDiaria"),
                rs.getInt("quilometragem")
        );
    }
}
