package qrcodeapi.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;
import qrcodeapi.Exceptions.ServiceFailureException;

import java.awt.image.BufferedImage;
import java.util.Map;

@Component
public class BufferedImageGenerator {

    private final Map<Character, ErrorCorrectionLevel> correctionMap = Map.of('L', ErrorCorrectionLevel.L,
            'M', ErrorCorrectionLevel.M,
            'Q', ErrorCorrectionLevel.Q,
            'H', ErrorCorrectionLevel.H);

    public BufferedImage getImage(int width, int height, String contents, Character correction) {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, correctionMap.get(Character.toUpperCase(correction)));
        try {
            BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            throw new ServiceFailureException("Failure writing QR Code");
        }
    }
}
