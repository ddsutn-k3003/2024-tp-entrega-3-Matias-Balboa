package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Vianda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;

public class ViandaRepository {


    //private Collection<Vianda> viandas;
    private static AtomicLong seqId = new AtomicLong();
    //private Collection<Vianda> viandas;

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entrega3_tp_dds");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    public Vianda save(Vianda vianda) {
        entityManager.getTransaction().begin();
        entityManager.persist(vianda);
        entityManager.getTransaction().commit();
        return vianda;
    }

    public List<Vianda> list(){

        List<Vianda> viandas = entityManager.createQuery("from Vianda", Vianda.class).getResultList();

        return viandas;
    }

    public Vianda findById(Long id) {
        Collection<Vianda> viandas = entityManager.createQuery("from Vianda",Vianda.class).getResultList();
        Optional<Vianda> first = viandas.stream().filter(x -> x.getId().equals(id)).findFirst();
        return first.orElseThrow(() -> new NoSuchElementException(
                String.format("No hay una vianda de id: %s", id)
        ));
    }

    public Vianda findByQr(String qr) {
        Collection<Vianda> viandas = entityManager.createQuery("from Vianda",Vianda.class).getResultList();
        Optional<Vianda> first = viandas.stream().filter(x -> x.getQr().equals(qr)).findFirst();
        return first.orElseThrow(() -> new NoSuchElementException(
                String.format("No hay una vianda un QR: %s", qr)
        ));
    }


    public List<Vianda> findByColaborador(Long idColab, Integer mes, Integer anio) {
        Collection<Vianda> viandas = entityManager.createQuery("from Vianda", Vianda.class).getResultList();
        return viandas.stream().filter(v -> v.getColaboradorId() == idColab
                && v.esValida(mes,anio)).toList();
    }


    public void update(Vianda vianda){
        entityManager.getTransaction().begin();
        entityManager.merge(vianda);
        entityManager.getTransaction().commit();

    }
}
