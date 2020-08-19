// © 2018 Luke Jagg
// MIT License

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.VPos;
import javafx.scene.transform.Rotate;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

class Cube{
	
	double x;
	double y;
	double z;
	
	double length;
	double depth;
	double height;
	
	Cube(double x, double y, double z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		length = 1;
		depth = 1;
		height = 1;
		
	}
	
	void drawCube() {
		
		double l = length / 2;
		double d = depth / 2;
		double h = height / 2;
		
		CubeDodgeGame.drawLine(x-l,y-h,z-d,x+l,y-h,z-d);
		CubeDodgeGame.drawLine(x-l,y-h,z+d,x+l,y-h,z+d);
		CubeDodgeGame.drawLine(x-l,y+h,z-d,x+l,y+h,z-d);
		CubeDodgeGame.drawLine(x-l,y+h,z+d,x+l,y+h,z+d);
		
		CubeDodgeGame.drawLine(x-l,y-h,z-d,x-l,y-h,z+d);
		CubeDodgeGame.drawLine(x-l,y+h,z-d,x-l,y+h,z+d);
		CubeDodgeGame.drawLine(x+l,y-h,z-d,x+l,y-h,z+d);
		CubeDodgeGame.drawLine(x+l,y+h,z-d,x+l,y+h,z+d);
		
		CubeDodgeGame.drawLine(x-l,y-h,z-d,x-l,y+h,z-d);
		CubeDodgeGame.drawLine(x-l,y-h,z+d,x-l,y+h,z+d);
		CubeDodgeGame.drawLine(x+l,y-h,z-d,x+l,y+h,z-d);
		CubeDodgeGame.drawLine(x+l,y-h,z+d,x+l,y+h,z+d);
		
	}
	
	void drawFaces() {
		
		double l = length / 2;
		double d = depth / 2;
		double h = height / 2;
		
		CubeDodgeGame.drawFace(x-l, y-h, z-d, x+l, y-h, z-d, x+l, y+h, z-d, x-l, y+h, z-d, 0.8);
		CubeDodgeGame.drawFace(x-l, y-h, z+d, x+l, y-h, z+d, x+l, y+h, z+d, x-l, y+h, z+d, 0.6);
		CubeDodgeGame.drawFace(x-l, y+h, z-d, x+l, y+h, z-d, x+l, y+h, z+d, x-l, y+h, z+d, 0.3);
		CubeDodgeGame.drawFace(x-l, y-h, z-d, x+l, y-h, z-d, x+l, y-h, z+d, x-l, y-h, z+d, 1);
		CubeDodgeGame.drawFace(x-l, y-h, z-d, x-l, y+h, z-d, x-l, y+h, z+d, x-l, y-h, z+d, 0.7);
		CubeDodgeGame.drawFace(x+l, y-h, z-d, x+l, y+h, z-d, x+l, y+h, z+d, x+l, y-h, z+d, 0.5);
		
	}

