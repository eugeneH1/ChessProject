package chess;

import chess.core.WindowManager;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Configuration;

public class Launcher {

    private static WindowManager window;
    public static void main(String[] args) {
        //System.out.println(Version.getVersion());
        window = new WindowManager("Chess", 1600, 900, false);
        //window.init();
        window.setClearColour(0.0f, 0.9f, 0.0f, 0.0f);

        while(!window.windowShouldClose()) {
            window.update();
        }

        window.cleanup();

    }
    public static WindowManager getWindow() {
        return window;
    }
}
