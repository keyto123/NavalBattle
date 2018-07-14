package game.boats;

public final class BoatStorage {

	public BoatStorage() {}

	private BoatType boats[] = new BoatType[] {
			new BoatType("boat1", 2), new BoatType("boat2", 3), new BoatType("boat3", 4),
	};
	
	public BoatType getBoatType(int index) {
		return boats[index];
	}
	
	public int getLength() {
		return boats.length;
	}

}
