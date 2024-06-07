package com.example.batch7.ch4.service.impl;

import com.example.batch7.ch4.entity.Merchant;
import com.example.batch7.ch4.entity.OrderDetail;
import com.example.batch7.ch4.entity.User;
import com.example.batch7.ch4.repository.MerchantRepository;
import com.example.batch7.ch4.repository.OrderDetailRepository;
import com.example.batch7.ch4.repository.OrderRepository;
import com.example.batch7.ch4.repository.UserRepository;
import com.example.batch7.ch4.service.InvoiceService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
public class ServiceImplInvoiceService implements InvoiceService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public File generateInvoice() {
        try {
            Document document = new Document();
            File file = new File("./cdn/GenerateInvoice.pdf");
            OutputStream outputStream = new FileOutputStream(file);

            PdfWriter.getInstance(document, outputStream);
            document.open();

            List<User> userPage = userRepository.findAll();
            List<OrderDetail> orderDetails = orderDetailRepository.findAll();

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingAfter(10f);
            table.setSpacingBefore(10f);

            table.addCell("Order Id");
            table.addCell("Name");
            table.addCell("Product");
            table.addCell("Merchant");
            table.addCell("Price");

            for (OrderDetail orderDetail : orderDetails) {
                table.addCell(orderDetail.getOrder_id().getId().toString());
                table.addCell(orderDetail.getOrder_id().getUser_id().getUsername());
                table.addCell(orderDetail.getProduct_id().getProduct_name());
                table.addCell(orderDetail.getProduct_id().getMerchant_id().getMerchant_name());
                table.addCell(String.valueOf(orderDetail.getTotal_price()));
            }

            document.add(table);
            document.close();
            outputStream.close();
            System.out.println("PDF Created: " + file.getAbsolutePath());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public File generateReportingMerchat() {
        try {
            Document document = new Document();
            File file = new File("./cdn/GenerateReportMerchant.pdf");
            OutputStream outputStream = new FileOutputStream(file);

            PdfWriter.getInstance(document, outputStream);
            document.open();

            List<Merchant> merchants = merchantRepository.findAll();

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingAfter(10f);
            table.setSpacingBefore(10f);

            table.addCell("Merchant Id");
            table.addCell("Merchant Name");
            table.addCell("Merchant Lokasi");

            for (Merchant merchant : merchants) {
                table.addCell(merchant.getId().toString());
                table.addCell(merchant.getMerchant_name());
                table.addCell(merchant.getMerchant_location());
            }

            document.add(table);
            document.close();
            outputStream.close();
            System.out.println("PDF Created: " + file.getAbsolutePath());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}