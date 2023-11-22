package be.cm.customer;

import be.cm.customer.dto.CreateCustomeDto;
import be.cm.customer.dto.CustomerDto;
import be.cm.customer.services.CustomerMapper;
import be.cm.customer.services.CustomerService;
import be.cm.security.Feature;
import be.cm.security.services.SecurityService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.Collection;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {


    private final CustomerService customerService;
    private final SecurityService securityService;

    public CustomerResource(CustomerService customerService, SecurityService securityService) {
        this.customerService = customerService;
        this.securityService = securityService;
    }


    @POST
    @ResponseStatus(201)
    public CustomerDto createCustomerAccount(CreateCustomeDto createCustomeDto) {
        return CustomerMapper.mapToDto(customerService.createCustomerAccount(createCustomeDto));
    }

    @GET
    @ResponseStatus(200)
    public Collection<CustomerDto> viewAllCustomers(@RestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.VIEW_ALL_CUSTOMERS);
        return CustomerMapper.mapToDto(customerService.getAllCustomers());
    }

    @Path("/{id}")
    @GET
    @ResponseStatus(200)
    public CustomerDto viewCustomerById(@RestHeader String authorization, @PathParam("id") Long id){
        securityService.validateAuthorization(authorization, Feature.VIEW_A_CUSTOMER);
        return CustomerMapper.mapToDto(customerService.getCustomerById(id));
    }
}
