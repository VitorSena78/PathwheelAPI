// Essa classe atua como um controlador Spring MVC para lidar com solicitações relacionadas a amostras de pavimentação.
// Ela é mapeada para o caminho "/v1/pavement-sample", o que significa que os endpoints relacionados estão acessíveis sob esse caminho.
package com.pathwheel.spring;

import com.pathwheel.dao.PavementSampleDAO;
import com.pathwheel.io.Logger;
import com.pathwheel.jdbc.JdbcDataAccessObjectListener;
import com.pathwheel.jdbc.JdbcPavementSampleDAO;
import com.pathwheel.request.RegisterSampleRequest;
import com.pathwheel.response.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/pavement-sample")
public class PavementSampleEndpoint implements JdbcDataAccessObjectListener {


	private PavementSampleDAO dao;


	// o Spring Boot já assume o padrão de codificação UTF-8
	// Este endpoint é acessado através de uma requisição POST na URL "/v1/pavement-sample/register".
	// Ele espera um corpo de requisição JSON que contenha detalhes sobre a amostra de pavimentação a ser registrada.
	// O método tenta registrar a amostra de pavimentação utilizando um objeto PavementSampleDAO.
	// Se o registro for bem-sucedido, uma resposta com código 200 (Sucesso) e descrição correspondente é retornada.
	// Em caso de exceção, a resposta é configurada com código 500 (Erro do Servidor Interno) e a mensagem de erro.
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response insert(@RequestBody RegisterSampleRequest request) {
		Logger.info("==[/v1/pavement-sample/register]=");
		Response response = new Response();
		try {
			Logger.info(request.toString());
			
			dao = new JdbcPavementSampleDAO(this);
			dao.register(request.getSample(), request.getSmartDevice());
			
			response.setCode(200);
			response.setDescription("Success");
			
		} catch (Exception e) {
			//System.out.println(jsonRequest);
			e.printStackTrace();
			response.setCode(500);
			response.setDescription(e.getMessage());
		}
		Logger.info("<< "+response.toString());
		return response;
	}

	// Implementação do método da interface JdbcDataAccessObjectListener.
	// Este método é chamado em caso de erro durante operações com objetos JDBC.
	// A mensagem de erro é registrada.
	@Override
	public void onErroJdbcDataAccessObject(Object sender, Exception e) {
		Logger.info(e.getMessage());
	}

	@Override
	public void onLogJdbcDataAccessObject(Object sender, String msg) {
		Logger.info(msg);
	}
}
