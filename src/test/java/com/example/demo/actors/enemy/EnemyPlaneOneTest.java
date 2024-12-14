package com.example.demo.actors.enemy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyPlaneOneTest {
	private static EnemyPlaneOne enemyPlane;

	@BeforeAll
	public static void setup(){
		enemyPlane = new EnemyPlaneOne(0, 0);
	}

	@Test
	public void testGetHealth() {
		assertEquals(1, enemyPlane.getHealth());
	}
}