package ar.edu.utn.dds.k3003.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "viandasv1")
public class Vianda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vianda_qr")
    private String qr;
    
    @Column(name = "vianda_colaborador_id")
    private Long colaboradorId;
    @Column(name = "vianda_heladera_id")
    private Integer heladeraId;
    @Column(name = "vianda_estado")
    @Enumerated(EnumType.STRING)
    private EstadoViandaEnum estado;
    @Column(name = "vianda_fecha_elaboracion")
    private LocalDateTime fechaElaboracion;

    public Vianda(Long id,String qr,Long colaboradorId,Integer heladeraId,EstadoViandaEnum estado){
        this.id = id;
        this.qr = qr;
        this.colaboradorId = colaboradorId;
        this.heladeraId = heladeraId;
        this.estado = estado;
        this.fechaElaboracion = LocalDateTime.now();
    }

    public Vianda(String qr,Long colaboradorId,Integer heladeraId,EstadoViandaEnum estado){
        this.qr = qr;
        this.colaboradorId = colaboradorId;
        this.heladeraId = heladeraId;
        this.estado = estado;
        this.fechaElaboracion = LocalDateTime.now();
    }
    public Vianda(String qr,Long colaboradorId,Integer heladeraId,EstadoViandaEnum estado,LocalDateTime fechaElaboracion){
        this.qr = qr;
        this.colaboradorId = colaboradorId;
        this.heladeraId = heladeraId;
        this.estado = estado;
        this.fechaElaboracion = fechaElaboracion;
    }
    public Boolean esValida(Integer mes, Integer anio){
        return (this.fechaElaboracion.getYear() == anio && this.fechaElaboracion.getMonthValue() == mes);
    }


}
