/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.mycompany.couteau_suisse.manager;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author eddy.parisi
 */
@Named
@RequestScoped
public class Manager {
    PdfDocument pdfDownload;
    private UploadedFile file;
    
    public void upload() throws IOException {
        if (file != null) {
            //préciser 
            Path path = Paths.get("C:\\images\\");
            String filename = FilenameUtils.getBaseName(file.getFileName());
            String extension = FilenameUtils.getExtension(file.getFileName());
            System.out.println(path + " " + filename + "." + extension);
            Path fichierTempo = Files.createTempFile(path, filename, "." + extension);
            
            try (InputStream input = file.getInputstream()) {
                Files.copy(input, fichierTempo, StandardCopyOption.REPLACE_EXISTING);
                FacesMessage message = new FacesMessage(file.getFileName() + " : is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
    
    public void fusionnerDeuxPdf() throws IOException{
        PdfWriter writer = new PdfWriter(new File("src/main/webapp/resources/pdf/pdf7.pdf"));
        writer.setSmartMode(true);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.initializeOutlines();
        PdfDocument addedDoc = new PdfDocument(new PdfReader(new File("src/main/webapp/resources/pdf/volotea.pdf")));
        addedDoc.copyPagesTo(1, addedDoc.getNumberOfPages(), pdfDoc);
        addedDoc.close();
        pdfDoc.close();
        pdfDownload = pdfDoc;
    }
    
    public static void main(String[] args) throws IOException {
    }
    
    
    
    public UploadedFile getFile() {
        return file;
    }
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
