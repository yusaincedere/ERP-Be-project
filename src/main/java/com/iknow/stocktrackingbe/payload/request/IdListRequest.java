package com.iknow.stocktrackingbe.payload.request;

import lombok.*;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdListRequest {
    private Set<Long> idList;
}
