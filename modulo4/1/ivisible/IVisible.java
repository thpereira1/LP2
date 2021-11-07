package ivisible;

import java.awt.Graphics;

public interface IVisible {
	boolean clicked (int x, int y);
    void paint (Graphics g, boolean focado);

}
