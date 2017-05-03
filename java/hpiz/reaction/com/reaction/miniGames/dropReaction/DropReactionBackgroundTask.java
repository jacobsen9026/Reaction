package hpiz.reaction.com.reaction.miniGames.dropReaction;

import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by cjacobsen on 5/1/2017.
 */

public class DropReactionBackgroundTask extends AsyncTask<String, Integer, String> {
    private final String TAG = "DropReactionBackgroundTask.java";
    private final DropReaction gActivity;
    private final WeakReference<DropReaction> gameActivity;
    private ConstraintLayout contentContainer;

    public DropReactionBackgroundTask(DropReaction a) {
        gameActivity = new WeakReference<DropReaction>(a);
        gActivity = gameActivity.get();
    }

    protected void onPreExecute() {
        gActivity.InitializeDrop();

    }

    @Override
    protected String doInBackground(String... params) {
        /*
        if (gActivity.redScore>9){

            gActivity.redWonGame();
            return "";
        }
        if (gActivity.blueScore>9){
            gActivity.blueWonGame();
            return "";
        }
*/

        if (params[0].equals("SLEEP:1000")) {
            Log.v("backgroundTask", "Running background sleep");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "SLEPT";
        } else {
            for (int x = 0; x < 100; x++) {

                gActivity.stepDrop(x);
                onProgressUpdate(x);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "GAME:NEXTSTEP";
        }
    }

    protected void onProgressUpdate(Integer progress) {

    }

    protected void onPostExecute(String result) {
        gActivity.drop();
        Log.v("backgroundTask", "Setting white");
        if (result.equals("GAME:NEXTSTEP")) {
            gActivity.setTopRed();
            gActivity.setBottomBlue();
            gActivity.startButtonListeners();
        } else if (result.equals("SLEPT")) {
            gActivity.runSingleRound();

        }
        // topHalf.setBackgroundColor(Color.WHITE);

        //gActivity.runSimonSaysGame();
    }


}