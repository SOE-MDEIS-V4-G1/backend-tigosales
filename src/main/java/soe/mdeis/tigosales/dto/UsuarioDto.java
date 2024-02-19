package soe.mdeis.tigosales.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private String ci;

    private String nombre;

    private String apellido;

    private String user;

    private String password;

    private String tipo;

    private boolean activo;
}
