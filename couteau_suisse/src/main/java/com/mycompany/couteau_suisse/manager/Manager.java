/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.mycompany.couteau_suisse.manager;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author eddy.parisi
 */
public class Manager {
    PdfDocument pdfDownload = null;
    
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
}
