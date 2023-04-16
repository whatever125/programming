package sources.Exceptions;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;


public class BeetleFlyAwayException extends Exception {
    public BeetleFlyAwayException() throws URISyntaxException {
        super("Жук пролетел мимо, Незнайка не сможет прославиться(((");
        URI uri = new URI("https://imgur.com/L6TNO4Z");
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
