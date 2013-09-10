package com.sks.TicTacTro;

import java.awt.BorderLayout;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;

public class Game extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	Board b;
	int majDim;
	int minDim;
	int players;

	public static void main(String[] args) {
		// Amount of boards
		if (!(args[0].equals("")) && !(args[1].equals(""))) {
			Game g = new Game(Integer.parseInt(args[0]),
					Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			g.majDim = Integer.parseInt(args[0]);
			g.minDim = Integer.parseInt(args[1]);
		} else {
			System.out
					.println("Usage: Tic.jar (integer)MajorDimension (integer)MinorDimention (integer)Players");
		}

		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventPostProcessor(new KeyEventPostProcessor() {

					public boolean postProcessKeyEvent(KeyEvent e)
							throws ConcurrentModificationException {
						System.out.println(e.getKeyCode());
						try {
							switch (e.getKeyCode()) {
							case 37:
								System.out.println("Left");
								break;
							case 38:
								System.out.println("Up");
								break;
							case 39:
								System.out.println("Right");
								break;
							case 40:
								System.out.println("Down");
								break;
							default:
								break;
							}
						} catch (Exception ex) {
							System.out.println("KEY EXCEPTION: "
									+ ex.toString());
						}
						return true;
					}
				});
	}

	public Game(int majDimArg, int minDimArg, int players) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		System.out.println("Playing tic-tac-tro with " + players + " players.");
		b = new Board(majDimArg, minDimArg, players);
		add(b);
		b.addMouseListener(this);
		setSize(600, 600);
		setVisible(true);
		this.players = players;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point selectedTarget;
		//For every box in every row of boxes in every board in every row of boards check to see where the mouse clicked
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						//Test if the mouse was clicked in this box+board combo
						if (b.gameMatrix[i][j].drawMatrix[k][l].contains(e
								.getPoint()) && b.gameMatrix[i][j].selected && b.gameMatrix[i][j].boardMatrix[k][l] == 0) {
							b.gameMatrix[i][j].setActive(k, l, 1+b.turn % players);
							b.gameMatrix[i][j].selected = false;
							b.gameMatrix[k][l].selected = true;
							selectedTarget = new Point(k,l);
							b.repaint();
							b.gameMatrix[i][j].determineWinner(); //Determine if the just clicked board is won, and set that board as won if it is
							b.turn++;
							b.animateSelected(selectedTarget);
						}
					}
				}
			}
		}
	}
}
