package com.example.wave.enums;

public enum Games {
	LEAGUE_OF_LEGENDS,
	TEAMFIGHT_TACTICS,
	VALORANT,
	STEAM_BATTLEGROUND,
	KAKAO_BATTLEGROUND,
	RAINBOW_SIX,
	BLIZZARD,
	OVERWATCH_TWO,
	LOST_ARK;

	@Override
	public String toString() {
		switch (this) {
		case LEAGUE_OF_LEGENDS:
			return "League of Legends";
		case TEAMFIGHT_TACTICS:
			return "Teamfight Tactics";
		case VALORANT:
			return "Valorant";
		case STEAM_BATTLEGROUND:
			return "Steam Battleground";
		case KAKAO_BATTLEGROUND:
			return "Kakao Battleground";
		case RAINBOW_SIX:
			return "Rainbow Six";
		case BLIZZARD:
			return "Blizzard";
		case OVERWATCH_TWO:
			return "Overwatch Two";
		case LOST_ARK:
			return "Lost Ark";

		default:
			return name(); // 기본 이름 반환
		}
	}

}