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
/*
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
*/


}
