package com.hutech.demo.mapper;

import com.hutech.demo.model.Helper;
import com.hutech.demo.response.HelperResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class HelperMapperImpl implements HelperMapper {

    @Override
    public HelperResponse toResponse(Helper Helper) {
        if ( Helper == null ) {
            return null;
        }

        HelperResponse helperResponse = new HelperResponse();

        helperResponse.setId( Helper.getId() );
        helperResponse.setUser( Helper.getUser() );
        helperResponse.setExperience( Helper.getExperience() );
        helperResponse.setSkills( Helper.getSkills() );
        helperResponse.setAvailability( Helper.getAvailability() );
        helperResponse.setRatePerHour( Helper.getRatePerHour() );
        helperResponse.setProfilePicture( Helper.getProfilePicture() );
        helperResponse.setRating( Helper.getRating() );
        helperResponse.setCreatedAt( Helper.getCreatedAt() );
        helperResponse.setUpdatedAt( Helper.getUpdatedAt() );

        return helperResponse;
    }

    @Override
    public List<HelperResponse> getList(List<Helper> Helpers) {
        if ( Helpers == null ) {
            return null;
        }

        List<HelperResponse> list = new ArrayList<HelperResponse>( Helpers.size() );
        for ( Helper helper : Helpers ) {
            list.add( toResponse( helper ) );
        }

        return list;
    }
}
