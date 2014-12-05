package com.mygdx.game.model;

public class World {
	public int mapWidth = 19;
	public int mapHeight = 13;
	public int unitSize = 32;
	public int[][] map = new int[][] {
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 1, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 1, 2, 1, 2 },
			{ 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 1, 2, 0, 2, 0, 2 },
			{ 2, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 1, 2, 0, 2, 0, 2 },
			{ 2, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
		};
	public Player player = new Player(unitSize, (mapHeight - 2) * unitSize);
	
	public void update (float delta) {
		checkCollision(player);
		player.update(delta);
	}
	
	private void checkCollision(MovingObject object) {
		if (object.state == MovingObject.State.STAND) {
			object.velocity.set(0, 0);
			return;
		}
		int x = Math.round(object.position.x / unitSize);
		int y = Math.round(object.position.y / unitSize);
		if (object.state == MovingObject.State.MOVING_RIGHT) {
		    if (map[x+1][y] != 0) {
		        if (object.position.x >= x * unitSize) {
		            object.position.x = x * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(object.speed, 0);
		        }
		    }
		    else {
		        if (object.velocity.y != 0) {
		            if ((object.velocity.y > 0 && object.position.y >= y * unitSize)
		                || (object.velocity.y < 0 && object.position.y <= y * unitSize)) {
		                object.position.y = y * unitSize;
		                object.velocity.set(object.speed, 0);
		            }
		        }
		        else {
		        	if (object.position.y > y * unitSize && object.position.x >= x * unitSize && map[x+1][y+1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, -object.speed);
		        	}
		        	else if (object.position.y < y * unitSize && object.position.x >= x * unitSize && map[x+1][y-1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, object.speed);
		        	}
		        	else {
		        		object.velocity.set(object.speed, 0);
		        	}
		        }
		    } 
		}
		
		if (object.state == MovingObject.State.MOVING_LEFT) {
		    if (map[x-1][y] != 0) {
		        if (object.position.x <= x * unitSize) {
		            object.position.x = x * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(-object.speed, 0);
		        }
		    }
		    else {
		        if (object.velocity.y != 0) {
		            if ((object.velocity.y > 0 && object.position.y >= y * unitSize)
		                || (object.velocity.y < 0 && object.position.y <= y * unitSize)) {
		                object.position.y = y * unitSize;
		                object.velocity.set(-object.speed, 0);
		            }
		        }
		        else {
		        	if (object.position.y > y * unitSize && object.position.x <= x * unitSize && map[x-1][y+1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, -object.speed);
		        	}
		        	else if (object.position.y < y * unitSize && object.position.x <= x * unitSize && map[x-1][y-1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, object.speed);
		        	}
		        	else {
		        		object.velocity.set(-object.speed, 0);
		        	}
		        }
		    } 
		}
		
		if (object.state == MovingObject.State.MOVING_UP) {
		    if (map[x][y+1] != 0) {
		        if (object.position.y >= y * unitSize) {
		            object.position.y = y * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(0, object.speed);
		        }
		    }
		    else {
		        if (object.velocity.x != 0) {
		            if ((object.velocity.x > 0 && object.position.x >= x * unitSize)
		                || (object.velocity.x < 0 && object.position.x <= x * unitSize)) {
		                object.position.x = x * unitSize;
		                object.velocity.set(0, object.speed);
		            }
		        }
		        else {
		        	if (object.position.x > x * unitSize && object.position.y >= y * unitSize && map[x+1][y+1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(-object.speed, 0);
		        	}
		        	else if (object.position.x < x * unitSize && object.position.y >= y * unitSize && map[x-1][y+1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(object.speed, 0);
		        	}
		        	else {
		        		object.velocity.set(0, object.speed);
		        	}
		        }
		    }
		}
		
		if (object.state == MovingObject.State.MOVING_DOWN) {
		    if (map[x][y-1] != 0) {
		        if (object.position.y <= y * unitSize) {
		            object.position.y = y * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(0, -object.speed);
		        }
		    }
		    else {
		        if (object.velocity.x != 0) {
		            if ((object.velocity.x > 0 && object.position.x >= x * unitSize)
		                || (object.velocity.x < 0 && object.position.x <= x * unitSize)) {
		                object.position.x = x * unitSize;
		                object.velocity.set(0, -object.speed);
		            }
		        }
		        else {
		        	if (object.position.x > x * unitSize && object.position.y >= y * unitSize && map[x+1][y-1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(-object.speed, 0);
		        	}
		        	else if (object.position.x < x * unitSize && object.position.y >= y * unitSize && map[x-1][y-1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(object.speed, 0);
		        	}
		        	else {
		        		object.velocity.set(0, -object.speed);
		        	}
		        }
		    }
		}
	}
}
