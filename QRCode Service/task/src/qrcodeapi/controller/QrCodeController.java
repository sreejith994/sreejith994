package qrcodeapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrCodeController {

    @GetMapping("/api/health")
    public Object getApiHealth() {
        return null;
    }

    @GetMapping("/api/qrcode")
    public ResponseEntity<Object> getQrCode() {
        // Return an empty response with 501 status code
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }


}
