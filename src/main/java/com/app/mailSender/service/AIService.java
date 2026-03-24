package com.app.mailSender.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class AIService {

    //Hardcoded for now (replace later with env var) very much unsafe to use this.....


    private final String API_KEY = System.getenv("GROQ_API_KEY");

    public String generateEmail(String to,String company, String position) {

        System.out.println("👉 AIService STARTED");

        try {
            // Check API key
            if (API_KEY == null || API_KEY.isEmpty()) {
                System.out.println("❌ API KEY is missing");
                return getFallbackEmail(company, position);
            }

            System.out.println("🔹 Using API KEY: " + API_KEY.substring(0, 10) + "...");

            String prompt =
                    "You are a professional job application assistant.\n\n" +

                            "Write a personalized job application email based on the following details:\n\n" +

                            "Candidate Details:\n" +
                            "- Name: Sarthak Jadhav\n" +
                            "- Experience: 3 years\n" +
                            "- Skills: Java, Spring Boot, REST APIs, Microservices, System Design\n" +
                            "- Background: Software developer with backend and system design experience\n\n" +

                            "Job Details:\n" +
                            "- Company: " + company + "\n" +
                            "- Role: " + position + "\n\n" +

                            "Instructions:\n" +
                            "IMPORTANT RULES:\n" +
                            "- DO NOT include subject line\n" +
                            "- DO NOT write 'Subject:' anywhere\n" +
                            "- Start directly with greeting (e.g., Hi Team, )\n\n" +
                            "- Do not give subject line at all for the mail, just body of mail is enough"+
                            "- Do not praise about company a lot just mention about me being interested in the role"+
                            "- Do NOT be generic\n" +
                            "- Mention relevant skills based on role\n" +
                            "- Make it concise and impactful\n" +
                            "- Keep it under 150 words\n" +
                            "- Professional tone\n\n" +

                            "Generate the email now:";

            System.out.println("🧠 Prompt: " + prompt);

            RestTemplate restTemplate = new RestTemplate();

            String url = "https://api.groq.com/openai/v1/chat/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Request body
            Map<String, Object> body = new HashMap<>();
            body.put("model", "llama-3.1-8b-instant");
            body.put("temperature", 0.7);
            body.put("max_tokens", 300);

            List<Map<String, String>> messages = new ArrayList<>();

            Map<String, String> message = new HashMap<>();
            message.put("role", "user");
            message.put("content", prompt);

            messages.add(message);
            body.put("messages", messages);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            System.out.println("🚀 Calling Groq API...");

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            System.out.println("📩 Raw Response: " + response);

            if (response.getBody() == null) {
                System.out.println("❌ Empty response body");
                return getFallbackEmail(company, position);
            }

            List choices = (List) response.getBody().get("choices");

            if (choices == null || choices.isEmpty()) {
                System.out.println("❌ No choices returned");
                return getFallbackEmail(company, position);
            }

            Map firstChoice = (Map) choices.get(0);
            Map messageMap = (Map) firstChoice.get("message");

            if (messageMap == null) {
                System.out.println("❌ message object missing");
                return getFallbackEmail(company, position);
            }

            String content = (String) messageMap.get("content");

            System.out.println("✅ AI GENERATED EMAIL:\n" + content);

            return content;

        } catch (Exception e) {
            System.out.println("❌ ERROR WHILE CALLING GROQ:");
            e.printStackTrace();
            return getFallbackEmail(company, position);
        }
    }

    // ✅ Always-safe fallback
    private String getFallbackEmail(String company, String position) {

        System.out.println("⚠️ USING FALLBACK EMAIL");

        return "Hi Hiring Team at " + company + ",\n\n" +
                "I am writing to apply for the " + position + " role.\n\n" +
                "Please find my resume attached.\n\n" +
                "Looking forward to your response.\n\n" +
                "Best regards,\n" +
                "Sarthak Jadhav";
    }
}