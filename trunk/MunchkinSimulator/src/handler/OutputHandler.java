package handler;

import message.command.AgentMayChooseToInterfereWithCombat;
import message.command.AgentMayChooseToPlayOneShot;
import message.command.AgentMayLookForTrouble;
import message.command.PlayerLooksForTrouble;
import message.command.AgentMustDecideOnALootCard;
import message.command.AgentMustDiscardCarriedItems;
import message.command.AgentMustDiscardFromHandEvent;
import message.command.IGameCommand;
import message.command.PlayerPlaysOneShotCardForAdventurerSide;
import message.command.PlayerPlaysOneShotCardForMonsterSide;
import message.command.PlayerDiscardsCarriedItem;
import message.command.PlayerDiscardsFromHand;
import message.command.PlayerTakesLootCard;
import message.event.CPhaseTransitionEvent;
import message.event.CurseAppliedEvent;
import message.event.DiscardDownToMaximumHandSizeEvent;
import message.event.DoorKickEvent;
import message.event.DrawTwoCardsFromEachDeckEvent;
import message.event.EquipItemEvent;
import message.event.FaceUpCurseCardDrawnEvent;
import message.event.IGameEvent;
import message.event.LevelGainEvent;
import message.event.LevelLossEvent;
import message.event.MonsterDoesBadStuffToPlayerEvent;
import message.event.PartyLootsTheBodyEvent;
import message.event.PlayerDeathEvent;
import message.event.PlayerFirstTurnAfterDyingEvent;
import message.event.PlayerGetsAwayEvent;
import message.event.PlayerKillsMonsterEvent;
import message.event.PlayerTriesToRunAwayEvent;
import message.event.PlayerWinsGameEvent;
import message.event.SearchTheRoomEvent;
import message.event.TakeTreasureFromSlainMonsterEvent;

public class OutputHandler extends AHandlerLink {

	public OutputHandler(IGameEventHandler handler) {
		super(handler);
	}

	public void processEvent(IGameEvent gameEvent) {

		/*
		 *  these make the output too verbose
		 */	
		if (gameEvent instanceof AgentMayChooseToInterfereWithCombat ||
			gameEvent instanceof AgentMayChooseToPlayOneShot ||
			gameEvent instanceof AgentMayLookForTrouble) {
			
			return;
		}
				
		if (gameEvent instanceof CPhaseTransitionEvent)
			processEvent((CPhaseTransitionEvent) gameEvent);

		if (gameEvent instanceof IGameCommand)
			processEvent((IGameCommand)gameEvent);
		
		else if (gameEvent instanceof AgentMustDecideOnALootCard)
			processEvent((AgentMustDecideOnALootCard) gameEvent);

		else if (gameEvent instanceof DoorKickEvent)
			processEvent((DoorKickEvent) gameEvent);

		else if (gameEvent instanceof FaceUpCurseCardDrawnEvent)
			processEvent((FaceUpCurseCardDrawnEvent) gameEvent);

		else if (gameEvent instanceof PlayerWinsGameEvent)
			processEvent((PlayerWinsGameEvent) gameEvent);

		else if (gameEvent instanceof CurseAppliedEvent)
			processEvent((CurseAppliedEvent) gameEvent);

		else if (gameEvent instanceof PlayerFirstTurnAfterDyingEvent)
			processEvent((PlayerFirstTurnAfterDyingEvent) gameEvent);

		else if (gameEvent instanceof DrawTwoCardsFromEachDeckEvent)
			processEvent((DrawTwoCardsFromEachDeckEvent) gameEvent);

		else if (gameEvent instanceof DiscardDownToMaximumHandSizeEvent)
			processEvent((DiscardDownToMaximumHandSizeEvent) gameEvent);

		else if (gameEvent instanceof TakeTreasureFromSlainMonsterEvent)
			processEvent((TakeTreasureFromSlainMonsterEvent) gameEvent);

		else if (gameEvent instanceof SearchTheRoomEvent)
			processEvent((SearchTheRoomEvent) gameEvent);

		else if (gameEvent instanceof PartyLootsTheBodyEvent)
			processEvent((PartyLootsTheBodyEvent) gameEvent);

		else if (gameEvent instanceof PlayerTriesToRunAwayEvent)
			processEvent((PlayerTriesToRunAwayEvent) gameEvent);

		else if (gameEvent instanceof PlayerKillsMonsterEvent)
			processEvent((PlayerKillsMonsterEvent) gameEvent);

		else if (gameEvent instanceof MonsterDoesBadStuffToPlayerEvent)
			processEvent((MonsterDoesBadStuffToPlayerEvent) gameEvent);

		else if (gameEvent instanceof PlayerGetsAwayEvent)
			processEvent((PlayerGetsAwayEvent) gameEvent);

		else if (gameEvent instanceof PlayerDeathEvent)
			processEvent((PlayerDeathEvent) gameEvent);

		else if (gameEvent instanceof AgentMustDiscardFromHandEvent)
			processEvent((AgentMustDiscardFromHandEvent) gameEvent);

		else if (gameEvent instanceof PlayerDiscardsFromHand)
			processEvent((PlayerDiscardsFromHand) gameEvent);

		else if (gameEvent instanceof AgentMustDiscardCarriedItems)
			processEvent((AgentMustDiscardCarriedItems) gameEvent);

		else if (gameEvent instanceof PlayerDiscardsCarriedItem)
			processEvent((PlayerDiscardsCarriedItem) gameEvent);

		else if (gameEvent instanceof EquipItemEvent)
			processEvent((EquipItemEvent) gameEvent);

		else if (gameEvent instanceof PlayerLooksForTrouble)
			processEvent((PlayerLooksForTrouble) gameEvent);

		else if (gameEvent instanceof LevelGainEvent)
			processEvent((LevelGainEvent) gameEvent);

		else if (gameEvent instanceof LevelLossEvent)
			processEvent((LevelLossEvent) gameEvent);

		else if (gameEvent instanceof PlayerTakesLootCard)
			processEvent((PlayerTakesLootCard) gameEvent);

		else if (gameEvent instanceof PlayerPlaysOneShotCardForAdventurerSide)
			processEvent((PlayerPlaysOneShotCardForAdventurerSide) gameEvent);

		else if (gameEvent instanceof PlayerPlaysOneShotCardForMonsterSide)
			processEvent((PlayerPlaysOneShotCardForMonsterSide) gameEvent);
	}

