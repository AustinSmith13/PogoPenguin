package edu.angelo.FinalProjectSmith;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AccelerometerHandler;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class PogoPenguinGame extends AndroidGame {
    public Screen getStartScreen() {
        // Get the accelerometer handler and give it to the loading screen
        AccelerometerHandler accelHandler = new AccelerometerHandler(this.getApplicationContext());
        LoadingScreen ls = new LoadingScreen(this);
        ls.accelHandler = accelHandler;
        return ls;
    }
}
