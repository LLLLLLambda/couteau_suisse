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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author eddy.parisi
 */
@Named
@RequestScoped
public class Manager {
    private final String pathPdfWritter = "src/main/webapp/resources/pdf/pdf7.pdf";
    private StreamedContent pdfDownload;
    private UploadedFile file;
    private List<InputStream> listFilesUploaded = new ArrayList<>();
    
    /**
     * Ajoute le fichier upload par l'utilisateur dans listFilesUploaded et 
     * lui signal avec un pop up sur son interface
     * 
     * @throws IOException 
     */
    public void upload() throws IOException {
        if (file != null) {
            InputStream in = file.getInputstream();
            listFilesUploaded.add(in);
            
            FacesMessage message = new FacesMessage(file.getFileName() + " : is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * Parcourt listFilesUploaded, creer  une fusion stocké dans pathPdfWritter,
     * et afffecté à pdfDownload.
     * 
     * @throws IOException 
     */
    public void fusionnerDeuxPdf() throws IOException{
        PdfWriter writer = new PdfWriter(new File(pathPdfWritter));
        writer.setSmartMode(true);
        try (PdfDocument pdfDoc = new PdfDocument(writer)) {
            pdfDoc.initializeOutlines();
            
            for (InputStream in : listFilesUploaded){
                try (PdfDocument docToAdd = new PdfDocument(new PdfReader(in))) {
                    docToAdd.copyPagesTo(1, docToAdd.getNumberOfPages(), pdfDoc);
                    docToAdd.close();
                }           
            }
        }
        
        InputStream in = new FileInputStream(new File(pathPdfWritter));
        pdfDownload = new DefaultStreamedContent(in);
    }
    
    public static void main(String[] args) throws IOException {
    }
    
    public List<InputStream> getListFilesUploaded() {
        return listFilesUploaded;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public String getPathPdfWritter() {
        return pathPdfWritter;
    }

    public StreamedContent getPdfDownload() {
        return pdfDownload;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage(event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
