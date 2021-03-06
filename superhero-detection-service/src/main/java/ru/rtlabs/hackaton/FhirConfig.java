package ru.rtlabs.hackaton;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mongodb.DBObject;
import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.rtlabs.hackaton.converter.AbstractMongoConverter;
import ru.rtlabs.hackaton.converter.ResourceDeserializer;
import ru.rtlabs.hackaton.converter.ResourceSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class FhirConfig {

    private static Logger logger = LoggerFactory.getLogger(FhirConfig.class);

    @Bean
    @Autowired
    public FhirContext fhirContext() {
        FhirContext fhirContext = FhirContext.forDstu3();
        return fhirContext;
    }

    @Bean
    @Autowired
    public ResourceDeserializer resourceDeserializer() {
        return new ResourceDeserializer();
    }

    @Bean
    @Autowired
    public ResourceSerializer resourceSerializer() {
        return new ResourceSerializer();
    }

    @Bean
    @Autowired
    public CustomConversions customConversions(ObjectMapper objectMapper) {
        List<Converter> converters = new ArrayList<>();

        converters.add(new AbstractMongoConverter<DBObject, Observation>() {
            @Override
            public Observation convert(DBObject dbObject) {
                return (Observation) super.convertToResource(objectMapper, dbObject, Observation.class);
            }
        });

        converters.add(new AbstractMongoConverter<Observation, DBObject>() {
            @Override
            public DBObject convert(Observation observation) {
                return super.convertToDBObject(objectMapper, observation);
            }
        });

        CustomConversions customConversions = new CustomConversions(converters);

        return customConversions;
    }

    @Bean
    @Autowired
    public ObjectMapper objectMapper(ResourceSerializer resourceSerializer, ResourceDeserializer resourceDeserializer) {
        ObjectMapper om = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Observation.class, resourceSerializer);
        module.addDeserializer(Observation.class, new JsonDeserializer<Observation>() {

            @Override
            public Observation deserialize(JsonParser parser, DeserializationContext context)
                    throws IOException, JsonProcessingException {
                return (Observation) resourceDeserializer.deserialize(parser, context);
            }
        });

        module.addSerializer(Patient.class, resourceSerializer);
        module.addDeserializer(Patient.class, new JsonDeserializer<Patient>() {

            @Override
            public Patient deserialize(JsonParser parser, DeserializationContext context)
                    throws IOException, JsonProcessingException {
                return (Patient) resourceDeserializer.deserialize(parser, context);
            }
        });

        module.addSerializer(DiagnosticReport.class, resourceSerializer);
        module.addDeserializer(DiagnosticReport.class, new JsonDeserializer<DiagnosticReport>() {

            @Override
            public DiagnosticReport deserialize(JsonParser parser, DeserializationContext context)
                    throws IOException, JsonProcessingException {
                return (DiagnosticReport) resourceDeserializer.deserialize(parser, context);
            }
        });
        om.registerModule(module);
        return om;
    }

    @Bean
    @Autowired
    public RestTemplate restTemplate(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        RestTemplate restTemplate = builder.build();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);

        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        return restTemplate;
    }
}
