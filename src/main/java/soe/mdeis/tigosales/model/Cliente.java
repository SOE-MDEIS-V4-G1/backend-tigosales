package soe.mdeis.tigosales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente extends Persona {

    @Column(length = 100)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;
}
