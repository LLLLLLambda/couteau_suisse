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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author eddy.parisi
 */
@Named
@SessionScoped
public class Manager implements Serializable {

    private final String pathPdfWritter = "C:\\Users\\errab\\app-pdf\\pdf.pdf";
    private StreamedContent pdfDownload;
    private UploadedFile file;
    private List<UserFile> userFiles = new ArrayList<>();
    private List<InputStream> listFilesUploaded = new ArrayList<>();
    private int nombreDePage;

    //supprimer page
    private int numeroDeLaPageASupprimer;
    
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
            
            // Afficher le nom des fichiers enregistrés
            UserFile uf = new UserFile(file.getFileName());
            userFiles.add(uf);
            
            //set le nombre de page (fait uniquement pour : suppression de page)
            if(listFilesUploaded.size() == 1) {
                PdfDocument pdf = new PdfDocument(new PdfReader(in));
                nombreDePage = pdf.getNumberOfPages();
            }
            
            FacesMessage message = new FacesMessage(file.getFileName() + " : is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    /**
     * reset les lists
     */
    public void reset() {
        listFilesUploaded.clear();
        userFiles.clear();        
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
            
            InputStream in = new FileInputStream(new File(pathPdfWritter));
            pdfDownload = new DefaultStreamedContent(in, "application/pdf", "hop.pdf");
            
            reset();
        }
        
        InputStream in = new FileInputStream(new File(pathPdfWritter));
        pdfDownload = new DefaultStreamedContent(in, "application/pdf", "hop.pdf");
        
        reset();
    }
    
    /**
     * Supprime une page du pdf
     *
     * @throws IOException
     */
    public void supprimerPage() throws IOException{
        
        if (listFilesUploaded.size() != 1) {
            InputStream in = listFilesUploaded.get(0);
            
            try (PdfDocument docToRemovePage = new PdfDocument(new PdfReader(in))) {
                docToRemovePage.removePage(numeroDeLaPageASupprimer);
                docToRemovePage.getNumberOfPages();
                docToRemovePage.close();
            }
            
            pdfDownload = new DefaultStreamedContent(in, "application/pdf", "supprimer.pdf");
        }
        
            reset();
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
    
    public int getNombreDePage() {
        return nombreDePage;
    }

    public int getNumeroDeLaPageASupprimer() {
        return numeroDeLaPageASupprimer;
    }

    public void setNombreDePage(int nombreDePage) {
        this.nombreDePage = nombreDePage;
    }

    public void setNumeroDeLaPageASupprimer(int numeroDeLaPageASupprimer) {
        this.numeroDeLaPageASupprimer = numeroDeLaPageASupprimer;
    }
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public List<UserFile> getUserFiles() {
        return userFiles;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage(event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
