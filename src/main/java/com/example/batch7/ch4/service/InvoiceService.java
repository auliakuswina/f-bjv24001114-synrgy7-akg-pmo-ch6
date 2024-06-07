package com.example.batch7.ch4.service;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Map;

public interface InvoiceService {
    File generateInvoice();

    File generateReportingMerchat();
}
