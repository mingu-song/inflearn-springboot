package mingu.inflearn.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SchedulerCronConfig {

    private final GlobalConfig globalConfig;

    @Bean
    public String schedulerCron() {
        return globalConfig.getSchedulerCron();
    }
}
