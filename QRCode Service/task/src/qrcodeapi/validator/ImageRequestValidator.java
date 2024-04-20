package qrcodeapi.validator;

import org.springframework.stereotype.Component;
import qrcodeapi.Exceptions.InvalidInputParamException;

import java.util.Arrays;
import java.util.List;

@Component
public class ImageRequestValidator {

    private final List<String> allowedImageTypes = Arrays.asList("PNG", "JPEG", "GIF");
    private final List<Character> allowedCorrectionTypes = Arrays.asList('L', 'M', 'Q', 'H');

    public void validateRequest(Integer size, String type, String content, Character correction) {
        if (null == content || content.replaceAll("\\s+", "").isEmpty()) {
            throw new InvalidInputParamException("Contents cannot be null or blank");
        }
//        else if(null == size ) {
//            throw new InvalidContentTypeException("size cannot be null or blank");
//        }else if(null == type ) {
//            throw new InvalidContentTypeException("type cannot be null or blank");
//        }
        else if (size < 150 || size > 350) {
            throw new InvalidInputParamException("Image size must be between 150 and 350 pixels");
        } else if (!allowedCorrectionTypes.contains(correction)) {
            throw new InvalidInputParamException("Permitted error correction levels are L, M, Q, H");
        } else if (!allowedImageTypes.contains(type.toUpperCase())) {
            throw new InvalidInputParamException("Only png, jpeg and gif image types are supported");
        }
    }
}
