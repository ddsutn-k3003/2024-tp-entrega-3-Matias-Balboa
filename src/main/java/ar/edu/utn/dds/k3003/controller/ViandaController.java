package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;

import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;
import ar.edu.utn.dds.k3003.model.auxDto;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.NoSuchElementException;

public class ViandaController {

    private final Fachada fachada;

    public ViandaController(Fachada fachada){
        this.fachada = fachada;
    }

    public void agregar(Context context){
        var viandaDTO = context.bodyAsClass(ViandaDTO.class);
        var viandaDTORta = this.fachada.agregar(viandaDTO);
        context.json(viandaDTORta);
        context.status(HttpStatus.CREATED);
    }

    public void buscarPorQr(Context context) {
        var qr = context.pathParamAsClass("qr", String.class).get();
        try{
            var viandaDTO = this.fachada.buscarXQR(qr);
            context.json(viandaDTO);
        }catch(NoSuchElementException ex){
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }


    public void buscarPorColaboradorIdAnioMes(Context context) {
        var colaboradorId = context.queryParamAsClass("colaboradorId", Long.class).get();
        var anio = context.queryParamAsClass("anio", Integer.class).get();
        var mes = context.queryParamAsClass("mes", Integer.class).get();
        var viandaDTO = this.fachada.viandasDeColaborador(colaboradorId,mes,anio);
        context.json(viandaDTO);

    }

    public void estaVencida(Context context) {
        var qr = context.pathParamAsClass("qr", String.class).get();
        try{
            var estado = this.fachada.evaluarVencimiento(qr);
            context.json(estado);
        }catch(NoSuchElementException ex){
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }

    public void modificarHeladeraVianda(Context context) {
        var qr = context.pathParamAsClass("qrVianda", String.class).get();
        var heladeraDestino = context.bodyAsClass(auxDto.class);
        try {
            fachada.modificarHeladera(qr,heladeraDestino.getHeladeraId());

        }catch(NoSuchElementException ex){
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }
}
