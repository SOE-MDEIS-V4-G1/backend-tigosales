package soe.mdeis.tigosales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @OneToOne
    @JoinColumn(name = "ruta_id", nullable = true)
    private Ruta ruta;
}
