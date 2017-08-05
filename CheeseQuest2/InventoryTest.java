// import game.*;
import game.system.*;
import game.object.item.*;
import game.object.item.background.*;
import game.object.item.background.person.*;
import game.object.item.collectable.*;
import game.object.item.collectable.consumable.drink.*;
import game.object.item.collectable.consumable.food.*;
import game.object.item.collectable.consumable.healer.*;
import game.object.item.collectable.container.*;
import game.object.item.collectable.currency.*;
import game.object.item.collectable.equipable.chest.*;
import game.object.item.collectable.equipable.feet.*;
import game.object.item.collectable.equipable.head.*;
import game.object.item.collectable.equipable.mainhand.*;
import game.object.item.collectable.equipable.offhand.*;

public class InventoryTest {

	public static void main(String[] args) {
		Inventory<Collectable> inv = new Inventory<Collectable>();
		System.out.println();
		System.out.println(inv);

		Coin c = Coin.getInstance();
		inv.add(c);
		System.out.println(inv);

		Coin c2 = Coin.getInstance();
		inv.add(c2);
		System.out.println(inv);

		inv.add(c, 5);
		System.out.println(inv);
	}
}
