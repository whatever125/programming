package client.GUIClient.GUI.mainWindow.visualizationTab;

import client.GUIClient.GUI.mainWindow.movieDialog.MovieInfoDialog;
import client.GUIClient.GUIClient;
import common.models.Movie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class VisualizationPanel extends JPanel implements ActionListener, MouseListener {
    private final int CELL_SIZE = 20;
    private final int MOVIE_SIZE = 20;
    private final int SECONDS = 3 * 1000;
    private final Window window;
    private final GUIClient client;
    private HashMap<Integer, Movie> movieHashMap = new HashMap<>();
    private HashMap<String, Color> colorHashMap = new HashMap<>();
    private final Timer timer = new Timer(SECONDS, this);
    private final Timer animationTimer = new Timer(10, this);
    private float animationAlpha = 0.01f;

    public VisualizationPanel(Window window, GUIClient client) {
        this.window = window;
        this.client = client;
        ToolTipManager.sharedInstance().setEnabled(true);
        setToolTipText("");
        addMouseListener(this);
        timer.start();
        animationTimer.start();
    }

    public void setData(HashMap<Integer, Movie> newMovieHashMap) {
        HashMap<String, Color> newColorHashMap = new HashMap<>();
        for (Movie movie: newMovieHashMap.values()) {
            String key = movie.getOwner();
            if (colorHashMap.containsKey(key)) {
                newColorHashMap.put(key, colorHashMap.get(key));
            } else {
                Color newColor;
                do {
                    int red = (int) (Math.random() * 256);
                    int green = (int) (Math.random() * 256);
                    int blue = (int) (Math.random() * 256);
                    newColor = new Color(red, green, blue);
                } while (calculateContrast(newColor) < 2);
                newColorHashMap.put(key, newColor);
            }
        }
        this.movieHashMap = newMovieHashMap;
        this.colorHashMap = newColorHashMap;
    }

    private double calculateContrast(Color color1) {
        double luminance1 = calculateLuminance(color1);
        double luminance2 = calculateLuminance(Color.WHITE);
        double contrast = (Math.max(luminance1, luminance2) + 0.05) / (Math.min(luminance1, luminance2) + 0.05);
        return contrast;
    }

    private double calculateLuminance(Color color) {
        double red = color.getRed() / 255.0;
        double green = color.getGreen() / 255.0;
        double blue = color.getBlue() / 255.0;
        double luminance = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
        return luminance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        printMesh(g);
        printMovies(g);
    }

    private void printMesh(Graphics g) {
        int width = getWidth(), height = getHeight();

        for (int x = CELL_SIZE; x < width; x += CELL_SIZE) {
            g.drawLine(x, 0, x, height - CELL_SIZE);
        }
        for (int y = CELL_SIZE; y < height; y += CELL_SIZE) {
            g.drawLine(CELL_SIZE, height - y, width, height - y);
        }
    }

    private void printMovies(Graphics g) {
        for (Integer key: movieHashMap.keySet()) {
            Movie movie = movieHashMap.get(key);
            Image image = getImage(movie.getOwner());

            Graphics2D g2d = (Graphics2D) g;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animationAlpha));
            g2d.drawImage(image, getX(movie), getY(movie), null);

//            g.drawImage(image, getX(movie), getY(movie), null);
        }
    }

    private int getX(Movie movie) {
        return movie.getCoordinates().getX() * CELL_SIZE + MOVIE_SIZE / 2;
    }

    private int getY(Movie movie) {
        return getHeight() - movie.getCoordinates().getY() * CELL_SIZE - CELL_SIZE - MOVIE_SIZE / 2;
    }

    private BufferedImage getImage(String key) {
        try {
            BufferedImage imageSource = ImageIO.read(new File(getClass().getResource("/icons/movie.png").toURI()));

            Color newColor = colorHashMap.get(key);

            int width = imageSource.getWidth();
            int height = imageSource.getHeight();

            BufferedImage coloredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = coloredImage.createGraphics();
            g2d.drawImage(imageSource, 0, 0, null);
            g2d.dispose();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = coloredImage.getRGB(x, y);
                    int alpha = (rgb >> 24) & 0xFF;
                    if (alpha != 0) {
                        coloredImage.setRGB(x, y, newColor.getRGB());
                    }
                }
            }

            BufferedImage resizedImage = new BufferedImage(MOVIE_SIZE, MOVIE_SIZE, BufferedImage.TYPE_INT_ARGB);
            g2d = resizedImage.createGraphics();
            g2d.drawImage(coloredImage, 0, 0, MOVIE_SIZE, MOVIE_SIZE, null);
            g2d.dispose();

            return resizedImage;
        } catch (IOException e) {
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        for (Movie movie: movieHashMap.values()) {
            if (isMovieChosen(movie, event.getPoint())) {
                String tooltip = new MessageFormat(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("tab.graph.toolTip")).format(new Object[]{movie.getName(), movie.getOwner()});
                return tooltip;
            }
        }
        return super.getToolTipText();
    }

    private boolean isMovieChosen(Movie movie, Point point) {
        double centerX = getX(movie) + (double) MOVIE_SIZE / 2;
        double centerY = getY(movie) + (double) MOVIE_SIZE / 2;
        double distance = Math.sqrt(Math.pow(centerX - point.x, 2) + Math.pow(centerY - point.y, 2));
        return distance <= (double) MOVIE_SIZE / 2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        } else if (e.getSource() == animationTimer) {
            animationAlpha += 0.01f;
            if (animationAlpha >= 1) {
                animationAlpha = 1;
                animationTimer.stop();
            }
            repaint();
        }
    }

    public void startAnimation() {
        animationAlpha = 0f;
        animationTimer.start();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!window.isBackgroundSet()) {
            return;
        }

        for (Integer key: movieHashMap.keySet()) {
            Movie movie = movieHashMap.get(key);
            if (isMovieChosen(movie, e.getPoint())) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    String owner = movie.getOwner();
                    // todo uncomment
                    if (Objects.equals(owner, client.getLogin())) {
                        // show popup on movie right-clicked
                        JPopupMenu popupMenu = new GraphPopUp(window, client, key, movie);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    MovieInfoDialog dialog = new MovieInfoDialog(window, client, key, movie);
                    dialog.setVisible(true);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
