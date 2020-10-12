import java.util.*;

public class RunStore {

	public static void main(String[] args) {
        StoreInfo tracker = new StoreInfo();
		Store store = new GourmetStore(30);

        store.runStore(4);
	}

}
