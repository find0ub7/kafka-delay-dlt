# kafka-delay-dlt
POC kafka retryable topic with DLT

Para testar:

- Subir kafka local:
  docker run --name local_kafka --rm -p 2181:2181 -p 9092:9092 allansilva/kafka4dev:2.2.0-scala.2.12
  
- Postar mensagem no topico "some-topic":
  - se postar uma mensagem cujo conteúdo seja "exception", o retry será executado com delay progressivo duplicando o tempo a cada tentativa. O delay inicial é de 1s e o número máximo de tentativas é 5.
