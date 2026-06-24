package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.ChatRequest;
import com.sena.sena_backend.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatbotService {



    @Value("${groq.api.key}")
    private String groqApiKey;

    private final ObjectMapper mapper = new ObjectMapper();

    public ChatResponse chat(ChatRequest request) {

        String userMessage = request.message();
        String aiResponse = callGroq(userMessage);

        return new ChatResponse(aiResponse);
    }


    private String callGroq(String message) {

        try {
            String prompt = """
            Eres SignaBOT, el asistente virtual de una aplicación educativa para aprender Lengua de Señas Mexicana (LSM).

Tu objetivo es ayudar a los usuarios a comprender conceptos relacionados con la Lengua de Señas Mexicana, la inclusión, la comunicación accesible y el aprendizaje de señas.

Responde de forma:
- Clara y fácil de entender.
- Amigable y motivadora.
- Breve cuando la pregunta sea simple.
- Más detallada cuando el usuario solicite explicaciones.
Puedes:
- Explicar conceptos de Lengua de Señas Mexicana.
- Resolver dudas sobre comunicación inclusiva.
- Dar consejos de aprendizaje.
- Explicar vocabulario relacionado con LSM.
- Motivar a los usuarios a continuar aprendiendo.

Si el usuario pregunta algo que no esté relacionado con la aplicación o con Lengua de Señas Mexicana, puedes responder de forma general como asistente educativo.

Nunca inventes información si no estás seguro. Si desconoces algo, indícalo de manera honesta.

Mantén siempre un tono respetuoso, positivo y educativo.

            Usuario: %s
            """.formatted(message);

            ObjectNode root = mapper.createObjectNode();
            root.put("model", "llama-3.3-70b-versatile");

            ObjectNode msg = mapper.createObjectNode();
            msg.put("role", "user");
            msg.put("content", prompt);

            ArrayNode messages = mapper.createArrayNode();
            messages.add(msg);

            root.set("messages", messages);
            root.put("temperature", 0.7);

            String jsonBody = mapper.writeValueAsString(root);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
                    .header("Authorization", "Bearer " + groqApiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpClient client=HttpClient.newBuilder()
                            .connectTimeout(Duration.ofSeconds(10))
                                    .build();

            HttpResponse<String> response= client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("GROQ STATUS: " + response.statusCode());
            System.out.println("GROQ BODY: " + response.body());

            JsonNode json = mapper.readTree(response.body());

            return json.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error conectando a la IA";
        }
    }
}
