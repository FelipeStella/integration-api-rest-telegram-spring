# Integração com a API do telegram

Nesta integração foram utilizadas três biblioteca diferentes para servir de exemplo, client nativo, RestTemplate e FeingClient.

## Java Http client nativo

O client HTTP foi adicionado em Java 11. Ele pode ser usado para solicitar recursos HTTP pela rede. Ele suporta HTTP / 1.1 e HTTP / 2 , ambos modelos de programação síncronos e assíncronos, lida com corpos de solicitação e resposta como react-streams e segue o padrão de construtor familiar.

## RestTemplate

O RestTemplate é a classe Spring central para acesso HTTP do lado do cliente. Conceitualmente, é muito semelhante ao JdbcTemplate , JmsTemplate e aos vários outros modelos encontrados no Spring Framework e outros projetos de portfólio. Isso significa, por exemplo, que o RestTemplate é thread-safe depois de construído e que você pode usar callbacks para personalizar suas operações.

## FeingClient

Feign é um cliente de serviço da Web declarativo. Facilita a escrita de clientes de serviço da web. Para usar o Feign, crie uma interface e anote-a. Possui suporte para anotações plugáveis, incluindo anotações Feign e anotações JAX-RS. O Feign também oferece suporte a codificadores e decodificadores plugáveis. Spring Cloud adiciona suporte para anotações Spring MVC e para usar o mesmo HttpMessageConvertersusado por padrão no Spring Web. Spring Cloud integra Eureka, Spring Cloud CircuitBreaker, bem como Spring Cloud LoadBalancer para fornecer um cliente HTTP com balanceamento de carga ao usar Feign.
