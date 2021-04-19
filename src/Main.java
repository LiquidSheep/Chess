
import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) throws Exception {
			launch();
	}

	Group root;
	ArrayList<Node> drawList = new ArrayList<Node>();

	@Override
	public void start(final Stage stage) {
		root = new Group();
	
		draw();
		root.getChildren().addAll(drawList);
	
		Scene scene = new Scene(root, 500, 500, Color.GRAY);
		stage.setScene(scene);
		stage.show();
	}
	
	public void draw() {
		drawBackground();
		createGraphicalChessBoard();
		drawPiece();
	}

	public void drawBackground() {
		Rectangle background = new Rectangle(500, 500, Color.web("#594e52"));
		drawList.add(background);
	}

	public void createGraphicalChessBoard() {
		for (int file=0; file<8; file++) {
			for (int rank=0; rank<8; rank++) {
				boolean isLightSquare = (file + rank) % 2 != 0;
				Color squareColor = (isLightSquare) ? Color.web("#e6bfab") : Color.web("#fdede4");
				int[] position = {file, rank};
				drawSquare(squareColor, position);
			} 
		}
	}

	public void drawSquare(Color c, int[] position) {
		Rectangle rect = new Rectangle(position[0]*50+50, position[1]*50+50, 50, 50);
		rect.setFill(c);
		drawList.add(rect);
	}

	public void drawPiece() {
		ImageView sprite = new ImageView(new File("C:\\Users\\tanta\\Desktop\\Program Folder\\javaFX\\Chess\\img\\1920px-Chess_Pieces_Sprite.svg.png").toURI().toString());
		double imgWidth = sprite.getFitWidth()/6;
		double imgHeight = sprite.getFitHeight()/2;
		ChessBoard cb = new ChessBoard();
		for (int i=0; i<64; i++) {
			int file = i/8;
			int rank = i%8;
			int piece = cb.squares[i];
			int pieceType = piece%8;
			int pieceColor = piece/16;
			if (piece!=0) {
				Image pImg = sprite.getImage();
				ImageView pImageView = new ImageView(pImg);
				pImageView.setViewport(new Rectangle2D((6-pieceType)*imgWidth, pieceColor*imgHeight, imgWidth, imgHeight));
				pImageView.setX(rank*50+50);
				pImageView.setY((7-file)*50+50);
				pImageView.setFitWidth(50);
				pImageView.setFitHeight(50);
				drawList.add(pImageView);
				//g.drawImage(sprite, , , , 50, 50);
			}
		}
	}
}