package com.example.api_taller_bicicleta.config;


import com.example.api_taller_bicicleta.entity.TallerConfig;
import com.example.api_taller_bicicleta.repository.TallerConfigRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TallerConfigRepository repository) {
        return args -> {
            if (!repository.existsById(1L)) {
                TallerConfig config = new TallerConfig();
                config.setId(1L);
                config.setCapacidadMaxima(10L);
                config.setBicicletasIngresadas(0L); // Inicializamos en 0
                repository.save(config);
                System.out.println("Configuración inicial del taller cargada con éxito.");
            }
        };
    }
}
