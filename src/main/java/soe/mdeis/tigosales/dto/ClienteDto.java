package soe.mdeis.tigosales.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private String ci;

    private String nombre;

    private String apellido;

    private String direccion;

    private String telefono;
}
