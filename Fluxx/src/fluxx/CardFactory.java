package fluxx;

import java.util.ArrayList;
import java.util.HashMap;

import fluxx.card.Card;
import fluxx.card.action.DiscardAndDraw;
import fluxx.card.action.DrawThreePlayTwoOfThem;
import fluxx.card.action.DrawTwoAndUseEm;
import fluxx.card.action.EmptyTheTrash;
import fluxx.card.action.EverybodyGetsOne;
import fluxx.card.action.ExchangeKeepers;
import fluxx.card.action.GoFish;
import fluxx.card.action.INeedAGoal;
import fluxx.card.action.LetsDoThatAgain;
import fluxx.card.action.LetsSimplify;
import fluxx.card.action.NoLimits;
import fluxx.card.action.RotateHands;
import fluxx.card.action.RulesReset;
import fluxx.card.action.ScrambleKeepers;
import fluxx.card.action.StealAKeeper;
import fluxx.card.action.TakeAnotherTurn;
import fluxx.card.action.Taxation;
import fluxx.card.action.TradeHands;
import fluxx.card.action.TrashAKeeper;
import fluxx.card.action.TrashANewRule;
import fluxx.card.action.UseWhatYouTake;
import fluxx.card.goal.AllYouNeedIsLove;
import fluxx.card.goal.FiveKeepers;
import fluxx.card.goal.Goal;
import fluxx.card.goal.NoYAndXGoal;
import fluxx.card.goal.TenCardsInHand;
import fluxx.card.goal.XAndYGoal;
import fluxx.card.keeper.Keeper;
import fluxx.card.newRule.DrawX;
import fluxx.card.newRule.FirstPlayRandom;
import fluxx.card.newRule.HandLimitX;
import fluxx.card.newRule.KeeperLimitX;
import fluxx.card.newRule.NoHandBonus;
import fluxx.card.newRule.PlayAll;
import fluxx.card.newRule.PlayX;
import fluxx.card.newRule.PoorBonus;
import fluxx.card.newRule.RichBonus;

public class CardFactory {

	private ArrayList<Card> _cards;

	private FluxxGame _game;

	private HashMap<String, Keeper> _keepers;

	public CardFactory(FluxxGame game) {
		_game = game;
		_cards = new ArrayList<Card>();
		_keepers = new HashMap<String, Keeper>();
		instantiateCards();
		_keepers = null;
	}

	private Goal generateNoYAndXGoal(String name, FluxxGame game, String x,
			String y) {
		return new NoYAndXGoal(name, _game, _keepers.get(x), _keepers.get(y));
	}

	private Goal generateXAndYGoal(String name, FluxxGame game, String x,
			String y) {
		return new XAndYGoal(name, _game, _keepers.get(x), _keepers.get(y));
	}

	public ArrayList<Card> getCards() {
		return _cards;
	}

	private void instantiateActions() {

		_cards.add(new DrawTwoAndUseEm("Draw 2 and use 'em", _game));
		_cards.add(new RulesReset("Rules Reset", _game));
		_cards.add(new UseWhatYouTake("Use what you take", _game));
		_cards.add(new ExchangeKeepers("Exchange Keepers", _game));
		_cards.add(new EverybodyGetsOne("Everybody gets 1", _game));
		_cards.add(new DiscardAndDraw("Discard & Draw", _game));
		_cards.add(new TrashAKeeper("Trash a Keeper", _game));
		_cards.add(new TakeAnotherTurn("Take another turn", _game));
		_cards.add(new RotateHands("Rotate Hands", _game));
		_cards.add(new Taxation("Taxation!", _game));
		_cards.add(new GoFish("Go Fish", _game));
		_cards.add(new ScrambleKeepers("Scramble Keepers", _game));
		_cards.add(new EmptyTheTrash("Empty the Trash", _game));
		_cards.add(new NoLimits("No Limits", _game));
		_cards.add(new TrashANewRule("Trash a New Rule", _game));
		_cards.add(new INeedAGoal("I Need a Goal", _game));
		_cards.add(new StealAKeeper("Steal a Keeper", _game));
		_cards.add(new TradeHands("Trade hands", _game));
		_cards.add(new LetsSimplify("Let's Simplify", _game));
		_cards.add(new DrawThreePlayTwoOfThem("Draw 3, play 2 of them", _game));
		_cards.add(new LetsDoThatAgain("Let's Do That Again", _game));
	}

