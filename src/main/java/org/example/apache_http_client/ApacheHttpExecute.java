package org.example.apache_http_client;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.instrumentation.apachehttpclient.v5_2.ApacheHttpClient5Telemetry;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class Execute {
    public static void main(String[] args) throws Exception {
//        SdkTracerProvider sdkTracerProvider =
//                SdkTracerProvider.builder()
//                        .addSpanProcessor(SimpleSpanProcessor.create(OtlpHttpSpanExporter.builder().build()))
//                        .build();
//        Runtime.getRuntime().addShutdownHook(new Thread(sdkTracerProvider::close));
//        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
//                .setTracerProvider(sdkTracerProvider)
//                .setPropagators(ContextPropagators.create(TextMapPropagator.composite(W3CTraceContextPropagator.getInstance(), W3CBaggagePropagator.getInstance())))
//                .build();
//        var client = ApacheHttpClient5Telemetry.builder(null).build().newHttpClient();
        var client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://www.baidu.com");
        try (CloseableHttpResponse response = client.execute(request)) {
            System.out.println(response.getCode());
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println(responseBody);
        }
    }
}
