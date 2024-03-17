package com.Brainwork.Report_API.serviceImpl;

import com.Brainwork.Report_API.entity.EligibilityDetails;
import com.Brainwork.Report_API.repo.EligibilityDetailsRepo;
import com.Brainwork.Report_API.request.SearchRequest;
import com.Brainwork.Report_API.response.SearchResponse;
import com.Brainwork.Report_API.service.EligibilityService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class EligibilityDetailsImpl implements EligibilityService {

    @Autowired
    private EligibilityDetailsRepo eligibilityDetailsRepo;

    @Override
    public List<String> getUniquePlansName() {

        return eligibilityDetailsRepo.findPlanName();
    }

    @Override
    public List<String> getUniquePlanStatus() {
        return eligibilityDetailsRepo.findPlanStatus();
    }

    @Override
    public List<SearchResponse> search(SearchRequest searchRequest) {
        List<SearchResponse> response = new ArrayList<>();
        EligibilityDetails queryBuilder = new EligibilityDetails();

        String planName = searchRequest.getPlanName();
        if (planName != null && !planName.equals("")) {
            queryBuilder.setPlanName(planName);
        }

        String planStatus = searchRequest.getPlanStatus();
        if (planStatus != null && !planStatus.equals("")) {
            queryBuilder.setPlanStatus(planStatus);
        }

        LocalDate planStartDate = searchRequest.getPlanStartDate();
        if (planStartDate != null) {
            queryBuilder.setPlanStartDate(planStartDate);
        }

        LocalDate planEndDate = searchRequest.getPlanEndDate();
        if (planEndDate != null) {
            queryBuilder.setPlanEndDate(planEndDate);
        }

        Example<EligibilityDetails> example = Example.of(queryBuilder);

        List<EligibilityDetails> findAll = eligibilityDetailsRepo.findAll(example);

        for (EligibilityDetails entity : findAll) {
            SearchResponse sr = new SearchResponse();
            BeanUtils.copyProperties(entity, sr);
            response.add(sr);
        }

        return response;
    }
    @Override
    public void generatePdf(HttpServletResponse response) throws Exception
    {
        List<EligibilityDetails> entities=eligibilityDetailsRepo.findAll();
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.MAGENTA);

        Paragraph p=new Paragraph("Search Report",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f,3.5f,3.0f,3.0f,1.5f});
        table.setSpacingBefore(10);


        PdfPCell pdfCell=new PdfPCell();
        pdfCell.setBackgroundColor(Color.BLUE);
        pdfCell.setPadding(5);
        font = FontFactory.getFont(FontFactory.HELVETICA);

        pdfCell.setPhrase(new Phrase("name",font));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("Email",font));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("Pno",font));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("Gender"));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("SSN",font));
        table.addCell(pdfCell);


        for(EligibilityDetails entity:entities){
            table.addCell(entity.getName());
            table.addCell(entity.getEmail());
            table.addCell(String.valueOf(entity.getMobile()));
            table.addCell(String.valueOf(entity.getGender()));
            table.addCell(String.valueOf(entity.getSsn()));
        }
        document.add(table);
        document.close();


    }

    @Override
    public void generateExcel(HttpServletResponse response) throws Exception {
        List<EligibilityDetails> entities = eligibilityDetailsRepo.findAll();

        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        HSSFRow rowHeader = sheet.createRow(0);

        rowHeader.createCell(0).setCellValue("Sr.No");
        rowHeader.createCell(1).setCellValue("Name");
        rowHeader.createCell(2).setCellValue("Mobile");
        rowHeader.createCell(3).setCellValue("Gender");
        rowHeader.createCell(4).setCellValue("SSN");

        int i = 1;
        for (EligibilityDetails entity : entities) {
            HSSFRow dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue(i);
            dataRow.createCell(1).setCellValue(entity.getName());
            dataRow.createCell(2).setCellValue(String.valueOf(entity.getMobile()));
            dataRow.createCell(3).setCellValue(String.valueOf(entity.getGender()));
            dataRow.createCell(4).setCellValue(String.valueOf(entity.getSsn()));
            i++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        book.write(outputStream);
        book.close();
        outputStream.close();
    }

}
