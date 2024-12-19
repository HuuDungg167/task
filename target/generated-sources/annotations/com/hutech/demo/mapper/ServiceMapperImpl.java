package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateServiceRequest;
import com.hutech.demo.model.Service;
import com.hutech.demo.response.ServiceResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public Service toService(CreateServiceRequest request) {
        if ( request == null ) {
            return null;
        }

        Service.ServiceBuilder service = Service.builder();

        service.name( request.getName() );
        service.description( request.getDescription() );

        return service.build();
    }

    @Override
    public ServiceResponse toResponse(Service Service) {
        if ( Service == null ) {
            return null;
        }

        ServiceResponse serviceResponse = new ServiceResponse();

        serviceResponse.setId( Service.getId() );
        serviceResponse.setName( Service.getName() );
        serviceResponse.setDescription( Service.getDescription() );
        serviceResponse.setCreatedAt( Service.getCreatedAt() );
        serviceResponse.setUpdatedAt( Service.getUpdatedAt() );

        return serviceResponse;
    }

    @Override
    public List<ServiceResponse> getList(List<Service> Services) {
        if ( Services == null ) {
            return null;
        }

        List<ServiceResponse> list = new ArrayList<ServiceResponse>( Services.size() );
        for ( Service service : Services ) {
            list.add( toResponse( service ) );
        }

        return list;
    }
}
