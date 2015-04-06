package com.tamina.cow4.socket;
import com.tamina.cow4.socket.message.TurnResult;
import msignal.Signal;
import com.tamina.cow4.socket.message.Error;
import com.tamina.cow4.socket.message.ClientMessage;
import com.tamina.cow4.socket.message.GetTurnOrder;
import com.tamina.cow4.socket.message.ID;
import com.tamina.cow4.model.GameMap;
import com.tamina.cow4.model.IAInfo;
import com.tamina.cow4.socket.message.GetTurnOrder;
import com.tamina.cow4.socket.message.Authenticate;
import org.tamina.net.URL;
import nodejs.net.TCPSocket;
import com.tamina.cow4.socket.message.ErrorCode;
class IA extends Client implements IIA {

    public var name:String;
    public var avatar:URL;
    public var turnComplete:Signal1<TurnResult>;
    public var pm:Int=1;

    private var _proxy:ClientProxy;

    public function new(c:TCPSocket) {
        super();
        _proxy = new ClientProxy(c);
        _proxy.messageSignal.add(clientMessageHandler);
        _proxy.errorSignal.add(exitHandler);
        turnComplete = new Signal1<TurnResult>();
    }

    public function toInfo():IAInfo {
        return new IAInfo(id, name, avatar.path, pm);
    }

    public function getTurnOrder(data:GameMap):Void {
        _proxy.sendMessage(new GetTurnOrder(data.toGameMapVO()));
    }


    private function clientMessageHandler(message:ClientMessage):Void {
        switch( message.type){
            case Authenticate.MESSAGE_TYPE:
                nodejs.Console.info('demande dauthentifiction');
                var auth:Authenticate = cast message;
                if (isLoggued) {
                    _proxy.sendError(new Error( ErrorCode.ALREADY_AUTH, 'deja ahtentifié'));
                } else {
                    isLoggued = true;
                    name = auth.name;
                    avatar = new URL(auth.avatar);
                    _proxy.sendMessage(new ID( this.id ));
                }
            case TurnResult.MESSAGE_TYPE:
                nodejs.Console.info('resultat du tour');
                var result:TurnResult = cast message;
                turnComplete.dispatch(result);
            default: _proxy.sendError(new Error( ErrorCode.UNKNOWN_MESSAGE, 'type de message inconnu'));

        }
    }

}
