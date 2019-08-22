package com.levi9.prodavnica.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.levi9.prodavnica.dto.OrderDTO;
import com.levi9.prodavnica.dto.OrderItemDTO;
import com.levi9.prodavnica.dto.OrderReportDTO;

@Component
public class PDFGenerator {

	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static ByteArrayInputStream ordersPDFReport(OrderReportDTO orderReportDTO) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();

			// adding text to PDF file
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Orders Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(6);
			// adding PDF table Header
			Stream.of("Order ID", "Order Date", "Book", "Book Qty",
					"Books Price", "Full Price").forEach(headerTitle -> {
						PdfPCell header = new PdfPCell();
						Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
						header.setBackgroundColor(BaseColor.LIGHT_GRAY);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.setBorderWidth(2);
						header.setPhrase(new Phrase(headerTitle, headFont));
						table.addCell(header);
					});

			for (OrderDTO oDTO : orderReportDTO.getOrderDTOList()) {
				for (OrderItemDTO oiDTO : oDTO.getOrderItemDTOList()) {
					// Order ID
					PdfPCell orderIdCell = new PdfPCell(new Phrase(oiDTO.getOrderId().toString()));
					orderIdCell.setPaddingLeft(2);
					orderIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					orderIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(orderIdCell);

					// Order Item ID
//					PdfPCell orderItemIdCell = new PdfPCell(new Phrase(oiDTO.getOrderItemId().toString()));
//					orderItemIdCell.setPaddingLeft(2);
//					orderItemIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//					orderItemIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//					table.addCell(orderItemIdCell);

					// Order Date
					PdfPCell orderDateCell = new PdfPCell(new Phrase(oDTO.getOrderDate()));
					orderDateCell.setPaddingLeft(2);
					orderDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					orderDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(orderDateCell);

					// Book details
					PdfPCell orderedBookCell = new PdfPCell(new Phrase(
							oiDTO.getBookDTO().getName() + " (ID: " + oiDTO.getBookDTO().getBookId().toString() + ")"));
					orderedBookCell.setPaddingLeft(2);
					orderedBookCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					orderedBookCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(orderedBookCell);

					// Order Item Amount
					PdfPCell orderItemAmountCell = new PdfPCell(new Phrase(Integer.toString(oiDTO.getOrderedAmount())));
					orderItemAmountCell.setPaddingLeft(2);
					orderItemAmountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					orderItemAmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(orderItemAmountCell);

					// Ordered Books Price
					PdfPCell orderBooksPriceCell = new PdfPCell(
							new Phrase(Double.toString(oiDTO.getTotalOrderedItemPrice())));
					orderBooksPriceCell.setPaddingLeft(2);
					orderBooksPriceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					orderBooksPriceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(orderBooksPriceCell);

					// Order Total Price
					PdfPCell orderPriceCell = new PdfPCell(new Phrase(Double.toString(oDTO.getOrderPrice())));
					orderPriceCell.setPaddingLeft(2);
					orderPriceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					orderPriceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(orderPriceCell);

				}
			}

			document.add(table);
			document.close();
		} catch (DocumentException de) {
			logger.error(de.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

}
