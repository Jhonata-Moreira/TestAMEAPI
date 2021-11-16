package ame.api.stepDefinitions;

import io.cucumber.core.exception.CucumberException;
import io.cucumber.docstring.CucumberDocStringException;
import io.cucumber.docstring.DocString;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class efetivacoesDummyStepsdefs {

    private Response response;
    private RequestSpecification request;
    private URI hostname;
    private Headers headers;
    private String empregadoId = "4582";

    @Dado("que o empregado acesse o {string}")
    public void que_o_cliente_acesse_o(String url) throws URISyntaxException {
        hostname = new URI(url);
    }

    @Dado("realize a chamada do {string}")
    public void realize_a_chamada_do(String uri) throws URISyntaxException {
        String hostUri = "";

        if(uri.length() <= 0){
            hostUri = hostname.getPath();
            hostUri = uri;
        }else{
            hostUri += uri;
        }
        hostname = new URI(hostname.getScheme(), hostname.getAuthority(), hostUri,
                hostname.getQuery(), hostname.getFragment());
    }
    @E("envie no body suas informacoes para cadastro:")
    public void envie_no_body_suas_informacoes_para_cadastro(DocString informacoes) {
        if (headers == null){
            try {
                setaHeaders();
                System.out.println(informacoes);
                request = given().body(informacoes.getContent());
            }catch(CucumberDocStringException e){
                e.getMessage();
            }
        }else{
            request = given().body(informacoes.getContent());
        }
    }

    /*
        Seta Headers padrões por não precisar justificar quais serão usados
     */
    private void setaHeaders() {
        List<Header> headersContent = new ArrayList<>();

        Header headerAccept = new Header("Accept", "*/*");
        Header headerContetType = new Header("Content-Type", "application/json");

        headersContent.add(headerAccept);
        headersContent.add(headerContetType);
        headers = new Headers(headersContent);
    }
    @Quando("utilizar a requisicao atraves do {string}")
    public void utilizar_a_requisicao_atraves_do(String metodo) {
        switch (metodo){
            case "GET":
                request =
                       given();
                response = request.given().param("empregadoId", empregadoId).get(hostname);
                break;
            case "POST":
                request =
                        given();
                response = request.post(hostname);
                break;
            case "PUT":
                request =
                        given();
                response = request.put(hostname);
                break;
            case "DELETE":
                request =
                        given();
                response = request.given().param("empregadoId", empregadoId).delete(hostname);
                break;
            default:
                System.out.println("Não encontrado o metodo informado");
        }
    }

    @Entao("verifico se o retono e {string}")
    public void verifico_se_o_retono_e(String status) {
        assertEquals(Integer.parseInt(status), response.statusCode());
    }

    @Entao("se aparece as {string} do empregado na response")
    public void se_aparece_as_do_cliente_na_response(String mensagem) {
        response
            .then()
            .body(Matchers.containsString(mensagem));
    }

    @E("valida o se foi gerado um id para o empregado")
    public void validaOSeFoiGeradoUmIdParaOEmpregado() {
        try{
            empregadoId = response.getBody().path("data.id").toString();
            System.out.println(empregadoId);
        }catch(CucumberException e){
            e.getMessage();
        }
    }
}
