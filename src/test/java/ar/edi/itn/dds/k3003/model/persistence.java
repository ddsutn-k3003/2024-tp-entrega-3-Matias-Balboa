package ar.edi.itn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;
import ar.edu.utn.dds.k3003.model.Vianda;
import ar.edu.utn.dds.k3003.repositories.ViandaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class persistence {

    static EntityManagerFactory entityManagerFactory ;
    EntityManager entityManager ;

    @BeforeAll
    public static void setUpClass() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("entrega3_tp_dds");
    }
    @BeforeEach
    public void setup() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
    }
    @Test
    public void testConectar() {
// vacío, para ver que levante el ORM
    }

    @Test
    public void testGuardarYRecuperarDoc() throws Exception {

        Vianda v1 = new Vianda(2L, "A1",2L,2,EstadoViandaEnum.PREPARADA);

        v1 = new ViandaRepository().save(v1);

        entityManager = entityManagerFactory.createEntityManager();
        Vianda v2 = entityManager.find(Vianda.class,v1.getId());
        Assertions.assertEquals(v1.getQr(), v2.getQr()); // también puede redefinir el equals
    }

}
