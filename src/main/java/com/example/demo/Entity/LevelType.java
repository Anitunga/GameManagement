package com.example.playerdemo.Entity;

public enum LevelType {
    BEGINNER,
    ADVANCED,
    EXPERT;

    // Determines the level based on total points
    public static LevelType getLevelByPoints(int points) {
        if (points <= 50) {
            return BEGINNER; // Player is a beginner if total points are <= 50
        } else if (points <= 100) {
            return ADVANCED; // Player is advanced if total points are <= 100
        } else {
            return EXPERT; // Player is expert if total points are > 100
        }
    }
}
