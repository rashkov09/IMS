package model.enums;

public enum ElectronicsType {
	LAPTOP("Laptop"),
	SMARTPHONE("Smartphone"),
	TELEVISION("Television"),
	AUDIO_EQUIPMENT("Audio Equipment"),
	CAMERA("Camera"),
	TABLET("Tablet"),
	WEARABLE_DEVICE("Wearable Device"),
	GAMING_CONSOLE("Gaming Console"),
	ACCESSORY("Accessory");

	private String type;

	ElectronicsType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}


	public static String getAll() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ElectronicsType.values().length; i++) {
			builder.append(i+1).append(". ").append(ElectronicsType.values()[i]).append(System.lineSeparator());
		}
		return builder.toString();
	}
}
