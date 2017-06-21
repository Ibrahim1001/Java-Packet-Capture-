package jpcappacketcapture;

import jpcap.PacketReceiver;
import jpcap.packet.Packet;

public class PacketContents implements PacketReceiver {

    /**
     *
     * @param packet
     */
    @Override
    public void receivePacket(Packet packet){
        JpcapInterface.TA_ShowPacket.append(
        packet.toString() + "\n---------------------------------------------" + 
                "-------------------------------------------------------\n\n");
    }
}