	private void processEvent(CPhaseTransitionEvent e) {
		System.out.println("---->" + e.type + " " + e.phase + "<----");
	}
	
	private void processEvent(IGameCommand c){
		System.out.println(c);
	}

	private void processEvent(DoorKickEvent e) {
		System.out.println(e.player.getName() + " kicks open the door...");
		System.out.println("... revealing: " + e.encounterCard + ".");
	}

	private void processEvent(FaceUpCurseCardDrawnEvent e) {
		System.out.println(e.player.getName() + " is cursed...");
	}

	private void processEvent(PlayerWinsGameEvent e) {
		System.out.println("\n !!! " + e.player.getName()
				+ " wins the game !!!\n");
	}

	private void processEvent(CurseAppliedEvent e) {
		System.out.println(e.player.getName() + " suffers " + e.card + ".");
	}

	private void processEvent(PlayerFirstTurnAfterDyingEvent e) {
		System.out.println("This is " + e.player.getName()
				+ "'s first turn since getting snuffed.");
	}

	private void processEvent(DrawTwoCardsFromEachDeckEvent e) {
		System.out.println(e.player.getName()
				+ " draws two cards from each deck.");
	}

	private void processEvent(DiscardDownToMaximumHandSizeEvent e) {
		System.out.println(e.player.getName() + " is required to discard "
				+ e.numberToDiscard + " cards.");
	}

	private void processEvent(TakeTreasureFromSlainMonsterEvent e) {
		System.out.println(e.player.getName()
				+ " takes the monster's treasure, " + "which yields "
				+ e.monsterTreasures + " cards.");
	}

	private void processEvent(SearchTheRoomEvent e) {
		System.out.println(e.player.getName() + " searches the room.");
	}

	private void processEvent(PartyLootsTheBodyEvent e) {
		System.out.println("... the other players loot the body:");
	}

	private void processEvent(PlayerTriesToRunAwayEvent e) {
		System.out.println("The fearful " + e.combat.getPlayer().getName()
				+ " flees the mighty monster.\n" + e.combat);
	}

	private void processEvent(PlayerKillsMonsterEvent e) {
		System.out.println("The mighty " + e.combat.getPlayer().getName()
				+ " slays the fearsom monster.\n" + e.combat);
	}

	private void processEvent(MonsterDoesBadStuffToPlayerEvent e) {
		System.out.println(e.player.getName() + " is caught, and the "
				+ "monster does bad stuff...");
	}

	private void processEvent(PlayerGetsAwayEvent e) {
		System.out.println("The cowardly, but fleet-of-foot, "
				+ e.player.getName() + " escapes.");
	}

	private void processEvent(PlayerDeathEvent e) {
		System.out.println(e.player.getName() + " is mortally wounded...");
	}

	private void processEvent(EquipItemEvent e) {
		System.out.println(e.player.getName() + " equips: " + e.newEquipment
				+ ".");
	}

	private void processEvent(LevelGainEvent e) {
		System.out.println(e.player.getName() + " goes up " + e.levelGain
				+ " levels to level " + e.player.getLevel() + ".");
	}

	private void processEvent(LevelLossEvent e) {
		System.out.println(e.player.getName() + " loses " + e.levelLoss
				+ " levels (down to a minimum of level one), "
				+ "for a net loss of " + e.netLevelLoss + " levels.");
	}

	// /////////////////////////////////////////////////////////////////////////

	// private void sysOutInPhaseBanner(String s) {
	// s = "> " + s + " <----";
	// String prefix = "";
	// for (int i = 0; i < 80 - s.length(); i++)
	// prefix += "-";
	// System.out.println(prefix + s);
	// }
	//
	// private void printBanner() {
	// System.out.print("}");
	// for (int i = 0; i < 78; i++)
	// System.out.print("~");
	// System.out.println("{");
	// }
	//
	// private void sysOutInSwordBanner(String s) {
	// s = " " + s + " ";
	// String tip = ";>";
	// String hilt = "[]:::::::[]";
	// String leftBladeSegment = "";
	// String rightBladeSegment = "";
	// int bladeLength = 80 - (s.length() + tip.length() + hilt.length());
	// for (int i = 0; i < (bladeLength + 1) / 2; i++)
	// // round up
	// leftBladeSegment += ";";
	// for (int i = 0; i < bladeLength / 2; i++)
	// rightBladeSegment += ";";
	// System.out.println(hilt + leftBladeSegment + s + rightBladeSegment
	// + tip);
	//	}

}
