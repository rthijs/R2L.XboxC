package view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Markers {

    private static final Texture MARKERS_TEXTURE = new Texture("markers32x32.png");
    private static final TextureRegion[] MARKERS_TEXTURE_REGIONS = new TextureRegion[4];

    static {
        MARKERS_TEXTURE_REGIONS[0] = new TextureRegion(MARKERS_TEXTURE,0,0,16,16);
        MARKERS_TEXTURE_REGIONS[1] = new TextureRegion(MARKERS_TEXTURE,16,0,16,16);
        MARKERS_TEXTURE_REGIONS[2] = new TextureRegion(MARKERS_TEXTURE,0,16,16,16);
        MARKERS_TEXTURE_REGIONS[3] = new TextureRegion(MARKERS_TEXTURE,16,16,16,16);
    }

    public static TextureRegion controllerMarker(int index) {
        return MARKERS_TEXTURE_REGIONS[index];
    }
}
