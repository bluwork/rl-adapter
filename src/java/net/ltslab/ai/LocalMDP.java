package net.ltslab.ai;


import clojure.java.api.Clojure;
import clojure.lang.IFn;
import com.google.gson.Gson;
import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.*;

public class LocalMDP implements MDP<LocalMDP.GameScreen, Integer, ActionSpace<Integer>> {

    private final IFn step;
    private final IFn reset;
    private int[] actions;
    private DiscreteSpace discreteSpace;
    private ObservationSpace<GameScreen> observationSpace;
    private byte[] screenBuffer;
    private StepData data;
    private Gson gson;

    public LocalMDP() {
        actions = new int[3];
        discreteSpace = new DiscreteSpace(actions.length);
        int[] shape = {Const.SHAPE_WIDTH, Const.SHAPE_HEIGHT, 3};
        observationSpace = new ArrayObservationSpace<>(shape);
        screenBuffer = new byte[shape[0] * shape[1] * shape[2]];
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("rl-adapter.environment"));
        step = Clojure.var("rl-adapter.environment", "step");
        reset = Clojure.var("rl-adapter.environment", "reset");
        gson = new Gson();

        initData();
    }

    private void initData() {

            byte [] zeroData = new byte[Const.SHAPE_HEIGHT * Const.SHAPE_WIDTH * 3];
            for (int i = 0; i < zeroData.length; i++) {
                zeroData[i] = 0;
            }
            data = new StepData();
            data.setLastFrame(zeroData);
            data.setDone(false);
            data.setReward(0);

    }

    @Override
    public ObservationSpace getObservationSpace() {
        return observationSpace;
    }

    @Override
    public ActionSpace getActionSpace() {
        return discreteSpace;
    }

    @Override
    public GameScreen reset() {
        System.out.println("Calling reset");

        String jsonized = (String) reset.invoke();
        StepData lastStep = gson.fromJson(jsonized, StepData.class);

        return new GameScreen(lastStep.getLastFrame());
    }

    @Override
    public void close() {
//        handler.postClose();
    }

    @Override
    public StepReply step(Integer action) {
        //System.out.println("Calling step  with action " + action);

        String jsonized = (String) step.invoke(action);
        data = gson.fromJson(jsonized, StepData.class);
        return new StepReply(new GameScreen(data.getLastFrame()), data.getReward(), data.isDone(), null);
    }

    @Override
    public boolean isDone() {

        return data.isDone();
    }

    @Override
    public MDP<GameScreen, Integer, ActionSpace<Integer>> newInstance() {
        return new LocalMDP();
    }

    public static class GameScreen implements Encodable {
        double[] array;

        public GameScreen(byte[] screen) {
            array = new double[screen.length];
            for (int i = 0; i < screen.length; i++) {
                array[i] = (screen[i] & 0xFF) / 255.0;
            }
        }

        public double[] toArray() {
            return array;
        }
    }
}