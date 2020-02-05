/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteau_suisse.manager;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.utils.PdfMerger;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eddy.parisi
 */
public class ManagerTest {
    
    public ManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of fusionnerDeuxPdf method, of class Manager.
     */
    @org.junit.Test
    public void testFusionnerDeuxPdf() throws IOException {
        PdfDocument un = new PdfDocument(new PdfReader("C:\\Users\\eddy.parisi\\Downloads\\TP - individuel.pdf"));
        PdfDocument deux = new PdfDocument(new PdfReader("C:\\Users\\eddy.parisi\\Downloads\\1910171033GY772 (2).pdf"));
        
        PdfMerger merger = new PdfMerger(un);
        merger.setCloseSourceDocuments(true);
        PdfMerger a = merger.merge(deux, 1, deux.getNumberOfPages());
        assertTrue(true);
    }
    
}
