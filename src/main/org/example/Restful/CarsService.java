package org.example.Restful;

import org.example.Dao.CarsDAO;
import org.example.Model.Car;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;


    @Path("/cars")
    public class CarsService {

        private CarsDAO dao = CarsDAO.getInstance();

        @GET
        @Path("/isAlive")
        @Produces(MediaType.TEXT_PLAIN)
        public String isAlive() {

            return "ServerTime is " + new Date();


        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response showAll() {
            List<Car> all = dao.all();
            if (all.size() > 0) {
                return Response.ok(all).build();
            }
            return Response.noContent().build();
        }

        @GET
        @Path("all-xml")
        @Produces(MediaType.APPLICATION_XML)
        public Response showAllXML() {
            List<Car> all = dao.all();
            if (all.size() > 0) {
                return Response.ok(all).build();
            }
            return Response.noContent().build();
        }

        @GET
        @Path("/first")
        @Produces(MediaType.APPLICATION_JSON)
        public Response first() {
            List<Car> all = dao.all();
            if (all.isEmpty()) {
                return Response.noContent().build();
            }
            return Response.ok(all.get(0)).build();
        }

        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getById(@PathParam("id") int id) {
            Car car = dao.getById(id);
            if (car != null) {
                return Response.ok(car).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }


        }

        @POST
        @Path("/add")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addCar(Car car) {
            int id = dao.add(car);
            return Response.ok().build();

        }


        //TODO
        @PUT
        @Path("/update")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateCar(Car car) {
            boolean updated = dao.update(car);
            if (updated) {
                return Response.ok("UPDATED").build();
            } else {
                return Response.status(Response.Status.NOT_MODIFIED).build();
            }
        }

        @DELETE
        @Path("/delete/{id}")
        public Response delete(@PathParam("id")int id){
            boolean isDeleted=dao.delete(id);
            if(isDeleted){
                return Response.ok("DELETED").build();
            }else{
                return Response.status(Response.Status.NOT_MODIFIED).build();

            }
        }
    }


