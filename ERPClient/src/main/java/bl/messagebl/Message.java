package bl.messagebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.messagedataservice.MessageDataService;
import po.MessagePO;
import rmi.MessageRemoteHelper;
import util.BillState;
import util.ResultMessage;
import util.UserPosition;
import vo.MessageVO;

public class Message {

	private MessageDataService messageDataService;
	ArrayList<MessagePO> messagePOs = new ArrayList<>();
	
	public Message(){
		messageDataService = MessageRemoteHelper.getInstance().getMessageDataService();
	}
	
	public ArrayList<MessageVO> show(UserPosition position) throws RemoteException{
		messagePOs = messageDataService.show(position);
		ArrayList<MessageVO> messageVOs = new ArrayList<>();
		for(MessagePO po:messagePOs){
			messageVOs.add(poTOvo(po));
		}
		return messageVOs;
	}
	
	public ResultMessage addMessage(BillState state, String ID, String messageTime, UserPosition position) throws RemoteException{
		MessagePO po = new MessagePO(state, ID, messageTime, position);
		System.out.println(po.getState() + state.toString());
		ResultMessage re = messageDataService.add(po);
		return re;
	}
	
	public ResultMessage deleteMessage(int messageID) throws RemoteException{
		ResultMessage re = messageDataService.delete(messageID);
		return re;
	}
	
	public static MessagePO voTOpo(MessageVO vo){
		return new MessagePO(vo.state, vo.billID, vo.messageTime, vo.position);
	}
	
	public static MessageVO poTOvo(MessagePO po){
		return new MessageVO(po.getMessageID(), po.getState(), po.getBillID(), po.getMessageTime(), po.getPosition());
	}
}
