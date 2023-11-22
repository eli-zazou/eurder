package be.cm.item;

import be.cm.item.dto.CreateItemDto;
import be.cm.item.dto.ItemDto;
import be.cm.item.dto.UpdateItemDto;
import be.cm.item.services.ItemMapper;
import be.cm.item.services.ItemService;
import be.cm.security.Feature;
import be.cm.security.services.SecurityService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;


@Path("/items")
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    private final ItemService itemService;
    private final SecurityService securityService;


    public ItemResource(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @POST
    @ResponseStatus(201)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDto addItem(@RestHeader String authorization, CreateItemDto createItemDto) {
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);
        return ItemMapper.mapToDto(itemService.addItem(createItemDto));
    }

    @Path("/{id}")
    @PUT
    @ResponseStatus(200)
    @Produces(MediaType.APPLICATION_JSON) // why when i put text_plain it won't work???
    public String updateItem(@RestHeader String authorization, @PathParam("id") Long id, UpdateItemDto updateItemDto) {
        securityService.validateAuthorization(authorization, Feature.UPDATE_ITEM);
        return itemService.updateItem(id, updateItemDto);
    }

}
