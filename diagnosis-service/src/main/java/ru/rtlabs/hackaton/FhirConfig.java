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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import ru.rtlabs.hackaton.converter.AbstractMongoConverter;
import ru.rtlabs.hackaton.converter.ResourceDeserializer;
import ru.rtlabs.hackaton.converter.ResourceSerializer;

import java.io.IOException;
import java.util.ArrayList;
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

        converters.add(new AbstractMongoConverter<DBObject, DiagnosticReport>() {
            @Override
            public DiagnosticReport convert(DBObject dbObject) {
                return (DiagnosticReport) super.convertToResource(objectMapper, dbObject, DiagnosticReport.class);
            }
        });

        converters.add(new AbstractMongoConverter<DiagnosticReport, DBObject>() {
            @Override
            public DBObject convert(DiagnosticReport diagnosticReport) {
                return super.convertToDBObject(objectMapper, diagnosticReport);
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
}
