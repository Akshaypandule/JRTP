package com.BrainWorks.ED_API.service;

import com.BrainWorks.ED_API.binding.ElgResponse;

public interface ElgDtlsService {

    public ElgResponse determineEligibility(Long caseNum);
}
