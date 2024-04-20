package qrcodeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qrcodeapi.service.BufferedImageGenerator;
import qrcodeapi.validator.ImageRequestValidator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
public class QrCodeController {

    private final Map<String, MediaType> mediaTypeMap;

    private final HttpMessageConverter<BufferedImage> httpMessageConverter;
    private final BufferedImageGenerator bufferedImageGenerator;
    private final ImageRequestValidator imageRequestValidator;

    @Autowired
    public QrCodeController(HttpMessageConverter<BufferedImage> httpMessageConverter,
                            BufferedImageGenerator bufferedImageGenerator,
                            ImageRequestValidator imageRequestValidator) {
        this.mediaTypeMap = Map.of("PNG", MediaType.IMAGE_PNG,
                "JPEG", MediaType.IMAGE_JPEG,
                "GIF", MediaType.IMAGE_GIF);
        this.httpMessageConverter = httpMessageConverter;
        this.bufferedImageGenerator = bufferedImageGenerator;
        this.imageRequestValidator = imageRequestValidator;
    }

    @GetMapping("/api/health")
    public Object getApiHealth() {
        return null;
    }

    @GetMapping(path = "/api/qrcode")
    public ResponseEntity<?> getImage(@RequestParam(required = false, defaultValue = "250") Integer size,
                                      @RequestParam(required = false, defaultValue = "PNG") String type,
                                      @RequestParam() String contents,
                                      @RequestParam(required = false, defaultValue = "L") Character correction) throws IOException {
        imageRequestValidator.validateRequest(size, type, contents, correction);
        BufferedImage bufferedImage = bufferedImageGenerator.getImage(size, size, contents, correction);
        try (var baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, type, baos); // writing the image in the PNG format
            byte[] bytes = baos.toByteArray();
            return ResponseEntity
                    .ok()
                    .contentType(mediaTypeMap.get(type.toUpperCase()))
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }


}
