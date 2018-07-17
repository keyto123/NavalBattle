package game.boats;

public final class BoatStorage {

	public BoatStorage() {}

	private BoatType boats[] = new BoatType[] {
			new BoatType(BoatTypes.SMALLBOAT), new BoatType(BoatTypes.MEDIUMBOAT), new BoatType(BoatTypes.LARGEBOAT),
	};
	
	public BoatType getBoatType(int index) {
		return boats[index];
	}
	
	public int getLength() {
		return boats.length;
	}

}
