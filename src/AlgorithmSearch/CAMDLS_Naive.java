package AlgorithmSearch;

import AgentsAbstract.AgentVariable;
import AgentsAbstract.NodeId;
import Main.MainSimulator;
import Messages.Msg;
import Messages.MsgAMDLS;
import Messages.MsgAMDLSColor;
import Messages.MsgAlgorithm;

import java.util.ArrayList;
import java.util.List;

import static Delays.ProtocolDelayOneInK.k_public;

public class CAMDLS_Naive extends AMDLS_V3 {

    public CAMDLS_Naive(int dcopId, int D, int agentId) {
        super(dcopId, D, agentId);
        this.isWithTimeStamp=true;

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
        AgentVariable.algorithmData = Integer.toString(k_public);
    }

    protected void sendAMDLSColorMsgs() {
        List<Msg> msgsToOutbox = new ArrayList<Msg>();

        for (NodeId recieverNodeId : neighborsConstraint.keySet()) {
            for (int i = 0; i < k_public; i++) {
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
            for (NodeId recieverNodeId : neighborsConstraint.keySet()) {
                for (int i = 0; i < k_public; i++) {

                    MsgAMDLS mva = new MsgAMDLS(this.nodeId, recieverNodeId, this.valueAssignment, this.timeStampCounter,
                        this.time, this.myCounter);
                msgsToOutbox.add(mva);
            }
        }
        this.outbox.insert(msgsToOutbox);
    }


/*
    protected boolean updateMessageInContext(MsgAlgorithm msgAlgorithm) {

        if (msgAlgorithm instanceof MsgAMDLSColor) {
            Integer colorN = ((MsgAMDLSColor) msgAlgorithm).getColor();
            neighborColors.put(msgAlgorithm.getSenderId(), colorN);
            if (this.myColor != null) {
                if (this.myColor > colorN) {
                    this.above.add(msgAlgorithm.getSenderId());
                } else {
                    this.below.add(msgAlgorithm.getSenderId());
                }
            }
        }


        if ( (msgAlgorithm instanceof MsgAMDLSColor)==false && this.myCounter<=1
                && !((MsgAMDLS)msgAlgorithm).isFromFuture()) {

            future.add((MsgAMDLS)msgAlgorithm);
        }


        else {
            super.updateMessageInContext(msgAlgorithm);
        }
        return true;

    }
*/
    /*
    protected boolean updateMessageInContext(MsgAlgorithm msgAlgorithm) {


        NodeId sender = msgAlgorithm.getSenderId();
        //if (this.id == 0 ){
        //    System.out.println("agent 0 receive msg from "+sender);
        //}
        int currentCounterInContext = this.counters.get(sender);
        int msgCounter = ((MsgAMDLS) msgAlgorithm).getCounter();



        //if (currentCounterInContext < msgCounter) {
            if (currentCounterInContext + 1 == msgCounter) {
                updateMsgInContextValueAssignmnet(msgAlgorithm);
                this.counters.put(sender, msgCounter);
            } else {
                this.future.add((MsgAMDLS) msgAlgorithm);
            }
            return true;
        //}


    }
*/
/*
    //TODO
    @Override
    public void changeRecieveFlagsToFalse() {
        consistentFlag = false;
        gotMsgFlag = false;
    }
*/


/*
    protected void changeRecieveFlagsToTrue(MsgAlgorithm msgAlgorithm) {

        if (msgAlgorithm instanceof MsgAMDLSColor) {
            changeRecieveFlagsToTrueMsgAMDLSColor();
        }

        boolean firstCondition = !this.isWaitingToSetColor && allNeighborsHaveColor();
        if (firstCondition || canSetColorFlag) {
            super.changeRecieveFlagsToTrue(msgAlgorithm);
        }


    }
    */

    /*
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
        if (aboveConsistent && belowConsistent ) {
            this.consistentFlag = true;
        } else {
            this.consistentFlag = false;
        }
    }*/
}
