package com.Brainwork.Report_API.service;

import com.Brainwork.Report_API.entity.EligibilityDetails;
import com.Brainwork.Report_API.request.SearchRequest;
import com.Brainwork.Report_API.response.SearchResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface EligibilityService {

    public List<String> getUniquePlansName();

    public List<String> getUniquePlanStatus();
    
    public List<SearchResponse> search(SearchRequest searchRequest);

    public void generatePdf(HttpServletResponse response)throws Exception;

    public void generateExcel(HttpServletResponse response) throws Exception;


}
