package net.ltslab.ai;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.rl4j.learning.IHistoryProcessor;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteConv;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.network.dqn.DQN;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdConv;
import org.deeplearning4j.rl4j.space.Encodable;
import org.deeplearning4j.rl4j.util.DataManager;

public class LocalDQN extends QLearningDiscreteConv {

    private final IFn showStepData;
    private final IFn showEpochData;
    private int skip = 100;

    public LocalDQN(MDP mdp, DQNFactoryStdConv.Configuration netConf, IHistoryProcessor.Configuration hpconf, QLConfiguration conf, DataManager dataManager) {
        super(mdp, netConf, hpconf, conf, dataManager);
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("rl-adapter.presenter"));
        showStepData = Clojure.var("rl-adapter.presenter", "show-step");
        showEpochData = Clojure.var("rl-adapter.presenter", "show-epoch-data");
    }

    @Override
    protected QLStepReturn trainStep(Encodable obs) {
        if (getStepCounter()% skip == 0) {
            QLStepReturn<LocalMDP.GameScreen> stepReturn = super.trainStep(obs);
            showStepData.invoke(getStepCounter(), stepReturn.getMaxQ(), stepReturn.getScore());
            return stepReturn;
        } else {
            return super.trainStep(obs);
        }
    }

    @Override
    protected DataManager.StatEntry trainEpoch() {
        DataManager.StatEntry epoch = super.trainEpoch();
        showEpochData.invoke(epoch.getEpochCounter(),epoch.getReward());
        return epoch;
    }
}
