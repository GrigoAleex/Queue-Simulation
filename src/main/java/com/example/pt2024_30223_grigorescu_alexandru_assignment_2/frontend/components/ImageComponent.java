package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageComponent extends ImageView {
    public ImageComponent(String imageUrl, Integer width, Integer height) throws FileNotFoundException {
        InputStream stream = getClass().getResourceAsStream(imageUrl);
        if (stream == null) throw new FileNotFoundException("The image was not found: " + imageUrl);

        this.setImage(new Image(stream));
        this.setFitWidth(width);
        this.setFitHeight(height);
    }
}
