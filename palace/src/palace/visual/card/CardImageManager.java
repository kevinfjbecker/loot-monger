package palace.visual.card;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import palace.logical.card.LogicalCard;


public abstract class CardImageManager {

	private static BufferedImage _cardBack;

	static {
		try {
			_cardBack = ImageIO.read(new File("cards/b.gif"));
		} catch (IOException e) {
		}
	}

	public static BufferedImage getCardFace(VisualCard visualCard) {
		BufferedImage cardFace = null;
		try {
			cardFace = ImageIO.read(new File("cards/"
					+ fileNameOf(visualCard.getLogicalCard()) + ".gif"));
		} catch (IOException e) {
		}
		return cardFace;
	}

	public static BufferedImage getCardBack() {
		return _cardBack;
	}

	private static String fileNameOf(LogicalCard card) {
		String s = "";
		switch (card.getRank()) {
		case Two:
			s += '2';
			break;
		case Three:
			s += '3';
			break;
		case Four:
			s += '4';
			break;
		case Five:
			s += '5';
			break;
		case Six:
			s += '6';
			break;
		case Seven:
			s += '7';
			break;
		case Eight:
			s += '8';
			break;
		case Nine:
			s += '9';
			break;
		case Ten:
			s += "t";
			break;
		case Jack:
			s += 'j';
			break;
		case Queen:
			s += 'q';
			break;
		case King:
			s += 'k';
			break;
		case Ace:
			s += 'a';
			break;
		}

		switch (card.getSuit()) {
		case Club:
			s += 'c';
			break;
		case Diamond:
			s += 'd';
			break;
		case Heart:
			s += 'h';
			break;
		case Spade:
			s += 's';
			break;
		}

		return s;
	}

}
