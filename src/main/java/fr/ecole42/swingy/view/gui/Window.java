package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.model.Map;

import javax.swing.*;
import java.awt.*;

public class Window {
	private JFrame jFrame;
	private static final short blockSize = 25;
	private Map map;

	public Window(Map map) {
		this.jFrame = new JFrame() {};
		this.map = map;
	}

	public void show() {
		this.jFrame.setVisible(true);
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jFrame.setSize(map.getSize() * blockSize, map.getSize() * blockSize);
	}
}
