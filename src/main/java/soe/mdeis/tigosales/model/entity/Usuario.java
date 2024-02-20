package soe.mdeis.tigosales.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soe.mdeis.tigosales.enums.Enums.TipoUsuario;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario extends Persona {

    @Column(length = 20, nullable = false, unique = true)
    private String user;

    @Column(length = 120, nullable = false)
    private String password;

    private TipoUsuario tipoUsuario;

    private boolean activo;
}
