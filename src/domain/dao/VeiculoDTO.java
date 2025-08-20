package domain.dao;

import java.time.Year;
/**
 * Classe usada para transportar dados do ClienteDAO para o ClienteRepository
 */
public record VeiculoDTO (String id ,
                          String placa,
                          String modelo,
                          Year anoFabricacao,
                          Double valorDiaria,
                          Integer quilometragem) {
}
