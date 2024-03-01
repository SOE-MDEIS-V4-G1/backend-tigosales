package soe.mdeis.tigosales.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
    private long cliente;
    private long usuario;
    private String observacion;
    private List<ItemVendidoDto> items;
}
