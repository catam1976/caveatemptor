package ro.clovertech.backend.resources;

import ro.clovertech.backend.business.CategoryTO;
import ro.clovertech.backend.business.IService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("category")
@RequestScoped
public class CategoryResource {

    @Inject
    private IService service;

    @GET
    public Response getCategories() {
        List<CategoryTO> rootCategories = service.getCategories();
        return Response.ok(rootCategories).build();
    }
}
