package com.kawahedukasi.controller;

import com.kawahedukasi.service.SoalDuaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/api/soaldua")
@Produces(MediaType.APPLICATION_JSON)
public class SoalDuaController {

    @Inject
    SoalDuaService service;

    @GET
    public Response factorial(@QueryParam("n") int n) {
        Map<Integer, Long> jawabanSoalDua = new HashMap<>();

        for (int i = 0; i <= n; i++) {
            jawabanSoalDua.put(i, service.hitungFaktorial(i));
        }

        return Response.ok(jawabanSoalDua).build();
    }
}
