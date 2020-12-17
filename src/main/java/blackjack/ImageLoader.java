package blackjack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by rohitkumar.c on 17/12/20
 **/

public class ImageLoader {
    public static BufferedImage load(String path) {
        ClassLoader loader = ImageLoader.class.getClassLoader();
        URL image = loader.getResource(path);
        try {
            if (image != null) {
                return ImageIO.read(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
