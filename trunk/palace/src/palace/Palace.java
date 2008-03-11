package palace;
import gui.Canvas;
import gui.WindowFrame;
import palace.logical.card.GameState;
import palace.visual.card.VisualCardPile;
import palace.visual.card.VisualCardStack;
import palace.visual.card.VisualDeck;
import palace.visual.card.VisualHand;
import palace.visual.card.VisualPlayArea;

public class Palace implements Runnable {

	public static void main(String[] args) {
		
		Palace palace = new Palace();
		
		palace.run();
		
	}

	private Canvas _canvas;

	private MouseInterpreter _mouseActionInterpreter;
	
	private WindowFrame _windowFrame;

	private VisualHand _otherHand;

	private VisualPlayArea _otherOpen;

	private VisualCardStack _otherVisualCardStackOne;

	private VisualCardStack _otherVisualCardStackTwo;

	private VisualCardStack _otherVisualCardStackThree;

	private VisualCardPile _pile;

	private VisualDeck _deck;

	private VisualCardStack _playerVisualCardStackOne;

	private VisualCardStack _playerVisualCardStackTwo;

	private VisualCardStack _playerVisualCardStackThree;

	private VisualHand _playerHand;

	private VisualPlayArea _playerOpen;

	private GameState _gameState;

	public Palace() {
		instantiateComponents();
		assembleVisualComponents();
		layoutVisualComponents();
		associateVisualAndLogicalComponents();
	}

	private void assembleVisualComponents() {

		_canvas.addElement(_otherHand);

		_otherOpen.addElement(_otherVisualCardStackOne);
		_otherOpen.addElement(_otherVisualCardStackTwo);
		_otherOpen.addElement(_otherVisualCardStackThree);
		_canvas.addElement(_otherOpen);

		_canvas.addElement(_pile);

		_canvas.addElement(_deck);

		_canvas.addElement(_playerOpen);
		_playerOpen.addElement(_playerVisualCardStackOne);
		_playerOpen.addElement(_playerVisualCardStackTwo);
		_playerOpen.addElement(_playerVisualCardStackThree);

		_canvas.addElement(_playerHand);

	}

	private void associateVisualAndLogicalComponents() {

		_gameState.getOtherPlayer().getHand().addObserver(_otherHand);

		_gameState.getOtherPlayer().getCardStackOne().addObserver(
				_otherVisualCardStackOne);
		_gameState.getOtherPlayer().getCardStackTwo().addObserver(
				_otherVisualCardStackTwo);
		_gameState.getOtherPlayer().getCardStackThree().addObserver(
				_otherVisualCardStackThree);

		_gameState.getPile().addObserver(_pile);

		_gameState.getPlayer().getCardStackOne().addObserver(
				_playerVisualCardStackOne);
		_gameState.getPlayer().getCardStackTwo().addObserver(
				_playerVisualCardStackTwo);
		_gameState.getPlayer().getCardStackThree().addObserver(
				_playerVisualCardStackThree);

		_gameState.getPlayer().getHand().addObserver(_playerHand);

		_canvas.setGameState(_gameState);
		_mouseActionInterpreter.setGameState(_gameState);
	}

	private void instantiateComponents() {

		_gameState = new GameState();

		_mouseActionInterpreter = new MouseInterpreter();
		
		MultiSelectSurface multiSelectSurface = new MultiSelectSurface();
		_canvas = new Canvas(_mouseActionInterpreter, multiSelectSurface);
		multiSelectSurface.setCanvas(_canvas);

		_windowFrame = new WindowFrame(_canvas);

		_otherHand = new VisualHand();

		_otherOpen = VisualPlayArea.getHorizontallyAlligned();
		_otherVisualCardStackOne = new VisualCardStack(_gameState
				.getOtherPlayer().getCardStackOne());
		_otherVisualCardStackTwo = new VisualCardStack(_gameState
				.getOtherPlayer().getCardStackTwo());
		_otherVisualCardStackThree = new VisualCardStack(_gameState
				.getOtherPlayer().getCardStackThree());

		_pile = new VisualCardPile(_gameState.getPile());
		_deck = new VisualDeck(_gameState.getDeck());

		_playerOpen = VisualPlayArea.getHorizontallyAlligned();
		_playerVisualCardStackOne = new VisualCardStack(_gameState.getPlayer()
				.getCardStackOne());
		_playerVisualCardStackTwo = new VisualCardStack(_gameState.getPlayer()
				.getCardStackTwo());
		_playerVisualCardStackThree = new VisualCardStack(_gameState
				.getPlayer().getCardStackThree());

		_playerHand = new VisualHand();

	}

	private void layoutVisualComponents() {

		_otherHand.setX(54);
		_otherHand.setY(4);

		_otherOpen.setX(32);
		_otherOpen.setY(_otherHand.getY() + 3 + _otherHand.getHeight());

		_pile.setX(52);
		_pile.setY(_otherOpen.getY() + 11 + _otherOpen.getHeight());

		_deck.setX(_pile.getX() + 2 * _pile.getWidth());
		_deck.setY(_pile.getY());

		_playerOpen.setX(32);
		_playerOpen.setY(_deck.getY() + 3 + _deck.getHeight());

		_playerHand.setX(_otherHand.getX());
		_playerHand.setY(_playerOpen.getY() + 11 + _playerOpen.getHeight());

	}

	public void run() {
		_windowFrame.createAndShowGUI("  Palace", 400, 600);
	}

}
