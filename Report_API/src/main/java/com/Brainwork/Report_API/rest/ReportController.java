package com.Brainwork.Report_API.rest;

import com.Brainwork.Report_API.request.SearchRequest;
import com.Brainwork.Report_API.response.SearchResponse;
import com.Brainwork.Report_API.service.EligibilityService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reportapi")
public class ReportController {

    @Autowired
    private EligibilityService service;


    @GetMapping("/plan")
    public ResponseEntity<List<String>> getAllPlans(){
        List<String> uniquePlansName = service.getUniquePlansName();
        return new ResponseEntity<>(uniquePlansName, HttpStatus.OK);
    }
    @GetMapping("/status")
    public ResponseEntity<List<String>> getAllPlanStatus(){
        List<String> uniquePlanStatus = service.getUniquePlanStatus();
        return new ResponseEntity<>(uniquePlanStatus,HttpStatus.OK);
    }


    @PostMapping("/search")
    public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest searchRequest){
        List<SearchResponse> search = service.search(searchRequest);
        return new ResponseEntity<>(search,HttpStatus.CREATED);
    }

    @GetMapping("/excel")
    public void excelReport(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment;filename=data.xls";
        response.setHeader(headerKey,headerValue);
        service.generateExcel(response);
    }
    @GetMapping("/pdf")
    public void pdfREport(HttpServletResponse response) throws Exception{
        response.setContentType("application/pdf");

        String headerKey="Content-Disposition";
        String headerValue="attachment;filename=data.pdf";
        response.setHeader(headerKey,headerValue);
        service.generatePdf(response);

    }
}
