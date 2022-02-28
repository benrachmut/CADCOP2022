package AlgorithmSearch;

import AgentsAbstract.AgentVariable;
import AgentsAbstract.NodeId;
import Messages.Msg;
import Messages.MsgAMDLS;
import Messages.MsgAMDLSColor;
import Messages.MsgAlgorithm;

import java.util.ArrayList;
import java.util.List;

import static Delays.ProtocolDelayOneInK.k_public;

public class CAMDLS_Naive extends AMDLS_V3 {

    public static int k = k_public;

    public CAMDLS_Naive(int dcopId, int D, int agentId) {
        super(dcopId, D, agentId);
    }



    @Override
    public void updateAlgorithmName() {
        AgentVariable.AlgorithmName = "CAMDLS Naive";
    }

    @Override
    public void updateAlgorithmHeader() {
        AgentVariable.algorithmHeader = "k algo";
    }

    @Override
    public void updateAlgorithmData() {
        AgentVariable.algorithmData = Integer.toString(k);
    }

    //TODO
    protected void sendAMDLSColorMsgs() {
        List<Msg> msgsToOutbox = new ArrayList<Msg>();

        for (NodeId recieverNodeId : neighborsConstraint.keySet()) {
            for (int i = 0; i < k; i++) {
                MsgAMDLSColor mva = new MsgAMDLSColor(this.nodeId, recieverNodeId, this.valueAssignment,
                        this.timeStampCounter, this.time, this.myCounter, this.myColor);

                msgsToOutbox.add(mva);
            }

        }
        outbox.insert(msgsToOutbox);
    }

    //TODO
    @Override
    protected void sendAMDLSmsgs() {
        List<Msg>msgsToOutbox = new ArrayList<Msg>();
        for (int i = 0; i < k; i++) {
            for (NodeId recieverNodeId : neighborsConstraint.keySet()) {
                MsgAMDLS mva = new MsgAMDLS(this.nodeId, recieverNodeId, this.valueAssignment, this.timeStampCounter,
                        this.time, this.myCounter);
                msgsToOutbox.add(mva);
            }
        }
        this.outbox.insert(msgsToOutbox);
    }


    protected boolean updateMessageInContext(MsgAlgorithm msgAlgorithm) {

        NodeId sender = msgAlgorithm.getSenderId();
        int currentCounterInContext = this.counters.get(sender);
        int msgCounter = ((MsgAMDLS) msgAlgorithm).getCounter();
        if (currentCounterInContext == msgCounter) {
            if (currentCounterInContext + 1 == msgCounter) {
                updateMsgInContextValueAssignmnet(msgAlgorithm);
                this.counters.put(sender, msgCounter);
            } else {
                this.future.add((MsgAMDLS) msgAlgorithm);
            }
        }
        return true;

    }


    //TODO
    @Override
    public void changeRecieveFlagsToFalse() {
        consistentFlag = false;
        gotMsgFlag = false;
    }

    //TODO
    @Override
    protected void changeRecieveFlagsToTrue(MsgAlgorithm msgAlgorithm) {
        NodeId sender = msgAlgorithm.getSenderId();
        int currentCounterInContext = this.counters.get(sender);
        int msgCounter = ((MsgAMDLS) msgAlgorithm).getCounter();
        if (currentCounterInContext == msgCounter) {
            this.consistentFlag = false;
        }


        if (this.myColor != null && this.myColor != 1 && !this.future.isEmpty()) {
            releaseFutureMsgs();
        }
        boolean aboveConsistent = isAboveConsistent();
        boolean belowConsistent = isBelowConsistent();
        if (aboveConsistent && belowConsistent) {
            this.consistentFlag = true;
        } else {
            this.consistentFlag = false;
        }
    }
}