	boolean checkCollision(double cx, double cy, double cz, double check) {
		
		if (Math.abs(x-cx) < check) {
			if (Math.abs(y-cy) < check) {
				if (Math.abs(z-cz) < check) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
}

// Includes Drawing
public class CubeDodgeGame extends Application {
	
	// VIEW SETTINGS
	static final int WIDTH = 800;
	static final int HEIGHT = 500;
	
	static final double FOV = 90;
	static final double VIEW_FACTOR = 360 / Math.tan(Math.toRadians(FOV / 2));
	
	static final int LINE_WIDTH = 1;
	
	static final double FADE_DISTANCE = 12;
	
	// GAME SETTINGS
	final static double SCREEN_SCALE = 14;
	final static int VIEW_DISTANCE = 19;//19;
	final static int CUBES_PER_UNIT = 1;
	
	final static double SCORE_SCALE = 0.25;
	static double SPEED = 25;
	
	final static boolean LOOK_INVERSION = false;
	
	static boolean cheating = false;
	
	// UI / SOUND
	static GraphicsContext gc;
	
	//final static Media music = new Media(new File("res/LunarAbyss.mp3").toURI().toString());
	//final static Media OOF = new Media(new File("res/Oof.mp3").toURI().toString());
	//static MediaPlayer musicPlayer = new MediaPlayer(music);
	
	// GAME
	static Random rnd = new Random();
	
	static double cameraX = 0;
	static double cameraY = 0;
	
	static double camX = 0;
	static double camY = 0;
	
	static ArrayList<Cube> cubes;
	
	static double score = 0; // Z
	static double highscore = 500;
	
	static boolean dead = false;
	
	static double xToScreen(double x) {
		return x + WIDTH / 2.0;
	}
	
	static double yToScreen(double y) {
		return y + HEIGHT / 2.0;
	}
	
	static double screenToX(double x) {
		return x - WIDTH / 2.0;
	}
	
	static double screenToY(double y) {
		return y - HEIGHT / 2.0;
	}
	
	static double sin(double t) {
		return Math.sin(t);
	}
	
	static double cos(double t) {
		return Math.cos(t);
	}
	
	static double tan(double t) {
		return Math.tan(t);
	}
	
	static double sqrt(double n) {
		return Math.sqrt(n);
	}
	
	static double sign(double n) {
		if(n>=0)
			return 1;
		return -1;
	}
	
	static void addCubes(int maxSize, int maxCubes) {
		
		int added = 0;
		HashSet<Integer> positions = new HashSet<Integer>();
		
		for (int i = 0; i < cubes.size(); i++) {
			
			if (cubes.size() >= maxCubes)
				return;
			
			if (!positions.contains((int)Math.round(cubes.get(i).z))) {
				
				cubes.add(new Cube(rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,cubes.get(i).z));
				positions.add((int)Math.round(cubes.get(i).z));
				added++;
				
				if (added >= maxSize)
					return;
				
			}
			
		}
	}
	
	static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2) {
		
		x1 -= cameraX;
		x2 -= cameraX;
		y1 -= cameraY;
		y2 -= cameraY;
		z1 -= score * SCORE_SCALE;
		z2 -= score * SCORE_SCALE;
		
//		double h1 = Math.hypot(x1, z1);
//		double h2 = Math.hypot(x2, z2);
//		
//		double θ1 = Math.asin(x1/h1);
//		double θ2 = Math.asin(x2/h2);
//		
//		x1 = h1 * sin(-θ1 + camY);
//		x2 = h2 * sin(-θ2 + camY);
//		
//		z1 = h1 * cos(-θ1 + camY);
//		z2 = h2 * cos(-θ2 + camY);
//		
//		double h3 = Math.hypot(y1, z1);
//		double h4 = Math.hypot(y2, z2);
//		
//		double θ3 = Math.acos(y1/h3);
//		double θ4 = Math.acos(y2/h4);
//		
//		y1 = h3 * cos(θ3 + camX);
//		y2 = h4 * cos(θ4 + camX);
//		
//		z1 = h3 * sin(θ3 + camX);
//		z2 = h4 * sin(θ4 + camX);
	
		if (z1 > 0  && z2 > 0) {
			
			double _x0 = x1*VIEW_FACTOR/z1;
			double _y0 = y1*VIEW_FACTOR/z1;
			double _x1 = x2*VIEW_FACTOR/z2;
			double _y1 = y2*VIEW_FACTOR/z2;
			
			if (z1>FADE_DISTANCE) {
				double fade = 1-(z1-FADE_DISTANCE)/(VIEW_DISTANCE-FADE_DISTANCE);
				gc.setStroke(Color.hsb(0, 0, 1, Math.min(Math.max(fade*fade,0),1)));
			}
			else 
				gc.setStroke(Color.WHITE);
			
//			gc.fillRect(xToScreen(_x0), yToScreen(_y0), LINE_WIDTH, LINE_WIDTH);
//			gc.fillRect(xToScreen(_x1), yToScreen(_y1), LINE_WIDTH, LINE_WIDTH);
//			
//			gc.save();
//			double angle = Math.toDegrees(Math.asin((_y1-_y0)/Math.hypot(_x1-_x0, _y1-_y0)));
//			
//			if (_x0 > _x1)
//				angle = 180-angle;
//			
//			Rotate r = new Rotate(angle, xToScreen(_x0), yToScreen(_y0));
//	        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
//			
//			gc.fillRect(xToScreen(_x0), yToScreen(_y0), Math.hypot(_x1-_x0, _y1-_y0), LINE_WIDTH);
//			gc.restore();
			
			gc.strokeLine(xToScreen(_x0), yToScreen(_y0), xToScreen(_x1), yToScreen(_y1));
			
		}
		
	}
	
	static void drawFace(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4, double shade) {
		
		x1 -= cameraX;
		x2 -= cameraX;
		y1 -= cameraY;
		y2 -= cameraY;
		z1 -= score * SCORE_SCALE;
		z2 -= score * SCORE_SCALE;
		x3 -= cameraX;
		x4 -= cameraX;
		y3 -= cameraY;
		y4 -= cameraY;
		z3 -= score * SCORE_SCALE;
		z4 -= score * SCORE_SCALE;
	
		if (z1 > 0  && z2 > 0 && z3 > 0 && z4 > 0) {
			
			double[] xPoints = new double[] {
					xToScreen(x1*VIEW_FACTOR/z1),
					xToScreen(x2*VIEW_FACTOR/z2),
					xToScreen(x3*VIEW_FACTOR/z3),
					xToScreen(x4*VIEW_FACTOR/z4)
			};
			
			double[] yPoints = new double[] {
					yToScreen(y1*VIEW_FACTOR/z1),
					yToScreen(y2*VIEW_FACTOR/z2),
					yToScreen(y3*VIEW_FACTOR/z3),
					yToScreen(y4*VIEW_FACTOR/z4)
			};
			
			gc.setFill(Color.hsb(0, 0, shade));
			
			gc.fillPolygon(xPoints, yPoints, 4);
			
		}
		
	}











	
	@Override
    public void start(Stage primaryStage) {
		
	    Group root = new Group();
	    	Scene s = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

	    	final Canvas canvas = new Canvas(WIDTH, HEIGHT);
	    	gc = canvas.getGraphicsContext2D();
	    	 
	    	root.getChildren().add(canvas);
	    
	    	primaryStage.initStyle(StageStyle.UNDECORATED);
	    	primaryStage.setTitle("RENDER CUBE");
	    	primaryStage.setScene(s);
	    	primaryStage.setResizable(false);
	    	primaryStage.show();
	    	
	    	canvas.setFocusTraversable(true);
    	
	    	primaryStage.setOnCloseRequest(e -> {
		    	
		    	System.exit(0);
		    	
	    	});
	    	
	    	canvas.setOnMouseMoved(e -> {
	    		cameraX = (LOOK_INVERSION ? -1 : 1) * SCREEN_SCALE * screenToX(e.getX()) / WIDTH; 
	    		cameraY = (LOOK_INVERSION ? -1 : 1) * SCREEN_SCALE * screenToY(e.getY()) / HEIGHT;
	    		
//	    		camY = -cameraX/50;
//	    		camX = -cameraY/50;
	    		
	    		gc.clearRect(0, 0, WIDTH, HEIGHT);
	    		
	    	});
	    	
	    	canvas.setOnKeyPressed(e -> {
	    		switch (e.getCode()) {
	    			case SPACE:
	    				if (dead) {
	    					SPEED = 25;
	    					start();
	    				}
	    					
	    				break;
	    			case E:
	    				cheating = !cheating;
	    				break;
	    			default:
	    				break;
	    		}
	    	});
	    	
	    	AnimationTimer timer = new AnimationTimer() {
	    		
	    		long lt = 0;
	    		
	    	    @Override
	    	    public void handle(long dt) {
	    	    	
	    	    		if (lt > 0) {
	    	    			
		    	    		double deltaTime = (dt - lt) / 1000000000.0;
		    	    	
		    	    		if (!dead)
		    	    			score += SPEED * deltaTime;
		    	    	
		    	    		renderCubes();
		    	    		
		    	    		HashMap<Integer, Integer> positions = new HashMap<Integer, Integer>();
		    	    		
		    	    		for (int i = 0; i < cubes.size(); i++) {
		    	    			
		    	    			if (!cheating && !dead && cubes.get(i).checkCollision(cameraX, cameraY, score * SCORE_SCALE, 0.55)) {
	    	    					dead = true;
	    	    					//musicPlayer.stop();
	    	    					//musicPlayer = new MediaPlayer(OOF);
	    	    					//musicPlayer.play();
	    	    					if (score > highscore) {
	    	    						highscore = score;
	    	    					}
	    	    				}
		    	    			
		    	    			if (cubes.get(i).z - score * SCORE_SCALE < -0.5) {
		    	    				
		    	    				cubes.get(i).x = rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2;
		    	    				cubes.get(i).y = rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2;
		    	    				//cubes.get(i).x = rnd.nextInt((int)SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2;
		    	    				//cubes.get(i).y = rnd.nextInt((int)SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2;
//		    	    				cubes.get(i).x = 0;
//		    	    				cubes.get(i).y = i%CUBES_PER_UNIT-10;
		    	    				
		    	    				cubes.get(i).z += VIEW_DISTANCE;

		    	    			}
		    	    			
		    	    			int pos = (int)Math.round(cubes.get(i).z - score * SCORE_SCALE);
	    	    				
	    	    				if (!positions.containsKey(pos))
	    	    					positions.put(pos, 0);
	    	    				
	    	    				positions.put(pos, positions.get(pos)+1);
		    	    		}
		    	    		
		    	    		if (positions.containsKey(VIEW_DISTANCE-1)) {
		    	    			if (score > 175) {
		    	    				if (score > 350) {
		    	    					if (score > 650) {
		    	    						if (score > 1050) {
		    	    							if (score > 1350) {
		    	    								if (score > 2100) {
		    	    									SPEED = 25 + (score-2100) / 60;
							    	    			}
		    	    								else if (cubes.size()<CUBES_PER_UNIT*VIEW_DISTANCE*14 && positions.get(VIEW_DISTANCE-1)<=CUBES_PER_UNIT*13) {
						    	    					cubes.add(new Cube(rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,VIEW_DISTANCE-1+score*SCORE_SCALE));
						    	    				}
						    	    			}
		    	    							else if (cubes.size()<CUBES_PER_UNIT*VIEW_DISTANCE*9 && positions.get(VIEW_DISTANCE-1)<=CUBES_PER_UNIT*8) {
					    	    					cubes.add(new Cube(rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,VIEW_DISTANCE-1+score*SCORE_SCALE));
					    	    				}
					    	    			}
		    	    						else if (cubes.size()<CUBES_PER_UNIT*VIEW_DISTANCE*5 && positions.get(VIEW_DISTANCE-1)<=CUBES_PER_UNIT*4) {
				    	    					cubes.add(new Cube(rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,VIEW_DISTANCE-1+score*SCORE_SCALE));
				    	    				}
				    	    			}
		    	    					else if (cubes.size()<CUBES_PER_UNIT*VIEW_DISTANCE*3 && positions.get(VIEW_DISTANCE-1)<=CUBES_PER_UNIT*2) {
			    	    					cubes.add(new Cube(rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,VIEW_DISTANCE-1+score*SCORE_SCALE));
			    	    				}
			    	    			}
		    	    				else if (cubes.size()<CUBES_PER_UNIT*VIEW_DISTANCE*2 && positions.get(VIEW_DISTANCE-1)<=CUBES_PER_UNIT) {
		    	    					cubes.add(new Cube(rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,VIEW_DISTANCE-1+score*SCORE_SCALE));
		    	    				}
		    	    			}
		    	    		}
		    	    		
		    	    		gc.setTextAlign(TextAlignment.LEFT);
		    	    		gc.setTextBaseline(VPos.TOP);
		    	    		gc.setFont(Font.font("Helvetica", FontWeight.LIGHT, 25));
		    	    		gc.setFill(Color.WHITE);
		    	    		gc.fillText(Math.round(score) + "", 4, 4);
		    	    		
		    	    		gc.setFill(Color.WHITE);
		    	    		gc.fillText(Math.round(highscore) + "", 4, 34);
	    	    		
	    	    		}
	    	    		
	    	    		lt = dt;
	    	    		
	    	    }
	    	    
	    	};
	    	
	    	timer.start();
	    	
	    	start();
	    	
	}
	
	static void createTheme() {
		/*
		musicPlayer.stop();
	    	musicPlayer = new MediaPlayer(music);
	    	musicPlayer.setVolume(1);
	    	
	    System.out.println(musicPlayer.getError());
	    	
	 	musicPlayer.setOnReady(new Runnable() {
	 		public void run() {
	 			musicPlayer.play();
	 			System.out.println("HEY");
	 		}
	 	});
	 	
	 	musicPlayer.setOnEndOfMedia(new Runnable() {
	   	    public void run() {
	   	    		createTheme();
	   	    }
	   	});
	 	*/
	}
	
	public static void start() {
		
		createTheme();
		
		score = 0;
		dead = false;

		cubes = new ArrayList<Cube>();
		
		for (int i = 0; i < VIEW_DISTANCE * CUBES_PER_UNIT; i++) {
		
			cubes.add(new Cube(rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,rnd.nextDouble()*(SCREEN_SCALE+1)-(SCREEN_SCALE+1)/2,i/CUBES_PER_UNIT+11));

		}
		
	}
	
	public static void renderCubes() {
		
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		cubes.forEach(c -> c.drawCube());
		
//		drawLine(-2,-2,-5,3,2,-5);
		
	}
	
	static int round(double n) {
		return (int)(n-(n%0.5)+(n-n%0.5)%1);
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}
