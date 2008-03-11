package palace;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import palace.event.PlayEvent;
import palace.event.PlayMultipleEvent;
import palace.logical.card.GameState;
import palace.toolkit.gui.Canvas;
import palace.toolkit.gui.InteractiveSurface;
import palace.toolkit.gui.MouseHub;
import palace.toolkit.visual.Active;
import palace.toolkit.visual.Interactive;
import palace.toolkit.visual.VisualTarget;
import palace.visual.card.VisualCard;
import palace.visual.card.VisualHand;
import static palace.visual.card.HorizontalOverlappedAlignment.*;

public class MouseInterpreter extends MouseHub {

	private Canvas _canvas;

	private GameState _gameState;

	private HashMap<Interactive, Point> _offsets;

	public MouseInterpreter() {
		_offsets = new HashMap<Interactive, Point>();
	}

	protected void calculateOffset(MouseEvent e) {
		if (_surface.isAnElementGrabbed()) {

			_offsets.clear();

			ArrayList<VisualCard> selected;
			selected = ((MultiSelectSurface) _surface).getSelectedElements();

			for (Interactive interactive : selected) {

				int xOffset = interactive.getX() - e.getX();
				int yOffset = interactive.getY() - e.getY();

				_offsets.put(interactive, new Point(xOffset, yOffset));
			}

			super.calculateOffset(e);
		}

	}

	private void dropCardOnTarget(MouseEvent e) {

		if (_surface.isAnElementGrabbed()) {
			Interactive grabbed = _surface.getGrabbedElement();
			if (grabbed instanceof VisualCard) {
				VisualCard targetingCard = (VisualCard) grabbed;
				VisualTarget target = getTarget(e.getX(), e.getY());
				if (target != null) {

					ArrayList<VisualCard> selected;
					selected = ((MultiSelectSurface) _surface)
							.getSelectedElements();

					if (selected.size() > 0) {

						for (Interactive interactive : selected)
							if (!(interactive instanceof VisualCard))
								return;

						VisualCard[] targetingCards = null;

						if (selected.contains(targetingCard)) {
							targetingCards = new VisualCard[selected.size()];
							targetingCards = selected.toArray(targetingCards);
						} else {
							targetingCards = new VisualCard[selected.size() + 1];
							targetingCards[0] = targetingCard;
							for (int i = 1; i < targetingCards.length; i++)
								targetingCards[i] = (VisualCard) selected
										.get(i - 1);
						}
						PlayMultipleEvent playMultipleEvent;
						playMultipleEvent = new PlayMultipleEvent(this,
								targetingCards, target);
						_gameState.actionPerformed(playMultipleEvent);
					} else {
						PlayEvent playEvent;
						playEvent = new PlayEvent(this, targetingCard, target);
						_gameState.actionPerformed(playEvent);
					}
					if (_gameState.wasLastMoveValid()) {

						if (!selected.contains(targetingCard)) {
							targetingCard.getParent().remove(targetingCard);
							targetingCard.setParent(null);
						}

						for (Interactive interactive : selected) {
							interactive.unselect();
							interactive.getParent().remove(interactive);
							interactive.setParent(null);
						}

						((MultiSelectSurface) _surface).getSelectedElements()
								.clear();
					}
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

		// //////////////////////////// Pass actionEvents to _actionListener //

		Interactive clickedInteractive;
		clickedInteractive = _surface.pick(e.getX(), e.getY());

		if (clickedInteractive instanceof Active) {
			_gameState.actionPerformed(((Active) clickedInteractive)
					.getActionEvent());
		} else {

			// ///////////////////////////////////////////// Select Elements //

			_surface.select(e.getX(), e.getY());

		}
	}

	public void mouseDragged(MouseEvent e) {
		((MultiSelectSurface) _surface).setDragging(true);
		if (_surface.isAnElementGrabbed()) {
			updateLocation(e);
		}
		_canvas.repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		_surface.target(e.getX(), e.getY());
		_canvas.repaint();
	}

	public void mousePressed(MouseEvent e) {

		_surface.grab(e.getX(), e.getY());

		calculateOffset(e);

		_canvas.repaint();
	}

	public void mouseReleased(MouseEvent e) {

		((MultiSelectSurface) _surface).setDragging(false);

		// ///////////////////////////////////////////// drop card on target //

		dropCardOnTarget(e);

		// /////////////////////////////////////////// reorder cards in hand //

		rearrangeCardsInHand();

		_surface.ungrab();

		_surface.alignElements();

		_canvas.repaint();
	}

	private void rearrangeCardsInHand() {
		if (_surface.isAnElementGrabbed()) {
			Interactive grabbed = _surface.getGrabbedElement();
			if (grabbed instanceof VisualCard) {
				VisualCard card = (VisualCard) grabbed;
				if (card.getParent() instanceof VisualHand) {
					VisualHand hand = (VisualHand) card.getParent();
					hand.changeCardPosition(card);
				}
			}
		}
	}

	public void setCanvas(Canvas canvas) {
		_canvas = canvas;
	}

	public void setGameState(GameState gameState) {
		_gameState = gameState;
	}

	public void setInteractiveSurface(InteractiveSurface interactiveSurface) {
		_surface = interactiveSurface;
	}

	protected void updateLocation(MouseEvent e) {
		if (_surface.isAnElementGrabbed()) {
			super.updateLocation(e);
			int spaceing = SPACING;
			Interactive grabbed = _surface.getGrabbedElement();
			for (Interactive interactive : ((MultiSelectSurface) _surface)
					.getSelectedElements()) {
				if (interactive == grabbed)
					continue;
				interactive.setX(grabbed.getX() + spaceing);
				interactive.setY(grabbed.getY());
				spaceing += SPACING;
			}
		}
	}

}
