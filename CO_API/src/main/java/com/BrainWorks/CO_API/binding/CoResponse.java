package com.BrainWorks.CO_API.binding;

import lombok.Data;

@Data
public class CoResponse {

    private Long totalTrigger;
    private Long successTrigger;
    private Long failedTrigger;
}
