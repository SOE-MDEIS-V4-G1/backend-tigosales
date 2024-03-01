package soe.mdeis.tigosales.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendidoDto {

    private Long id;

    private double precio;

    private int cantidad;
}
