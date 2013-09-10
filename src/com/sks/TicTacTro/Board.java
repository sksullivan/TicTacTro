package com.sks.TicTacTro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements MouseMotionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	int minDim, majDim, majLength, minLength, offset, mouseX, mouseY, players, turn, cellSize;
	TicTacToeBoard[][] gameMatrix;
	ArrayList<Color> colors = new ArrayList<Color>();
	Point selectedTarget, selectedPos;
	Timer fps;

	public Board(int majDim, int minDim, int players) {
		cellSize = 40;
		this.players = players;
		fps = new Timer(30, this);
		fps.addActionListener(this);
		addMouseMotionListener(this);
		this.minDim = minDim;
		this.majDim = majDim;
		minLength = 30;
		offset = 200;
		selectedPos = new Point(offset,offset);
		selectedTarget = new Point(offset,offset);
		majLength = minDim * minLength;
		gameMatrix = new TicTacToeBoard[3][3];
		
		for (int i = 0; i < players; i++) {
			colors.add(new Color((int)(Math.random()*255), (int)(Math.random()*255),  (int)(Math.random()*255)));
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameMatrix[i][j] = new TicTacToeBoard(3,offset*i,offset*j,cellSize,players);
			}
		}
		gameMatrix[1][1].selected = true;
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameMatrix[i][j].draw(g, colors);
			}
		}
		
		g.setColor(colors.get(turn%players));
		g.fillRect(mouseX - minLength / 4, mouseY - minLength / 4, minLength / 2, minLength / 2);
		
		g.setColor(Color.red);
		g.drawRect(selectedPos.x-5, selectedPos.y-5, cellSize*3+10, cellSize*3+10);
		
		/*
		int smallestDim = 0;

		if (getWidth() > getHeight()) {
			smallestDim = getHeight();
		} else {
			smallestDim = getWidth();
		}

		majLength = (int) (smallestDim / majDim * .7);
		offset = (int) (majLength * .3);
		minLength = (int) (majLength / minDim);

		for (int i = 0; i < majDim; i++) {
			for (int j = 0; j < majDim; j++) {
				for (int k = 0; k < minDim; k++) {
					for (int l = 0; l < minDim; l++) {
						squareMatrix[i][j][k][l] = new Rectangle(offset
								* (i + 1) + minLength * k + majLength * i,
								offset * (j + 1) + minLength * l + majLength
										* j, minLength, minLength);
					}
				}
			}
		}

		for (int i = 0; i < majDim; i++) {
			for (int j = 0; j < majDim; j++) {
				for (int k = 0; k < minDim; k++) {
					for (int l = 0; l < minDim; l++) {
						squareMatrix[i][j][k][l] = new Rectangle(offset
								* (i + 1) + minLength * k + majLength * i,
								offset * (j + 1) + minLength * l + majLength
										* j, minLength, minLength);
					}
				}
			}
		}

		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i < majDim; i++) {
			int voff = majLength * i;
			for (int j = 0; j < majDim; j++) {
				int hoff = majLength * j;

				g.drawRect(offset * (j + 1) + hoff, offset * (i + 1) + voff,
						0 + majLength, 0 + majLength);
			}
		}

		for (int i = 0; i < majDim; i++) {
			for (int j = 0; j < majDim; j++) {
				for (int k = 0; k < minDim; k++) {
					g.drawLine(offset * (j + 1) + majLength * j, offset
							* (i + 1) + majLength * i + minLength * (k + 1),
							offset * (j + 1) + majLength * j + majLength,
							offset * (i + 1) + majLength * i + minLength
									* (k + 1));
				}
			}
		}
		for (int i = 0; i < majDim; i++) {
			for (int j = 0; j < majDim; j++) {
				for (int k = 0; k < minDim; k++) {
					g.drawLine(offset * (i + 1) + majLength * i + minLength
							* (k + 1), offset * (j + 1) + majLength * j, offset
							* (i + 1) + majLength * i + minLength * (k + 1),
							offset * (j + 1) + majLength * j + majLength);
				}
			}
		}

		for (int i = 0; i < majDim; i++) {
			for (int j = 0; j < majDim; j++) {
				for (int k = 0; k < minDim; k++) {
					for (int l = 0; l < minDim; l++) {
						g.setColor(gameMatrix[i][j][k][l]);
						g.fillRect((int) squareMatrix[i][j][k][l].getX() + 1,
								(int) squareMatrix[i][j][k][l].getY() + 1,
								(int) squareMatrix[i][j][k][l].getWidth() - 1,
								(int) squareMatrix[i][j][k][l].getHeight() - 1);
					}
				}
			}
		}
	*/
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}

	public void animateSelected(Point target) {
		this.selectedTarget = new Point((int)target.getX()*offset,(int)target.getY()*offset);
		System.out.println(this.selectedPos.x+" "+this.selectedTarget.getX());
		fps.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(selectedTarget.getX() > selectedPos.getX()) {
			selectedPos.x+=3;
		} else if(selectedTarget.getX() < selectedPos.getX()) {
			selectedPos.x-=3;
		}
		if(selectedTarget.getY() > selectedPos.getY()) {
			selectedPos.y+=3;
		} else if(selectedTarget.getY() < selectedPos.getY()) {
			selectedPos.y-=3;
		}
		if(selectedTarget.equals(selectedPos)) {
			fps.stop();
		}
		repaint();
	}
}