	private void instantiateCards() {

		/*
		 * fist create all the Keepers
		 */
		for (String name : Keeper.names) {
			Keeper keeper = new Keeper(name, _game);
			_cards.add(keeper);
			_keepers.put(name, keeper);
		}

		/*
		 * X and Y Goals
		 */
		_cards.add(generateXAndYGoal("Baked Goods", _game, "Bread", "Cookies"));
		_cards.add(generateXAndYGoal("Bed Time", _game, "Sleep", "Time"));
		_cards.add(generateXAndYGoal("Chocolate Cookies", _game, "Chocolate",
				"Cookies"));
		_cards.add(generateXAndYGoal("Chocolate Milk", _game, "Chocolate",
				"Milk"));
		_cards.add(generateXAndYGoal("Death by Chocolate", _game, "Death",
				"Chocolate"));
		_cards.add(generateXAndYGoal("Dreamland", _game, "Sleep", "Dreams"));
		_cards.add(generateXAndYGoal("Hearts and Minds", _game, "Love",
				"The Brain"));
		_cards.add(generateXAndYGoal("Hippyism", _game, "Peace", "Love"));
		_cards.add(generateXAndYGoal("Milk and Cookies", _game, "Milk",
				"Cookies"));
		_cards.add(generateXAndYGoal("Night and Day", _game, "The Moon",
				"The Sun"));
		_cards.add(generateXAndYGoal("Rocket Science", _game, "The Rocket",
				"The Brain"));
		_cards.add(generateXAndYGoal("Rocket to the Moon", _game, "The Rocket",
				"The Moon"));
		_cards.add(generateXAndYGoal("Squishy Chocolate", _game, "The Sun",
				"Chocolate"));
		_cards.add(generateXAndYGoal("The Appliances", _game, "Television",
				"The Toaster"));
		_cards.add(generateXAndYGoal("Time is Money", _game, "Time", "Money"));
		_cards.add(generateXAndYGoal("Toast", _game, "Bread", "The Toaster"));
		_cards.add(generateXAndYGoal("War = Death", _game, "War", "Death"));
		_cards.add(generateXAndYGoal("Winning the Lottery", _game, "Dreams",
				"Money"));

		/*
		 * No Y and X Goals
		 */
		_cards
				.add(generateNoYAndXGoal("Peace (no War)", _game, "Peace",
						"War"));
		_cards.add(generateNoYAndXGoal("The Brain (no TV)", _game, "The Brain",
				"Television"));

		/*
		 * All you need is Love
		 */
		_cards.add(new AllYouNeedIsLove("All you need is Love", _game, _keepers
				.get("Love")));

		/*
		 * 5 Keepers
		 */
		_cards.add(new FiveKeepers("5 Keepers", _game));

		/*
		 * 10 cards in hand
		 */
		_cards.add(new TenCardsInHand("10 cards in hand", _game));

		instantiateNewRules();

		instantiateActions();

	}

	private void instantiateNewRules() {
		/*
		 * Draw 2, 3, 4, 5
		 */
		_cards.add(new DrawX("Draw 2", _game, 2));
		_cards.add(new DrawX("Draw 3", _game, 3));
		_cards.add(new DrawX("Draw 4", _game, 4));
		_cards.add(new DrawX("Draw 5", _game, 5));

		/*
		 * Play 2, 3, 4
		 */
		_cards.add(new PlayX("Play 2", _game, 2));
		_cards.add(new PlayX("Play 3", _game, 3));
		_cards.add(new PlayX("Play 4", _game, 4));

		/*
		 * KeeperLimit 2, 3, 4
		 */
		_cards.add(new KeeperLimitX("Keeper Limit 2", _game, 2));
		_cards.add(new KeeperLimitX("Keeper Limit 3", _game, 3));
		_cards.add(new KeeperLimitX("Keeper Limit 4", _game, 4));

		/*
		 * HandLimit 0, 1, 2
		 */
		_cards.add(new HandLimitX("Hand Limit 0", _game, 0));
		_cards.add(new HandLimitX("Hand Limit 1", _game, 1));
		_cards.add(new HandLimitX("Hand Limit 2", _game, 2));

		/*
		 * Play All
		 */
		_cards.add(new PlayAll("Play All", _game));

		/*
		 * First Play Random
		 */
		_cards.add(new FirstPlayRandom("First Play Random", _game));

		/*
		 * No-Hand Bonus
		 */
		_cards.add(new NoHandBonus("No-Hand Bonus", _game));

		/*
		 * Poor Bonus
		 */
		_cards.add(new PoorBonus("Poor Bonus", _game));

		/*
		 * Rich Bonus
		 */
		_cards.add(new RichBonus("Rich Bonus", _game));
	}

}
