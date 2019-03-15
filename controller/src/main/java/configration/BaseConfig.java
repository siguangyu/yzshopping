package configration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.lynu",useDefaultFilters = true)
@EnableAutoConfiguration
public class BaseConfig {

}
