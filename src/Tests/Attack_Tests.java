package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Attack;
import model.Difficulty;
import model.attacks.BodyAttack;
import model.attacks.HeadAttack;
import model.attacks.IdleAttack;

public class Attack_Tests {
	
	ArrayList<Attack> attackSequence;
	
	@Before
	public void setUp() {
		attackSequence = new ArrayList<Attack>();
		attackSequence.add(new BodyAttack(Difficulty.Easy));
		attackSequence.add(new BodyAttack(Difficulty.Medium));
		attackSequence.add(new BodyAttack(Difficulty.Hard));
		attackSequence.add(new HeadAttack(Difficulty.Easy));
		attackSequence.add(new HeadAttack(Difficulty.Medium));
		attackSequence.add(new HeadAttack(Difficulty.Hard));
		attackSequence.add(new IdleAttack(Difficulty.Easy));
		attackSequence.add(new IdleAttack(Difficulty.Medium));
		attackSequence.add(new IdleAttack(Difficulty.Hard));
	}
	
	// TODO Write test for all difficulties
	
	@Test
	public void testBodyAttackEasy() {
		Attack currentAttack = attackSequence.get(0);
		
	}
}
