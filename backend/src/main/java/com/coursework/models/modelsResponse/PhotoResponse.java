package com.coursework.models.modelsResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoResponse {
    private Long id;
    private Long institutionId;
    private String url;


    public PhotoResponse(Long id, Long institutionId, String url) {
        this.id = id;
        this.institutionId = institutionId;
        this.url = url;
    }


}
