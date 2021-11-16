#language:pt
#encoding:UTF-8
#Autor: Jhonata-Moreira

@valida-Api-dummy
Funcionalidade: Efetivacao da funcionalidade de eventos da api Dummy

  Esquema do Cenario: Tenta realizar o cadastro do empregado
    Dado que o empregado acesse o "<HostName>"
    E realize a chamada do "<EndPoint>"
    E envie no body suas informacoes para cadastro:
    """
    {"name":"empregado","salary":"1500","age":"20"}
    """
    Quando utilizar a requisicao atraves do "<Metodo>"
    Entao verifico se o retono e "<Status>"
    E se aparece as "<informacao>" do empregado na response
    E valida o se foi gerado um id para o empregado

    Exemplos:
      | HostName                           |  informacao    | EndPoint        | Metodo | Status |
      | http://dummy.restapiexample.com    |  success       | /api/v1/create  | POST   | 200    |

  Esquema do Cenario: Deve realizar a operação de consulta do empregado
    Dado que o empregado acesse o "<HostName>"
    E realize a chamada do "<EndPoint>"
    Quando utilizar a requisicao atraves do "<Metodo>"
    Entao verifico se o retono e "<Status>"
    E se aparece as "<informacao>" do empregado na response

    Exemplos:
      | HostName                           |  informacao | EndPoint                        | Metodo | Status |
      | http://dummy.restapiexample.com    |  success    | /api/v1/employee/{empregadoId}  | GET    | 200    |

  Esquema do Cenario: Deve realizar a operação de deletar o empregado
    Dado que o empregado acesse o "<HostName>"
    E realize a chamada do "<EndPoint>"
    Quando utilizar a requisicao atraves do "<Metodo>"
    Entao verifico se o retono e "<Status>"
    E se aparece as "<informacao>" do empregado na response

    Exemplos:
      | HostName                           |  informacao | EndPoint                        | Metodo    | Status |
      | http://dummy.restapiexample.com    |  success    | /api/v1/delete/{empregadoId}    | DELETE    | 200    |