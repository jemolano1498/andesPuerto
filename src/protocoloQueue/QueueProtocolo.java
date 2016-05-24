package protocoloQueue;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.naming.NamingException;

import dao.DAOCarga;
import dao.DAOTablaExportadores;
import queue.RemoteQueueInteractor;
import vos.Carga;
import vos.factura;

public class QueueProtocolo {

	public static void main(String[] args) {
		
	//interaccion Remota 
	RemoteQueueInteractor remoteQInteractor;
	//el dao donde van ha realizar las consultas o modificaciones
	DAOCarga daoCarga = new DAOCarga();
	DAOTablaExportadores daoExportadores = new DAOTablaExportadores();
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
					try{
						daoCarga.asignarCargaAArea(mensajeNuevo.split("-")[1], mensajeNuevo.split("-")[2]);
					}
					catch (Exception e){
						remoteQInteractor.sendTextMessage("respSolicitudRF14-FAIL");
					}
					
					remoteQInteractor.sendTextMessage("respSolicitudRF14-OK");
					
					break;
				case "RF15":
					factura fac = daoExportadores.darCostoFacturaExportadoresConPuertoAndes(mensajeNuevo.split("-")[1], mensajeNuevo.split("-")[2]);
					if (fac != null)
					{
						remoteQInteractor.sendTextMessage("respSolicitudRF15-OK-"+fac.getBono());
					}
					remoteQInteractor.sendTextMessage("respSolicitudRF15-FAIL");
					
					break;
				case "RC11":
					ArrayList<Carga> resp = daoCarga.consultarCargas(mensajeNuevo.split("-")[1], 
							mensajeNuevo.split("-")[2], 
							mensajeNuevo.split("-")[3], 
							mensajeNuevo.split("-")[4], 
							mensajeNuevo.split("-")[5]);
					
					remoteQInteractor.sendTextMessage("RC11Resp"+resp);
					
					break;
				case "RC12":
					factura fac2 = daoExportadores.darCostoFacturaExportadoresConPuertoAndes(mensajeNuevo.split("-")[1], mensajeNuevo.split("-")[2]);
					if (fac2 != null)
					{
						remoteQInteractor.sendTextMessage("respSolicitudRC12-OK-"+fac2.getCosto());
					}
					remoteQInteractor.sendTextMessage("respSolicitudRC12-FAIL");
					
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
