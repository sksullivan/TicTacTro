package com.sks.TicTacTro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class TicTacToeBoard {

	int players;
	int dim;
	int[][] boardMatrix;
	int cellSize;
	Rectangle[][] drawMatrix;
	int winner = -1;
	boolean selected;
	boolean winMatrix[][] = {
			{ true, false, false, false, true, false, false, false, true },
			{ true, false, false, true, false, false, true, false, false },
			{ true, true, true, false, false, false, false, false, false },
			{ false, true, false, false, true, false, false, true, false },
			{ false, false, true, false, false, true, false, false, true },
			{ false, false, true, false, true, false, true, false, false },
			{ false, false, false, true, true, true, false, false, false },
			{ false, false, false, false, false, false, true, true, true } };

	public TicTacToeBoard(int dim, int x, int y, int cellSize,
			int players) {
		this.cellSize = cellSize;
		this.players = players;
		this.dim = dim;
		drawMatrix = new Rectangle[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				drawMatrix[i][j] = new Rectangle(x + cellSize * i, y
						+ cellSize * j, cellSize, cellSize);
			}
		}
		boardMatrix = new int[dim][dim];
	}

	public void setActive(int x, int y, int player) {
		boardMatrix[x][y] = player;
	}

	public void reset() {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				boardMatrix[i][j] = 0;
			}
		}
	}

	public int determineWinner() {
		System.out.println("Determining Winner for dat");
		boolean[] activeCells;
		boolean won = true;
		for (int p = 0; p < players; p++) {
			activeCells = new boolean[9];
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (boardMatrix[i][j] == p+1) {
						activeCells[j * 3 + i] = true; // Why i and j reversed?
														// (philosophy)
					}
				}
			}
			//Done creating actveCells list, now check it against winMatrix
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if (activeCells[j] != winMatrix[i][j]) {
						won = false;
						break;
					}
				}
				if (won) {
					winner = p;
					return p;
				}
				won = true;
			}
		}
		return -1;
	}

	public void draw(Graphics g, ArrayList<Color> colors) {
		if (winner != -1) {
			g.setColor(colors.get(winner));
			g.drawRect(drawMatrix[0][0].x - 5, drawMatrix[0][0].y - 5,
					drawMatrix[0][0].width * 3 + 10,
					drawMatrix[0][0].height * 3 + 10);
			g.drawRect(drawMatrix[0][0].x - 4, drawMatrix[0][0].y - 4,
					drawMatrix[0][0].width * 3 + 8,
					drawMatrix[0][0].height * 3 + 8);
		}
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				Rectangle r = drawMatrix[i][j];
				g.setColor(Color.black);
				g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(),
						(int) r.getHeight());
				if (boardMatrix[i][j] == 0) {
					g.setColor(Color.white);
				} else {
					g.setColor(colors.get(boardMatrix[i][j] - 1));
				}
				g.fillRect((int) r.getX() + 1, (int) r.getY() + 1,
						(int) r.getWidth() - 1, (int) r.getHeight() - 1);
			}
		}
	}
}
