package protocoloQueue;

import javax.jms.JMSException;
import javax.naming.NamingException;

import dao.DAOCarga;
import queue.RemoteQueueInteractor;

public class QueueProtocolo {

	public static void main(String[] args) {
		
	//interaccion Remota 
	RemoteQueueInteractor remoteQInteractor;
	//el dao donde van ha realizar las consultas o modificaciones
	DAOCarga daoCarga = new DAOCarga();
	try {
		remoteQInteractor = new RemoteQueueInteractor();
		while(true)
		{
			String mensajeNuevo = remoteQInteractor.receiveTextMessage();
			
			if (!mensajeNuevo.isEmpty())
			{
				// estructura del mensaje
				//caso 1 : RF14-mensaje                ------- inicio requerimiento
				//caso 2 : RF15-mensaje                ------- inicio requerimiento
				//caso 3 : respSolicitudRF14-mensaje   ------- respuesta requerimiento
				//caso 4 : respSolicitudRF15-mensaje   ------- respuesta requerimieto
				//caso 5 : finReq-mensaje              ------- envia respuesta final y acaba
				
				String respuesta;
				String rec=mensajeNuevo.split("-")[0];
				switch (rec) {
				case "RF14":
					//alguno de nosotros solicita req 14
					respuesta = "ok";
					remoteQInteractor.sendTextMessage("respSolicitudRF14-"+respuesta);
					
					break;
				case "RF15":
					//alguno de nosotros solicita req 15
					respuesta = "ok";
					remoteQInteractor.sendTextMessage("respSolicitudRF15-"+respuesta);
					
					break;
				case "respSolicitudRF14":
					//alguno de nosotros responde req 15
					respuesta = "ok";
					remoteQInteractor.sendTextMessage("FinReq"+respuesta);
					
					break;
				case "respSolicitudRF15":
					//alguno de nosotros responde req 15
					respuesta = "ok";
					remoteQInteractor.sendTextMessage("FinReq"+respuesta);
					
					break;
				default:
					break;
				}
				
				
			}
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	} 
	
	
			
	
	}
}
