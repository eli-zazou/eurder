package be.cm.order;

import be.cm.order.dto.OrderDto;
import be.cm.order.dto.CreateOrderDto;
import be.cm.order.entities.OrderReport;
import be.cm.order.services.OrderMapper;
import be.cm.order.services.OrderService;
import be.cm.security.Feature;
import be.cm.security.services.SecurityService;
import be.cm.security.user.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.jboss.resteasy.reactive.RestHeader;

@Path("/orders")
public class OrderResource {

    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderResource(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @POST
    public OrderDto orderItems(@RestHeader String authorization, CreateOrderDto createOrderDto) {
        User user = securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        return OrderMapper.mapToDto(orderService.createOrder(createOrderDto, user.getCustomer()));
    }

    @GET
    public OrderReport viewReportOfOrders(@RestHeader String authorization) {
        User user = securityService.validateAuthorization(authorization, Feature.VIEW_ORDER_REPORTS);
        return orderService.viewReportOfOrders(user.getCustomer());
    }

    @POST
    @Path("/{id}")
    public OrderDto reOrder(@RestHeader String authorization, @PathParam("id") Long id) {
        User user = securityService.validateAuthorization(authorization, Feature.REORDER_ORDER);
        return OrderMapper.mapToDto(orderService.reOrder(id, user.getCustomer()));
    }

}
