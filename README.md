# Encurtador de URLs

Esta API é responsável por encurtar URLs longas e deixa-las mais compactas e simples de compartilhar.

## Exemplo

O serviço espera uma chamada para encurtar uma URL.

**[POST]** `{{host}}/shorten-url`

```json
{
    "url": "https://teste-encurtador-url.com.br"
}
```

E responde com a URL encurtada:

```
HTTP/1.1 200 OK
```

```json
{
    "url": "https://xxxx.com/ABC"
}
```
