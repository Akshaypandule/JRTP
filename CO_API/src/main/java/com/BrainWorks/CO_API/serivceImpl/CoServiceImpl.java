package com.BrainWorks.CO_API.serivceImpl;

import com.BrainWorks.CO_API.binding.CoResponse;
import com.BrainWorks.CO_API.entity.CitizenAppEntity;
import com.BrainWorks.CO_API.entity.CoTriggerEntity;
import com.BrainWorks.CO_API.entity.DcCaseEntity;
import com.BrainWorks.CO_API.entity.ElgDtlsEntity;
import com.BrainWorks.CO_API.repo.CitizenAppRepo;
import com.BrainWorks.CO_API.repo.CoTriggerRepo;
import com.BrainWorks.CO_API.repo.DcCaseRepo;
import com.BrainWorks.CO_API.repo.ElgDtlsRepo;
import com.BrainWorks.CO_API.service.CoService;
import com.BrainWorks.CO_API.utitlity.EmailUtility;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class CoServiceImpl implements CoService {

    @Autowired
    private CoTriggerRepo coTriggerRepo;
    @Autowired
    private ElgDtlsRepo elgDtlsRepo;
    @Autowired
    private DcCaseRepo dcCaseRepo;
    @Autowired
    private CitizenAppRepo citizenAppRepo;

    @Autowired
    private EmailUtility emailUtility;
    @Override
    public CoResponse processOnPendingTrigger() throws IOException, MessagingException {
        CitizenAppEntity citizenAppEntity=null;
        CoResponse coResponse=new CoResponse();
        List<CoTriggerEntity> pending = coTriggerRepo.findByTrigStatus("Pending");
        coResponse.setTotalTrigger(Long.valueOf(pending.size()));
        for(CoTriggerEntity entity:pending)
        {
            ElgDtlsEntity elgiEntity = elgDtlsRepo.findByCaseNum(entity.getCaseNum());
            Optional<DcCaseEntity> findBy = dcCaseRepo.findById(entity.getCaseNum());
            if(findBy.isPresent()){
                DcCaseEntity dcCaseEntity = findBy.get();
                Integer appId = dcCaseEntity.getAppId();
                Optional<CitizenAppEntity> byId = citizenAppRepo.findById(appId);
                if(byId.isPresent()){
                     citizenAppEntity = byId.get();
                }
            }
            Long failed=0l;
            Long success=0l;
            try{
                generateAndSendPdf(elgiEntity,citizenAppEntity);
                success++;
            }
            catch (Exception e){
                e.printStackTrace();
                failed++;
            }
            // generate Pdf and send Email
            coResponse.setSuccessTrigger(success);
            coResponse.setFailedTrigger(failed);
        }
        return coResponse;
    }
    private void generateAndSendPdf(ElgDtlsEntity elgi,CitizenAppEntity citizenApp) throws IOException, MessagingException
    {
       Document document=new Document(PageSize.A4);
       File file=new File(elgi.getCaseNum()+".pdf");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PdfWriter.getInstance(document,fileOutputStream);
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        font.setColor(Color.MAGENTA);
        Paragraph p=new Paragraph("Status Report",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f,3.5f,3.0f,3.0f,1.5f,3.0f,3.0f});
        table.setSpacingBefore(10);


        PdfPCell pdfCell=new PdfPCell();
        pdfCell.setBackgroundColor(Color.BLUE);
        pdfCell.setPadding(5);
        font = FontFactory.getFont(FontFactory.HELVETICA);

        pdfCell.setPhrase(new Phrase("citizen Name",font));
        table.addCell(pdfCell);


        pdfCell.setPhrase(new Phrase("Plan name",font));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("Plan Status",font));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("Plan Start Date",font));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("Plan end Date"));
        table.addCell(pdfCell);

        pdfCell.setPhrase(new Phrase("Benefit Amount",font));
        table.addCell(pdfCell);
        pdfCell.setPhrase(new Phrase("Denial Reason",font));
        table.addCell(pdfCell);



            table.addCell(citizenApp.getFullName());
            table.addCell(elgi.getPlanName());
            table.addCell(elgi.getPlanStatus());
            table.addCell(elgi.getPlanStartDate().toString());
            table.addCell(elgi.getPlanEndDate().toString());
            table.addCell(elgi.getBenefitAmount()+" ");
            table.addCell(elgi.getDenialReason());
        document.add(table);
        document.close();
        String subject=" ";
        String body=" ";

        emailUtility.sendEmail(citizenApp.getEmail(),subject,body,file);
        updateTrigger(elgi.getCaseNum(), file);
    }
    private void updateTrigger(Long caseNum,File file) throws IOException
    {
        CoTriggerEntity coEntity = coTriggerRepo.findByCaseNum(caseNum);
        byte[] arr=new byte[(byte) file.length()];
        FileInputStream fis=new FileInputStream(file);
        fis.read(arr);
        coEntity.setPdf(arr);
        coEntity.setTrigStatus("Complete");
        coTriggerRepo.save(coEntity);
    }
}
