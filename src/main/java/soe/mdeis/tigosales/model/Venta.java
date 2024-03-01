package soe.mdeis.tigosales.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soe.mdeis.tigosales.enums.Enums.TipoOperacionCaja;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Venta extends OperacionCaja {

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Override
    @JsonIgnore
    @Transient
    public TipoOperacionCaja getTipo() {
        return TipoOperacionCaja.VENTA;
    }

    @Transient
    private List<ItemVendido> itemVendidos;

}
