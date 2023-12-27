package com.coursework.models.modelsResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoResponse {
    private Long id;
    private String url;
    private Long institutionId;



    public PhotoResponse(Long id,  String url,Long institutionId) {
        this.id = id;
        this.institutionId = institutionId;
        this.url = url;
    }


}
