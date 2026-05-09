package com.soft.movie_ticket_booking_system.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRGenerater {
    public static byte[] generateQRCodeImage(String data, int width, int height) {

        try {

            QRCodeWriter writer = new QRCodeWriter();

            BitMatrix bitMatrix = writer.encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    200,
                    200);

            BufferedImage image = new BufferedImage(
                    200,
                    200,
                    BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < 200; x++) {

                for (int y = 0; y < 200; y++) {

                    image.setRGB(
                            x,
                            y,
                            bitMatrix.get(x, y)
                                    ? 0xFF000000
                                    : 0xFFFFFFFF);
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(image, "png", baos);

            return baos.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException("Failed to Generate QR Code");
        }

    }
}
