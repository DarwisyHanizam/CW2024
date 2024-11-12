package com.example.demo;

public class UserPlane extends FighterPlane {
	//Initialize User Plane
	private static final String IMAGE_NAME = "userplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	//Level Boundry Box
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double X_LEFT_BOUND = 5.0;
	private static final double X_RIGHT_BOUND = 1100.0;
	//User + Projectile Velocity
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HORIZONTAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int velocityMultiplierX;
	private int velocityMultiplierY;
	private int numberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplierX = velocityMultiplierY = 0;
	}
	
	@Override
	public void updatePosition() {
		if (isMovingX() && isMovingY()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * velocityMultiplierX);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
				this.setTranslateY(initialTranslateX);
			}

			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplierY);
			double newPositionY = getLayoutY() + getTranslateY();
			if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		} else if (isMovingX()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * velocityMultiplierX);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
				this.setTranslateY(initialTranslateX);
			}
		} else if (isMovingY()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplierY);
			double newPositionY = getLayoutY() + getTranslateY();
			if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	private boolean isMovingX() {
		return velocityMultiplierX != 0;
	}
	private boolean isMovingY() {
		return velocityMultiplierY != 0;
	}

	public void moveUp() {
		velocityMultiplierY = -1;
	}

	public void moveLeft() {
		velocityMultiplierX = -1;
	}

	public void moveDown() {
		velocityMultiplierY = 1;
	}

	public void moveRight() {
		velocityMultiplierX = 1;
	}

	public void stop() {
		velocityMultiplierX = velocityMultiplierY = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}
