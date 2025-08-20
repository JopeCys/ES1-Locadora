package domain.dao;

import java.time.LocalDateTime;

/**
 * Classe usada para transportar dados do ClienteDAO para o ClienteRepository
 */
public record LocacaoDTO(String id,
                         String idVeiculo,
                         String idCliente,
                         LocalDateTime horaDataLocacao) {
}
