package chess;

import chess.core.EngineManager;
import chess.core.WindowManager;
import chess.core.utils.Consts;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Configuration;

public class Launcher {

    private static WindowManager window;
    private static EngineManager engine;

    public static void main(String[] args) {
        window = new WindowManager(Consts.TITLE, 1600, 900, false);
        engine = new EngineManager();

        try {
            engine.start();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static WindowManager getWindow() {
        return window;
    }
}
