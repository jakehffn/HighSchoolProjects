package engine;

import game.RenderWindow;
import java.awt.*;

public interface IGameLogic {

    void init() throws Exception;

    void input(RenderWindow renderer);

    void update(float interval);

    void render(Graphics g);
}
