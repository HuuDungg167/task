package com.hutech.demo.mapper;

import com.hutech.demo.model.Helper;
import com.hutech.demo.response.HelperResponse;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface HelperMapper {
//    Helper toHelper(CreateHelperRequest request);

    HelperResponse toResponse(Helper Helper);
    List<HelperResponse> getList(List<Helper> Helpers);
}
